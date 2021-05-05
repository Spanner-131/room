$(function(){
	//初始化视频信息
	var videoId = $('#videoId').html();
	console.log('videoId:' + videoId);
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
			// $('source').attr('src','../../static/video/' + AjaxJson.data.url);
		    var videoSrc = '<video id="newVideo" ishivideo="true" autoplay="true" isrotate="true" autoHide="true" controls="controls"> \
		    <source src="../../static/video/' + AjaxJson.data.url + '" type="video/mp4"/></video>'
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

			var funBoxContent = '<div class="funcbox"><button class="funcButton"  id="likeButton" onclick="like()"></button><span class="num">' + AjaxJson.data.likeAmount + '</span></div> \
			                     <div class="funcbox"><button class="funcButton"  id="coltButton" onclick="collect()"></button><span class="num">' + AjaxJson.data.coltAmount + '</span></div> \
			                     <div class="funcbox"><button class="funcButton"  id="comtButton" value="1" onclick="showTextBox(this.value)"><img class="icon" src="../../static/img/icon/initComt.png"/></button><span class="num">' + AjaxJson.data.cmtAmount + '</span></div> '
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

			// 判断 关注
			if(AjaxJson.data.isSubscribed == '1'){
				$('#subscribe').attr('style','color: white; background-color: #eb7350;');
			}else{
				$('#subscribe').attr('style','color: black; background-color: white;');
			}
			// 判断 点赞
			if(AjaxJson.data.isLiked == '1'){
				$('#likeButton').html('<img class="icon" src="../../static/img/icon/like.png">');
			}else{
				$('#likeButton').html('<img class="icon" src="../../static/img/icon/initLike.png"/>');
			}
			// 判断 收藏
			if(AjaxJson.data.isCollected == '1'){
				$('#coltButton').html('<img class="icon" src="../../static/img/icon/colt.png">');
			}else{
				$('#coltButton').html('<img class="icon" src="../../static/img/icon/initColt.png"	>');
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
			if(AjaxJson.message == '点赞成功'){
				$('#likeButton').html('<img class="icon" src="../../static/img/icon/like.png">');
			}else{
				$('#likeButton').html('<img class="icon" src="../../static/img/icon/initLike.png"/>');
			}
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
			if(AjaxJson.message == '收藏成功'){
				$('#coltButton').html('<img class="icon" src="../../static/img/icon/colt.png">');
			}else{
				$('#coltButton').html('<img class="icon" src="../../static/img/icon/initColt.png">');
			}
		}
	})
}

function showTextBox(value){
	if(value == '1'){
		$('#textBox').show();
		$('#comtButton').html('<img class="icon" src="../../static/img/icon/comt.png">');
		$('#comtButton').val('0');
	}else{
		$('#textBox').hide();
		$('#comtButton').html('<img class="icon" src="../../static/img/icon/initComt.png">')
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

// window.onload = function () {
// 	var newVideo = document.getElementById('newVideo');
// 	console.log(newVideo);
// 	var tol = newVideo.duration;
// 	console.log(tol);
// 	newVideo.addEventListener('timeupdate',function() {
// 		var currentTime = newVideo.currentTime;
// 		console.log(currentTime);
// 	})
//
// 	// 暂时不用
// 	function playBySeconds(num) {
// 		newVideo.currentTime = num;
// 	}
// }

// 根据地址获取经纬度 2021.04.21测试接口，先放这里
function getLocation() {
	let that = this
	console.log(that.project_location)
	let data = {
		key: '9eccd11e86da324e94d86f80133f05ac',
		address: '浦东新区博山路51弄40号106室'
	}
	$.ajax({
		url: 'https://restapi.amap.com/v3/geocode/geo',
		type: 'get',
		dataType: 'jsonp',
		data,
		success: function (data) {
			// console.log(data.geocodes[0].location)//获取到的经纬度
			console.log(data)
		}
	})
}



