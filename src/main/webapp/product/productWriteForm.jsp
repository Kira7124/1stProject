<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productWriteForm.jsp</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css"/>
<link rel="stylesheet" type="text/css" href="./css/layout.css"/>
<link rel="stylesheet" type="text/css" href="./css/productWriteForm.css"/>
<link id="pagestyle" href="./css/board.css" rel="stylesheet" />
<script src="./js/jquery.js"></script>

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
		<div id="product-row">	
		 	<form action="./ProductWriteAction.pd" method="post" enctype="multipart/form-data">
		 			<div class="productWrite-bg mb-4">
						<div class="card-header pb-0">
			            </div>
	        			<table>
							<tr class="writeForm">
								<th colspan="2" id="th">상품 내용 작성하기</th>	
							</tr>
							<tr class="writeForm">
								<td>거래 종류 : </td>
								<td>
									<input type="radio" name="title"  value="구매" required id="pur" checked="checked">
									<label class="radio-part" for="pur">구매</label>
									<input type="radio" name="title"  value="대여" id="rent">
									<label class="radio-part" for="rent">대여</label>
									<input type="radio" name="title"  value="판매" id="sale">
									<label class="radio-part" for="sale">판매</label>
									
								</td>		
							</tr>
							<tr class="writeForm">	
								<td>카테고리 이름 : </td>
								<td>
									<input type="radio" name="category" value="스마트폰" required id="sm" checked>
									<label class="radio-part" for="sm">스마트폰</label>
									<input type="radio" name="category" value="태블릿" id="ta">
									<label class="radio-part" for="ta">태블릿</label>
									<input type="radio" name="category" value="웨어러블" id="we">
									<label class="radio-part" for="we">웨어러블</label>
								</td>		
							</tr>
							<tr class="writeForm">
								<td>제조사 : </td>
								<td>
									<input type="radio" name="company" value="삼성" required id="sam" checked>
									<label class="radio-part" for="sam">삼성</label>
									<input type="radio" name="company" value="애플" id="ap">
									<label class="radio-part" for="ap">애플</label>
								</td>		
							</tr>
							<tr class="writeForm">
								<td><label for="mo">모델명 : </label></td>
								<td><input type="text" name="model" id="mo" placeholder="예시 : A3090" autofocus required></td>		
							</tr>
							<tr class="writeForm">
								<td><label for="na">상품 이름 : </label></td>
								<td><input type="text" name="name" id="na" placeholder="예시 : 아이폰 15 Pro" required></td>		
							</tr>
							<tr class="writeForm">
								<td>색상 : </td>
								<td>
									<label for="co"><input type="text" name="color" id="co" placeholder="색상1" required></label>
									<label for="co"><input type="text" name="color2" id="co" placeholder="색상2"></label>
									<label for="co"><input type="text" name="color3" id="co" placeholder="색상3"></label>
								</td>
							</tr>
							<tr class="writeForm">
								<td>용량 : </td>
								<td>
									<label for="cap"><input type="text" name="capacity" id="cap" placeholder="용량1, 예시 : 128gb" required></label>	
									<label for="cap"><input type="text" name="capacity2" id="cap" placeholder="용량2"></label>
									<label for="cap"><input type="text" name="capacity3" id="cap" placeholder="용량3"></label>	
								</td>
							</tr>
							<tr class="writeForm">
								<td>용량별 가격 : </td>
								<td>
									<p style="color:red; margin:0 0 0 0;">* 거래 종류가 '판매'일 때는 값을 비워 두세요.</p>
									<label for ="op1"><input type="text" name="option5" id="op1" pattern="[0-9]+" placeholder="용량1 가격, 숫자만 입력, 예시 : 100000"></label>
									<label for ="op1"><input type="text" name="option1" id="op1" pattern="[0-9]+" placeholder="용량2 가격, 숫자만 입력, 예시 : 100000"></label>
									<label for ="op1"><input type="text" name="option2" id="op1" pattern="[0-9]+" placeholder="용량3 가격, 숫자만 입력, 예시 : 100000"></label> 
								</td>
							</tr>
							<tr class="writeForm">
								<td>등급별 가격 : </td>
								<td>
									<p style="color:red; margin:0 0 0 0;">* 거래 종류가 '판매'일 때는 값을 비워 두세요.</p>
									<label for ="op2"><input type="text" name="option6" id="op2" pattern="[0-9]+" placeholder="S등급 가격, 숫자만 입력, 예시 : 100000"></label>
									<label for ="op2"><input type="text" name="option3" id="op2" pattern="[0-9]+" placeholder="A등급 가격, 숫자만 입력, 예시 : 100000"></label>
									<label for ="op2"><input type="text" name="option4" id="op2" pattern="[0-9]+" placeholder="B등급 가격, 숫자만 입력, 예시 : 100000"></label> 
								</td>
							</tr>
							<tr class="writeForm">
								<td><label for="pr">출고가(기준 가격) : </label></td>
								<td><input type="text" name="price" id="pr" pattern="[0-9]+" placeholder="숫자만 입력 가능, 예시 : 1550000" required></td>		
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
							<input type="submit" value="상품 등록하기" id="button">
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