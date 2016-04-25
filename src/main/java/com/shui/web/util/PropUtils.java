package com.shui.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shui.web.conf.AppConfig;

public class PropUtils {
	private static Logger log = LoggerFactory.getLogger(PropUtils.class);

	public static Properties getProps(String propFileName) {
		Properties props = new Properties();
		String path = propFileName;
		InputStream ins = null;
		try {
			ins = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(path);
			if(null == ins) {
				File file = new File(AppConfig.RESOURCE_PATH + path);
				ins = new FileInputStream(file);
			}
			props.load(ins);
		} catch (IOException e) {
			log.error("error loading file " + path + " " + e.getMessage(), e);
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
