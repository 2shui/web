package com.shui.weibo.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.shui.web.util.PropUtils;

public class WeiboConfig {
	public WeiboConfig(){}
	private static Properties props = new Properties(); 
	static{
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("resource.properties"));
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return (String) PropUtils.getValue("resource.properties", key);
//		return props.getProperty(key);
	}

    public static void updateProperties(String key,String value) {    
            props.setProperty(key, value); 
    } 
}
