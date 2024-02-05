<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통합검색</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css"/>
<link rel="stylesheet" type="text/css" href="./css/layout.css"/>
<link id="pagestyle" href="./css/board.css" rel="stylesheet" />
<script src="./js/jquery.js"></script>

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
    <div class="row">
     <div class="card mb-4">
     	<div class="card-header pb-0">
     		<div class="pd-title">
           	<h6>상품 통합검색</h6>
     		</div>
 	     </div>
 	     	<c:choose>
 	     	<c:when test="${not empty searchProducts }">
	     	 <c:forEach var="product" items="${searchProducts }">
	     	 <div class="product-container">
	     	 	<ul class="pd_list">
	     	 		<li>
	     	 		<c:choose>
	                      	<c:when test="${product.title eq '구매' }">
		                      <div>
		  	     	 			 <a href="./orderDetail.pd?product_id=${product.product_id }"><img src="./img/${product.image }" class="pd_img"></a>
		  	     	 		  </div>
		  	     	 		  <div class="table-container">
   	 								 <a href="./orderDetail.pd?product_id=${product.product_id }"><span class="mb-0 text-sm">${product.name }</span></a>
   	 							  </div>
	                      	</c:when>
	                      	<c:when test="${product.title eq '대여' }">
		                      <div>
		  	     	 			 <a href="./lentalDetail.pd?product_id=${product.product_id }"><img src="./img/${product.image }" class="pd_img"></a>
		  	     	 		  </div>
		  	     	 		  <div class="table-container">
   	 								 <a href="./lentalDetail.pd?product_id=${product.product_id }"><span class="mb-0 text-sm">${product.name }</span></a>
   	 							  </div>
	                      	</c:when>
	                      	<c:otherwise>
	                      		<div>
		  	     	 			 <a href="./saleDetail.pd?product_id=${product.product_id }"><img src="./img/${product.image }" class="pd_img"></a>
		  	     	 		  </div>
		  	     	 		  <div class="table-container">
   	 								 <a href="./saleDetail.pd?product_id=${product.product_id }"><span class="mb-0 text-sm">${product.name }</span></a>
   	 							  </div>
	                      	</c:otherwise>
                      </c:choose>
                      <ul>
	 					<li><p class="mb-0 text-sm">${product.title }</li>
	 					<li><p class="mb-0 text-sm price-color"><fmt:formatNumber value="${product.price}" pattern="#,###" />원</p></li>
	 					<li><p class="mb-0 text-sm">${product.model }</p></li>
	 					<li><p class="mb-0 text-sm"><img src="./icon/적립금.gif"><fmt:formatNumber value="${product.price/100 }" pattern="#,###" />원</p></li>
		   	 		 </ul> 		 	
	     	 		 </li>
	     	 	 </ul>
	     	 </div>
	     	 </c:forEach>
 	     	</c:when>
 	     	<c:otherwise>
 	     	 <div class="no-search">
 	     		<h2>검색한 상품이 없습니다.</h2>
     		  </div>
 	     	</c:otherwise>
 	     	</c:choose>
	     </div>
    </div>
</body>
</html>