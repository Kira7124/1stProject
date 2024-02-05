<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 기기 대여</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css"/>
<link rel="stylesheet" type="text/css" href="./css/layout.css"/>
<link id="pagestyle" href="./css/board.css" rel="stylesheet" />
<script src="./js/jquery.js"></script>

 		<script>
 		
            // 창 사이즈에 따라 움직이는 제이쿼리문
         /*    $(document).ready(function(){ 
                $('selector').css('width', $(window).width()); 
                $('selector').css('height', $(window).height()); 
                $(window).resize(function() { 
                    $('selector').css('width', $(window).width()); 
                    $('selector').css('height', $(window).height()); 
                }); 
            }); */
            

            $(function() {
                let currentBtn = null; // 현재 선택된 메뉴 아이콘 저장 변수
                let currentBox = null; // 현재 선택된 Box 저장 변수
                let currentSpan = null; // 현재 선택된 span 태그 저장 변수

                // 페이지 로드 시 HOME 버튼을 클릭한 상태로 초기화
                const homeButton = $('.mn_btn:eq(2)');
                setButtonStyle(homeButton);

                // 클릭 가능한 모든 메뉴 아이콘에 대한 이벤트 처리
                $('.mn_btn').click(function() {
                    if (currentBtn !== null && !$(this).is(currentBtn)) {
                        resetButtonStyle(currentBtn);
                    }
                    setButtonStyle($(this));
                });

                // 스타일 초기화 함수
                function resetButtonStyle($btn) {
                    $btn.closest('.mn_box').css('box-shadow', 'none');
                    $btn.find('.mn_icon').css('background-color', '#fff');
                    $btn.find('.mn_icon img').css('filter', '');
                    $btn.find('.clickable-span').css('color', '#A0AEC0');
                }

                // 스타일 설정 함수
                function setButtonStyle($btn) {
                    $btn.closest('.mn_box').css('box-shadow', 'rgba(0, 0, 0, 0.05) 14px 14px 14px');
                    $btn.find('.mn_icon').css('background-color', '#3182ce');
                    $btn.find('.mn_icon img').css('filter', 'invert(100%) sepia(0%) saturate(0%) hue-rotate(93deg) brightness(103%) contrast(103%)');
                    $btn.find('.clickable-span').css('color', '#000');
                    currentBtn = $btn;
                }
            });

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
         <!-- 메인페이지 -->	      
	       <div class="row">
	       <div class="card mb-4">
		      	<div class="card-header pb-0">
		      		<div class="pd-title">
		            <h6>내 기기 대여</h6>
		      		</div>
		      		<div class="pd-subtitle">
			      	<form action="./LentalListAction.pd" method="post" >
						<input type="submit" id="product-btn" name ="category" value="스마트폰">
						<input type="submit" id="product-btn" name ="category"  value="태블릿">
						<input type="submit" id="product-btn" name ="category"  value="웨어러블">
						<input type="submit" id="product-btn" name ="company"  value="삼성">
						<input type="submit" id="product-btn" name ="company"  value="애플">	
			  	     </form>
		      		</div>
		      		<c:if test="${sessionScope.mdto.id eq 'admin' }">
	      				<button type="submit" class="insert-btn" value="등록하기" onclick="location.href='ProductWrite.pd'">등록하기</button>
	      			</c:if>
		  	     </div>
	  	     	 <c:forEach var="dto" items="${productList}">
	  	     	 <div class="product-container">
	  	     	 	<ul class="pd_list">
	  	     	 		<li>
	  	     	 		<div>
	  	     	 			<a href="./lentalDetail.pd?product_id=${dto.product_id }"><img src="./img/${dto.image }" class="pd_img"></a>
	  	     	 		</div>
	  	     	 		<div class="table-container">
	     	 				<a href="./lentalDetail.pd?product_id=${dto.product_id }"><span class="mb-0 text-sm">${dto.name }</span></a>
	     	 			</div>
	     	 			<ul>
	     	 					<li><p class="mb-0 text-xxs">일일 대여 가격</p></li>
	     	 					<li><p class="mb-0 text-sm price-color"><fmt:formatNumber value="${dto.price}" pattern="#,###" />원</p></li>
	     	 					<li><p class="mb-0 text-sm">${dto.model }</p></li>
	     	 					<li><p class="mb-0 text-sm"><img src="./icon/적립금.gif"><fmt:formatNumber value="${dto.price/100 }" pattern="#,###" />원</p></li>
	     	 			</ul>
	  	     	 		</li>
	  	     	 	</ul>
	  	     	 </div>
	  	     	 </c:forEach>
	  	     </div>
	      </div>
</body>
</html>