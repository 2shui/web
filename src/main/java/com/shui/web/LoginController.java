package com.shui.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shui.web.repo.MemberMapper;
import com.shui.web.server.AssociatorService;
import com.shui.weibo.Oauth;
import com.shui.weibo.http.AccessToken;
import com.shui.weibo.model.WeiboException;

@SpringBootApplication
@RequestMapping("/login")
@MapperScan("com.shui.web.repo")
@Scope("prototype")
public class LoginController {
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private AssociatorService associatorService;

	@RequestMapping("/weibo")
	public String access(HttpServletRequest request) throws WeiboException {
		request.getSession().setAttribute("login_current_url",
				request.getHeader("Referer"));
		Oauth oauth = new Oauth();
		return "redirect:" + oauth.authorize("code");
	}

	@RequestMapping("/weibo_redirect")
	public String redirect(@RequestParam String code, HttpServletRequest request, HttpServletResponse resp)
			throws WeiboException {
		Oauth oauth = new Oauth();
		AccessToken token = oauth.getAccessTokenByCode(code);
		return "redirect:" + associatorService.weiboLogin(request, resp, token);
	}
	
}
