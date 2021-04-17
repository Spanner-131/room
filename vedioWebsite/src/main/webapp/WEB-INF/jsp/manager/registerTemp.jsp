<%@page pageEncoding="utf-8" contentType="text/html" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>注册表管理</title>
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
        <button class="button" id="search" onclick="searchBox()">查询</button>
        <button class="button" id="clear" onclick="clearBox()">重置</button>
    </div>
    <table id="registerBox" lay-filter="test1"></table>
</body>

<script type="text/html" id="registerBar">
    <%--    <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="admit" onclick="openRegisterAgree()">通过</button>--%>
    <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="admit">通过</button>
    <%--    <button class="layui-btn layui-btn-sm" lay-event="del" onclick="openRegisterDel()">删除</button>--%>
    <button class="layui-btn layui-btn-sm" lay-event="del">删除</button>
</script>
</html>