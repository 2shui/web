package com.shui.web;

import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shui.web.conf.AppConfig;
import com.shui.web.model.Marks;
import com.shui.web.repo.MarksMapper;

@RestController
@SpringBootApplication
@RequestMapping("/book")
@MapperScan("com.shui.web.repo")
@Scope("prototype")
public class BookController {

	@Autowired
	private MarksMapper marksMapper;
	
	@RequestMapping("/mark")
	public Marks mark(Marks marks, HttpServletResponse response) {
		marks.setCustomerId(123456L);
		marksMapper.addBook(marks);
		response.setHeader("Access-Control-Allow-Origin", "http://"+AppConfig.WEB_SITE);
		return marks;
	}
}
