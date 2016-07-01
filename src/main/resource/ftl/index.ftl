<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<meta name="mobile-agent" content="format=html5;url=http://www.2shui.com.cn/index.html">
  	<link rel="alternate" media="only screen and(max-width: 640px)" href="http://www.2shui.com.cn/index.html">
  	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8; charset=UTF-8" />
  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  	<title>2水(2SHUI.COM.CN)--水利万物而不争</title>
  	<meta name="keywords" content="2水,2水网,养生,菜谱,美食,食疗,保健,健康养生,各地菜谱,创意美食,心理养生,女人养生,房事养生,经络养生,保健知识,中医养生,运动养生" />
	<meta name="description" content="2水网，致力于美食及养生健康知识的普及，推进养生理念的更新。2水网，2SHUI.COM.CN" />
  	<link href="http://${site}/css/content.css" rel="stylesheet" media="screen">
  	<link href="http://${site}/css/content_public_new.css" rel="stylesheet" media="screen">
  	<link rel="canonical" href="/index.html"/>
  	<script type="text/javascript" src="http://${site}/js/angular.min.js"></script>
  	<script type="text/javascript" src="http://${site}/js/common.js"></script>
  	<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=850480782" type="text/javascript" charset="utf-8"></script>
  	<script type="text/javascript">var adgs = new Array();
    	var adcs = new Array();<#list adcs as add>adcs[${add_index}] = ${add};</#list>adcs.sort(randomsort);
    </script>
  </head>
  <body>
  	<div class="header">
	    <div class="con">
	        <div class="nav">
	            <div class="Nav_list1"> <a rel="nofollow" href="/" >2水(2shui.com.cn)网首页</a> </div>
	            <div class="clear"></div>
	        </div>
	        <span class="a1">
	        	<wb:login-button type="3,5" onlogin="login" style="float:right;margin:5px 0;" onlogout="logout">登录按钮</wb:login-button>
	        	<!--
	        	<span id="associator" style="float:right;margin:5px 0;font-size:12px;">
					<a href="http://boot.test2shui.com.cn/login/weibo">
						<img src="http://timg.sjs.sinajs.cn/t4/appstyle/widget/images/loginButton/loginButton_18.png">
					</a>
				</span>
				-->
	        	<form novalidate name="search" ng-submit="submit()" ng-controller="searchCtrl" id="searchApp">
					<input type="text" ng-model="text" placeholder="请输入搜索关键词" required />
					<button type="submit" class="BTN">搜索</button>
				</form>
	        	<a target="_blank" href="/map.html">网站地图</a>
	        </span>
	        <div class="clear"></div>
	    </div>
	</div>
	<div class="con" style="height:90px;">
	<script type="text/javascript">var jd_union_unid="1000034163",jd_ad_ids="505:6",jd_union_pid="CM/SpNzFKhDznu3cAxoAIOPa9cMBKgA=";var jd_width=960;var jd_height=90;var jd_union_euid="";var p="BxIGXB1ZFQsVNwpfBkgyTUMIRmtKRk9aZV8ETVxNNwpfBkgyZUU1XTsWXkhnN08hFXVLDzROK10DRAtZK14VAxQPVhtdHTISBlQaWhcHEgBXK2tKRk9aZVA1FDJNQwhGaxUHFQdcGF4RChUFVB5rFA%3D%3D";</script>
	<script type="text/javascript" charset="utf-8" src="http://u.x.jd.com/static/js/auto.js"></script>
	</div>
	<div class="con">
		<div class="con_left">
			<div id="hotApp" ng-controller="hotCtrl">
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
							<br/>
							<div>{{article.content}}</div>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
			</div>
			<div>
			<script type="text/javascript">var jd_union_unid="1000034163",jd_ad_ids="1470:6",jd_union_pid="CIqV3N/FKhDznu3cAxoAILna8MMBKgA=";
			var jd_width=629;var jd_height=144;var jd_union_euid="";
			var p="BxIGUxNbFgEXNwpfBkgyTUMIRmtKRk9aZV8ETVxNNwpfBkgyQVI%2BcjlhakBiHX0NcQQWeA14DGMYYgtZK14VAxQPVhtdHTISBlQaWhcHEgBXK2tKRk9aZVA1FDJNQwhGaxUHFQdcGVsTChQFXB1rFA%3D%3D";</script>
			<script type="text/javascript" charset="utf-8" src="http://u.x.jd.com/static/js/auto.js"></script>
			</div>
			<div id="randomApp" ng-controller="randomCtrl">
				<div style="border-bottom:1px solid #CCC;">
					<b><span>随机看</span></b>
					<span ng-click="other()" class="index_other">随一批 »</span>
				</div>
				<div>
					<ul class="con_left_ul index_title">
						<li ng-repeat="article in arr">
							<span><a href="http://${site}/{{article.fileName}}.html" target='_blank'
								title="{{article.title}}">{{article.title}}</a>
						</li>
					</ul>
				</div>
			</div>
			<div>
			<script type="text/javascript">var jd_union_unid="1000034163",jd_ad_ids="1470:6",jd_union_pid="CIKq6N/FKhDznu3cAxoAIMTq8MMBKgA=";
			var jd_width=629;var jd_height=144;var jd_union_euid="";
			var p="BxIGUxNbFgEXNwpfBkgyTUMIRmtKRk9aZV8ETVxNNwpfBkgyCAYhbxpQS29lVmFdYl5UegtdGnBZVAtZK14VAxQPVhtdHTISBlQaWhcHEgBXK2tKRk9aZVA1FDJNQwhGaxUHFQdcGVsdChUAUx9rFA%3D%3D";</script>
			<script type="text/javascript" charset="utf-8" src="http://u.x.jd.com/static/js/auto.js"></script>
			</div>
			<div class="clear"></div>
		</div>
		
		<!--右侧内容-->
		<div class=" con_right">
			<div>
			</div>
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
			<div>
			<script type="text/javascript">var jd_union_unid="1000034163",jd_ad_ids="513:6",jd_union_pid="CMG19d/FKhDznu3cAxoAIOXS8MMBKgA=";var jd_width=300;var jd_height=250;var jd_union_euid="";var p="BxIGUxNaFgcXNwpfBkgyTUMIRmtKRk9aZV8ETVxNNwpfBkgyRlMWcAZvSRZlVFsaamNNHQcaHlRwYgtZK14VAxQPVhtdHTISBlQaWhcHEgBXK2tKRk9aZVA1FDJNQwhGaxUHFQdcGVgWAhIHVxJrFA%3D%3D";</script>
			<script type="text/javascript" charset="utf-8" src="http://u.x.jd.com/static/js/auto.js"></script>
			</div>
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
			<div><!-- adv -->
			</div>
		</div>
		
	</div>
	
	<div class="clear"></div>
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
    		article.content = '${article.content?substring(0,50)?js_string}';
    		article.fileName = '${article.fileName}';
    		hotArticle[${article_index}] = article;
    	</#list>
    	var randomArr = new Array();
    	<#list randomArticle as article>
    		var article = {};
    		article.title = '${article.title}';
    		article.fileName = '${article.fileName}';
    		randomArr[${article_index}] = article;
    	</#list>
    	var hotApp = angular.module('hotApp',[]);
    	var randomApp = angular.module('randomApp', []);
    	var searchApp = angular.module('searchApp', []);
    	hotApp.controller('hotCtrl', function($scope) {
    		$scope.arr = hotArticle.slice(6*hotNum,6*(hotNum+1));
    		$scope.other = function() {
    			hotNum += 1;
    			hotNum %= 5;
    			$scope.arr = hotArticle.slice(6*hotNum,6*(hotNum+1));
    		}
		});
		randomApp.controller('randomCtrl', function($scope, $http) {
    		$scope.arr = randomArr;
    		$scope.other = function() {
    			$http.jsonp('http://${sld}/page/uncertain?callback=JSON_CALLBACK')
    				.success(function(data){
    					randomArr = [];
    					for(var i=0;i<data.length;i++){
    						var article = {};
    						article.fileName = data[i].fileName;
    						article.title = data[i].title;
    						randomArr[i] = article;
    					}
    					$scope.arr = randomArr;
    				}).error(function(data,header,config,status){
    					console.log(header);
    				});
    		}
		});
		searchApp.controller('searchCtrl', function($scope) {
			$scope.submit = function() {
				if($scope.text) {
					window.open('https://www.baidu.com/s?wd='+$scope.text+"site:${site}");
				}
			}
		});
    	angular.bootstrap(document.getElementById("hotApp"), ['hotApp']);
    	angular.bootstrap(document.getElementById("randomApp"), ['randomApp']);
    	angular.bootstrap(document.getElementById("searchApp"), ['searchApp']);
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