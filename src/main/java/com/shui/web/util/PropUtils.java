package com.shui.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropUtils {
	private static Logger log = LoggerFactory.getLogger(PropUtils.class);

	public static Properties getProps(String propFileName) {
		Properties props = new Properties();
		InputStream ins = null;
		try {
			ins = PropUtils.class.getResourceAsStream("/" + propFileName);
			props.load(ins);
		} catch (IOException e) {
			log.error("error loading file " + propFileName + " " + e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(ins);
		}
		return props;
	}
	
	public static Object getValue(String propFileName, String propKey) {
		Properties props = PropUtils.getProps(propFileName);
		return props.get(propKey);
	}
}
