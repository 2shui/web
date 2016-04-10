package com.shui.web.repo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.shui.web.model.Page;

public interface PageMapper {
	
	@Select("select * from web where id = #{id}")
	Page getById(@Param("id") Long id);
	
	@Select("SELECT id,uri,title FROM web order by rand() limit #{num}")
	List<Page> getRandom(@Param("num") int num);
	
	@Select("select * from web limit #{begin},#{num}")
	List<Page> findLimit(@Param("begin") int begin, @Param("num") int num);
	
	@Select("select * from web where fileName IS NOT NULL order by fileName limit #{begin},#{num}")
	List<Page> findNotStatic(@Param("begin") int begin, @Param("num") int num);
	
	@Update("update web set hits=hits+1 where fileName=#{name}")
	void readOne(@Param("name") String name);
	
	@Update("update web set enjoy=enjoy+1 where fileName=#{name}")
	void enjoyOne(@Param("name") String name);
	
	@Update("update web set fileName=#{name} where id=#{id}")
	void updateFileName(@Param("id") Long id, @Param("name") String name);
}
