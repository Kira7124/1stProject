<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link rel="stylesheet" type="text/css" href="./css/reset.css"/>
<link rel="stylesheet" type="text/css" href="./css/layout.css"/>
<link rel="stylesheet" id="pagestyle" href="./css/board.css"  />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="js/jquery.js"></script>
<script src="js/findMemberId.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
	    			<br><br><h4>아이디 찾기</h4>
	    		</div>
	    		<div class="login-input" style="margin-top: 50px;">
			        <form action="./findMemberIdAction.me" method="post">
			            <p>가입 시 등록한 이메일</p>
			            <span><input type="email" placeholder="&nbsp;&nbsp;이메일" name="inputEmail" id="find-email"></span>
			            <p>인증번호 입력란</p>
			            <span ><input type="text" placeholder="&nbsp;&nbsp;인증번호" name="name" id="num-input" style="width: 200px;"></span>
			            <button type="button" class="btn btn-dark" onclick="return AuthNumberRequest();">인증번호 받기</button>
			            <div id="msg-randomNum"></div>
			            <p><input type="submit" class="login-bt" value="아이디 찾기" onclick="return clickFindId();"></p>
			        </form>
	    		</div>
	   		</div>
   		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
</body>
</html>