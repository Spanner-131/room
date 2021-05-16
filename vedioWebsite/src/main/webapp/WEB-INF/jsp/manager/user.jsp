<%@page pageEncoding="utf-8" contentType="text/html" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>用户管理</title>
    <script src="../static/js/jquery/jquery.min.js"></script>
    <script src="../static/js/layui/layui.js"></script>
    <link rel="stylesheet" href="../static/js/layui/css/layui.css">
    <script src="../static/js/manager/manager.js"></script>
    <link rel="stylesheet" href="../static/css/manager/search.css">
</head>
<body>
<div id="searchBox">
    <span class="sign">时间：</span><input class="condition" id="time"/>
    <span class="sign">学号：</span><input class="condition" id="code"/>
    <span class="sign">姓名：</span><input class="condition" id="realName"/>
    <button class="button" id="search" onclick="searchBox()">查询</button>
    <button class="button" id="clear" onclick="clearBox()">重置</button>
</div>
<table id="userBox" lay-filter="test3"></table>
</body>

<script type="text/html" id="userBar">
    <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="role">设定角色</button>
</script>
</html>