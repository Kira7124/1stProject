<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css"/>
<link rel="stylesheet" type="text/css" href="./css/layout.css"/>
<link id="pagestyle" href="./css/board.css" rel="stylesheet" />
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="./js/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="./js/myPage.js"></script>

 		<script>
           
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
              timeout = setTimeout(function() {
                var logoutButton = document.getElementById("logoutButton");
                if (logoutButton) {
                  logoutButton.style.display = "none"; // 버튼을 숨김
                }
              }, 500); // 0.5초 후에 숨김
            }

            function cancelHideLogoutButton() {
              clearTimeout(timeout); // 마우스가 input 태그로 옮겨갔을 때 숨김 동작을 취소
            }
       </script>
       
       
</head>
<body>
	<!-- 왼쪽 메뉴 -->
	<jsp:include page="../inc/left.jsp"/>
	       <!-- 상단메뉴 -->
		<jsp:include page="../inc/top.jsp"/>
        <!-- 메인 -->
        <div class="row" >
           	<div class="card mb-4">
		      	<div class="card-header pb-0">
		       		<h2 style="text-align: center;">기본 회원정보</h2>
		       		<div class="profile-container">
		       		<table class="profile-table">
		       			<tr class= "profile">
		       				<td>이름</td>
		       				<td>${mdto.name }</td>
		       				<td></td>
		       			</tr>
		       			<tr class= "profile">
		       				<td>회원등급</td>
		       				<td>${mdto.grade }</td>
		       				<c:choose>
		       					<c:when test="${mdto.grade eq 'gold' }">
		       						<td><img src="./icon/골드.png" class="grade-img"></td>
		       					</c:when>
		       					<c:when test="${mdto.grade eq 'silver' }">
		       						<td><img src="./icon/실버.png" class="grade-img"></td>
		       					</c:when>
		       					<c:when test="${mdto.grade eq 'bronze' }">
		       						<td><img src="./icon/브론즈.png" class="grade-img"></td>
		       					</c:when>
		       				</c:choose>
		       			</tr>
		       			<tr class= "profile">
		       				<td>아이디</td>
		       				<td>${mdto.id }</td>
		       			</tr>
		       			<tr class= "profile">
		       				<td>현재 비밀번호</td>
		       				<td>********</td>
		       				<td><input type="button" value="비밀번호 변경" class="toggle-button" id="changePasswordBtn"></td>       				
		       			</tr>
		       			<tr class= "profile">
		       				<td>현재 이메일</td>
		       				<td>${mdto.email }</td>
		       				<td><input type="button" value="이메일 변경" class="toggle-button" id="currentEmailBtn">
		       				</td>
		       				
		       			</tr>
		       			<tr style="display: none;" id="changeEmailTr" class= "profile">
		       				<td>변경할 이메일</td>
		       				<td><input type="text" name="newEmail" id="newEmail"></td>
		       				<td><input type="button" value="변경하기" class="toggle-button" id="changeEmailBtn">
		       				</td>
		       			</tr>
		       			<tr class= "profile">
		       				<td>현재 휴대전화</td>
		       				<td>${mdto.phoneNum }</td>
		       				<td><input type="button" value="휴대전화 변경" class="toggle-button" id="currentPhoneNumBtn"></td>
		       			</tr>
		       			<tr style="display: none;" id="changePhoneNumtr" class= "profile">
		       				<td>변경할 휴대전화</td>
		       				<td><input type="tel" id="newPhoneNum" placeholder="예) 01012341234 "></td>
		       				<td><input type="button" value="변경하기" id="changePhoneNumBtn" class="toggle-button" ></td>
		       			</tr>
		       		</table>
		       		</div>

		       		<div class="history-container">
			   			       				 
			       		<form action="PurchaseHistory.od" method="post">
			       			<input type="button" id="purchaseHistory" value="구매내역" onclick="toggleHistory('purchaseHistoryContainer')"> 
			       		</form>
			       		
			       		<form action="RentalHistory.od" method="post">
			       			<input type="button" id="rentalHistory" value="대여내역" onclick="toggleHistory('rentalHistoryContainer')"> 
			       		</form>
			       		
						<form action="SaleHistory.pd" method="post">
						  <input type="button" id="saleHistory" value="판매내역" onclick="toggleHistory('saleHistoryContainer')">
						</form>
						
						<form action="CenterHistory.as" method="post">
						   <input type="button" id="centerHistory" value="수리내역" onclick="toggleHistory('centerHistoryContainer')">
						</form>
			         
						<form action="CartHistory.pd" method="post">
						   <input type="button" id="cartHistory" value="장바구니" onclick="toggleHistory('cartHistoryContainer')">
						</form>
		       		</div>
		       				       		
		       		<div id="purchaseHistoryContainer">
		       			<div id="outputPurchaseHistory"></div>
		       		</div>

		       		<div id="rentalHistoryContainer">
		       			<div id="outputRentalHistory"></div>
		       		</div>
			
					<div id="saleHistoryContainer">
					  <div id="outputSaleHistory"></div>
					</div>

			         <div id="centerHistoryContainer">
			            <div id="outputCenterHistory"></div>
			         </div>	

			         <div id="cartHistoryContainer">
			            <div id="outputCartHistory"></div>
			         </div>		
							
			    	</div>
		   	 </div>
	    </div>
</body>
</html>