$(function(){
    init();
    $('#time').val('');
    $('#code').val('');
    // var winHeight = $(window).height();
    // $('body').attr('style','height:'+ winHeight);
})

/**
* 表格初始话
* */
function init(startTime,endTime,userCode,realName) {
    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: "#registerBox",
            height: 500,
            url: '/sspu/campus/registerBox',
            where:{
              "startTime":startTime,
              "endTime":endTime,
              "userCode":userCode
            },
            page: true, //分页
            cols: [[
                {checkbox: true},
                {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left', hide: true},
                {field: 'userName', title: '用户名', width: 80},
                {field: 'realName', title: '姓名', width: 80},
                {field: 'userCode', title: '学号', width: 120},
                {field: 'phone', title: '手机号', width: 120},
                {field: 'mail', title: '邮箱', width: 180},
                {field: '', title: '操作', toolbar: '#registerBar', width: 150}
            ]]
        });

        table.on('tool(test1)',function(obj){
            var data = obj.data;
            var layEvent = obj.event;

            if(layEvent === 'admit'){
                    layer.confirm('确认通过吗？',function(index){
                        var res = agreeRegister(data);
                        if(res){
                            obj.del()
                            layer.close(index);
                            alert('用户注册通过');
                            // delRegister(data.id);
                        }else{
                            layer.close(index);
                            alert('通过失败，请重新操作');
                        }
                    })
            }else if(layEvent === 'del'){
                layer.confirm('确认删除吗？',function(index){
                    var res = delRegister(data.id);
                    if(res){
                        obj.del();
                        layer.close(index);
                        alert('删除成功！');
                    }else{
                        layer.close(index);
                        alert('删除失败！');
                    }
                })
            }
        });

        table.render({
            elem: "#commentBox",
            height: 500,
            url: "/sspu/campus/commentBox",
            where:{
              "startTime":startTime,
              "endTime":endTime,
              "userCode":userCode
            },
            page: true,
            cols: [[
                {checkbox: true},
                {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left', hide: true},
                {field: 'userCode', title: '学号', width: 120},
                {field: 'videoId', title: '视频号', width: 80},
                {field: 'content', title: '评论内容', width: 900},
                {field: '', title: '操作', toolbar: '#commentBar', width: 150}
            ]]
        })

        table.on('tool(test2)',function(obj){
            var data = obj.data;
            var layEvent = obj.event;

            if(layEvent === 'admit'){
                layer.confirm('确认通过吗？',function(index){
                    var res = agreeComment(data);
                    if(res){
                        obj.del();
                        layer.close(index);
                        alert('评论通过！');
                        // delComment(data.id);
                    }else{
                        layer.close(index);
                        alert('评论通过失败，请重新操作');
                    }
                })
            }else if(layEvent === 'del'){
                layer.confirm('确认删除吗？',function(index){
                    var res = delComment(data.id);
                    if(res){
                        obj.del();
                        layer.close(index);
                        alert('删除成功！');
                    }else{
                        layer.close(index);
                        alert('删除失败！');
                    }
                })
            }
        });

        table.render({
            elem: '#userBox',
            height: 500,
            url: 'userBox',
            where: {
                "startTime": startTime,
                "endTime": endTime,
                "userCode": userCode,
                "realName": realName
            },
            page: true,
            cols: [[
                {checkbox: true},
                {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left', hide: true},
                {field: 'userName', title: '用户名', width: 80},
                {field: 'realName', title: '姓名', width: 80},
                {field: 'userCode', title: '学号', width: 120},
                {field: 'phone', title: '手机号', width: 120},
                {field: 'mail', title: '邮箱', width: 180},
                {field: 'role',title: '身份',width: 100},
                {field: '', title: '操作', toolbar: '#userBar', width: 120}
            ]]
        })

        table.on('tool(test3)',function(obj) {
            var data = obj.data;
            var layEvent = obj.event;
            if(layEvent = "role") {
                layer.open({
                    type: 2,
                    area:['20%','20%'],
                    title: '用户权限',
                    content: 'setRole/' + data.id,
                    btn:['确认','取消'],
                    yes: function(index,layero){
                        var form = $(window.frames["layui-layer-iframe" + index].document).contents().find("#infoForm");
                        form.ajaxSubmit({
                            url:'/sspu/campus/updateRole',
                            type: 'post',
                            dataType: 'json',
                            success: function(AjaxJson){
                                layer.close(index);
                                alert(AjaxJson.message);
                            }
                        })
                    },
                    btn2: function (index) {
                        layer.close(index)
                    }
                })
            }
        })
    })
}

layui.use('laydate',function () {
    var laydate = layui.laydate;

    laydate.render({
        elem:'#time',
        type:'date'
    })
})

function searchBox(){
    var createTime = $('#time').val();
    var userCode = $('#code').val();
    var realName = $('#realName').val();
    var startTime = "";
    var endTime = "";
    if(createTime != ""){
        startTime = createTime + " 00:00:00";
        endTime = createTime + " 23:59:59";
    }
    init(startTime,endTime,userCode,realName);
};

function clearBox(){
    $('#time').val('');
    $('#code').val('');
    init();
};

function agreeRegister(data){
    var res = false;
    $.ajax({
        url:'/sspu/campus/agreeRegister',
        dataType:'json',
        type:'post',
        data:{
            'id': data.id,
            'headImg': data.headImg,
            'isAdmin': data.isAdmin,
            'mail': data.mail,
            'password': data.password,
            'phone': data.phone,
            'realName': data.realName,
            'userCode': data.userCode,
            'userName': data.userName
        },
        async:false,
        success:function(AjaxJson){
            res = AjaxJson.success;
        }
    })
    return res;
}

function agreeComment(data){
    var res = false;
    $.ajax({
        url:'/sspu/campus/agreeComment',
        dataType:'json',
        type:'post',
        data:{
            'id': data.id,
            'userCode': data.userCode,
            'videoId': data.videoId,
            'content': data.content
        },
        async:false,
        success:function(AjaxJson){
            res = AjaxJson.success;
        }
    })
    return res;
}

function delRegister(id){
    var res = false;
    $.ajax({
      url:'/sspu/campus/delRegister',
      type:'post',
      dataType: 'json',
      data:{
          "id":id,
      },
      async:false,
      success:function(AjaxJson){
          res = AjaxJson.success;
      }
    })
    return res;
}

function delComment(id){
    var res = false;
    $.ajax({
        url:'/sspu/campus/delComt',
        type:'post',
        dataType: 'json',
        data:{
            "id":id,
        },
        async:false,
        success:function(AjaxJson){
            res = AjaxJson.success;
        }
    })
    return res;
}

function switchPage(name){
    console.log(name);
    $('iframe').attr('src',name);
}







































/**
*  手动设置弹出层，未用table.on,滚
* */

// function closeLayer(){
//     layer.closeAll();
// };

// function openRegisterAgree(){
//     layer.open({
//         title:'确认通过吗？',
//         type:1,
//         area:['250px','140px'],
//         content:'<button class="layui-btn layui-btn-normal layui-btn-sm" style="margin:20px 45px;" onclick="agreeRegister()">确认</button>\n' +
//             '    <button class="layui-btn layui-btn-sm" onclick="closeLayer()">取消</button>'
//     })
// }

// function openCommentAgree(){
//     layer.open({
//         title:'确认通过吗？',
//         type:1,
//         area:['250px','140px'],
//         content:'<button class="layui-btn layui-btn-normal layui-btn-sm" style="margin:20px 45px;" onclick="agreeComment()">确认</button>\n' +
//             '    <button class="layui-btn layui-btn-sm" onclick="closeLayer()">取消</button>'
//     })
// }

// function openRegisterDel(){
//     layer.open({
//         title:'确认删除吗？',
//         type:1,
//         area:['250px','140px'],
//         content:'<button class="layui-btn layui-btn-normal layui-btn-sm" style="margin:20px 45px;" onclick="">确认</button>\n' +
//             '    <button class="layui-btn layui-btn-sm" onclick="closeLayer()">取消</button>'
//     })
// }

// function openCommentDel(){
//     layer.open({
//         title:'确认删除吗？',
//         type:1,
//         area:['250px','140px'],
//         content:'<button class="layui-btn layui-btn-normal layui-btn-sm" style="margin:20px 45px;" onclick="">确认</button>\n' +
//             '    <button class="layui-btn layui-btn-sm" onclick="closeLayer()">取消</button>'
//     })
// }

