<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css"/>
<link rel="stylesheet" type="text/css" href="./css/layout.css"/>
<link rel="stylesheet" id="pagestyle" href="./css/board.css"  />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

</head>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<body>

	<!-- 전체영역 -->
	<div id="login_pg">
		<!-- 로그인페이지 배경 -->
	    <video muted autoplay loop id="play_bg" width="100%" height="100%">
	        <source src="./img/loginbg.mp4" type="video/mp4">
	    </video>
	    <!-- 로고부분 -->
	    <div class="login-logo">
                <a href="./main.ma" class="login-logo-bt">
                	<img src="./icon/login_logo.png">
                </a>
        </div>
        <!-- 로그인 입력부분 -->
	    <div class="login-form-bg">
	    	<div class="login-form">
	    		<div class="login-title">
	    			<h4>Login</h4>
	    		</div>
	    		<div class="login-input">
			        <form action="./LoginAction.me" method="post">
			            <p>아이디</p>
			            <span><input type="text" placeholder="&nbsp;&nbsp;아이디" name="id"></span>
			            <p>비밀번호</p> 
			            <span><input type="password" placeholder="&nbsp;&nbsp;비밀번호" name="pw"></span>
			            		<p><input type="submit" class="login-bt" value="로그인"></p>
			        </form>
	    		</div>
	    		<div class="login-join">
		    		<p>회원이 아니신가요??</p>
				    <p><input type="button" value="회원가입" onclick="location.href='./join.me';"></p>
	    		</div>
				<div class="login-join">
		    		<button type="button" class="id-find-btn" onclick="location.href='./findMemberId.me';">아이디 찾기</button>
		    		<button type="button" class="pass-find-btn" onclick="location.href='./findMemberPw.me';">비밀번호 찾기</button>
	    		</div>
	   		</div>
   		</div>
	</div>
	
</body>
</html>