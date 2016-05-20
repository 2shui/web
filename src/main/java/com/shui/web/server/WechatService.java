package com.shui.web.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shui.web.bean.WechatType;
import com.shui.web.conf.AppConfig;
import com.shui.web.model.Page;
import com.shui.web.repo.PageMapper;
import com.shui.web.util.MD5Util;

@MapperScan("com.shui.web.repo")
@Component("wechatService")
public class WechatService {
	private static Logger log = LoggerFactory.getLogger(WechatService.class);
	@Autowired
	private PageMapper pageMapper;

	public String getToken() throws IOException {
		if ((System.currentTimeMillis() / 1000 - AppConfig.WECHAT_EXPIRES_TIME) > 7000) {
			String pathUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
					+ AppConfig.WECHAT_APPID
					+ "&secret="
					+ AppConfig.WECHAT_SECRET;
			URL url = new URL(pathUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出
			httpConn.setDoInput(true);// 使用 URL 连接进行输入
			httpConn.setUseCaches(false);// 忽略缓存
			httpConn.setRequestMethod("GET");// 设置URL请求方法
			httpConn.setRequestProperty("Accept", "*/*"); // 设置接收数据的格式
			httpConn.connect();

			int length = (int) httpConn.getContentLength();// 获取长度
			InputStream is = httpConn.getInputStream();
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String result = new String(data, "UTF-8");
				JsonObject json = new JsonParser().parse(result)
						.getAsJsonObject();
				AppConfig.WECHAT_ACCESS_TOKEN = json.get("access_token")
						.getAsString();
				AppConfig.WECHAT_EXPIRES_TIME = System.currentTimeMillis() / 1000;
			}
		}

		System.out.println("wechat access token is:"
				+ AppConfig.WECHAT_ACCESS_TOKEN);
		return AppConfig.WECHAT_ACCESS_TOKEN;
	}

	public boolean validate(String signature, String timestamp, String nonce) {
		List<String> list = new ArrayList<String>();
		list.add(timestamp);
		list.add(nonce);
		list.add(AppConfig.WECHAT_TOKEN);
		Collections.sort(list);
		String temp = String.join("", list);
		if (signature.equals(MD5Util.SHA1(temp))) {
			return true;
		}
		return false;
	}

	public Map<String, String> parseReq(HttpServletRequest request) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			InputStream inputStream = request.getInputStream();
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			Element root = document.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> elementList = root.elements();
			for (Element e : elementList) {
				resultMap.put(e.getName(), cut(e.getText()));
			}
			inputStream.close();
			inputStream = null;
		} catch (IOException | DocumentException e1) {
			log.error("wechat parseReq error:{}", e1.getMessage());
			e1.printStackTrace();
		}

		return resultMap;
	}

	private String cut(String str) {
		return str.replace("<![CDATA[", "").replace("]]>", "");
	}

	private String buildNotSupportResponse(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[" + map.get("FromUserName")
				+ "]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[" + map.get("ToUserName")
				+ "]]></FromUserName>");
		sb.append("<CreateTime>" + System.currentTimeMillis() / 1000
				+ "</CreateTime>");
		sb.append("<MsgType><![CDATA[text]]></MsgType>");
		sb.append("<Content><![CDATA[小水暂时只能回答您的文本消息，请输入您想了解的信息查询...]]></Content>");
		sb.append("</xml>");
		return sb.toString();
	}

	private String buildWelcome(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[" + map.get("FromUserName")
				+ "]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[" + map.get("ToUserName")
				+ "]]></FromUserName>");
		sb.append("<CreateTime>" + System.currentTimeMillis() / 1000
				+ "</CreateTime>");
		sb.append("<MsgType><![CDATA[event]]></MsgType>");
		sb.append("<Event><![CDATA[SCAN]]></Event>");
		sb.append("<Content><![CDATA[关注养生，关注健康，关注美食，关爱自己，关爱家人。欢迎您订阅2水，更多信息请输入消息查询，如“祛湿”]]></Content>");
		sb.append("</xml>");
		return sb.toString();
	}

	private String buildResponse(List<Page> list, Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[" + map.get("FromUserName")
				+ "]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[" + map.get("ToUserName")
				+ "]]></FromUserName>");
		sb.append("<CreateTime>" + System.currentTimeMillis() / 1000
				+ "</CreateTime>");
		sb.append("<MsgType><![CDATA[news]]></MsgType>");
		sb.append("<ArticleCount>" + list.size() + "</ArticleCount>");
		sb.append("<Articles>");
		for (Page page : list) {
			sb.append("<item>");
			sb.append("<Title><![CDATA[" + page.getTitle() + "]]></Title>");
			sb.append("<Description><![CDATA[" + page.getContent()
					+ "]]></Description>");
			sb.append("<PicUrl><![CDATA[" + "" + "]]></PicUrl>");
			sb.append("<Url><![CDATA[http://" + AppConfig.WEB_SITE + "/"
					+ page.getFileName() + ".html]]></Url>");
			sb.append("</item>");
		}
		sb.append("</Articles>");
		sb.append("</xml>");

		return sb.toString();
	}

	public String buildResponse(List<Page> list, Map<String, String> map,
			WechatType type) {
		switch (type) {
		case TEXT:
			return buildResponse(list, map);
		case EVENT:
			return buildWelcome(map);
		default:
			return buildNotSupportResponse(map);
		}
	}
}
