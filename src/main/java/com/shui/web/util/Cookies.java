package com.shui.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cookies {
	private static Logger log = LoggerFactory.getLogger(Cookies.class);
	public static String get(HttpServletRequest req, String key) {
		for (Cookie cookie : req.getCookies()) {
			if (key.equals(cookie.getName())) {
				cookie.getValue();
				return cookie.getValue();
			}
		}
		return null;
	}

	public static void set(HttpServletResponse resp, String key, String value) {
		try {
			value = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("cookie in chinese value encode error:{}", e);
			e.printStackTrace();
		}
		Cookie cookie = new Cookie(key, value);
		cookie.setDomain((String) PropUtils.getValue("resource.properties", "shortSite"));
		cookie.setPath("/");
		cookie.setMaxAge(3600);
		resp.addCookie(cookie);
	}
	
	public static void remove(HttpServletRequest req, HttpServletResponse resp, String key) {
		for (Cookie cookie : req.getCookies()) {
			if (key.equals(cookie.getName())) {
				Cookie c = new Cookie(key, cookie.getValue());
				c.setDomain((String) PropUtils.getValue("resource.properties", "shortSite"));
				c.setPath("/");
				c.setMaxAge(0);
				resp.addCookie(c);
			}
		}
	}
}
