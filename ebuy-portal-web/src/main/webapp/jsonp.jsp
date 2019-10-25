<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>jsonp测试</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		console.log($.cookie("EBUY-TOKEN"));
		var token = $.cookie("EBUY-TOKEN");
		//发送ajax请求获取用户信息
		$.get("http://sso.ebuy.com/user/token/"+token,function(data){
			console.log(data);
		});
	});
</script>
</head>
<body>
测试
</body>
</html>