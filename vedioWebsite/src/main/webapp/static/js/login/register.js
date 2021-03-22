// init
$(function () {
    makeCheckCode();
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
function register(){
    var userName = $('#userName').val();
    var password = $('#pwd').val();
    var userCode = $('#userCode').val();
    var phone = $('#phone').val();
    var mail = $('#mail').val();
    var checkCode = $('#checkCode').val();
    var makeCkCode = $('#makeCkCode').text();
    if(checkCode == makeCkCode){
        // 学号验证
        if(userCode.match(/^\d{11}$/)){
            // 手机号验证
            if(phone.match(/^\d{11}$/)){
                //邮箱验证
                if(mail.match(/^\b[\w.%+-]+@[\w.-]+\.[a-zA-Z]{2,6}\b$/)){
                    if(password.length >= 6){
                        $.ajax({
                            url:'addUser',
                            type:'post',
                            datatype:'json',
                            data:{
                                'userName':userName,
                                'password':password,
                                'userCode':userCode,
                                'phone':phone,
                                'mail':mail
                            },
                            success:function x(data) {
                                if(data != 0){
                                    alert("注册成功");
                                }else{
                                    alert("注册失败");
                                    makeCheckCode();
                                    $('#checkCode').val("");
                                }
                            }
                        });
                    }else{
                        alert('密码输入最少为6位有效字符');
                    }
                }else{
                    alert('邮箱输入有误！');
                }
            }else{
                alert('手机号输入有误！');
            }
        }else{
            alert('学号输入有误！');
            // 此处学号不用清空
        }
    }else{
        alert("验证码有误！");
        makeCheckCode();
        $('#checkCode').val("");
    }
}