<%@page pageEncoding = "utf-8" contentType="text/html" %>
<html>
    <head>
        <title>设置角色</title>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="../../static/css/public.css"/>
        <link rel="stylesheet" href="../../static/css/manager/setRole.css"/>
        <script type="text/javascript" src="../../static/js/jquery/jquery.form.js"></script>
    </head>
    <body>
<%--        <form id="infoForm" action="/sspu/campus/updateRole" method="post">--%>
        <form id="infoForm">
            <input class="dn" name="id" value="${id}"/>
            <select class="db" name="role">
                <option selected="selected" value="0"></option>
                <option value="0">用户</option>
                <option value="1">管理员</option>
            </select>
<%--            <input class="db" type="submit" value="确定"/>--%>
        </form>
    </body>
</html>