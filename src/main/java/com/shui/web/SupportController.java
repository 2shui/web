package com.shui.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shui.web.conf.AppConfig;
import com.shui.web.server.WechatService;

/**
 * 第三方接口支持
 * */
@RestController
@SpringBootApplication
@RequestMapping("/support")
@Scope("prototype")
public class SupportController {

	private static Logger log = LoggerFactory
			.getLogger(SupportController.class);

	@Autowired
	private WechatService wechatService;
	
	/**
	 * 获取微信二维码ticket https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
	 * */
	@RequestMapping("/getWechatQrcode")
	@ResponseBody
	public void getWechatQrcode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String pathUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" 
				+ wechatService.getToken();
		URL url = new URL(pathUrl);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setDoOutput(true);// 使用 URL 连接进行输出
		httpConn.setDoInput(true);// 使用 URL 连接进行输入
		httpConn.setUseCaches(false);// 忽略缓存
		httpConn.setRequestMethod("POST");// 设置URL请求方法
		httpConn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
		httpConn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
		httpConn.setRequestProperty("Content-Length", String.valueOf(pathUrl.length()));
		httpConn.connect();
		OutputStreamWriter out = new OutputStreamWriter(
				httpConn.getOutputStream(), "UTF-8"); // utf-8编码
		out.append("{\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\": {\"scene\": {\"scene_id\": 1000}}}");
		out.flush();
		out.close();

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
			response.getWriter().write(result);
		}
	}

	@RequestMapping("/getToken")
	public Map<String, Object> getToken(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != request.getHeader("Referer")
				&& request.getHeader("Referer").contains(AppConfig.WEB_SITE)) {
			String uri = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials"
					+ "&client_id="
					+ AppConfig.BAIDU_YUYIN_API_KEY
					+ "&client_secret=" + AppConfig.BAIDU_YUYIN_SECRET_KEY;
			try {
				HttpURLConnection conn = (HttpURLConnection) new URL(uri)
						.openConnection();
				if (200 == conn.getResponseCode()) {
					JsonObject json = new JsonParser().parse(
							printResponse(conn)).getAsJsonObject();
					map.put("tk", json.get("access_token").getAsString());
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

		return map;
	}

	/**
	 * 获取response信息<br/>
	 * 不成功时返回空json串
	 * 
	 * @param
	 * */
	private String printResponse(HttpURLConnection conn) {
		try {
			if (200 == conn.getResponseCode()) {
				InputStream is = conn.getInputStream();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
					sb.append("\r");
				}
				br.close();
				return sb.toString();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return "{}";
	}
}
