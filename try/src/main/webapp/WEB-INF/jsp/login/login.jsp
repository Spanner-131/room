<!DOCTYPE html>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <script src="../static/js/jquery/jquery.min.js"></script>
    <script src="../static/js/login/login.js"></script>
</head>
<body>
<div class="center">
<%--    <p>用户名：<input name="userName" id="userName"/></p>--%>
<%--    <p>密码：<input name="pwd" id="pwd"/></p>--%>
<%--    <p><input type="text" id="checkCode" name="checkCode" placeholder="输入验证码"/><button id="makeCkCode" onclick="makeCheckCode()"></button></p>--%>
<%--    <button onclick="submit()">登入</button>--%>
    <script>
        // $(function () {
        //     makeCheckCode();
        // })
        // //验证码
        // function makeCheckCode(){
        //     var str = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        //     var validateCode = "";
        //     for(var i = 4;i > 0;i--){
        //         var pos = Math.floor(Math.random() * str.length);
        //         validateCode += str[pos];
        //     }
        //     document.getElementById('makeCkCode').innerHTML = validateCode;
        // }
        //
        // function submit(){
        //     var userName = $('#userName').val();
        //     var password = $('#pwd').val();
        //     var checkCode = $('#checkCode').val();
        //     var makeCkCode = $('#makeCkCode').text();
        //     if(checkCode == makeCkCode){
        //         $.ajax({
        //             url:'check',
        //             type:'post',
        //             datatype:'json',
        //             data:{
        //                 'userName':userName,
        //                 'password':password
        //             },
        //             success:function x(data) {
        //                 if(data == 1){
        //                     alert("登入成功");
        //                 }else{
        //                     alert("账号或密码错误");
        //                     makeCheckCode();
        //                 }
        //             }
        //         });
        //     }else{
        //         alert("验证码有误！");
        //         makeCheckCode();
        //     }
        // }
    </script>
</div>
<div>
    <p>用户名：<input name="userName" id="userName"/></p>
    <p>密码：<input name="pwd" id="pwd"/></p>
    <p>学号：<input name="userCode" id="userCode"/></p>
    <p>手机：<input name="phone" id="phone"/></p>
    <p>邮箱：<input name="mail" id="mail"/></p>
    <p><input type="text" id="checkCode" name="checkCode" placeholder="输入验证码"/><button id="makeCkCode">checkCode</button></p>
    <button onclick="submit()">注册</button>
    <script>

    </script>
</div>
</body>
</html>