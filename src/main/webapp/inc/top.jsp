<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	
	 var timeout; // 타임아웃 변수를 설정

    function showLogoutButton() {
      var logoutButton = document.getElementById("logoutButton");
      if (logoutButton) {
        clearTimeout(timeout); // 기존 타임아웃을 취소
        logoutButton.style.display = "inline-block"; // 버튼을 표시
      }
    }

    function hideLogoutButton() {
      // 일정 시간 후에 로그아웃 버튼을 숨김
      timeout = setTimeout(function () {
        var logoutButton = document.getElementById("logoutButton");
        if (logoutButton) {
          logoutButton.style.display = "none"; // 버튼을 숨김
        }
      }, 500); // 0.5초 후에 숨김
    }

    function cancelHideLogoutButton() {
      clearTimeout(timeout); // 마우스가 input 태그로 옮겨갔을 때 숨김 동작을 취소
    }

    function showAndHideLogoutButton() {
      showLogoutButton();
      hideLogoutButton();
    }
</script>
<body>
	<!-- 마이페이지 -->
     
     <div class="mypage">
     	 <c:choose>
     	 	<c:when test="${empty sessionScope.mdto }">
     	 		<a href="./loginForm.me" onclick="showAlert()">
		            <span class="mypage_bt_icon"><img src="./icon/마이페이지.png"></span>
		         </a>
		       
		       <
     	 	</c:when>
     	 	<c:when test="${sessionScope.mdto.id eq 'admin' }">
		         <a href="./MemberListAction.me">
		            <span class="mypage_bt_icon"><img src="./icon/마이페이지.png"></span>
		         </a>
     	 	</c:when>
     	 	<c:otherwise>
     	 		<a href="./myPage.me">
		            <span class="mypage_bt_icon"><img src="./icon/마이페이지.png"></span>
		         </a>
     	 	</c:otherwise>
     	 </c:choose>
     	 
     	 <script type="text/javascript">
		     function showAlert() {
		         alert("로그인이 필요한 페이지입니다. 로그인 페이지로 이동합니다.");
		     }
		 </script>
     </div>
     <!-- 로그인 -->
     <div class="login">
         <c:choose>
     		 <c:when test="${not empty sessionScope.mdto}">
       			<p class="login_bt_text logout_bt" onmouseover="showLogoutButton()" onmouseout="hideLogoutButton()">${sessionScope.mdto.id}</p>
				<input type="button" class="sm"  value="로그아웃" id="logoutButton" 
						onmouseover="cancelHideLogoutButton()" onmouseout="hideLogoutButton()" onclick="location.href='./LogoutAction.me';">
      		 </c:when>
     	 	 <c:otherwise>
		          <a href="./loginForm.me">
		              <span class="login_bt_text">로그인</span>
		          </a>
      	  	  </c:otherwise>
  	  	 </c:choose>
      </div>
     <!-- 검색창 -->
     <div class="search">
             <form action="./productSearchList.pd">
                 <input type="text" name="search" size="15" placeholder="  상품검색" 
                 style="border: none;width: 150px; height: 30px; font-size: 17px; float: left; border-radius: 10px 0 0 10px">
                 <input type="submit" value="" class="btn_submit">
             </form>
     </div>
     
</body>
</html>