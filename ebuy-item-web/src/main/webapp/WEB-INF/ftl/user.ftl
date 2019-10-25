<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>这里是freemarker的测试</title>
<style>
table{width:500px;height:200px;border:1px solid;}
/*td{background-color:#eee;}*/
</style>
</head>
<body>
<p>
	<ul>
		<li>编号:${u.id}</li>
		<li>姓名:${u.name}</li>
		<li>电话:${u.tel}</li>
		<li>
			<#if score gte 85>
				优秀
			<#elseif score gte 70 && score lt 85>
				合格
			<#else>
				一顿爆揍
			</#if>
		</li>
	</ul>
	<table>
		<tr><th>序号</th><th>编号</th><th>姓名</th><th>电话</th></tr>
		<#list users as user>
			<#if user_index % 2 == 0>
				<tr style="background-color:#eee;">
			<#else>
				<tr>
			</#if>
			<td>${user_index}</td><td>${user.id}</td><td>${user.name}</td><td>${user.tel}</td></tr>
		</#list>
	</table>
	<ol>
	<li>当前时间:${now?date}</li>
	<li>当前时间:${now?time}</li>
	<li>当前时间:${now?datetime}</li>
	<li>当前时间:${now?string("yyyy年MM月dd日 HH:mm:ss")}</li>
	</ol>
	<p>Null值处理</p>
	<ol>
		<li>Null值：${addr!}</li>
		<li>Null值：${addr!"国家软件园"}</li>
		<li>
			判断一个值是否为null:
			<#if addr??>
				非空
			<#else>
				为空
			</#if>
		</li>
	</ol>
	<p>
		<#include "hello.ftl">
	</p>
</p>
</body>
</html>