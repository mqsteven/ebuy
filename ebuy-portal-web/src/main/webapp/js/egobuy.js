var GEEK = {
	checkLogin : function(){
		var _ticket = $.cookie("EBUY-TOKEN");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://sso.ebuy.com/user/token/" + _ticket,
			dataType : "jsonp",//注明是使用jsonp请求。   而且要求服务器端支持jsonp请求
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "，欢迎来到易购购物网！<a href=\"http://sso.ebuy.com/user/logout\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	GEEK.checkLogin();
});