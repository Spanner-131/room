$(function(){
	initCarousel();
	videosDisplay();
	getVideoBySpt();
	getCurrentUserInfo();
})

function getCurrentUserCode(){
	var currentUserCode = sessionStorage.getItem('currentUserCode');
	if(currentUserCode == "") {
		var currentUserName = sessionStorage.getItem('currentUserName');
		$.ajax({
			url: 'init',
			type: 'get',
			dataType: 'json',
			async: false,
			data: {
				'userName': currentUserName,
				'userCode': ''
			},
			success: function (AjaxJson) {
				currentUserCode = AjaxJson.data.userCode;
			}
		})
	}
	return currentUserCode;
}

// 获取 头像 给nav用
function getCurrentUserInfo(){
	var currentUserCode = getCurrentUserCode();
	$.ajax({
		url: 'getCurrentUserInfo',
		type: 'post',
		dataType: 'json',
		data: {
			userCode: currentUserCode,
		},
		success: function (AjaxJson) {
			if(AjaxJson.data != null){
				var navInfoHtml = '<div id="navInfo"><img src="../static/img/'+ AjaxJson.data.headImg +'" alt="请登录"> \
									<ul><li class="ac"><a href="javascript:void(0)">个人资料</a></li> \
									<li class="ac"><a href="javascript:void(0)">更新日志</a></li> \
									<li class="ac"><a href="javascript:void(0)" onclick="quitCurrentUser()">退出</a></li></ul></div>';
				$('#headImg').html(navInfoHtml);
			}
		}
	})
}

// 退出当前用户
function quitCurrentUser() {
	sessionStorage.setItem('currentUserCode','');
	window.location.reload();
}

function initCarousel(){
	layui.use('carousel',function () {
		var carousel = layui.carousel;

		carousel.render({
			elem: '#carousel',
			width: '100%',
			arrow: 'always',
			anim: 'leftright',
			interval: '5000'
		});
	});
}

function videosDisplay(){
    var currentUserCode = getCurrentUserCode();
	$.ajax({
		url:'getRecdVideo',
		dataType:'json',
		data:{
			'userCode': currentUserCode
		},
		success:function(AjaxJson){
		    var videoBottomHtml = '';
		    for(i = 0;i < AjaxJson.data.length; i++){
		        if(AjaxJson.data[i] != null){
		            var count = 0;
		            // var content = '<a href = "play/' + AjaxJson.data[i].id + '"><div class="squareBottom"> \
		            var content = '<a onclick=layerOpen("play/'+ AjaxJson.data[i].id + '")><div class="squareBottom"> \
                                   <img class="cover" src="../static/img/' + AjaxJson.data[i].coverUrl + '"> \
                                   <div class="info"><div class="titleBottom">' + AjaxJson.data[i].title + '</div>\
                                   <div class="userNameBottom">' + AjaxJson.data[i].userName + '</div>\
                                   <div class="tag" id="tag1">' + AjaxJson.data[i].tag1 + '</div> \
                                   <div class="tag" id="tag2">' + AjaxJson.data[i].tag2 + '</div> \
                                   </div></div></a>'
                    //加个计数器
                    count += 1;
                }
		        if((count == 6) || (count != 6 && (i + 1) == AjaxJson.data.length)){
                    var row = '<div class="rowBottom">' + content + '<div>';
                    videoBottomHtml += row;
                }
            }
		    $('#videos-display-bottom').html(videoBottomHtml);
		}
	})
}

function getVideoBySpt(){
	var currentUserCode = getCurrentUserCode();
	if(currentUserCode == '' || currentUserCode == null){
        var videoRightHtml = '<div class="loginAlert"><a id="loginButton" href="login">登录</a></div>'
        $('#videosRight').html(videoRightHtml);
    }else{
	    $.ajax({
		    url:'getVideoBySpt',
		    type:'post',
		    dataType: 'json',
		    data:{
			    "userCode": currentUserCode,
		    },
		    success:function(AjaxJson){
			    //右侧关注
			    var videoRightHtml = ''
			    for(i = 0;i < AjaxJson.data.length;i++){
				    var content = '<a href="javascript:void(0)" onclick=layerOpen("play/'+ AjaxJson.data[i].id + '")><div class="row"> \
							   <img class="headImg" src="../static/img/'+ AjaxJson.data[i].headImg + '"/> \
							   <div class="contentRight"> \
							   <div class="userNameRight">' + AjaxJson.data[i].userName + '<div class="timeRight">'
			    	           	+ AjaxJson.data[i].createTimeStr + '</div></div> \
			    	           <div class="titleRight">Title:&emsp;' + AjaxJson.data[i].title + '</div></div> \
			     	           <img class="coverRight" src="../static/img/' + AjaxJson.data[i].coverUrl + '"/></div></a>';
				    videoRightHtml += content;
			    }
			    $('#videosRight').html(videoRightHtml);
		    }
	    })
    }
}

function layerOpen(url) {
	layui.use('layer',function () {
		var layer = layui.layer;
		layer.open({
			type: 2,
			title: '',
			content: url,
			shade: 0.6,
			area: ['1518px','650px']
		})
	})
}