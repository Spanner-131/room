$(function(){
	initCarousel();
	getVideoBySpt();
})

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
	$.ajax({
		url:'',
		dataType:'json',
		data:{
			'userName':userName,
			'userCode':userCode
		},
		success:function(data){
			//遍历data
			
			//下侧视频
			var videoBottomHtml = '<a href="' + data.url +'"><div class="row"> \
			<div class="squareBottom"><img class="cover" src="'+ data.coverUrl + '"/></div> \
			<div class="titleBottom">' + data.title + '</div> \
			<div class="userNameBottom">' + data.userName + '</div> \
			</div></a>';
			$('#vedio-display-bottom').html(vedioBottomHtml);
		}
	})
}

function getVideoBySpt(){
	var currentUserCode = getCurrentUserCode();
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
				var content = '<div class="row"> \
							   <img class="headImg" src="../static/img/'+ AjaxJson.data[i].headImg + '"/> \
							   <div class="contentRight"> \
							   <div class="userNameRight">' + AjaxJson.data[i].userName + '<div class="timeRight">'
			    	           	+ AjaxJson.data[i].createTimeStr + '</div></div> \
			    	           <div class="titleRight">Title:&emsp;' + AjaxJson.data[i].title + '</div></div> \
			     	           <img class="coverRight" src="../static/img/' + AjaxJson.data[i].coverUrl + '"/></div>';
				videoRightHtml += content;
			}
			$('#videosRight').html(videoRightHtml);
		}
	})
}