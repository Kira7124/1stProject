<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productUpdateForm.jsp</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css"/>
<link rel="stylesheet" type="text/css" href="./css/layout.css"/>
<link rel="stylesheet" type="text/css" href="./css/productWriteForm.css"/>
<link id="pagestyle" href="./css/board.css" rel="stylesheet" />
<link href="./css/saleWriteForm.css" rel="stylesheet">
<script src="./js/jquery.js"></script>
<script language="JavaScript" type="text/javascript"></script>

 		<script>
 		
            // 창 사이즈에 따라 움직이는 제이쿼리문
            $(document).ready(function(){ 
                $('selector').css('width', $(window).width()); 
                $('selector').css('height', $(window).height()); 
                $(window).resize(function() { 
                    $('selector').css('width', $(window).width()); 
                    $('selector').css('height', $(window).height()); 
                }); 
            });
            

            $(function() {
                let currentBtn = null; // 현재 선택된 메뉴 아이콘 저장 변수
                let currentBox = null; // 현재 선택된 Box 저장 변수
                let currentSpan = null; // 현재 선택된 span 태그 저장 변수

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

            
       </script>
</head>
<body>
	<!-- 왼쪽 메뉴 -->
	<jsp:include page="../inc/left.jsp"/>
    <!-- 상단메뉴 -->
	<jsp:include page="../inc/top.jsp"/>
        <!-- 메인페이지 -->
		<div id ="product-row">
		 	<form action="./ProductUpdateProAction.pd" method="post" enctype="multipart/form-data">
		 		<input type="hidden" name="product_id" value="${pdto.product_id}">
			 		<div class="productWrite-bg mb-4">
						<div class="card-header pb-0">
						</div>
						<table>
							<tr class="writeForm">
								<th colspan="4">상품 내용 수정하기</th>
							</tr>
							<tr class="writeForm">
								<td>거래 종류 : </td>
								<td>
								    <c:choose>
									    <c:when test="${pdto.title eq '구매'}">
									        <c:set var="purChecked" value="checked" />
									        <c:set var="rentChecked" value="" />
									        <c:set var="saleChecked" value="" />
									    </c:when>
									    <c:when test="${pdto.title eq '대여'}">
									        <c:set var="purChecked" value="" />
									        <c:set var="rentChecked" value="checked" />
									        <c:set var="saleChecked" value="" />
									    </c:when>
									    <c:when test="${pdto.title eq '판매'}">
									        <c:set var="purChecked" value="" />
									        <c:set var="rentChecked" value="" />
									        <c:set var="saleChecked" value="checked" />
									    </c:when>
									    <c:otherwise>
									        <c:set var="purChecked" value="" />
									        <c:set var="rentChecked" value="" />
									        <c:set var="saleChecked" value="" />
									    </c:otherwise>
									</c:choose>
									
									<input type="radio" name="title" value="구매" required id="pur" ${purChecked}>
									<label class="radio-part" for="pur">구매</label>
									
									<input type="radio" name="title" value="대여" id="rent" ${rentChecked}>
									<label class="radio-part" for="rent">대여</label>
									
									<input type="radio" name="title" value="판매" id="sale" ${saleChecked}>
									<label class="radio-part" for="sale">판매</label>
								</td>
								<td>등록 일시 : </td>
								<td>
									<label for="reg">
										<input type="text" name="regdate" value="${pdto.regdate}" id="reg" readonly>
									</label>
								</td>		
							</tr>
							<tr class="writeForm">
								<td>카테고리 이름 : </td>
								<td>
								
									<c:choose>
									    <c:when test="${pdto.category eq '스마트폰'}">
									        <c:set var="smChecked" value="checked" />
									        <c:set var="taChecked" value="" />
									        <c:set var="weChecked" value="" />
									    </c:when>
									    <c:when test="${pdto.category eq '태블릿'}">
									        <c:set var="smChecked" value="" />
									        <c:set var="taChecked" value="checked" />
									        <c:set var="weChecked" value="" />
									    </c:when>
									    <c:when test="${pdto.category eq '웨어러블'}">
									        <c:set var="smChecked" value="" />
									        <c:set var="taChecked" value="" />
									        <c:set var="weChecked" value="checked" />
									    </c:when>
									    <c:otherwise>
									        <c:set var="smChecked" value="" />
									        <c:set var="taChecked" value="" />
									        <c:set var="weChecked" value="" />
									    </c:otherwise>
									</c:choose>
									
									<input type="radio" name="category" value="스마트폰" required id="sm" ${smChecked } >
									<label class="radio-part" for="sm">스마트폰</label>
										
									<input type="radio" name="category" value="태블릿" id="ta" ${taChecked }>
									<label class="radio-part" for="ta">태블릿</label>
									 	
									<input type="radio" name="category" value="웨어러블" id="we" ${weChecked }>	
									<label class="radio-part" for="we">웨어러블</label>
								</td>
								<td>수정 일시 : </td>
								<td>
									<label for="up">
										<input type="text" name="update_date" value="${pdto.update_date}" id="up" readonly>
									</label>
								</td>	
							<tr class="writeForm">
								<td>제조사 : </td>
								<td colspan="3">
									<c:choose>
									    <c:when test="${pdto.company eq '삼성'}">
									        <c:set var="samChecked" value="checked" />
									        <c:set var="apChecked" value="" />
									    </c:when>
									    <c:when test="${pdto.company eq '애플'}">
									        <c:set var="samChecked" value="" />
									        <c:set var="apChecked" value="checked" />
									    </c:when>
									    <c:otherwise>
									        <c:set var="samChecked" value="" />
									        <c:set var="apChecked" value="" />
									    </c:otherwise>
									</c:choose>
								
									<input type="radio" name="company" value="삼성" required id="sam"  ${samChecked }>
									<label class="radio-part" for="sam">삼성</label>
	
									<input type="radio" name="company" value="애플" id="ap" ${apChecked }>
									<label class="radio-part" for="ap">애플</label>
										
								</td>		
							</tr>			
							<tr class="writeForm">
								<td><label for="mo">모델명 : </label></td>
								<td colspan="3"><input type="text" name="model" id="mo" value="${pdto.model}" autofocus required></td>		
							</tr>
							<tr class="writeForm">
								<td><label for="na">상품 이름 : </label></td>
								<td colspan="3"><input type="text" name="name" id="na" value="${pdto.name}" required></td>		
							</tr>
							<tr class="writeForm">
								<td><label for="co">색상 : </label></td>
								<td colspan="3">
									<input type="text" name="color" id="co" value="${pdto.color}" required>
									<input type="text" name="color2" id="co" value="${pdto.color2}">
									<input type="text" name="color3" id="co" value="${pdto.color3}">
								</td>		
							</tr>
							<tr class="writeForm">
								<td><label for="cap">용량 : </label></td>
								<td colspan="3">
									<input type="text" name="capacity" id="cap" value="${pdto.capacity}" required>
									<input type="text" name="capacity2" id="cap" value="${pdto.capacity2}">
									<input type="text" name="capacity3" id="cap" value="${pdto.capacity3}">
								</td>		
							</tr>
							<tr class="writeForm">
									<td>용량별 가격 : </td>
									<td colspan="3">
										<label for ="op1">
											<input type="text" name="option1" id="op1" pattern="[0-9]+" value="${pdto.option5}" required>
										</label>
										<label for ="op1">
											<input type="text" name="option2" id="op1" pattern="[0-9]+" value="${pdto.option1}">
										</label>
										<label for ="op1">
											<input type="text" name="option3" id="op1" pattern="[0-9]+" value="${pdto.option2}">
										</label> 
									</td>
								</tr>
								<tr class="writeForm">
									<td>등급별 가격 : </td>
									<td colspan="3">
										<label for ="op2">
											<input type="text" name="option4" id="op2" pattern="[0-9]+" value="${pdto.option6}" required>
										</label>
										<label for ="op2">
											<input type="text" name="option5" id="op2" pattern="[0-9]+" value="${pdto.option3}" required>
										</label>
										<label for ="op2">
											<input type="text" name="option6" id="op2" pattern="[0-9]+" value="${pdto.option4}" required>
										</label> 
									</td>
								</tr>
							<tr class="writeForm">
								<td><label for="pr">출고가(기준 가격) : </label></td>
								<td colspan="3"><input type="text" name="price" id="pr" pattern="[0-9]+" value="${pdto.price}" required></td>		
							</tr>
							<tr class="writeForm">
								<td>이미지 첨부</td>
								<td>
									<label class="input-file-button" for="im">업로드</label>	
									<input type="file" name="image" id="im" accept="image/*">
									<span id="file-name-display"></span>
								</td>	
							</tr>			
						</table>
					<div>
						<input type="submit" value="상품 수정하기" id="button">
						<input type="reset" value="다시 입력하기" id="button">
					</div>
					</div>		
			</form>      
	    </div>
	    
	    
	    <script type="text/javascript">
	 		document.getElementById('im').addEventListener('change', function() {
	 		    var fileNameDisplay = document.getElementById('file-name-display');
	 		    var fileName = this.value.split('\\').pop(); // 파일 경로에서 파일명만 추출
	
	 		    if (fileName) {
	 		        fileNameDisplay.textContent = '파일명: ' + fileName;
	 		    } else {
	 		        fileNameDisplay.textContent = ''; // 파일을 선택하지 않았을 때
	 		    }
	 		});
		</script>
</body>
</html>