package com.shui.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shui.web.conf.AppConfig;
import com.shui.web.model.Page;
import com.shui.web.repo.PageMapper;
import com.shui.web.util.SafeMapBuilder;

@RestController
@SpringBootApplication
@RequestMapping("/page")
@MapperScan("com.shui.web.repo")
@Scope("prototype")
public class PageController {

	@Autowired
	private PageMapper pageMapper;

	@RequestMapping("/get/{id}")
	public Map<String, Object> test(@PathVariable("id") Long id,HttpServletRequest request) {
		Page page = pageMapper.getById(id);
		return SafeMapBuilder.buildMap(page);
	}

	/**
	 * 获取随机页面 <br/>
	 */
	@RequestMapping(value="/uncertain")
	@ResponseBody
	public List<Map<String, Object>> uncertain(HttpServletResponse response) {
		List<Page> list = pageMapper.getRandom(AppConfig.RANDOM_ARTICLE_NUM);
		return SafeMapBuilder.buildMap(list);
	}

	@RequestMapping("/hits/{name}")
	@ResponseBody()
	public void readOne(@PathVariable("name") String name, HttpServletRequest request) {
		if (null != request.getHeader("Referer") && request.getHeader("Referer").contains("www.2shui.com.cn")) {
			pageMapper.readOne(name);
		}
	}
	
	@RequestMapping("/enjoy/{name}")
	@ResponseBody
	public void enjoyOne(@PathVariable("name") String name, HttpServletRequest request) {
		if (null != request.getHeader("Referer") && request.getHeader("Referer").contains("www.2shui.com.cn")) {
			pageMapper.enjoyOne(name);
		}
	}
	
	@RequestMapping("/map")
	@ResponseBody
	public List<Map<String, Object>> map(
			@RequestParam(value = "pNo", required = false) Integer pNo) {
		if (null == pNo || pNo < 1) {
			pNo = 1;
		}
		List<Page> list = pageMapper.limit((pNo - 1)
				* AppConfig.LIST_AERICLE_NUM, AppConfig.LIST_AERICLE_NUM);
		return SafeMapBuilder.buildMap(list);
	}
}
