package com.shui.web.repo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.shui.web.model.Page;

public interface PageMapper {
	
	/**
	 * */
	@Select("select * from web where id = #{id}")
	Page getById(@Param("id") Long id);
	
	/**
	 * 获取静态化的随机页面
	 * */
	@Select("SELECT id,title,fileName FROM web where fileName IS NOT NULL"
			+ " order by rand() limit #{num}")
	List<Page> getRandom(@Param("num") int num);
	
	/**
	 * 首页随机静态文章 TODO
	 * */
	@Select("select id,uri,title,content,fileName from web where fileName IS NOT NULL "
			+ "order by rand() limit #{num}")
	List<Page> random(@Param("num") int num);
	
	/**
	 * 重新初始化页面获取列表
	 * */
	@Select("select * from web where fileName IS NOT NULL limit #{begin},#{num}")
	List<Page> findStaticLimit(@Param("begin") int begin, @Param("num") int num);
	
	/**
	 * 地图获取静态页面分页
	 * */
	@Select("select title,fileName from web where fileName IS NOT NULL limit #{begin},#{num}")
	List<Page> limit(@Param("begin") int begin, @Param("num") int num);
	
	/**
	 * 静态化页面获取未静态页面列表
	 * */
	@Select("select * from web where fileName IS NULL order by fileName limit #{begin},#{num}")
	List<Page> findNotStatic(@Param("begin") int begin, @Param("num") int num);
	
	@Update("update web set hits=hits+1 where fileName=#{name}")
	void readOne(@Param("name") String name);
	
	@Update("update web set enjoy=enjoy+1 where fileName=#{name}")
	void enjoyOne(@Param("name") String name);
	
	@Update("update web set fileName=#{name} where id=#{id}")
	void updateFileName(@Param("id") Long id, @Param("name") String name);
}
