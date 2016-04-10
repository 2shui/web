package com.shui.web.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SafeMapBuilder {
	private static Logger log = LoggerFactory.getLogger(SafeMapBuilder.class);
			
	public static Map<String, Object> buildMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();

		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if (null != field.get(obj)) {
					map.put(field.getName(), field.get(obj));
				}
			}
		} catch (IllegalArgumentException e) {
			log.error("class {} Access Illegal Argument.", obj.getClass());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			log.error("class {} Access Illegal.", obj.getClass());
			e.printStackTrace();
		}
		return map;
	}
	
	public static List<Map<String, Object>> buildMap(List<?> obj) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Object o : obj) {
			Map<String, Object> map = new HashMap<String, Object>();
			Field[] fields = o.getClass().getDeclaredFields();

			try {
				for (Field field : fields) {
					field.setAccessible(true);
					if (null != field.get(o)) {
						map.put(field.getName(), field.get(o));
					}
				}
			} catch (IllegalArgumentException e) {
				log.error("class {} Access Illegal Argument.", o.getClass());
			} catch (IllegalAccessException e) {
				log.error("class {} Access Illegal.", o.getClass());
			}
			list.add(map);
		}
		return list;
	}
}
