<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>登录</title>
        <link href="${pageContext.request.contextPath}/static/css/login/login.css" type="text/css" rel="stylesheet">
        <script src="../static/js/jquery/jquery.min.js"></script>
        <script src="../static/js/login/login.js"></script>
    </head>
    <body>
        <div class="loginBox">
            <div class="row">
                <label>账户：</label><input id="id" type="text" placeholder="用户名/学号"/>
            </div>
            <div class="row">
                <label>密码：</label><input id="pwd" type="password"/>
            </div>
            <div class="row">
                <label>验证码：</label><input type="text" id="checkCode" name="checkCode" placeholder="输入验证码"/><button id="makeCkCode" onclick="makeCheckCode()"></button>
            </div>
            <div class="row">
                <button onclick="submit()">登入</button>
            </div>
            <%--<div class="row">--%>
            <%--<a href="register">注册Go!</a>--%>
            <%--</div>--%>
        </div>
    </body>
</html>