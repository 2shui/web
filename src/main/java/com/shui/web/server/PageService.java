package com.shui.web.server;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shui.web.conf.AppConfig;
import com.shui.web.model.Page;
import com.shui.web.repo.PageMapper;
import com.shui.web.util.Decimal52;
import com.shui.web.util.FreemarkerUtils;
import com.shui.web.util.PropUtils;

@MapperScan("com.shui.web.repo")
@Component("pageService")
public class PageService {
	private static Logger log = LoggerFactory.getLogger(PageService.class);
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
		
		page.setFileName(name);
		page.setContent(page.getContent().replace("　　", "\n　　"));
		page.setContent(page.getContent().replace("　　", "<br/>　　"));
		staticPage(page, 1);
	}
	
	public void restatic(Page page) {
		page.setContent(page.getContent().replace("　　", "\n　　"));
		page.setContent(page.getContent().replace("　　", "<br/>　　"));
		staticPage(page, 1);
	}
	
	private void staticPage(Page page, int pageNo) {
		List<Page> list = pageMapper.getRandom(AppConfig.RANDOM_ARTICLE_NUM);
		String path = FreemarkerUtils.class.getResource("/").getFile()
				+ "/ftl/";
		FreemarkerUtils.initTemplate(path, "page.ftl", null);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", page.getTitle());
		map.put("pageName", page.getFileName());
		map.put("site", AppConfig.WEB_SITE);
		map.put("sld", AppConfig.DYNAMIC_SITE);
		map.put("recommend", list);
		map.putAll(subStringM(page.getContent(), pageNo));
		
		String fileName;
		if (pageNo > 1) {
			fileName = PropUtils.getValue("resource.properties", "pagePath")
					+ page.getFileName() + "_" + pageNo + ".html";
		} else {
			fileName = PropUtils.getValue("resource.properties", "pagePath")
					+ page.getFileName() + ".html";
		}
		FreemarkerUtils.analysisTemplate(fileName, map, null);
		if ((Integer) map.get("countNum") > pageNo) {
			staticPage(page, pageNo + 1);
		}
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
	
	private Map<String, Object> subStringM(String str, int pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = 0;// 已经截取字符的长度
		int length = 0;// 每个字符的长度
		StringBuffer sb = new StringBuffer();
		char ch[] = str.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			try {
				length = String.valueOf(ch[i]).getBytes("GBK").length;
			} catch (UnsupportedEncodingException e) {
				log.error(e.getMessage());
			}
			num += length;
			if (num <= AppConfig.ARTICLE_WORLD_NUM * pageNo
					&& (pageNo - 1) * AppConfig.ARTICLE_WORLD_NUM < num) {
				sb.append(ch[i]);
			}
		}
		map.put("pageNo", pageNo);
		map.put("countNum", 0 == num % AppConfig.ARTICLE_WORLD_NUM ? num
				/ AppConfig.ARTICLE_WORLD_NUM
				: (num / AppConfig.ARTICLE_WORLD_NUM) + 1);
		map.put("body", sb.toString());
		return map;
	}
}
