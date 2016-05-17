package com.shui.web.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * freemarker生成html
 * 
 * @author zgjlovelife@gmail.com <br/>
 *         2016-3-25 17:59:36
 * 
 * */
public class FreemarkerUtils {
	private static Logger log = LoggerFactory.getLogger(FreemarkerUtils.class);
	private static Configuration config;
	private static Template template;

	private FreemarkerUtils() {
	}

	public static void init(File file) {
		if (null == config) {
			config = new Configuration();
			try {
				config.setDirectoryForTemplateLoading(file);
				config.setObjectWrapper(new DefaultObjectWrapper());
			} catch (IOException e) {
				log.error("freemarker init config error:" + e);
			}
		}
	}

	public static void initTemplate(String fileName, String codeType) {
		codeType = null == codeType ? "UTF-8" : codeType;
		File filePath = new File(FreemarkerUtils.class.getResource("/")
				.getFile());
		if (null == config) {
			init(filePath.getParentFile());
		}
		try {
			template = config.getTemplate(fileName, codeType);
		} catch (IOException e) {
			log.error("freemarker initTemplate template error:" + e);
		}
	}

	public static void analysisTemplate(String file, Map<?, ?> data,
			String codeType) {
		if (null == config || null == template)
			return;
		codeType = null == codeType ? "UTF-8" : codeType;
		try {
			FileOutputStream os = new FileOutputStream(file);
			Writer out = new OutputStreamWriter(os, codeType);
			template.process(data, out);
			out.flush();
			out.close();
		} catch (TemplateException | IOException e) {
			log.error("create html file error:" + e);
		}
	}
	
}
