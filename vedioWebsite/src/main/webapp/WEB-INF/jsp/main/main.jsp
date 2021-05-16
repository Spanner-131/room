<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<htmL>
<head>
	<meta charset="utf-8"/>
	<title>主页</title>
	<link rel="stylesheet" href="../static/css/main/main.css" type="text/css" />
    <link rel="stylesheet" href="../static/js/layui/css/layui.css"/>
    <link rel="stylesheet" href="../static/css/public.css"/>
	<script src="../static/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="../static/js/main/main.js"></script>
    <script type="text/javascript" src="../static/js/layui/layui.js"></script>
</head>	
<body>
	<!--标题-->
    	<div class="top">
            <div id="logo">
                <a class="fl" href="#"><img src="../static/img/sspu.png" width="230" height="60" alt="sspu"/></a>
            </div>
            <div class="drop-down">
                <ul>
                <li class="fl tn ac"><a href="http://www.sspu.edu.cn/">
                <span class="title">学校首页</span>
                <span class="sub-title">HOMEPAGE</span>
                </a></li>
                <li class="fl tn ac"><a href="javascript:void(0)">
                <span class="title">视频仓库</span>
                <span class="sub-title">VLIB</span>
                </a></li>
                <li class="fl tn ac"><a href="javascript:void(0)">
                <span class="title">校内互动</span>
                <span class="sub-title">COMMUNITY</span>
                </a></li>
                <li class="fl tn ac"><a href="javascript:void(0)">
                <span class="title">体育活动</span>
                <span class="sub-title">SPORTS</span>
                </a></li>
                <li class="fl tn ac"><a href="javascript:void(0)">
                <span class="title">校园地图</span>
                <span class="sub-title">MAP</span>
                </a></li>
                </ul>
            </div>
            <div id="headImg">

            </div>
        </div>

	<div id = "center">
		<div id = "box1">
			<div class="layui-carousel" id="carousel">
				<div carousel-item>
                    <div><a href="javascript:void(0)"><img src="../static/img/carousel/乘风破浪.jpg"/></a></div>
                    <div><a href="javascript:void(0)"><img src="../static/img/carousel/俯视.jpg"/></a></div>
                    <div><a href="javascript:void(0)"><img src="../static/img/carousel/雷姆.jpg"/></a></div>
                </div>
			</div>
			<div id = "videos-display-bottom">

			</div>
		</div>
		
		<div id = "box2">
			<div id="box-title">
					我的关注：
			</div>
			<div id="videosRight">
			</div>
		</div>
	</div>
</body>
</htmL>