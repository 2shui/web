package com.shui.web;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shui.web.model.Page;
import com.shui.web.repo.PageMapper;
import com.shui.web.util.Decimal52;
import com.shui.web.util.FreemarkerUtils;
import com.shui.web.util.MD5Util;

@RestController
@SpringBootApplication
@RequestMapping("/sdstscakfefrfnbb")
@MapperScan("com.shui.web.repo")
public class AdminController {

	@Autowired
	private PageMapper pageMapper;
	
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
		Integer ymdh = Integer.parseInt(String.format("%1$tY%1$tm%1$td%1$tH",
				new Date()));
		Decimal52 decimal = new Decimal52();
		String md5 = MD5Util.bytesToMD5(decimal.getDecimal(ymdh).getBytes());
		System.out.println(md5);
		if (auth.equals(md5)) {
			List<Page> list = pageMapper.findLimit(begin, num);
			list.forEach(page -> FreemarkerUtils.create(page));
		}
	}
}
