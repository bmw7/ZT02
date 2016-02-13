$(document).ready(function(){
	
	// 若选择了 标题 类型，则取消提交
	$('#inputForm').submit(function(){
		var articleType = $('#articleCategoryIdAndType').val().split("and")[1];
		if(parseInt(articleType) == 0){
		    alert("未正确选择文章类型，请重新选择。");
			return false;
		}
	});
		
	 // 获取浏览器缩放比
     function detectZoom(){ 
  		var ratio = 0,
    	screen = window.screen,
    	ua = navigator.userAgent.toLowerCase();
 
   		if (window.devicePixelRatio !== undefined) {
      		ratio = window.devicePixelRatio;
  		}else if (~ua.indexOf('msie')) {  
    		if (screen.deviceXDPI && screen.logicalXDPI) {
      		ratio = screen.deviceXDPI / screen.logicalXDPI;
    		}
  	 	}else if (window.outerWidth !== undefined && window.innerWidth !== undefined) {
   		    ratio = window.outerWidth / window.innerWidth;
        }
        
   		return ratio;
	 };
	 
	// 适配不同分辨率 
	var ratio = detectZoom();
	//alert($.support.opera);
	//if($.browser.opera){ ratio = 1; }
	
	var screenWidth = window.screen.width*ratio;
	//alert(detectZoom());
	//alert(window.screen.width);
	switch(screenWidth){
		case 1920:
			$('.custom_body').addClass('w1920');
			break;
		case 1680:
			$('.custom_body').addClass('w1680');
			break;
		case 1600:
			$('.custom_body').addClass('w1600');
			break;
		case 1440:
			$('.custom_body').addClass('w1440');
			break;
		case 1400:
			$('.custom_body').addClass('w1400');
			break;
		case 1366:
			$('.custom_body').addClass('w1366');
			break;
		case 1360:
			$('.custom_body').addClass('w1360');
			break;
		case 1280:
			$('.custom_body').addClass('w1280');
			break;
		case 1152:
			$('.custom_body').addClass('w1152');
			break;
		case 1024:
			$('.custom_body').addClass('w1024');
			break;
	}
	
	// 根据网页可视区域高度调整编辑器高度
	var editorHeight = parseInt(parent.clientHeight() - 420);
	$('#editor').css('height',editorHeight);
	
});