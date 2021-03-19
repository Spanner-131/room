$(function () {
    makeCheckCode();
})
//验证码
function makeCheckCode(){
    var str = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    var validateCode = "";
    for(var i = 4;i > 0;i--){
        var pos = Math.floor(Math.random() * str.length);
        validateCode += str[pos];
    }
    document.getElementById('makeCkCode').innerHTML = validateCode;
}

function submit(){
    var userName = $('#userName').val();
    var password = $('#pwd').val();
    var checkCode = $('#checkCode').val();
    var makeCkCode = $('#makeCkCode').text();
    if(checkCode == makeCkCode){
        $.ajax({
            url:'check',
            type:'post',
            datatype:'json',
            data:{
                'userName':userName,
                'password':password
            },
            success:function x(data) {
                if(data == 1){
                    alert("登入成功");
                }else{
                    alert("账号或密码错误");
                    makeCheckCode();
                }
            }
        });
    }else{
        alert("验证码有误！");
        makeCheckCode();
    }
}