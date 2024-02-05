<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제하기</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css"/>
<link rel="stylesheet" type="text/css" href="./css/layout.css"/>
<link id="pagestyle" href="./css/board.css" rel="stylesheet" />
 <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=<!-- f8p8fgvkm7 -->"></script>
<script src="./js/jquery.js"></script>
<script language="JavaScript" type="text/javascript"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

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

                // 페이지 로드 시 HOME 버튼을 클릭한 상태로 초기화
                const homeButton = $('.mn_btn:eq(5)');
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
					<div class="center-write">
						<div class="center-title">
			              <h6>후기글 삭제</h6>
			            </div>
			            <form action = "./ReviewDeleteAction.bo?pageNum=${param.pageNum }" method="post">	
			            <input type="hidden" name="board_bno" value="${param.board_bno }">
					            
				            <div class="center-detail">
									후기글제목 : ${sessionScope.board_subject } 
				            </div>		                                   
				            <div class="center-detail">
									후기글내용 : ${sessionScope.board_content }
				            </div>
				             <div class="center-detail">
									첨부파일 : <input id="input4" type="file" name="board_file" >
				            </div>
				             <div class="center-detail">
									이미지 : <input id="input5" type="image" name="board_image" >
				            </div>
				             <div class="center-detail">
									글비밀번호 : <input id="input5" type="password" name="board_pass" >
				            </div>
			           
				            <input type="submit" id="center-app" value="삭제하기">	           
							<input type=button  id="center-app" value="목록으로" class="button" onclick="location.href='Review.bo'">
		      
			            </form>
					</div>
	            </div>
	        </div>

			
	    </div>
	     
 
</body>
</html>