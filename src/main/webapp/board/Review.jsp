<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포토후기</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css" />
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link id="pagestyle" href="./css/board.css" rel="stylesheet" />
<script src="./js/jquery.js"></script>
<script language="JavaScript" type="text/javascript"></script>

<script>
	// 창 사이즈에 따라 움직이는 제이쿼리문
	$(document).ready(function() {
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
			$btn.closest('.mn_box').css('box-shadow',
					'rgba(0, 0, 0, 0.05) 14px 14px 14px');
			$btn.find('.mn_icon').css('background-color', '#3182ce');
			$btn
					.find('.mn_icon img')
					.css(
							'filter',
							'invert(100%) sepia(0%) saturate(0%) hue-rotate(93deg) brightness(103%) contrast(103%)');
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
						<h6>포토후기</h6>
					</div>
					<div class="card-header pb-0">	
					<c:if test="${sessionScope.mdto.id != null }">	              			  
		              <input type = "button" class="insert-btn" value="후기등록" onclick="location.href='ReviewWrite.bo'">
		            </c:if>
		            </div>
					<div class="card-body px-0 pt-0 pb-2">
						<div class="table-responsive p-0">
							<table class="table align-items-center mb-0">
								<thead>
									<tr>
										<th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7" style="width: 20px;">번호</th>
										<th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7" style="width: 50px;">제품정보</th>
										<th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">제목</th>
										<th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">작성자</th>
										<th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">조회수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="dto" items="${review }">
										<tr>
											<td><div class="d-flex px-2 py-1">
													<div class="d-flex flex-column justify-content-center">
														<h6 class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7" style="margin-left:10px;">${dto.board_bno }</h6>
													</div>
												</div>
											</td>
											<td>
												<a href="./ReviewContent.bo?board_bno=${dto.board_bno }&pageNum=${pageNum}&search=${param.search}&board_file=${dto.board_file }"><img src="./upload/${dto.board_file}" alt="Image" style="max-width: 120%; height: auto;"></a>
											</td>
											<td class="align-middle text-center text-sm">
												<p class="text-xs font-weight-bold mb-0"><a href="./ReviewContent.bo?board_bno=${dto.board_bno }&pageNum=${pageNum}&search=${param.search}&board_file=${dto.board_file }">${dto.board_subject }</a></p>
											</td>
											<td class="align-middle text-center"><span class="text-secondary text-xs font-weight-bold">${dto.id }</span></td>
											<td class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7" >${dto.board_readcount }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div id="table_search">
						   <form action="./Review.bo" method="get" class="search_form">
								<input type="text" name="search" class="input_box">
								<input type="submit" value="검색" class="table-search-btn">
						    </form>
						</div>
			   			<div class="clear"></div>
						<div id="page_control">
							<c:if test="${startPage > pageBlock }">
								<a href="./Review.bo?pageNum=${startPage-pageBlock }&search=${param.search}">이전페이지</a>
							</c:if>
							
							<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
								<a href="./Review.bo?pageNum=${i }&search=${param.search}">${i }</a>
							</c:forEach>
							
							<c:if test="${endPage < pageCount }">
								<a href="./Review.bo?pageNum=${startPage+pageBlock }&search=${param.search}">다음페이지</a>
							</c:if>
						</div>
					</div>
				</div>
		</div>
</body>
</html>