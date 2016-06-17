<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<meta name="mobile-agent" content="format=html5;url=http://www.2shui.com.cn/index.html">
  	<link rel="alternate" media="only screen and(max-width: 640px)" href="http://www.2shui.com.cn/index.html">
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8; charset=UTF-8" />
  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  	<title>${title}-2水网(2SHUI.COM.CN)--做有态度的看客！</title>
  	<meta name="keywords" content="2水,2水网,读书,读书笔记，书评，有态度，看客，在线读书，伴读" />
	<meta name="description" content="2水网，做有态度的看客！读名著，写随笔，酒逢知己饮，诗向会人吟，三人行必有一伴读。" />
  	<style type="text/css">
	.img_sina_share {
	    cursor: pointer;
	    display: none;
	    position: absolute;
	}
	</style>
  </head>
  <body style="margin: 5px 20px;">
  	${content}
  	<img id="imgSinaShare" class="img_sina_share" style="display:none;" title="将选中内容分享到新浪微博" src="http://simg.sinajs.cn/blog7style/images/common/share.gif" />

<script type="text/javascript">
var $sinaMiniBlogShare = function(eleShare, eleContainer) {
	var eleTitle = document.getElementsByTagName("title")[0];
	eleContainer = eleContainer || document;
	var funGetSelectTxt = function() {
		var txt = "";
		if(document.selection) {
			txt = document.selection.createRange().text;	// IE
		} else {
			txt = document.getSelection();
		}
		return txt.toString();
	};
	eleContainer.onmouseup = function(e) {
		e = e || window.event;
		var txt = funGetSelectTxt(), sh = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
		var left = (e.clientX - 40 < 0) ? e.clientX + 20 : e.clientX - 40, top = (e.clientY - 40 < 0) ? e.clientY + sh + 20 : e.clientY + sh - 40;
		
		if (txt) {
			eleShare.style.display = "inline";
			eleShare.style.left = left + "px";
			eleShare.style.top = top + "px";
		} else {
			eleShare.style.display = "none";
		}
	};
	eleShare.onclick = function() {
		var txt = funGetSelectTxt(), title = (eleTitle && eleTitle.innerHTML)? eleTitle.innerHTML : "未命名页面";
		if (txt) {
			window.open('http://v.t.sina.com.cn/share/share.php?title=' + txt + '→来自页面"' + title + '"的文字片段&url=' + window.location.href);	
		}
	};
};
$sinaMiniBlogShare(document.getElementById("imgSinaShare"));
</script>
</body>
</html>