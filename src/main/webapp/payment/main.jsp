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
    <!-- 전체 창 -->
    <div id="wrap">
        <!-- 뒷배경 -->
        <div class="bg"></div>
        <!-- 메뉴 -->
        <div class="main">
            <!-- 로고 -->
            <div class="logo">
                <a href="main.ma"><img src="./icon/logo.png"></a>
            </div>
            <!-- 메인메뉴 -->
            <div class="main_mn">
                <!-- 홈버튼 -->
                <div class="mn_box">
                    <a href="main.ma" class="mn_btn"> 
                            <div class="mn_icon">
                                <img src="./icon/home.png">
                            </div>
                            <span class="clickable-span">HOME</span>
                    </a>
                </div>
                <!-- 중고매입버튼 -->
                <div class="mn_box">
                    <a href="./product/orderList.jsp" class="mn_btn">
                            <div class="mn_icon">
                                <img src="./icon/중고판매.png">
                            </div>
                            <span class="clickable-span">내 기기 구매</span>
                    </a>
                </div>
                <!-- 중고대여버튼 -->
                <div class="mn_box">
                    <a href="./product/lentalList.jsp" class="mn_btn">
                            <div class="mn_icon">
                                <img src="./icon/기기대여.png">
                            </div>
                            <span class="clickable-span">내 기기 대여</span>
                    </a>
                </div>
                <!-- 중고판매버튼 -->
                <div class="mn_box">
                    <a href="./saleList.pd" class="mn_btn">
                            <div class="mn_icon">
                                <img src="./icon/중고매입.png">
                            </div>
                            <span class="clickable-span">내 기기 판매</span>
                    </a>
                </div>
                <!-- 기기대여버튼 -->
                <div class="mn_box">
                    <a href="./center/center.jsp" class="mn_btn">
                            <div class="mn_icon">
                                <img src="./icon/수리센터.png">
                            </div>
                            <span class="clickable-span">A/S 센터</span>
                    </a>
                </div>
                <!-- 포토후기버튼 -->
                <div class="mn_box">
                    <a href="./board/Review.jsp" class="mn_btn">
                            <div class="mn_icon">
                                <img src="./icon/포토후기.png">
                            </div>
                            <span class="clickable-span">포토후기</span>
                    </a>
                </div>
                
                <!-- 고객센터 부분 -->
                <div class="mn_center">
                <h2>고객센터</h2>
                
                <!-- 공지사항 부분 -->
                <div class="mn_box">
                    <a href="./Announcement.bo" class="mn_btn">
                            <div class="mn_icon">
                                <img src="./icon/공지사항.png">
                            </div>
                            <span class="clickable-span">공지사항</span>
                    </a>
                </div>
                <!-- Q&A 부분 -->
                <div class="mn_box">
                    <a href="./Q&A.bo" class="mn_btn">
                            <div class="mn_icon">
                                <img src="./icon/Q&A.png">
                            </div>
                            <span class="clickable-span">Q&A</span>
                    </a>
                </div>
            </div>
        </div>
            <!-- 메뉴 하단부분 -->
            <div class="footer">
                <h1>SHOP GUIDE</h1>
                <h1>050-7871-2220</h1>
                <p>근무시간</p>
                <p>오전 10:00 ~ 오후19:00</p>
                <o>주말 및 공휴일 휴무</o>
            </div>
        </div>
        <!-- 마이페이지 -->
        <div class="mypage">
            <a href="./member/myPage.jsp">
               <span class="mypage_bt_icon"><img src="./icon/마이페이지.png"></span>
            </a>
        </div>
        <!-- 로그인 -->
        <div class="login">
            <c:choose>
		       <c:when test="${not empty sessionScope.mdto}">
		        <p class="login_bt_text logout_bt" onmouseover="showLogoutButton()" onmouseout="hideLogoutButton()">${sessionScope.mdto.id}</p>
				<input type="button" class="sm"  value="로그아웃" id="logoutButton" 
						onmouseover="cancelHideLogoutButton()" onclick="location.href='./LogoutAction.me';">
		       </c:when>
		       <c:otherwise>
		           <a href="./loginForm.me">
		               <p class="login_bt_text">로그인</p>
		           </a>
		       </c:otherwise>
		   </c:choose>
        </div>
        <!-- 검색창 -->
        <div class="search">
                <form action="">
                    <input type="text" name="search" size="15" placeholder="  검색" 
                    style="border: none;width: 150px; height: 30px; font-size: 17px; float: left; border-radius: 10px 0 0 10px">
                    <input type="submit" value="" class="btn_submit">
                </form>
        </div>

        <!-- 메인이미지 -->
        <div id="main_img">
            <div class="img_area">
                <ul class="imgs">
                    <li><img src="./img/배너1.png"></li>
                    <li><img src="./img/배너2.png"></li>
                    <li><img src="./img/배너3.png"></li>
                    <li><img src="./img/배너4.png"></li>
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
    
    </div>	
</body>
</html>