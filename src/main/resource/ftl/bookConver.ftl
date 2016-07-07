<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<title>${chineseName}-2水网(2SHUI.COM.CN)--做有态度的看客！</title>
  	<meta name="keywords" content="2水,2水网,读书,读书笔记，书评，有态度，看客，在线读书，伴读" />
	<meta name="description" content="2水网，做有态度的看客！读名著，写随笔，酒逢知己饮，诗向会人吟，三人行必有一伴读。" />
  	<meta name="viewport" content="width = 1050, user-scalable = no" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<script type="text/javascript" src="http://${site}/js/jquery.min.js"></script>
	<script type="text/javascript" src="http://${site}/js/modernizr.2.5.3.min.js"></script>
	<script type="text/javascript" src="http://${site}/js/common.js"></script>
  
  	<link href="http://${site}/css/book.css" rel="stylesheet" media="screen">
  </head>
  <body style="margin: 5px 20px;">
  	<span id="associator" style="float:right;margin:5px 0;font-size:12px;">
  		<a style="text-decoration:none;" href="http://boot.2shui.com.cn/login/qq">
			<img src="http://img.2shui.com.cn/qq.png">
		</a>
		<a style="text-decoration:none;" href="http://boot.2shui.com.cn/login/weibo">
			<img src="http://img.2shui.com.cn/weibo.png">
		</a>
  	
		<a href="http://boot.2shui.com.cn/login/weibo">
			<img src="http://timg.sjs.sinajs.cn/t4/appstyle/widget/images/loginButton/loginButton_18.png">
		</a>
	</span>
  	<div class="flipbook-viewport">
	<div class="container">
		<div class="flipbook">
		<#list 1..num as n>
			<div id="w${n}" onmouseup="n(${n})"></div>
		</#list>
		<#if 0==num/2>
			<div id="end">end</div>
		</#if>
		</div>
	</div>
</div>


<div id="writeBox" class="writeBox">
	<form id="form">
		<div class="box_form">
		<div class="box_mark"><span id="box_tex"></span></div>
		<div class="box_write">
			<textarea name="note" placeholder="读书笔记" 
				style="margin:0 auto;width:294px;"></textarea>
			<input id="bookName" type="hidden" name="bookName" />
			<input id="bookNum" type="hidden" name="bookNum" />
			<input id="markText" type="hidden" name="markText" />
			<input type="button" value="留言" onclick="mark()" /></div>
		</div>
	</form>
</div>

<div id="login" class="writeBox">
	<div id="box" style="width:200px;height:100px;margin:-50px 0 0 -100px;">
		<span style="font-weight:600;text-transform:lowercase;line-height:20px;color:#333;font-size:12px;">请使用第三方账号登录：</span>
		<a href="http://boot.2shui.com.cn/login/weibo">
				<img src="http://timg.sjs.sinajs.cn/t4/appstyle/widget/images/loginButton/loginButton_18.png">
		</a>
	</div>
</div>
<!--
<div id="login" class="writeBox">
	<div id="box">
	    <h2 class="title">用户登录</h2>
	    <div class="login">
		    <form id="login">
		        <p><label>账户：</label><input type="text" name="userName" class="text"></p>
		        <p><label>密码：</label><input type="text" name="pwd" class="text"></p>
		        <p><input type="submit" name="" id="" value="提 交" class="btn">
		        	<a href="http://${site}/reg.html">注册</a></p>
		    </form>
	    </div>
	</div>
</div>
-->
<img id="imgMark" class="img_mark" style="display:none;" 
  		title="为该段添加读书笔记" src="http://img.2shui.com.cn/shui_25.png" />
<div class="share_box" id="share_box">
	<div class="share_inner" id="share_inner"></div>
</div>

<script type="text/javascript">var name = "${name}";var uri='http://boot.2shui.com.cn';var uul='http://www.2shui.com.cn';</script>
<script type="text/javascript" src="http://${site}/js/book.js"></script>
</body>
</html>