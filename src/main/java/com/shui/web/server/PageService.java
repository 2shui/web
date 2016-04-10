package com.shui.web.server;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shui.web.model.Page;
import com.shui.web.repo.PageMapper;
import com.shui.web.util.Decimal52;
import com.shui.web.util.FreemarkerUtils;

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
		
		String path = FreemarkerUtils.class.getResource("/").getFile() + "/ftl/";
		FreemarkerUtils.initTemplate(path, "page.ftl", null);
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", page.getTitle());
		map.put("body", page.getContent());
		FreemarkerUtils.analysisTemplate("E:/workspace/html/"+name+".html", map, null);
	}
}
