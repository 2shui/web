package com.shui.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shui.web.bean.WechatType;
import com.shui.web.conf.AppConfig;
import com.shui.web.model.Page;
import com.shui.web.server.FullIndexService;
import com.shui.web.server.WechatService;

@RestController
@SpringBootApplication
@RequestMapping("/wechat")
@MapperScan("com.shui.web.repo")
@Scope("prototype")
public class WechatController {
	@Autowired
	private FullIndexService fullIndexService;
	@Autowired
	private WechatService wechatService;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public void index(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		boolean isGet = request.getMethod().toLowerCase().equals("get");
		if (isGet) {
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");
			if (wechatService.validate(signature, timestamp, nonce)) {
				response.getWriter().write(echostr);
			}
		} else {
			Map<String, String> reqMap = wechatService.parseReq(request);
			if ("text".equalsIgnoreCase(reqMap.get("MsgType"))) {
				List<Page> list = fullIndexService.search(new String[] {
						AppConfig.INDEX_TITLE, AppConfig.INDEX_CONTENT },
						reqMap.get("Content"));
				String input = wechatService.buildResponse(list, reqMap, WechatType.TEXT);
				response.getWriter().write(input);
			} else if("event".equalsIgnoreCase(reqMap.get("MsgType"))){
				String input = wechatService.buildResponse(null, reqMap, WechatType.EVENT);
				response.getWriter().write(input);
			}else {
				String input = wechatService.buildResponse(null, reqMap, WechatType.IMAGE);
				response.getWriter().write(input);
			}
		}
	}

}
