<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>폰 미쳤다!!</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="css/layout.css"/>
    <link rel="stylesheet" id="pagestyle" href="css/board.css"  />
    <script src="js/jquery.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script language="JavaScript" type="text/javascript"></script>
    
    
    <script>
          /*   // 창 사이즈에 따라 움직이는 제이쿼리문
            $(document).ready(function(){ 
                $('selector').css('width', $(window).width()); 
                $('selector').css('height', $(window).height()); 
                $(window).resize(function() { 
                    $('selector').css('width', $(window).width()); 
                    $('selector').css('height', $(window).height()); 
                }); 
            }); */

            $(function(){
                // 변수 생성
                var current = 0;
                var setIntervalId;
                var $btns = $('.btns li');      // 슬라이드 버튼 변수 생성
                
                //초기값 첫번째 버튼으로 설정
                $btns.eq(0).addClass('active');

                // 슬라이드 버튼 클릭 시 해당 페이지로 이동
                $('.btns li').click(function(){
                        var i = $(this).index();
                        move(i);
                        //console.log(i)
                        $btns.removeClass('active'); // 모든 버튼에서 'active' 클래스 제거
                         $(this).addClass('active'); // 클릭된 버튼에 'active' 클래스 추가
                });

                $('.imgs').on({
                        // 슬라이드 마우스 오버 시 멈춤
                        mouseover:function(){
                            clearInterval(setIntervalId)
                        },
                        // 슬라이드 마우스 아웃 시 작동
                        mouseout:function(){
                            timer();  
                        }     
                });

                // 슬라이드 넘어가는 시간 함수
                timer();
                function timer(){
                    setIntervalId = setInterval(function(){
                    var n = current + 1;
                    if(n==4){
                    n=0; 
                    }
                    move(n);  
                    }, 3000);
                }
            

                // 슬라이드 이동 함수
                function move(i) {
                    if (current == i) return;

                    // 현재 활성 버튼에서 클래스 제거
                    $btns.eq(current).removeClass('active');

                    var currentEl = $('.imgs > li').eq(current);
                    var nextEl = $('.imgs > li').eq(i);

                    currentEl.css({ left: "0%" }).animate({ left: "-100%" });
                    nextEl.css({ left: "100%" }).animate({ left: "0%" });

                    current = i;

                    // 새로운 활성 버튼에 클래스 추가
                    $btns.eq(current).addClass('active');
                }

                

            });
            
            

            $(function() {
                let currentBtn = null; // 현재 선택된 메뉴 아이콘 저장 변수
                let currentBox = null; // 현재 선택된 Box 저장 변수
                let currentSpan = null; // 현재 선택된 span 태그 저장 변수

                // 페이지 로드 시 HOME 버튼을 클릭한 상태로 초기화
                const homeButton = $('.mn_btn:first');
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
	<jsp:include page="./inc/left.jsp"/>
        <!-- 상단메뉴 -->
		<jsp:include page="./inc/top.jsp"/>
        <!-- 메인이미지 -->
        <div id="main_img">
            <div class="img_area">
                <ul class="imgs">
                    <li>
                	 	<a href="./center.as">
               	 			<img src="./img/배너1.png">
                	 	</a>
            	 	</li>                	 	
                    <li>
                    	<img src="./img/배너2.png">
                    </li>
                    <li>
                    	<a href="./saleList.pd">
                    		<img src="./img/배너3.jpg">
                    	</a>
                    </li>
                    <li>
                    	<img src="./img/배너4.jpg">
                    </li>
                </ul>
                <!-- 메인이미지 버튼부분 -->
                <ul class="btns">
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>
            <!-- 하단이미지 부분 -->
            <div class="footer_imgs">
                <img src="./img/footer_top_content.png">
            </div>
        </div>
</body>
</html>