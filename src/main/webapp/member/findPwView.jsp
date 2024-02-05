<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link rel="stylesheet" type="text/css" href="./css/reset.css"/>
<link rel="stylesheet" type="text/css" href="./css/layout.css"/>
<link rel="stylesheet" id="pagestyle" href="./css/board.css"  />
<script src="js/jquery.js"></script>
<script src="js/findMemberPwView.js"></script>
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
	    			<br><br><h4>새로운 비밀번호 설정</h4> 
	    		</div>
	    		<div class="login-input" style="margin-top: 80px;">
			        <form action="./setNewPasswordAction.me?id=${param.id}" method="post" name="fr-newPw">
		            	
			            <p>새로운 비밀번호</p> 
			            <span id="span-pw">
			            	<input style="width: 400px;" type="password" placeholder="&nbsp;&nbsp;8 ~ 15자 영문, 숫자, 특수문자 조합" name="newPw" id="new-pw">
			            </span>
		            	<div id="msg-pw"></div>
		            	
			            <p>새로운 비밀번호 확인</p> 
			            <span id="span-pw2">
			            	<input style="width: 400px;" type="password" placeholder="&nbsp;&nbsp;8 ~ 15자 영문, 숫자, 특수문자 조합" name="newPassword" id="new-pw2">
			            </span>
		            	<div id="msg-pw2"></div>
			            <p><input type="submit" class="login-bt" value="새로운 비밀번호로 변경" onclick="return clickChangeNewPw();"></p>
			        </form>
	    		</div>
	   		</div>
   		</div>
	</div>
	
</body>
</html>