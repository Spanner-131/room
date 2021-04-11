var id = sessionStorage.getItem("");
$(function(){
	scroll();
	videosDisplay();
});

function scroll(){
	//百度一下轮播图
	//不写js的话，就还有css实现
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
			//右侧关注
			var videoRightHtml = '<a href="' + data.url +'"><div class="row"> \
			<div class ="squareRight"> <img src="' + data.coverUrl + '" /></div> \
			<div class="titleRight">'+ data.title + '</div> \
			<div class="userNameRight">' + data.userName + '</div> \
			<div class="timeRight">'+ data.createTime +'</div> \
			</div></a>';
			$('#vedio-display-right').html(videoRightHtml);
			
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