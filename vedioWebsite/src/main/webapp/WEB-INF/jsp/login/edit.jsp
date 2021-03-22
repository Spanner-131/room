<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>登录</title>
        <script src="../static/js/jquery/jquery.min.js"></script>
        <script src="../static/js/login/edit.js"></script>
    </head>
    <body>
        <div class="editBox">
            <div class="row">
                <label>用户名：</label><input id="userName" type="text" required="required"/>
            </div>
            <div class="row">
                <label>名字：</label><input id="realName" type="text" required="required" readonly="readonly"/>
            </div>
            <div class="row">
                <label>密码：</label><input id="pwd" type="password" required="required" minlength="6"/>
            </div>
            <div class="row">
                <label>学号：</label><input id="userCode" type="text" required="required" maxlength="11" minlength="11" readonly="readonly"/>
            </div>
            <div class="row">
                <label>手机：</label><input id="phone" type="text" maxlength="11" minlength="11"/>
            </div>
            <div class="row">
                <label>邮箱：</label><input id="mail" type="email"/>
            </div>
            <div class="row">
                <button onclick="edit()">保存</button>
            </div>
        </div>
    </body>
</html>