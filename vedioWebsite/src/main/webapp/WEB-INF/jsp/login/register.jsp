<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>注册</title>
        <link href="../static/css/login/register.css" rel="stylesheet">
        <script src="../static/js/jquery/jquery.min.js"></script>
        <script src="../static/js/login/register.js"></script>
    </head>
    <body>
        <div class="registerBox">
            <div class="row">
                <label>用户名：</label><input class="inputBox" id="userName" type="text" required="required"/>
            </div>
            <div class="row">
                <label>姓名：</label><input class="inputBox" id="realName" type="text" required="required"/>
            </div>
            <div class="row">
                <label>密码：</label><input class="inputBox" id="pwd" type="password" required="required" minlength="6"/>
            </div>
            <div class="row">
                <label>学号：</label><input class="inputBox" id="userCode" type="text" required="required" maxlength="11" minlength="11"/>
            </div>
            <div class="row">
                <label>手机：</label><input class="inputBox" id="phone" type="text" maxlength="11" minlength="11"/>
            </div>
            <div class="row">
                <label>邮箱：</label><input class="inputBox" id="mail" type="email"/>
            </div>
            <div class="row">
                <label>验证码：</label><input type="text" id="checkCode" name="checkCode" placeholder="输入验证码"/><button id="makeCkCode" onclick="makeCheckCode()"></button>
            </div>
            <div class="row">
                <button id="register" onclick="register()">注册</button>
            </div>
            <div class="row">
                <a class="go" id="login" href="login">返回登录</a>
                <a class="go" id="homepage" href="homepage">主页</a>
            </div>
        </div>
    </body>
</html>