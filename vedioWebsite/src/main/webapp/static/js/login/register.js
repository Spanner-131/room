// init
$(function () {
    initInputbox();
    makeCheckCode();
})

function initInputbox() {
    $('#userName').val('');
    $('#realName').val('');
    $('#pwd').val('');
    $('#userCode').val('');
    $('#phone').val('');
    $('#mail').val('');
    $('#checkCode').val('');
}

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
    var realName = $('#realName').val();
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
                                'realName':realName,
                                'password':password,
                                'userCode':userCode,
                                'phone':phone,
                                'mail':mail
                            },
                            success:function x(AjaxJson) {
                                    alert(AjaxJson.message);
                                    if(!AjaxJson.success){
                                        alert(AjaxJson.message);
                                        makeCheckCode();
                                        $('#checkCode').val('');
                                    }else{
                                        window.location.href = 'homepage';
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