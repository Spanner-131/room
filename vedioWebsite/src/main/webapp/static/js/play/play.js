$(function(){
	//初始化视频信息
	var videoId = $('#videoId').html();
	console.log(videoId);
	videoGo(videoId);
});

//获取学号
function getCurrentUserCode(){
	var currentUserCode = sessionStorage.getItem('currentUserCode');
	if(currentUserCode == "") {
		var currentUserName = sessionStorage.getItem('currentUserName');
		$.ajax({
			url: '/sspu/campus/init',
			type: 'get',
			dataType: 'json',
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

function videoGo(videoId){
	$.ajax({
		url:'/sspu/campus/getVideo',
		type:'post',
		dataType:'json',
		data:{
			'id':videoId
		},
		success:function(AjaxJson){
		    var videoSrc = '<video ishivideo="true" autoplay="true" isrotate="true" autoHide="true" controls="controls"> \
		    <source src="' + AjaxJson.data.url + '" type="video/mp4"/></video>'
            $('#videoPlayer').html(videoSrc);

			var userInfo = '<img id="headImg" src="' + AjaxJson.data.headImg + '" /> \
			<div id="userName">'+ AjaxJson.data.userName + '</div> \
			<div id="userCode">' + AjaxJson.data.userCode + '</div> \
			<div id="brief">' + AjaxJson.data.subAmount + '关注&emsp;' + AjaxJson.data.vdoAmount + '视频</div> \
			<button id="subscribe" onclick="subscribe()">+ 关注</button>'
			$('#userInfo').html(userInfo);
			
			var videoInfo = '<div id="title">标题:' + AjaxJson.data.title + '<button id="controlsDes">^</button></div> \
			<div id="description">描述:'+ AjaxJson.data.description + '</div> \
			<div class="browseInfo">' + AjaxJson.data.bowsAmount + '</div> \
			<div class="browseInfo">' + AjaxJson.data.createTimeStr + '</div> \
			<div class="browseInfo">视频号:' + videoId + '</div>'
			$('#videoInfo').html(videoInfo);

			var funBoxContent = '<div class="funcbox"><button class="funcButton" onclick="like()">点</button><span class="num">' + AjaxJson.data.likeAmount + '</span></div> \
			                     <div class="funcbox"><button class="funcButton" onclick="collect()">收</button><span class="num">' + AjaxJson.data.coltAmount + '</span></div> \
			                     <div class="funcbox"><button class="funcButton" onclick="showTextBox()">评</button><span class="num">' + AjaxJson.data.cmtAmount + '</span></div> '
			$('#funBox').html(funBoxContent);

			var commentBoxContent = '<div id="comTitle">评论框</div>'
			var comListLength = AjaxJson.data.comList.length;
			var comContent = '<div class="comment"> \
			<img id="comHeadImg" src="' + AjaxJson.data.comList.headImg + '" /> \
			<div class="comUserName">'+ AjaxJson.data.comList.userName + '</div> \
			<div class="createTime">'+ AjaxJson.data.comList.createTime + '</div> \
			<div class="content">'+ AjaxJson.data.comList.content + '</div> \
			</div>';
			for (i = 0;i < comListLength;i++){
				commentBoxContent += comContent;
			}
			$('#comment').html(commentBoxContent);

		}
	})
}

function subscribe(){
	var userCode = $('#userCode').html();
	var currentUserCode = getCurrentUserCode();
	console.log(currentUserCode);
	$.ajax({
		url:'/sspu/campus/subOrNot',
		type:'post',
		dataType:'json',
		data:{
			'userCode1':currentUserCode,
			'userCode2':userCode
		},
		success:function (AjaxJson) {
			alert(AjaxJson.message);
		}
	})
}

function like(){
	var currentUserCode = getCurrentUserCode();
	var videoId = $('#videoId').html();
	$.ajax({
		url:'/sspu/campus/pointLikeOrNot',
		type:'post',
		dataType:'json',
		data:{
			'userCode':currentUserCode,
			'videoId':videoId
		},
		success:function (AjaxJson) {
			alert(AjaxJson.message);
		}
	})
}

function collect(){
	var currentUserCode = getCurrentUserCode();
	var videoId = $('#videoId').html();
	$.ajax({
		url:'/sspu/campus/collectOrNot',
		type:'post',
		dataType:'json',
		data:{
			'userCode':currentUserCode,
			'videoId':videoId
		},
		success:function (AjaxJson) {
			alert(AjaxJson.message);
		}
	})
}

function showTextBox(){
	$('#textBox').show();
}