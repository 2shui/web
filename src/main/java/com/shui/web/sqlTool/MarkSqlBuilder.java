package com.shui.web.sqlTool;

import static org.apache.ibatis.jdbc.SqlBuilder.*;
public class MarkSqlBuilder {

	public String getSql() {
		BEGIN();
		SELECT("a.id, a.bookName, a.bookNum, a.markText, a.note, m.nickName");
		FROM("member m");
		FROM("marks a");
		WHERE("m.id = a.customerId");
		WHERE("a.bookName = #{bookName}");
		WHERE("a.bookNum = #{bookNum}");
		return SQL();
	}
}
