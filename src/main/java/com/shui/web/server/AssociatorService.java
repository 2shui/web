package com.shui.web.server;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.qzone.UserInfoBean;
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
		if(!StringUtils.isNullOrEmpty(token.getAccessToken())) {
			Member m = memberMapper.getByOpenidAndType(token.getUid(), "weibo");
			String userName = null;
			if(null == m) {
				try {
					User user = new Users(token.getAccessToken()).showUserById(token.getUid());
					String uid = user.getId();
					userName = user.getName();
					String profileImage = user.getProfileImageUrl();
					String gender = user.getGender();
					Member member = new Member(uid, userName, new Date(), "weibo", gender, null, profileImage);
					memberMapper.reg(member);
				} catch (WeiboException e) {
					log.error("weibo token showUserById error:{}", e);
					e.printStackTrace();
				}
			} else {
				userName = m.getNickName();
			}
			Cookies.set(resp, AppConfig.LOGIN_NAME, userName);
			request.getSession().setAttribute(AppConfig.BOOK_LOGIN, m);
		}
		
		String uri = (String) request.getSession().getAttribute("login_current_url");
		request.getSession().removeAttribute("login_current_url");
		uri = null == uri || uri.isEmpty() ? AppConfig.FULL_SITE : uri;
		return uri;
	}
	
	public String qqLogin(HttpServletRequest request, HttpServletResponse resp, com.qq.connect.javabeans.AccessToken token) {
		String accessToken = token.getAccessToken();
		if(!StringUtils.isNullOrEmpty(accessToken)) {
			OpenID openIDObj =  new OpenID(accessToken);
			try {
				String openID = openIDObj.getUserOpenID();
				Member m = memberMapper.getByOpenidAndType(openID, "qq");
				String userName = null;
				if(null == m) {
					UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
					UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
					userName = userInfoBean.getNickname();
					String profileImage = userInfoBean.getAvatar().getAvatarURL30();
					String gender = userInfoBean.getGender();
					Member member = new Member(openID, userName, new Date(), "qq", gender, null, profileImage);
					memberMapper.reg(member);
					m = memberMapper.getByOpenidAndType(openID, "qq");
				} else {
					userName = m.getNickName();
				}
				
				Cookies.set(resp, AppConfig.LOGIN_NAME, userName);
				request.getSession().setAttribute(AppConfig.BOOK_LOGIN, m);
			} catch (QQConnectException e) {
				log.error("qq access error:{}", e);
				e.printStackTrace();
			}
		}
		
		String uri = (String) request.getSession().getAttribute("login_current_url");
		request.getSession().removeAttribute("login_current_url");
		uri = null == uri || uri.isEmpty() ? AppConfig.FULL_SITE : uri;
		return uri;
	}
}
