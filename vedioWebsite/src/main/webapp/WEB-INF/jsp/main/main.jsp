<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<htmL>
<head>
	<meta charset="utf-8"/>
	<title>主页</title>
	<link rel="stylesheet" href="../static/css/main/main.css" type="text/css" />
    <link rel="stylesheet" href="../static/js/layui/css/layui.css"/>
	<script src="../static/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="../static/js/main/main.js"></script>
    <script type="text/javascript" src="../static/js/layui/layui.js"></script>
</head>	
<body>
	<!--标题-->
    	<div class="top">
        <a href="#"><img id="logo" src="../static/img/sspu.png" width="230" height="60" alt="sspu"/></a>
        <div class="drop-down">
        <ul>
        <li><a href="http://www.sspu.edu.cn/">
        <span class="title">学校首页</span>
        <span class="sub-title">HOMEPAGE</span>
        </a></li>
        <li><a href="javascript:void(0)">
        <span class="title">视频仓库</span>
        <span class="sub-title">VLIB</span>
        </a></li>
        <li><a href="javascript:void(0)">
        <span class="title">校内互动</span>
        <span class="sub-title">COMMUNITY</span>
        </a></li>
        <li><a href="javascript:void(0)">
        <span class="title">体育活动</span>
        <span class="sub-title">SPORTS</span>
        </a></li>
        <li><a href="javascript:void(0)">
        <span class="title">校园地图</span>
        <span class="sub-title">MAP</span>
        </a></li>
        <li>

        </li>
        </ul>
<%--        <div class="drop-down-content">--%>
<%--        <ul>--%>
<%--        	<a href="#"><li>游戏下载</li></a>--%>
<%--        	<a href="#"><li>点券充值</li></a>--%>
<%--        	<a href="#"><li>官方社区</li></a>--%>
<%--        	<a href="#"><li>LPL联赛</li></a>--%>
<%--        	<a href="#"><li>联系客服</li></a>--%>
<%--        	<a href="#"><li>新手指引</li></a>--%>
<%--        	<a href="#"><li>周边商城</li></a>--%>
<%--        	<a href="#"><li>官方论坛</li></a>--%>
<%--        	<a href="#"><li>全球总决赛</li></a>--%>
<%--        	<a href="#"><li>转区系统</li></a>--%>
<%--        </ul>--%>
<%--        </div>--%>
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
					<div class="rowBottom">
						<div class="squareBottom">
							<img class="cover" src="../static/img/ASH.png"/>
							<div class="titleBottom">ash</div>
							<div class="useNameBottom">hb</div>
						</div>
						<div class="squareBottom">
							<img class="cover" src="../static/img/ASH.png"/>
							<div class="titleBottom">ash</div>
							<div class="useNameBottom">hb</div>
						</div>
						<div class="squareBottom">
							<img class="cover" src="../static/img/ASH.png"/>
							<div class="titleBottom">ash</div>
							<div class="useNameBottom">hb</div>
						</div>
						<div class="squareBottom">
							<img class="cover" src="../static/img/ASH.png"/>
							<div class="titleBottom">ash</div>
							<div class="useNameBottom">hb</div>
						</div>
					</div>
					<div class="rowBottom">
						<div class="squareBottom">
							<img class="cover" src="../static/img/ASH.png"/>
							<div class="titleBottom">ash</div>
							<div class="useNameBottom">hb</div>
						</div>
						<div class="squareBottom">
							<img class="cover" src="../static/img/ASH.png"/>
							<div class="titleBottom">ash</div>
							<div class="useNameBottom">hb</div>
						</div>
						<div class="squareBottom">
							<img class="cover" src="../static/img/ASH.png"/>
							<div class="titleBottom">ash</div>
							<div class="useNameBottom">hb</div>
						</div>
						<div class="squareBottom">
							<img class="cover" src="../static/img/ASH.png"/>
							<div class="titleBottom">ash</div>
							<div class="useNameBottom">hb</div>
						</div>
						<div class="squareBottom">
							<img class="cover" src="../static/img/ASH.png"/>
							<div class="titleBottom">ash</div>
							<div class="useNameBottom">hb</div>
						</div>
						<div class="squareBottom">
							<img class="cover" src="../static/img/ASH.png"/>
							<div class="titleBottom">ash</div>
							<div class="useNameBottom">hb</div>
						</div>
						<div class="squareBottom">
							<img class="cover" src="../static/img/ASH.png"/>
							<div class="titleBottom">ash</div>
							<div class="useNameBottom">hb</div>
						</div>
						<div class="squareBottom">
							<img class="cover" src="../static/img/ASH.png"/>
							<div class="titleBottom">ash</div>
							<div class="useNameBottom">hb</div>
						</div>
					</div>
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