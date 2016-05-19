package com.shui.web.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shui.web.conf.AppConfig;
import com.shui.web.model.Page;
import com.shui.web.repo.PageMapper;
import com.shui.web.util.LuceneIKUtil;

@MapperScan("com.shui.web.repo")
@Component("fullIndexService")
public class FullIndexService {
	private static Logger log = LoggerFactory.getLogger(FullIndexService.class);
	@Autowired
	private PageMapper pageMapper;
	
	public List<Page> search(String[] fields, String key) {
		List<Page> result = new ArrayList<Page>();
		LuceneIKUtil index = LuceneIKUtil.getInstance();
		try {
			List<Document> documents = index.search(fields, key);
			for (Document doc : documents) {
				Page page = new Page();
				page.setTitle(doc.get(AppConfig.INDEX_TITLE));
				page.setFileName("http://" + AppConfig.WEB_SITE + "/"
						+ doc.get(AppConfig.INDEX_FILE_NAME) + ".html");
				page.setContent(doc.get(AppConfig.INDEX_CONTENT));
				result.add(page);
			}
		} catch (IOException|ParseException e) {
			log.error("index search error:{}", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public void fullIndex() {
		List<Page> listPage = pageMapper.findStatic();
		List<Document> documents = indexDocument(listPage);
		LuceneIKUtil index = LuceneIKUtil.getInstance();
		long begin = System.currentTimeMillis();
		try {
			index.createIndex(documents, true);
		} catch (Exception e) {
			log.error("full index error:{}", e.getMessage());
			e.printStackTrace();
		}
		log.info("index all page cost:{}ms", System.currentTimeMillis() - begin);
	}
	
	public List<Document> indexDocument(List<Page> list) {
		List<Document> documents = new ArrayList<Document>();
		for (Page page : list) {
			Document doc = new Document();
			doc.add(new Field(AppConfig.INDEX_FILE_NAME, page.getFileName(),
					TextField.TYPE_STORED));
			doc.add(new Field(AppConfig.INDEX_TITLE, page.getTitle(),
					TextField.TYPE_STORED));
			doc.add(new Field(AppConfig.INDEX_CONTENT, page.getContent(),
					TextField.TYPE_STORED));
			documents.add(doc);
		}
		return documents;
	}
}
