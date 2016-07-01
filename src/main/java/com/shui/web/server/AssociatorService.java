package com.shui.web.server;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shui.web.conf.AppConfig;
import com.shui.web.model.Member;
import com.shui.web.repo.MemberMapper;
import com.shui.web.util.Cookies;
import com.shui.weibo.Users;
import com.shui.weibo.http.AccessToken;
import com.shui.weibo.model.User;
import com.shui.weibo.model.WeiboException;

@MapperScan("com.shui.web.repo")
@Component("associatorService")
public class AssociatorService {
	private static Logger log = LoggerFactory.getLogger(AssociatorService.class);
	@Autowired
	private MemberMapper memberMapper;

	public String weiboLogin(HttpServletRequest request, HttpServletResponse resp, AccessToken token) {
		Member m = memberMapper.getByOpenidAndType(token.getUid(), "weibo");
		if(null == m) {
			try {
				User user = new Users(token.getAccessToken()).showUserById(token.getUid());
				String uid = user.getId();
				String userName = user.getName();
				String profileImage = user.getProfileImageUrl();
				String gender = user.getGender();
				Member member = new Member(uid, userName, new Date(), "weibo", gender, null, profileImage);
				memberMapper.reg(member);
				m = memberMapper.getByOpenidAndType(uid, "weibo");
			} catch (WeiboException e) {
				log.error("weibo token showUserById error:{}", e);
				e.printStackTrace();
			}
		}
		Cookies.set(resp, AppConfig.LOGIN_NAME, m.getNickName());
		request.getSession().setAttribute(AppConfig.BOOK_LOGIN, m);
		
		String uri = (String) request.getSession().getAttribute("login_current_url");
		request.getSession().removeAttribute("login_current_url");
		uri = null == uri || uri.isEmpty() ? AppConfig.FULL_SITE : uri;
		return uri;
	}
	
	public void tet(HttpServletRequest request, Member m) {
		request.getSession().setAttribute(AppConfig.BOOK_LOGIN, m);
		request.getSession().removeAttribute("login_current_url");
	}
}
