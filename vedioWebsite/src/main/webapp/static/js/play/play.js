$(function(){
	//初始化视频信息
	var videoId = $('#videoId').html();
	console.log(videoId);
	videoGo(videoId);
	$('textarea').val("");
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
	var currentUserCode = getCurrentUserCode();
	$.ajax({
		url:'/sspu/campus/getVideo',
		type:'post',
		dataType:'json',
		data:{
			'id':videoId,
			'currentUserCode': currentUserCode
		},
		success:function(AjaxJson){
		    var videoSrc = '<video ishivideo="true" autoplay="true" isrotate="true" autoHide="true" controls="controls"> \
		    <source src="' + AjaxJson.data.url + '" type="video/mp4"/></video>'
            $('#videoPlayer').html(videoSrc);

			var userInfo = '<img id="headImg" src="../../static/img/' + AjaxJson.data.headImg + '" /> \
			<div id="userName">'+ AjaxJson.data.userName + '</div> \
			<div id="userCode">' + AjaxJson.data.userCode + '</div> \
			<div id="brief">' + AjaxJson.data.subAmount + '关注&emsp;' + AjaxJson.data.vdoAmount + '视频</div> \
			<button id="subscribe" onclick="subscribe()">+ 关注</button>'
			$('#userInfo').html(userInfo);
			
			var videoInfo = '<div id="title">标题:' + AjaxJson.data.title + '</div> \
			<div id="description">描述:'+ AjaxJson.data.description + '</div> \
			<div class="browseInfo">' + AjaxJson.data.bowsAmount + '</div> \
			<div class="browseInfo">' + AjaxJson.data.createTimeStr + '</div> \
			<div class="browseInfo">视频号:' + videoId + '</div>'
			$('#videoInfo').html(videoInfo);

			var funBoxContent = '<div class="funcbox"><button class="funcButton"  onclick="like()">点</button><span class="num">' + AjaxJson.data.likeAmount + '</span></div> \
			                     <div class="funcbox"><button class="funcButton"  onclick="collect()">收</button><span class="num">' + AjaxJson.data.coltAmount + '</span></div> \
			                     <div class="funcbox"><button class="funcButton"  id="comtButton" value="1" onclick="showTextBox(this.value)">评</button><span class="num">' + AjaxJson.data.cmtAmount + '</span></div> '
			$('#funBox').html(funBoxContent);

			var commentBoxContent = '<div id="comTitle">评论</div>'
			var comListLength = AjaxJson.data.comList.length;
			for (i = 0;i < comListLength;i++){
				var comContent = '<div class="comment"> \
							      <img id="comHeadImg" src="../../static/img/' + AjaxJson.data.comList[i].headImg + '" /> \
			                  	  <div class="comUserName">'+ AjaxJson.data.comList[i].userName + '</div> \
				                  <div class="createTime">'+ AjaxJson.data.comList[i].createTimeStr + '</div> \
				                  <div class="content">'+ AjaxJson.data.comList[i].content + '</div> \
				                  </div>';
				commentBoxContent += comContent;
			}
			$('#commentBox').html(commentBoxContent);

			if(AjaxJson.data.isSubscribed == '1'){
				$('#subscribe').attr('style','color: white; background-color: #eb7350;');
			}else{
				$('#subscribe').attr('style','color: black; background-color: white;');
			}
			if(AjaxJson.data.isLiked == '1'){

			}
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
			if(AjaxJson.message == '关注成功！'){
				$('#subscribe').attr('style','color: white; background-color: #eb7350;');
			}else{
				$('#subscribe').attr('style','color: black; background-color: white;');
			}
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

function showTextBox(value){
	if(value == '1'){
		$('#textBox').show();
		$('#comtButton').val('0');
	}else{
		$('#textBox').hide();
		$('#comtButton').val('1');
	}
}

function submitConfirm() {
	layui.use('layer',function(){
		var layer = layui.layer;
		layer.confirm('确认提交你的评论吗？',{btn:['确定','取消']},function (index) {
			var res= submit();
			if(res){
				$('textarea').val("");
				layer.close(index);
			}
		});
	});
}

function submit() {
	var currentUserCode = getCurrentUserCode();
	var videoId = $('#videoId').html();
	var content = $('textarea').val();
	console.log(content);

	var res = false;
	$.ajax({
		url: '/sspu/campus/addComt',
		type: 'post',
		dataType: 'json',
		data: {
			'userCode': currentUserCode,
			'videoId': videoId,
			'content': content
		},
		async: false,
		success: function(AjaxJson){
			res = AjaxJson.success;
			alert(AjaxJson.message);
		}
	})
	return res;
}

