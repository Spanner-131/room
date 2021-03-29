//ModelAndVied.setAttribute("",id);
$(function(){
	var videoId = $('#videoId').html();
	console.log(videoId);
	videoGo(videoId);
});

function videoGo(videoId){
	$.ajax({
		url:'/sspu/campus/getVideo',
		type:"post",
		dataType:'json',
		data:{
			'id':videoId
		},
		success:function(AjaxJson){
		    var videoSrc = '<video ishivideo="true" autoplay="true" isrotate="true" autoHide="true" controls="controls"> \
		    <source src="' + AjaxJson.data.url + '" type="video/mp4"/></video>'
            $('#videoPlayer').html(videoSrc);

			var userInfo = '<img id="headImg" src="' + AjaxJson.data.headImg + '" /></div> \
			<div id="userName">'+ AjaxJson.data.userName + '</div> \
			<div id="brief">' + AjaxJson.data.subAmount + '&emsp;' + AjaxJson.vdoAmount + '</div> \
			<button id="subscribe">+ 关注</button>'
			$('#userInfo').html(userInfo);
			
			var videoInfo = '<div id="title">' + AjaxJson.data.title + '<button id="controlsDes">^</button></div> \
			<div id="description">'+ AjaxJson.data.description + '</div> \
			<div class="browseInfo"> + AjaxJson.data.bowsAmount + </div> \
			<div class="browseInfo"> + AjaxJson.data.createTime + </div> \
			<div class="browseInfo"> + videoId + </div>'
			$('#videoInfo').html(videoInfo);

			var funBoxContent = '<div class="funcbox"><button class="funcButton">点</button><span class="num">' + AjaxJson.data.likeAmount + '</span></div> \
			                     <div class="funcbox"><button class="funcButton">收</button><span class="num">' + AjaxJson.data.coltAmount + '</span></div> \
			                     <div class="funcbox"><button class="funcButton">评</button><span class="num">' + AjaxJson.data.cmtAmount + '</span></div> '
			$('#funBox').html(funBoxContent);

			var commentBoxContent = '<div class="row"> \
			<div class="headImg"> <img src="' + data.headImg + '" /></div> \
			<div class="userName2">'+ data.userName + '</div> \
			<div class="time">'+ data.createTime + '</div> \
			<div class="comment">'+ data.comment + '</div> \
			</div>';
			$('#comment').html(comment);
			
			var vedioPlayer = '<vedio src=' + data.url +'/>';
			$('#vedioPlayer').html(vedioPlayer);
		}
	})
}