package com.shui.web.repo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import com.shui.web.bean.NoteMark;
import com.shui.web.model.Marks;
import com.shui.web.sqlTool.MarkSqlBuilder;

public interface MarksMapper {

	@Insert("insert into marks(bookName,bookNum,customerId,markText,note) "
			+"values(#{bookName},#{bookNum},#{customerId},#{markText},#{note})")
	public void addBook(Marks marks);
	
	@SelectProvider(type = MarkSqlBuilder.class, method = "getSql")
	@ResultMap(value = "getPageMark")
	public NoteMark getPageMark(Marks mark);
}
