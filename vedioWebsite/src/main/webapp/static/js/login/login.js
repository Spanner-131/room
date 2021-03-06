// init
$(function () {
    makeCheckCode();
    $('input').val("");
})

// checkcode
function makeCheckCode(){
    var str = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    var validateCode = "";
    for(var i = 4;i > 0;i--){
        var pos = Math.floor(Math.random() * str.length);
        validateCode += str[pos];
    }
    document.getElementById('makeCkCode').innerHTML = validateCode;
}

// submit
function submit(){
    var id = $('#id').val();
    // 正则判断用户名||学号
    if(id.match(/^\d{11}$/)){
        var userCode = id;
    }else{
        var userName = id;
    }
    var password = $('#pwd').val();
    var checkCode = $('#checkCode').val();
    var makeCkCode = $('#makeCkCode').text();
    if(checkCode == makeCkCode){
        $.ajax({
            url:'enter',
            type:'post',
            datatype:'json',
            data:{
                'userName':userName,
                'userCode':userCode,
                'password':password
            },
            success:function x(data) {
                if(data == 1){
                    alert("登入成功");
                    // 有空自己弄下shiro
                    if(userName != "" && userName != null){
                        sessionStorage.setItem('currentUserName',userName);
                    }else if(userCode !=null && userCode != ""){
                        sessionStorage.setItem('currentUserCode',userCode);
                    }
                    window.location.href = 'homepage';
                }else if(data == 2){
                    window.location.href = 'manager';
                }else if(data == 0){
                    alert("账号或密码错误");
                    makeCheckCode();
                    $('#checkCode').val("");
                }
            }
        });
    }else{
        alert("验证码有误！");
        makeCheckCode();
        $('#checkCode').val("");
    }
}