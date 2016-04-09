package com.shui.web;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shui.web.model.Page;
import com.shui.web.repo.PageMapper;

@RestController
@SpringBootApplication
@RequestMapping("/page")
@MapperScan("com.shui.web.repo")
public class PageController {

	@Autowired
	private PageMapper pageMapper;

	@RequestMapping("/get/{id}")
	public String test(@PathVariable("id") Long id) {
		Page page = pageMapper.getById(id);
		return null == page ? "NONE" : page.getTitle();
	}

	/**
	 * 获取随机页面 <br/>
	 */
	@RequestMapping("/uncertain")
	public String uncertain() {
		List<Page> list = pageMapper.getRandom(20);
		StringBuilder sb = new StringBuilder();
		list.forEach(page -> sb.append(page.getTitle()).append("||"));
		return sb.toString();
	}

}
