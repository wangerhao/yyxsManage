(function($){
	
	if(typeof $.scrollxutil == "undefined"){
		$.scrollxutil = {};
	}
	
	$.scrollxutil.scollx = function(p){
		var d = document, dd = d.documentElement, db = d.body, w = window, o = d.getElementById(p.id), ie6 = /msie 6/i.test(navigator.userAgent), style, timer;
		if (o) {
			o.style.cssText += ";position:" + (p.f && !ie6 ? 'fixed' : 'absolute')
					+ ";" + (p.l == undefined ? 'right:0;' : 'left:' + p.l + 'px;')
					+ (p.t != undefined ? 'top:' + p.t + 'px' : 'bottom:0');
			if (p.f && ie6) {
				o.style.cssText += ';left:expression(documentElement.scrollLeft + '
						+ (p.l == undefined ? dd.clientWidth - o.offsetWidth : p.l)
						+ ' + "px");top:expression(documentElement.scrollTop +'
						+ (p.t == undefined ? dd.clientHeight - o.offsetHeight
								: p.t) + '+ "px" );';
				dd.style.cssText += ';background-image: url(about:blank);background-attachment:fixed;';
			} else {
				if (!p.f) {
					w.onresize = w.onscroll = function() {
						clearInterval(timer);
						timer = setInterval(function() {
								//双选择为了修复chrome 下xhtml解析时dd.scrollTop为 0 
								var st = (dd.scrollTop || db.scrollTop), c;
								c = st
										- o.offsetTop
										+ (p.t != undefined ? p.t
												: (w.innerHeight || dd.clientHeight)
														- o.offsetHeight);
								if (c != 0) {
									o.style.top = o.offsetTop
											+ Math.ceil(Math.abs(c) / 10)
											* (c < 0 ? -1 : 1) + 'px';
								} else {
									clearInterval(timer);
								}
							}, 10)
					}
				}
			}
		}
	}
	
	/** 滚动条是否达到底部 */
	$.scrollxutil.reachBottom = function(){
		var scrollTop = 0;
	    var clientHeight = 0;
	    var scrollHeight = 0;
	    if (document.documentElement && document.documentElement.scrollTop) {
	        scrollTop = document.documentElement.scrollTop;
	    } else if (document.body) {
	        scrollTop = document.body.scrollTop;
	    }
	    if (document.body.clientHeight && document.documentElement.clientHeight) {
	        clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight: document.documentElement.clientHeight;
	    } else {
	        clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight: document.documentElement.clientHeight;
	    }
	    scrollHeight = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
	    if (scrollTop + clientHeight == scrollHeight) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	/** 滚动条是否达到顶部 */
	$.scrollxutil.reachTop = function(){
		var scrollTop = 0;
		if (document.documentElement && document.documentElement.scrollTop) {
	        scrollTop = document.documentElement.scrollTop;
	    } else if (document.body) {
	        scrollTop = document.body.scrollTop;
	    }
	    if (scrollTop <= 4) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	//取页面内容实际高度
	$.scrollxutil.getScrollHeight = function(){
		return Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);  
	}
})(jQuery);
$(function(){
	
	/** 如果页面高度大于1000 ，滚动按钮显示 */
	if($.scrollxutil.getScrollHeight() > 1000){
		$("#scorllxDiv").show();
	}
	
	/**
	 * 浮动设置（兼容所有游览器）
	 * id 你要滚动的内容的id 
	 * l 横坐标的位置 不写为紧贴右边 
	 * t 你要放在页面的那个位置默认是贴着底边 0是贴着顶边 
	 * f 1表示固定 不写或者0表示滚动 
	 */
	$.scrollxutil.scollx({
		id : 'scorllxDiv',
		//r : 30,
		//t : 600,
		f : 1
	})
	
	/** 锚链接上下滚动,时间设为1秒结束整个滚动 */
    $("#scrollxBtn").click(function(){
        var hr = $(this).attr("lang");
        var anh = $(hr).offset().top;
        $("html,body").stop().animate({scrollTop:anh},1000);
    })
    
    /** 滚动条到底部后和到顶部后处理 */
    $(window).bind("scroll", function(){ 
    	if($.scrollxutil.reachTop()){
			$("#scrollxBtn").attr("class","glyphicon glyphicon-arrow-down");
        	$("#scrollxBtn").attr("lang","#pageBottom")
		}
        if($.scrollxutil.reachBottom()){
        	$("#scrollxBtn").attr("class","glyphicon glyphicon-arrow-up");
        	$("#scrollxBtn").attr("lang","#pageTop");
        }
	}); 
})