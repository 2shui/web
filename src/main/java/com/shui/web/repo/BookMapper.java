package com.shui.web.repo;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shui.web.model.Book;

public interface BookMapper {

	@Insert("insert into book(content,path,bookName,bookNum) values(#{content},#{path},#{bookName},#{bookNum})")
	public void addBook(Book book);
	
	@Select("select * from book where id = #{id}")
	Book getById(@Param("id") Long id);
	
	@Select("select * from book where bookName = #{bookName}")
	List<Book> getByBookName(@Param("bookName") String bookName);
}
