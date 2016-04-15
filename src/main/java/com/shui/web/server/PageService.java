package com.shui.web.server;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shui.web.conf.AppConfig;
import com.shui.web.model.Page;
import com.shui.web.repo.PageMapper;
import com.shui.web.util.Decimal52;
import com.shui.web.util.FreemarkerUtils;
import com.shui.web.util.MD5Util;
import com.shui.web.util.PropUtils;

@MapperScan("com.shui.web.repo")
@Component("pageService")
public class PageService {
	
	@Autowired
	private PageMapper pageMapper;

	public void staticPage(Page page) {
		Decimal52 decimal = new Decimal52();
		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		sb.append(random.nextInt(999));
		sb.append(page.getId());
		sb.append(random.nextInt(999));
		String name = decimal.getDecimal(new BigDecimal(sb.toString()));
		pageMapper.updateFileName(page.getId(), name);

		String path = FreemarkerUtils.class.getResource("/").getFile()
				+ "/ftl/";
		FreemarkerUtils.initTemplate(path, "page.ftl", null);
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", page.getTitle());
		map.put("body", page.getContent());
		FreemarkerUtils.analysisTemplate(
				PropUtils.getValue("resource.properties", "pagePath") + name
						+ ".html", map, null);
	}

	public void staticIndex(List<Page> hotArticle, List<Page> randomArticle,
			List<Page> ranklistArticle, List<String> hotWord) {
		String path = FreemarkerUtils.class.getResource("/").getFile()
				+ "/ftl/";
		FreemarkerUtils.initTemplate(path, "index.ftl", null);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hotArticle", hotArticle);
		map.put("randomArticle", randomArticle);
		map.put("ranklistArticle", ranklistArticle);
		map.put("hotWord", hotWord);
		map.put("site", AppConfig.WEB_SITE);
		map.put("sld", AppConfig.DYNAMIC_SITE);
		FreemarkerUtils.analysisTemplate(
				PropUtils.getValue("resource.properties", "pagePath")
						+ "index.html", map, null);
	}
	
	/**
	 * 返回首页热搜词
	 * */
	public List<String> hotWord() {
		List<String> list = new ArrayList<String>();
		String hots = (String) PropUtils.getProps("resource.properties").get(
				"hotWord");
		List<String> hotWord = Arrays.asList(hots.split(","));
		Collections.shuffle(hotWord);
		int toIndex = hotWord.size() > AppConfig.HOT_WORD_NUM ? AppConfig.HOT_WORD_NUM
				: hotWord.size();
		list = hotWord.subList(0, toIndex);

		return list;
	}
	
	/**
	 * 认证
	 * */
	public boolean auth(String auth) {
		return true;
//		Integer ymdh = Integer.parseInt(String.format("%1$tY%1$tm%1$td%1$tH",
//				new Date()));
//		Decimal52 decimal = new Decimal52();
//		String md5 = MD5Util.bytesToMD5(decimal.getDecimal(ymdh).getBytes());
//		return auth.equals(md5);
	}
}
