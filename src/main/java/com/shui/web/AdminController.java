package com.shui.web;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shui.web.conf.AppConfig;
import com.shui.web.model.Page;
import com.shui.web.repo.PageMapper;
import com.shui.web.server.PageService;
import com.shui.web.util.Decimal52;

@RestController
@SpringBootApplication
@RequestMapping("/sdstscakfefrfnbb")
@MapperScan("com.shui.web.repo")
@Scope("prototype")
public class AdminController {

	@Autowired
	private PageMapper pageMapper;
	@Autowired
	private PageService pageService;
	
	@RequestMapping("/dec/{ymdh}")
	public String test(@PathVariable("ymdh") Integer ymdh) {
		Decimal52 decimal = new Decimal52();
		return decimal.getDecimal(ymdh);
	}

	/**
	 * 静态化页面
	 * */
	@RequestMapping("/static/{auth}/{begin}/{num}")
	public void staticPage(@PathVariable("auth") String auth,
			@PathVariable("num") int begin, @PathVariable("num") int num) {
		if (pageService.auth(auth)) {
			List<Page> list = pageMapper.findNotStatic(begin, num);
			list.forEach(page -> pageService.staticPage(page));
		}
	}
	
	@RequestMapping("/index/{auth}")
	public void staticIndex(@PathVariable("auth") String auth) {
		if (pageService.auth(auth)) {
			List<Page> contents = pageMapper.getRandom(AppConfig.HOT_ARTICLE_NUM);
			
		}
	}
}
