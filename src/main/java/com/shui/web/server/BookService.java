package com.shui.web.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.shui.web.model.Book;
import com.shui.web.util.FreemarkerUtils;
import com.shui.web.util.PropUtils;

@MapperScan("com.shui.web.repo")
@Component("bookService")
public class BookService {
	public List<String> staticPage(List<Book> books) {
		List<String> list = new ArrayList<String> ();
		FreemarkerUtils.initTemplate("book.ftl", null);
		for(Book book:books){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", book.getChineseName());
			map.put("content", book.getContent());
			String fileName = PropUtils.getValue("resource.properties", "bookPath")+
					book.getBookName()+"_"+book.getBookNum()+".html";
			FreemarkerUtils.analysisTemplate(fileName, map, null);
			list.add(fileName);
		}
		return list;
	}
	
}
