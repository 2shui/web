package com.shui.web.repo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shui.web.model.Page;

public interface PageMapper {
	
	@Select("select id,title,uri from web where id = #{id}")
	Page getById(@Param("id") Long id);
	
	@Select("SELECT id,uri,title FROM web order by rand() limit #{num}")
	List<Page> getRandom(@Param("num") int num);
	
	@Select("select * from web limit #{begin},#{num}")
	List<Page> findLimit(@Param("begin") int begin, @Param("num") int num);
}
