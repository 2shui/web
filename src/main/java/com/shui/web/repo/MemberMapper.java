package com.shui.web.repo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shui.web.model.Member;

public interface MemberMapper {

	@Insert("insert into member(openId,nickName,regTime,originType,gender,email,profileImage)"
			+ " values(#{openId},#{nickName},#{regTime},#{originType},#{gender},#{email},#{profileImage})")
	public void reg(Member member);
	
	@Select("select * from member where openId = #{openId} and originType= #{originType}")
	public Member getByOpenidAndType(@Param("openId") String openId, @Param("originType") String originType);
}
