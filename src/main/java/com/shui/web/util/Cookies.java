package com.shui.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cookies {
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
