package com.shui.web.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.shui.web.conf.AppConfig;
import com.shui.web.model.Book;
import com.shui.web.util.FreemarkerUtils;
import com.shui.web.util.PropUtils;

@MapperScan("com.shui.web.repo")
@Component("bookService")
public class BookService {
	public List<String> staticPage(List<Book> books) {
		List<String> list = new ArrayList<String>();
		FreemarkerUtils.initTemplate("book.ftl", null);
		String bookName = null;
		for (Book book : books) {
			if (null == bookName) {
				bookName = book.getBookName();
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", book.getChineseName());
			map.put("content", book.getContent());
			String fileName = PropUtils.getValue("resource.properties",
					"bookPath")
					+ book.getBookName()
					+ "_"
					+ book.getBookNum()
					+ ".html";
			FreemarkerUtils.analysisTemplate(fileName, map, null);
			list.add(fileName);
		}
		list.add(staticConver(bookName, books.size()));
		return list;
	}
	
	private String staticConver(String bookName, int pageNum) {
		if (!StringUtils.isNullOrEmpty(bookName)) {
			FreemarkerUtils.initTemplate("bookConver.ftl", null);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", bookName);
			map.put("num", pageNum);
			map.put("site", AppConfig.WEB_SITE);
			String fileName = PropUtils.getValue("resource.properties",
					"bookPath") + bookName + ".html";
			FreemarkerUtils.analysisTemplate(fileName, map, null);
			return fileName;
		}
		return null;
	}
}
