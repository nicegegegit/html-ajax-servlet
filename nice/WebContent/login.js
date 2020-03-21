/**
 * 
 */

var loginJS={
		init:function(){
			var me = this;
			console.log("login");
			me.refreshInfo();
		},
		refreshInfo:function(){
			setInterval(function(){
				$("#footerInfo").text(new Date());
			}, 100);
		}
}

$(function(){
	loginJS.init();
});