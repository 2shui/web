package com.shui.web.util;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SafeMapper {
	private static Logger log = LoggerFactory.getLogger(SafeMapper.class);
	
	public <T> T map(Object source, Class<T> destinationClass) {
		if (null == source) {
			return null;
		}

		Map<String, Object> sourceMap = SafeMapBuilder.buildMap(source);
		return build(sourceMap, destinationClass);
	}

	public <T> T map(Map<String, Object> source, Class<T> destinationClass) {
		if (null == source || source.isEmpty()) {
			return null;
		}

		return build(source, destinationClass);
	}

	public <T> List<T> map(List<?> source, Class<T> destinationClass) {
		if (null == source || source.isEmpty()) {
			return null;
		}
		List<T> target = new LinkedList<T>();
		for (Object o : source) {
			target.add(map(o, destinationClass));
		}
		return target;
	}

	private <T> T build(Map<String, Object> source, Class<T> destinationClass) {
		try {
			T destination = destinationClass.newInstance();
			Field[] fields = destinationClass.getDeclaredFields();
			for (Field field : fields) {
				if (source.containsKey(field.getName())) {
					field.setAccessible(true);
					field.set(destination, source.get(field.getName()));
				}
			}
			return destination;
		} catch (InstantiationException e) {
			log.error("destinationClass {} Instantiation error.",
					destinationClass);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			log.error("destinationClass {} Access Illegal.", destinationClass);
			e.printStackTrace();
		}
		return null;
	}
}
