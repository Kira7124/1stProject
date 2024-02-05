<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원수정페이지(관리자)</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css"/>
<link rel="stylesheet" type="text/css" href="./css/layout.css"/>
<link rel="stylesheet" id="pagestyle" href="./css/board.css"  />
<script src="js/jquery.js"></script>
<script src="js/validate.js"></script>
<script src="js/address.js"></script>
<script src="js/checkDuplicateId.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<body>

<%
    String memberId = request.getParameter("id");
    String memberPw = request.getParameter("pw");
    String memberName = request.getParameter("name");
    String memberPhoneNum = request.getParameter("phoneNum");
    String memberAddress = request.getParameter("address");
    String memberEmail = request.getParameter("email");
%>

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
	    	<div class="join-form">
	    		<div class="join-title">
	    			<h4>회원정보수정(관리자)</h4>
	    		</div>
	    		<div class="join-input" style="margin-top: 20px;">
			        <form action="./MemberUpdateAction.me" method="post">
			        	<p>이름</p>
			            <span><input type="text" value="<%=memberName %>" name="name" id="join-name" readonly></span>
			            <p>아이디</p>
			            <span><input type="text" value="<%=memberId %>" name="id" id="join-id" readonly></span>
			            <p>비밀번호</p> 
			            <span><input type="password" value="<%=memberPw %>" name="pw" id="join-pw"></span>
			            <p>생년월일</p>
			            <span><input type="date"name="birthDate" id="join-birth"></span>
			            <p>휴대폰 번호</p>
			            <span><input type="tel" value="<%=memberPhoneNum %> " name="phoneNum" id="join-phoneNum"></span>
			            <p>주소</p>
			            <span id="join-input-add">
			            	<input type="text" id="join_postcode" name="zip_code" placeholder="우편번호">
							<input type="button" id="join_button" onclick="return sample6_execDaumPostcode()" value="주소 검색" style="outline: none;"><br>
							<input type="text" id="join_address" name="address" placeholder="주소"><br>
							<input type="text" id="join_detailAddress" name="address_detail" placeholder="상세주소">
							<input type="text" id="join_extraAddress" name="refrence" placeholder="참고항목">
			            </span>
			            <p>이메일</p>
			            <span><input type="email" value="<%=memberEmail %>" name="email" id="join-email"></span>
			            <p><input type="submit" class="login-bt" value="수정하기"></p>
			        </form>
	    		</div>
				
				<div class="login-api">
		           
				</div>
	   		</div>
   		</div>
	</div>
			
</body>
</html>