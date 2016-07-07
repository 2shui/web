function loadApp() {
	// Create the flipbook
	$('.flipbook').turn({
			// Width
			width:922,
			// Height
			height:600,
			// Elevation
			elevation: 50,
			// Enable gradients
			gradients: true,
			// Auto center this flipbook
			autoCenter: true,
			when: {
				turning: function(e, page, view) {
					var book = $(this),
					currentPage = book.turn('page'),
					pages = book.turn('pages');
					load("w",name, page);
					if(pages > (page+1)){
						load("w",name, page+1);
					}
					setTimeout(interested, 5000, name, page);
				}
			}
	});
	$(document).keydown(function(e){
		var previous = 37, next = 39;
		switch (e.keyCode) {
			case previous:
				$('.flipbook').turn('previous');
				break;
			case next:
				$('.flipbook').turn('next');
				break;
		}
	});
	var flipbook = $('.flipbook');
	flipbook.bind(($.isTouch) ? 'touchend' : 'click', zoomHandle);
}

function load(prefix, bookName, index) {
	var html = $('#' + prefix + index).html();
	if(null == html || '' == html) {
		$.ajax({url: bookName + '_' + index + '.html?s='+new Date().getTime()}).done(function(pageHtml) {
			$('#' + prefix + index).html(pageHtml);
		});
	}
}
load('w',name, 1);
// Load the HTML4 version if there's not CSS transform
yepnope({
	test : Modernizr.csstransforms,
	yep: [uul+'/js/turn.js'],
	nope: [uul+'/js/turn.html4.min.js'],
	both: [uul+'/css/basic.css'],
	complete: loadApp
});

var markTxt,markNum;
var markBook = name;
function n(num){markNum=num;}
function openShare(txt) {
	var box = document.getElementById("writeBox");
	box.style.display = "block";
	box.style.zIndex = 999;
	var span = document.getElementById("box_tex");
	markTxt = txt;
	span.innerHTML = txt;
}
function login(){
	logout();
	document.getElementById("writeBox").style.display = 'none';
	document.getElementById("login").style.display = 'inline';
}
function associator() {
	var name = getCookie('associator_name');
	//name = decodeURI(name);
	if(null!=name) {
		$("#associator").html(name+"<a style='color:#999;margin-left:5px;' href='javascript:logout();'>退出</span>");
	}
}
function mark() {
	$("#bookName").val(markBook);
	$("#bookNum").val(markNum);
	$("#markText").val(markTxt);
	$.ajax({
		cache:true,
		type:"POST",
		headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
		url:uri+"/book/mark",
		data:$('#form').serialize(),
		async: false,
		dataType: 'jsonp',
		xhrFields:{withCredentials:true},
		crossDomain:true,
		error: function(request) {console.log(request);},
		success: function(data) {
			console.log(data);
			if('nologin' == data.sign) {
				login();
			} else {
				alert("笔记添加成功！");
			}
			$("#writeBox").hide();
		}
	});
}

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
		var txt = funGetSelectTxt();
		//, title = (eleTitle && eleTitle.innerHTML)? eleTitle.innerHTML : "未命名页面";
		openShare(txt);
		eleShare.style.display = "none";
	};
};
function logout() {
var exp = new Date();exp.setTime(exp.getTime() - 1);var cval=getCookie('associator_name');
if(cval!=null)document.cookie= name + "associator_name="+cval+";expires="+exp.toGMTString();
var h="<a style='text-decoration:none;' href='http://boot.2shui.com.cn/login/qq'>" +
		"<img src='http://img.2shui.com.cn/qq.png'></a>"+
		"<a style='text-decoration:none;' href='http://boot.2shui.com.cn/login/weibo'>" +
		"<img src='http://img.2shui.com.cn/weibo.png'></a>";
$("#associator").html(h);
}
$sinaMiniBlogShare(document.getElementById("imgMark"));
associator();
var times;
function interested(name, page){
	times = Math.ceil(Math.random()*3);
	if(0 == times%3){
		$.ajax({url:uri+"/book/interested",
			async:false,
			dataType: 'jsonp',
			xhrFields:{withCredentials:true},
			crossDomain:true,
			success:function(data){
				if('nologin' == data.sign) {}else{
					$("#share_box").attr('class', 'share_box');
					$("#share_inner").attr('class', 'share_inner');
					$("#share_inner").html(data.msg+'<br/>--<b>'+data.user+'</b>');
				}
				console.log(data);
				console.log(data.sign);
		}});
	}
}