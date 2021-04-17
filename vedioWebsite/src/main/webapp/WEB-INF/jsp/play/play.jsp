<%@page pageEncoding="UTF-8" contentType="text/html" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>视频播放</title>
	<link href="../../static/css/play/play.css"  rel="stylesheet" type="text/css">
<%--	<link rel="stylesheet" href="../../static/js/layui/css/layui.css">--%>
	<script src="../../static/js/jquery/jquery.min.js"></script>
	<script src="../../static/js/layui/layui.js"></script>
	<script src="../../static/js/play/play.js"></script>
</head>
<body>
  
		<div id= "videoBox">
			<div id="videoId" style="display:none">${videoId}</div>
			<div id="videoPlayer">
			</div>
		</div>
		
		<div id = "infoBox">
			<div id="userInfo">

			</div>
			<div id="videoInfo">
				
			</div>
			<div id="funBox">

			</div>
			<div id="textBox">
				<!-- textBox 用ui -->
                <textarea rows="4" cols="37">

                </textarea>
				<button id="comtSubmit" onclick="submitConfirm()">提交</button>
			</div>
			<div id="commentBox">

			</div>
		</div>
		
</body>
</html>