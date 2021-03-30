$(function () {
    // var id = sessionStorage.getItem('user');
    // if(id.match(/^\d{11}$/)){
    //     var userCode = id;
    // }else{
    //     var userName = id;
    // }
    var currentUserName = sessionStorage.getItem('currentUserName');
    var currentUserCode = sessionStorage.getItem('currentUserCode')
    $.ajax({
        url:'init',
        type:'get',
        dataType:'json',
        async:false,
        data:{
            'userCode':currentUserCode,
            'userName':currentUserName
        },
        success:function (user) {
            $('#userName').val(user.data.userName);
            $('#userCode').val(user.data.userCode);
            $('#mail').val(user.data.mail);
            $('#phone').val(user.data.phone);
            $('#realName').val(user.data.realName);
            $('#pwd').val(user.data.password);
        }
    })
})


// edit
function edit(){
    var userName = $('#userName').val();
    var password = $('#pwd').val();
    var phone = $('#phone').val();
    var mail = $('#mail').val();
    var realName = $('#realName').val();
    var userCode = $('#userCode').val();
            // 手机号验证
            if(phone.match(/^\d{11}$/)){
                //邮箱验证
                if(mail.match(/^\b[\w.%+-]+@[\w.-]+\.[a-zA-Z]{2,6}\b$/)){
                    if(password.length >= 6){
                        $.ajax({
                            url:'updateUser',
                            type:'post',
                            datatype:'json',
                            data:{
                                'userName':userName,
                                'password':password,
                                'phone':phone,
                                'mail':mail,
                                'realName':realName,
                                'userCode':userCode
                            },
                            success:function x(data) {
                                if(data.success == true){
                                    alert("修改成功");
                                }else{
                                    alert("修改失败");
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
}