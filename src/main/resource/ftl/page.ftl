<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<meta name="mobile-agent" content="format=html5;url=http://www.2shui.com.cn/index.html">
  	<link rel="alternate" media="only screen and(max-width: 640px)" href="http://www.2shui.com.cn/index.html">
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8; charset=UTF-8" />
  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  	<title>${title}--2水(2SHUI.COM.CN)--水利万物而不争</title>
  	<meta name="keywords" content="2水,2水网,养生,菜谱,美食,食疗,保健,健康养生,各地菜谱,创意美食,心理养生,女人养生,房事养生,经络养生,保健知识,中医养生,运动养生" />
	<meta name="description" content="2水网，致力于美食及养生健康知识的普及，推进养生理念的更新。2水网，2SHUI.COM.CN" />
  	<link href="http://www.2shui.com.cn/css/content.css" rel="stylesheet" media="screen">
  	<link href="http://www.2shui.com.cn/css/content_public_new.css" rel="stylesheet" media="screen">
  	<link rel="canonical" href="/index.html"/>
  	<script type="text/javascript" src="http://www.2shui.com.cn/js/angular.min.js"></script>
  	<script type="text/javascript" src="http://www.2shui.com.cn/js/common.js"></script>
  	
  </head>
  <body>
  	<div class="header">
	    <div class="con">
	        <div class="nav">
	            <div class="Nav_list1"> <a rel="nofollow" href="/" >2水(2shui.com.cn)网首页</a> </div>
	            <div class="clear"></div>
	        </div>
	        <span class="a1">
	        	<form novalidate name="search" ng-submit="submit()" ng-controller="searchCtrl" id="searchApp">
					<input type="text" ng-model="text" placeholder="请输入搜索关键词" required />
					<button type="submit" class="BTN">搜索</button>
				</form>
	        	<a target="_blank" href="/map.html">网站地图</a>
	        </span>
	        <div class="clear"></div>
	    </div>
	</div>
	<div class="con" style="height:80px;">广告</div>
	<div class="con">
		<div class="con_left">
			<div id="app" ng-controller="ctrl">
				<div style="float:right; width:200px;height:200px;">广告</div>
				<iframe style="width:0; height:0; margin-top:-10px;" name="submitFrame" src="about:blank"></iframe>
				<form name="dd" method="post" target="submitFrame" action="http://tsn.baidu.com/text2audio">
					<input type="hidden" name="tex" value="FDAFEAFD"/>
					<input type="hidden" name="lan" value="zh"/>
					<input type="hidden" name="cuid" value="1460975073938"/>
					<input type="hidden" name="ctp" value="1"/>
					<input type="hidden" name="tok" value="24.57bdd55ce62204e1cc63be3192bc6275.2592000.1462956899.282335-7986971" />
					<button type="submit" class="BTN"><!--在线听--></button>
				</form>
				<div ng-model="page.body" style="font-size:18px;line-height:180%;">${body}</div>
				<ul class="pagination">
				<#if pageNo == 1>
					<li class="previous-off">&laquo;上一页</li>
				<#else>
					<li><a href="http://${site}/${pageName}_${pageNo-1}.html">&laquo;上一页</a></li>
				</#if>
				<#list 1..countNum as pn>
				<#if pn==pageNo><li class="active">${pn}</li>
				<#else>
					<#if pn==1><li><a href="http://${site}/${pageName}.html">${pn}</a></li>
					<#else><li><a href="http://${site}/${pageName}_${pn}.html">${pn}</a></li>
					</#if>
				</#if>
				</#list>
				<#if pageNo == countNum>
					<li class="previous-off">下一页 &raquo;</li>
				<#else>
					<li class="next">
						<a href="http://${site}/${pageName}_${pageNo+1}.html">Next &raquo;</a></li>
				</#if>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		
		<!--右侧内容-->
		<div class=" con_right">
			<div>广告</div>
			<div id="recommendApp" ng-controller="recommendCtrl" class="hot module">
				<h3 style="border-bottom:1px solid #CCC;">精彩推荐
					<span ng-click="other()" class="index_other">更多 »</span>
				</h3>
				<div class="IndexTabs">
					<div id="IndexTabs-0">
                        <ul class="tab-content tabCurrent">
                        	<li ng-repeat="article in arr">
                        		<em>{{$index + 1}}</em>
                        		<p>
                        			<a rel='nofollow' href='http://${site}/{{article.fileName}}.html'
                        				 target='_blank' title='{{article.title}}'>
                        				{{article.title}}
                        			</a>
                        		</p>
                        	</li>
						</ul>
						
                    </div>
				</div>
			</div>
			<div>广告</div>
		</div>
		<div>广告</div>
	</div>
	
	<div class="footer">
        <p><a rel="nofollow" href="http://www.2shui.com.cn/">2水网</a>|
        	<a rel="nofollow" href="#">加入我们</a>|
        	<a rel="nofollow" href="#">联系我们</a>|
        	<a rel="nofollow" href="#">反馈</a></p>
        <p>© Copyright 2015 2水网 Inc.All Rights Reserved. 豫ICP备15018592号-1</p>
    </div>
    
    <!--
	<div class="share_box" id="share_box">
        <div class="bdsharebuttonbox">
            <a rel="nofollow" class="share_box_a1" data-cmd="more" href="#" >更多分享</a>
            <a rel="nofollow" class="share_box_a2" data-cmd="qzone" title="分享到QQ空间" href="#">QQ空间</a>
            <a rel="nofollow" class="share_box_a3" data-cmd="weixin" title="分享到微信" href="#">微信</a>
            <a rel="nofollow" class="share_box_a4" data-cmd="sqq" title="分享给QQ好友" href="#">QQ好友</a>
            <a rel="nofollow" class="share_box_a5" data-cmd="tsina" title="分享到新浪微博" href="#">新浪微博</a>
            <a rel="nofollow" class="share_box_a6" data-cmd="tqq" title="分享到腾讯微博" href="#">腾讯微博</a>
        </div>
    </div>
    <script>
        with(document)0
        [(getElementsByTagName('head')[0]||body).appendChild(createElement('script'))
        .src='http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='+~(-new Date()/36e5)];
    </script>
    -->
    <div class="BackTop_box">
		<!--
        <div class="weixin">
            <p class="img"></p>
            <p>微信扫一扫</p>
        </div>
        <p class="BackTop"><a rel="nofollow" href="#"></a></p>
		-->
        <div class="clear"></div>
    </div>
    <script type="text/javascript">
    	var recommendArr = new Array();
    	<#list recommend as article>
    	<#if article_index < 8>
    		var article = {};
    		article.title = '${article.title}';
    		article.fileName = '${article.fileName}';
    		recommendArr[${article_index}] = article;
    	</#if>
    	</#list>
    	var recommendApp = angular.module('recommendApp',[]);
    	var searchApp = angular.module('searchApp', []);
    	//var app = angular.module('app', []);
    	recommendApp.controller('recommendCtrl', function($scope, $http) {
    		$scope.arr = recommendArr;
    		$scope.other = function() {
    			$http.jsonp('http://${sld}/page/uncertain?callback=JSON_CALLBACK')
    				.success(function(data){
    					randomArr = [];
    					for(var i=0;i<data.length;i++){
    						if(i<8){
	    						var article = {};
	    						article.fileName = data[i].fileName;
	    						article.title = data[i].title;
	    						randomArr[i] = article;
    						}else{break;}
    					}
    					$scope.arr = randomArr;
    				}).error(function(data,header,config,status){
    					console.log(header);
    				});
    		}
		});
		/*
		app.controller('ctrl', function($scope, $http) {
			$scope.tk = '24.57bdd55ce62204e1cc63be3192bc6275.2592000.1462956899.282335-7986971';
			$scope.timestamp = ''+new Date().getTime();
			$scope.url = 'http://tsn.baidu.com/text2audio?tex=abv&&lan=zh&cuid='
				+$scope.timestamp+'&ctp=1&tok='+$scope.tk;
			$scope.uri = 'http://tsn.baidu.com/text2audio';
			$scope.body = 'tex=abv&&lan=zh&cuid='+$scope.timestamp+'&ctp=1&tok='+$scope.tk;
			$scope.listen = function() {
				
				console.log(12);
			}
		});
		*/
		searchApp.controller('searchCtrl', function($scope) {
			$scope.submit = function() {
				if($scope.text) {
					window.open('https://www.baidu.com/s?wd='+$scope.text+"site:${site}");
				}
			}
		});
		angular.bootstrap(document.getElementById("recommendApp"), ['recommendApp']);
		angular.bootstrap(document.getElementById("searchApp"), ['searchApp']);
		//angular.bootstrap(document.getElementById("app"), ['app']);
		/*
		setInterval("myInterval()",1000);
		function myInterval(){
			window.frames["submitFrame"].document.getElementsByTagName("video");
		}*/
		console.log(encodeURI(encodeURI('zh')));
    </script>
    <script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?a9e3ff10ba849d11ea3a955e61e36607";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
    
  </body>
</html>