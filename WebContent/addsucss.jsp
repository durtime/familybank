<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>success</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="layuimini/lib/layui-v2.5.5/css/layui.css" media="all">
		<link rel="stylesheet" href="layuimini/css/public.css" media="all">
		<script src="layuimini/lib/layui-v2.5.5/layui.js" type="text/javascript"></script>			
		</script>
		    <style>
	    div {
	    	text-align: center;
	    	font-size: larger;
	    }
	    a {
	    	color: black;
	    	text-decoration: none;
	    	font-size: larger;
	    }
	    
    </style>
	</head>
<body>
		<script type="text/javascript">
		layui.use('layer', function () {
				layer = layui.layer;
				
				layer.open({
					  title: '添加ok'
					  ,content: '\(^o^)/~'
					});     
					  
		});
		</script>
		<br>

		<div>		<a href="newshouru.html">在收入一笔点这里</a></div>
		<br>
		<br>
				<div>		<a href="newzhichu.html">在支出一笔点这里</a></div>
</body>
</html>