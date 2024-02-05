<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
                    <a href="./orderList.pd" class="mn_btn">
                            <div class="mn_icon">
                                <img src="./icon/중고판매.png">
                            </div>
                            <span class="clickable-span">내 기기 구매</span>
                    </a>
                </div>
                <!-- 중고대여버튼 -->
                <div class="mn_box">
                    <a href="./lentalList.pd" class="mn_btn">
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
                    <a href="./center.as" class="mn_btn">
                            <div class="mn_icon">
                                <img src="./icon/수리센터.png">
                            </div>
                            <span class="clickable-span">A/S 센터</span>
                    </a>
                </div>
                <!-- 포토후기버튼 -->
                <div class="mn_box">
                    <a href="./Review.bo" class="mn_btn">
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
                    <a href="./QNA.bo" class="mn_btn">
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
</body>
</html>