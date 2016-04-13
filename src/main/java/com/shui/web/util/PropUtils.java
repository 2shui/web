package com.shui.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropUtils {
	private static Logger log = LoggerFactory.getLogger(SafeMapBuilder.class);

	public static Properties getProps(String propFileName) {
		Properties props = new Properties();
		String path = "/" + propFileName;
		InputStream ins = null;
		try {
			ins = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(path);
			props.load(ins);
		} catch (IOException e) {
			log.error("error loading file " + path + " " + e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(ins);
		}
		return props;
	}
}
