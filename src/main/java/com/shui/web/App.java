package com.shui.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.shui.web.interceptor.BookLoginInterceptor;
import com.shui.web.interceptor.ProxyInterceptor;

/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
public class App extends WebMvcConfigurerAdapter
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
    
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(new ProxyInterceptor()).addPathPatterns("/**/**");
    	registry.addInterceptor(new BookLoginInterceptor()).addPathPatterns("/book/**");
    }
}
