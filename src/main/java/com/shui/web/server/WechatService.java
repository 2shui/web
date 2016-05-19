package com.shui.web.server;

import java.io.IOException;
import java.io.InputStream;
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
		} catch (IOException|DocumentException e1) {
			log.error("wechat parseReq error:{}", e1.getMessage());
			e1.printStackTrace();
		}
		

		return resultMap;
	}
	
	private String cut(String str) {
		return str.replace("<![CDATA[", "").replace("]]>", "");
	}
	
	public String buildNotSupportResponse(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[" + map.get("FromUserName")
				+ "]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[" + map.get("ToUserName")
				+ "]]></FromUserName>");
		sb.append("<CreateTime>" + System.currentTimeMillis() / 1000
				+ "</CreateTime>");
		sb.append("<MsgType><![CDATA[text]]></MsgType>");
		sb.append("<Content><![CDATA[sorry 啦，小水感应不到你的新号啦~/(ㄒoㄒ)/~~]]></Content>");
		sb.append("</xml>");
		return sb.toString();
	}
	
	public String buildResponse(List<Page> list, Map<String, String> map) {
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
}
