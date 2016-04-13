package com.shui.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shui.web.conf.AppConfig;

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
	 * @param
	 * */
	private String printResponse(HttpURLConnection conn) {
		try {
			if (200 == conn.getResponseCode()) {
				InputStream is = conn.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
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
