//ModelAndVied.setAttribute("",id);
$(function(){
	vedioGo();
});

function() vedioGo(){
	$.ajax({
		url:'',
		dataType:'json',
		data:{
			'vedioId':id;
		},
		success:function(data){
			
			var userInfo = '<div class="row"> \
			<div id="headImg"> <img src="' + data.headImg + '" /></div> \
			<div id="userName1">'+ data.userName + '</div> \
			<button id="subscribed">关注<button> \
			</div>';
			$('#userInfo').html(userInfo);
			
			var vedioDescription = '<div class="row"> \
			<div id="title">' + data.title + '</div> \
			<div id="time">'+ data.createTime + '</div> \
			<div id="description">'+ data.description + '</div> \
			</div>';
			$('#vedioInfo').html(vedioInfo);
			
			var comment = '<div class="row"> \
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