<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<meta name="mobile-agent" content="format=html5;url=http://www.2shui.com.cn/index.html">
  	<link rel="alternate" media="only screen and(max-width: 640px)" href="http://www.2shui.com.cn/index.html">
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8; charset=UTF-8" />
  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  	<title>2水(2SHUI.COM.CN)--水利万物而不争</title>
  	<link href="../css/content.css" rel="stylesheet" media="screen">
  	<link href="../css/content_public_new.css" rel="stylesheet" media="screen">
  	<link rel="canonical" href="/index.html"/>
  	<script type="text/javascript" src="http://${site}/js/angularjs-1.4.6.min.js"></script>
  	<script type="text/javascript" src="http://${site}/js/common.js"></script>
  	
  	<style>
		.h70{height:70px;}
		.con_left_ul li{width:300px;float:left;margin-right:10px;}
		.index_title li{background:#EEE;margin-top:5px;}
		.index_title li span{
			white-space:nowrap;display:inline-block;
			overflow:hidden;text-overflow:ellipsis;width:280px;}
		.index_title li div{
			text-overflow: -o-ellipsis-lastline;overflow: hidden;
			text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 2;
			-webkit-box-orient: vertical;}
		.index_title li div{background:#BDE3CA;overflow:hidden;height:50px;}
		.index_other{float:right;margin-right:30px;cursor:pointer;}
	</style>
  </head>
  <body>
  	<div class="header">
	    <div class="con">
	        <div class="nav">
	            <div class="Nav_list1"> <a rel="nofollow" href="/" >2水(2shui.com.cn)网首页</a> </div>
	            <div class="clear"></div>
	        </div>
	        <span class="a1"><a target="_blank" href="/map/">网站地图</a></span>
	        <div class="clear"></div>
	    </div>
	</div>
	<div class="con" style="height:80px;">广告</div>
	<div class="con">
		<div class="con_left">
			<div id="hotApp" ng-controller="hotCtrl">
			---------------------
			<div style="border-bottom:1px solid #CCC;">
				<b><span>热门文章</span></b>
				<span ng-click="other()" class="index_other">换一批 »</span>
			</div>
			<div style="height:250px;">
				<ul class="con_left_ul index_title h70">
					<li ng-repeat="article in arr">
						<span>
							<b>
								<a href="http://${site}/{{article.fileName}}.html" target='_blank'
									title="{{article.title}}">{{article.title}}</a>
							</b>
						</span>
					</li>
				
				
				<#list hotArticle as article>
				<#if article_index < 6>
					<li>
						<span>
							<b>
								<a href="http://${site}/${article.fileName}.html" target='_blank'
									title="${article.title}">${article.title}</a>
							</b>
						</span>
						<br/>
						<div>${article.content}</div>
					</li>
				</#if>
				</#list>
				</ul>
				<div class="clear"></div>
			</div>
			-----------------------------------
			</div>
			<div style="height:80px;">广告</div>
			<div style="border-bottom:1px solid #CCC;">
				<b><span>随机看</span></b>
				<span id="random" class="index_other">随一批 »</span>
			</div>
			<div>
				<ul class="con_left_ul index_title">
				<#list randomArticle as article>
					<li>
						<span><a href="http://${site}/${article.fileName}.html" target='_blank'
							title="${article.title}">${article.title}</a>
					</li>
				</#list>
				</ul>
			</div>
			<div>广告</div>
			<div class="clear"></div>
		</div>
		
		<!--右侧内容-->
		<div class=" con_right">
			<!-- @@@@@@@@  引入广告 -->
			<div>广告</div>
			<div class="hot_links">
				<h3>热门链接</h3>
				<ul>
				<#list hotWord as word>
					<li>
						<a target='_balnk' href='http://www.baidu.com/s?wd=${word}site:${site}'>
							${word}
						</a>
					</li>
				</#list>
				</ul>
				<div class="clear"></div>
			</div>
			<div>广告</div>
			<!--最热点击-->
			<div class="hot module">
				<h3>热点排行</h3>
				<div id="IndexTabs" class="IndexTabs">
					<!--
                    <ul class="TabTitle">
                        <li><a href="#IndexTabs-0">饮食</a></li>
                        <li><a href="#IndexTabs-1">偏方</a></li>
                    </ul>
					-->
					<div id="IndexTabs-0">
                        <ul class="tab-content tabCurrent">
                        <#list ranklistArticle as article>
                        	<li>
                        		<em>${article_index+1}</em>
                        		<p>
                        			<a rel='nofollow' href='http://${site}/${article.fileName}.html'
                        				 target='_blank' title='${article.title}'>
                        				${article.title}
                        			</a>
                        		</p>
                        	</li>
                        </#list>
						</ul>
                    </div>
                    <!--
                    <div id="IndexTabs-1">
                        <ul class="tab-content">
                            <li><em>1</em><p><a rel='nofollow' href='#' target='_blank' title=''>.. </a></p>
                            </li>
                        </ul>
                    </div>
                    -->
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
    	var hotNum = 0;
    	var hotArticle = new Array();
    	<#list hotArticle as article>
    		var article = {};
    		article.title = '${article.title}';
    		article.content = '${article.content?js_string}';
    		article.fileName = '${article.fileName}';
    		hotArticle[${article_index}] = article;
    	</#list>
    	console.log(hotArticle);
    	var hotApp = angular.module('hotApp',[]);
    	//var app2 = angular.module('app2', []);
    	
    	hotApp.controller('hotCtrl', function($scope) {
    		$scope.arr = hotArticle.slice(6*hotNum,6*(hotNum+1));
    		$scope.other = function() {
    			hotNum += 1;
    			hotNum %= 5;
    			$scope.arr = hotArticle.slice(6*hotNum,6*(hotNum+1));
    		}
		});
    	
    	angular.bootstrap(document.getElementById("hotApp"), ['hotApp']);
    	//angular.bootstrap(document.getElementById("app2"), ['app2']);
    </script>
  </body>
</html>