<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<title>${title}-2水网(2SHUI.COM.CN)--做有态度的看客！</title>
  	<meta name="keywords" content="2水,2水网,读书,读书笔记，书评，有态度，看客，在线读书，伴读" />
	<meta name="description" content="2水网，做有态度的看客！读名著，写随笔，酒逢知己饮，诗向会人吟，三人行必有一伴读。" />
  	<meta name="viewport" content="width = 1050, user-scalable = no" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<script type="text/javascript" src="${site}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${site}/js/modernizr.2.5.3.min.js"></script>
  </head>
  <body style="margin: 5px 20px;">
  	<div class="flipbook-viewport">
	<div class="container">
		<div class="flipbook">
			<div id="w1"><iframe id="i1" src="thelittleprince_1.html" style="width:100%;height:100%;background:#333333;" scrolling="no" frameborder="0" ></iframe></div>
			<div id="w2"><iframe id="i2" src="#" style="width:100%;height:100%;" scrolling="no" frameborder="0" ></iframe></div>
			<div id="w3"><iframe id="i3" src="#" style="width:100%;height:100%;" scrolling="no" frameborder="0" ></iframe></div>
			<div id="w4"><iframe id="i4" src="#" style="width:100%;height:100%;" scrolling="no" frameborder="0" ></iframe></div>
			<div id="w5"><iframe id="i5" src="#" style="width:100%;height:100%;" scrolling="no" frameborder="0" ></iframe></div>
			<div id="w6"><iframe id="i6" src="#" style="width:100%;height:100%;" scrolling="no" frameborder="0" ></iframe></div>
			<div id="w7"><iframe id="i7" src="#" style="width:100%;height:100%;" scrolling="no" frameborder="0" ></iframe></div>
			<div id="w8"><iframe id="i8" src="#" style="width:100%;height:100%;" scrolling="no" frameborder="0" ></iframe></div>
			<div id="w9"><iframe id="i9" src="#" style="width:100%;height:100%;" scrolling="no" frameborder="0" ></iframe></div>
			<div id="w10"><iframe id="i10" src="#" style="width:100%;height:100%;" scrolling="no" frameborder="0" ></iframe></div>
			
		</div>
	</div>
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
					
					load("i", "w", page);
					if(pages > (page+1)){
						load("i", "w", page+1);
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

function load(prefix, parentPre, index) {
	if($("#"+prefix+index).attr("src") != "thelittleprince_"+index+".html") {
		$("#"+prefix+index).attr("src","thelittleprince_"+index+".html");
	}
}

// Load the HTML4 version if there's not CSS transform
yepnope({
	test : Modernizr.csstransforms,
	yep: ['lib/turn.js'],
	nope: ['lib/turn.html4.min.js'],
	both: ['css/basic.css'],
	complete: loadApp
});
</script>

</body>
</html>