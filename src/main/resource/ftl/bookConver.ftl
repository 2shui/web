<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<title>${name}-2水网(2SHUI.COM.CN)--做有态度的看客！</title>
  	<meta name="keywords" content="2水,2水网,读书,读书笔记，书评，有态度，看客，在线读书，伴读" />
	<meta name="description" content="2水网，做有态度的看客！读名著，写随笔，酒逢知己饮，诗向会人吟，三人行必有一伴读。" />
  	<meta name="viewport" content="width = 1050, user-scalable = no" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<script type="text/javascript" src="http://${site}/js/jquery.min.js"></script>
	<script type="text/javascript" src="http://${site}/js/modernizr.2.5.3.min.js"></script>
  
  	<style type="text/css">
  	#writeBox{
  		display:none;
  		top:0%;
  		left:0%;
  		width:100%;
  		height:100%;
  		background-color:rgba(160,160,160,0.5);
  		position:absolute;
  		filter: alpha(opacity=40);
  	}
  	.box_form{
  		background:#fff;
  		margin:15% auto;
  		width:300px;
  		border: 2px solid #000000;
  		-moz-border-radius: 15px; 
  		-webkit-border-radius: 15px; 
  		border-radius:15px;
  	}
  	.box_mark{
  		background-color:rgba(13, 82, 25, 0.21);
  		padding:10px;
  		-moz-border-radius: 15px 15px 0 0; 
  		-webkit-border-radius: 15px 15px 0 0; 
  		border-radius:15px 15px 0 0;
  	}
  	.box_write{
  		margin-bottom:10px;
  		text-align:right;
  	}
  	</style>
  </head>
  <body style="margin: 5px 20px;">
  	<div class="flipbook-viewport">
	<div class="container">
		<div class="flipbook">
		<#list 1..num as n>
			<div id="w${n}"></div>
		</#list>
		<#if 0==num/2>
			<div id="end">end</div>
		</#if>
		</div>
	</div>
</div>


<div id="writeBox">
	<form id="form">
		<div class="box_form">
		<div class="box_mark"><span id="box_tex"></span></div>
		<div class="box_write">
			<textarea name="note" placeholder="读书笔记" 
				style="margin:0 auto;width:300px;"></textarea>
			<input id="bookName" type="hidden" name="bookName" />
			<input id="bookNum" type="hidden" name="bookNum" />
			<input id="markText" type="hidden" name="markText" />
			<input type="button" value="留言" onclick="mark()" /></div>
		</div>
	</form>
</div>
<script type="text/javascript">

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
					
					load("w", page);
					if(pages > (page+1)){
						load("w", page+1);
					}
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

function load(prefix, index) {
	var html = $('#' + prefix + index).html();
	if(null == html || '' == html) {
		$.ajax({url: index + '.html'}).done(function(pageHtml) {
			$('#' + prefix + index).html(pageHtml);
		});
	}
}
load('w',1);
// Load the HTML4 version if there's not CSS transform
yepnope({
	test : Modernizr.csstransforms,
	yep: ['lib/turn.js'],
	nope: ['lib/turn.html4.min.js'],
	both: ['css/basic.css'],
	complete: loadApp
});

var markTxt,markNum;
var markBook = '${name}';
function openShare(txt, num) {
	var box = document.getElementById("writeBox");
	box.style.display = "block";
	box.style.zIndex = 999;
	var span = document.getElementById("box_tex");
	markTxt = txt;markNum = num;
	span.innerHTML = num+'#'+txt;
}
function mark() {
	$("#bookName").val(markBook);
	$("#bookNum").val(markNum);
	$("#text").val(markTxt);
	$.ajax({
		cache:true,
		type:"POST",
		headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
		url:"http://boot.2shui.com.cn/book/mark",
		data:$('#form').serialize(),
		async: false,
		error: function(request) {console.log(request);},
		success: function(data) {alert('ok');console.log(data);}
	});
}
</script>

</body>
</html>