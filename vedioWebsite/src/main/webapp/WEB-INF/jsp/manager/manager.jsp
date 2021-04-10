<%@page pageEncoding="utf-8" contentType="text/html" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>管理员</title>
    <script src="../static/js/jquery/jquery.min.js"></script>
    <script src="../static/js/layui/layui.js"></script>
    <link rel="stylesheet" href="../static/js/layui/css/layui.css">
    <script src="../static/js/manager/manager.js"></script>
</head>
<body>
<ul class="layui-nav">
    <li class="layui-nav-item"><a href="javascript:void(0)">注册管理</a></li>
    <li class="layui-nav-item"><a href="javascript:void(0)">评论管理</a></li>
    <li class="layui-nav-item"><a href="login">退出</a></li>
</ul>
    <div id="searchBox">
        <span>时间：</span><input id="time" width="120px"/>
        <span>学号：</span><input id="code" width="120px"/>
        <button id="search" onclick="">查询</button>
        <button id="clear" onclick="clearBox()">重置</button>
    </div>
    <table id="registerBox" lay-filter="test1"></table>
    <table id="commentBox" lay-filter="test2"></table>
</body>

<script type="text/html" id="registerBar">
<%--    <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="admit" onclick="openRegisterAgree()">通过</button>--%>
    <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="admit">通过</button>
<%--    <button class="layui-btn layui-btn-sm" lay-event="del" onclick="openRegisterDel()">删除</button>--%>
    <button class="layui-btn layui-btn-sm" lay-event="del">删除</button>
</script>

<script type="text/html" id="commentBar">
    <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="admit" onclick="openCommentAgree()">通过</button>
    <button class="layui-btn layui-btn-sm" lay-event="del" onclick="openCommentDel()">删除</button>
</script>

</html>