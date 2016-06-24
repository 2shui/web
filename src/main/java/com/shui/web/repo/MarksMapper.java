package com.shui.web.repo;

import org.apache.ibatis.annotations.Insert;

import com.shui.web.model.Marks;

public interface MarksMapper {

	@Insert("insert into marks(bookName,bookNum,customerId,markText,note) "
			+"values(#{bookName},#{bookNum},#{customerId},#{markText},#{note})")
	public void addBook(Marks marks);
}
