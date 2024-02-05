<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
</head>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link rel="stylesheet" type="text/css" href="./css/reset.css"/>
<link rel="stylesheet" type="text/css" href="./css/layout.css"/>
<link rel="stylesheet" id="pagestyle" href="./css/board.css"  />
<script src="js/jquery.js"></script>
<script src="js/updatePassword.js"></script>
<script language="JavaScript" type="text/javascript"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
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
	    			<h4>비밀번호 변경</h4>
	    		</div>
	    		<div class="updatePassword-input">
			        <form action="./JoinAction.me" method="post">
			        	<p>현재 비밀번호</p> 
			            <span id="span-pw">
			            	<input type="password" placeholder="&nbsp;&nbsp;현재 비밀번호" name="pw" id="input-current-password">
			            	<div id="msg-currentPw"></div>
			            </span>
			            <p>새 비밀번호</p> 
			            <span id="span-pw">
			            	<input type="password" placeholder="&nbsp;&nbsp;8 ~ 15자 영문, 숫자, 특수문자 조합" name="pw" id="input-newPassword">
			            	<div id="msg-newPw"></div>
			            </span>
			            <p>새 비밀번호 확인</p> 
			            <span id="span-pw">
			            	<input type="password" placeholder="&nbsp;&nbsp;8 ~ 15자 영문, 숫자, 특수문자 조합" name="pw" id="input-newPassword2">
			            	<div id="msg-newPw2"></div>
			            </span>
			            <span id="span-pw">
			            	<input type="button" value="변경하기" id="updatePasswordBtn" onclick="updatePassword();">
			            </span>
			        </form>
	    		</div>

	   		</div>
   		</div>
	</div>
	
</body>
</html>