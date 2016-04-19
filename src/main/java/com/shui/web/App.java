package com.shui.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
//    public static void main(String[] args) {
//    	Calendar calendar = Calendar.getInstance();
//    	 calendar.setTime(new Date());
//    	 int w = calendar.get(Calendar.DAY_OF_WEEK);
//    	 System.out.println(w);
//	}
}
