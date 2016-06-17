package com.shui.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shui.web.conf.AppConfig;
import com.shui.web.model.Book;
import com.shui.web.model.Page;
import com.shui.web.repo.BookMapper;
import com.shui.web.repo.PageMapper;
import com.shui.web.server.BookService;
import com.shui.web.server.FullIndexService;
import com.shui.web.server.PageService;
import com.shui.web.util.Decimal52;
import com.shui.web.util.SafeMapBuilder;

@RestController
@SpringBootApplication
@RequestMapping("/sdstscakfefrfnbb")
@MapperScan("com.shui.web.repo")
@Scope("prototype")
public class AdminController {

	@Autowired
	private PageMapper pageMapper;
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private PageService pageService;
	@Autowired
	private BookService bookService;
	@Autowired
	private FullIndexService fullIndexService;
	
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
			@PathVariable("begin") int begin, @PathVariable("num") int num) {
		if (pageService.auth(auth)) {
			List<Page> list = pageMapper.findNotStatic(begin, num);
			list.forEach(page -> pageService.staticPage(page));
		}
	}
	
	@RequestMapping("/restatic/{auth}/{begin}/{num}")
	public void restaticPage(@PathVariable("auth") String auth,
			@PathVariable("begin") int begin, @PathVariable("num") int num) {
		if (pageService.auth(auth)) {
			List<Page> list = pageMapper.findStaticLimit(begin, num);
			list.forEach(page -> pageService.restatic(page));
		}
	}
	
	@RequestMapping("/mapSite/{auth}/{begin}/{num}")
	public void mapSite(@PathVariable("auth") String auth,
			@PathVariable("begin") int begin, @PathVariable("num") int num) {
		if (pageService.auth(auth)) {
			List<Page> list = pageMapper.limit(begin, num);
			pageService.siteMap(list);
		}
	}
	
	@RequestMapping("/index/{auth}")
	public void staticIndex(@PathVariable("auth") String auth) {
		if (pageService.auth(auth)) {
			List<Page> list = pageMapper.random(AppConfig.HOT_ARTICLE_NUM
					+ AppConfig.RANDOM_ARTICLE_NUM
					+ AppConfig.RANKLIST_AERICLE_NUM);
			List<Page> hotArticle = list.subList(0, AppConfig.HOT_ARTICLE_NUM);
			List<Page> randomArticle = list.subList(AppConfig.HOT_ARTICLE_NUM,
					AppConfig.HOT_ARTICLE_NUM + AppConfig.RANDOM_ARTICLE_NUM);
			List<Page> ranklistArticle = list.subList(
					AppConfig.HOT_ARTICLE_NUM + AppConfig.RANDOM_ARTICLE_NUM,
					AppConfig.HOT_ARTICLE_NUM + AppConfig.RANDOM_ARTICLE_NUM
							+ AppConfig.RANKLIST_AERICLE_NUM);
			List<String> hotWord = pageService.hotWord();
			pageService.staticIndex(hotArticle, randomArticle, ranklistArticle, hotWord);
		}
	}
	
	@RequestMapping("/indexed/{auth}")
	public void indexed(@PathVariable("auth") String auth){
		if (pageService.auth(auth)) {
			fullIndexService.fullIndex();
		}
	}
	
	@RequestMapping("/s/{key}/{auth}")
	@ResponseBody
	public List<Map<String, Object>> search(@PathVariable("auth") String auth,
			@PathVariable("key") String key) {
		if (pageService.auth(auth)) {
			String[] fields = new String[] { AppConfig.INDEX_TITLE,
					AppConfig.INDEX_CONTENT };
			List<Page> page = fullIndexService.search(fields, key);
			return SafeMapBuilder.buildMap(page);
		}
		return null;
	}
	
	@RequestMapping("/addBook")
	public Book book(Book book, HttpServletResponse response) {
		bookMapper.addBook(book);
		response.setHeader("Access-Control-Allow-Origin", "http://"+AppConfig.WEB_SITE);
		return book;
	}
	
	@RequestMapping("/getBook/{id}")
	public Book getbook(@PathVariable("id") long id) {
		return bookMapper.getById(id);
	}
	
	@RequestMapping("/staticBook/{bookName}")
	public List<String> staticBook(@PathVariable("bookName") String bookName) {
		List<Book> list = bookMapper.getByBookName(bookName);
		return bookService.staticPage(list);
	}
}
