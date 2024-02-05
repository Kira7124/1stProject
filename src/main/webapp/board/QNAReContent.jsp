<%@page import="com.project4.board.db.BoardDTO"%>
<%@page import="javax.swing.plaf.basic.BasicTabbedPaneUI"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글답변</title>
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
		const homeButton = $('.mn_btn:eq(7)');
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
	<jsp:include page="../inc/left.jsp" />
	<!-- 상단메뉴 -->
	<jsp:include page="../inc/top.jsp" />
	<!-- 메인페이지 -->
	<div class="row">
		<div class="card">
			<div class="card-header pb-0 mb-4">
				<div class="section section-properties">
					<div class="container">
						<div class="two_third first" style="height: auto">
							<div class="col-lg-12">
								<h2 class="font-weight-bold text-primary heading">
									<b>문의글답변</b>
								</h2>
								<hr />
								<br> <br>
								<div class="two_third first">
									<form method=post action="./QNAContent.bo" id="acc">
										<table class="table">
											<tr>
												<th width=20% class="text-right">글번호</th>
											</tr>
											<tr>
												<td>${dto.board_bno }</td>
											</tr>
											<tr>
												<th width=20% class="text-right">답변자아이디</th>
											</tr>
											<tr>
												<td width=80%>${dto.id }</td>
											</tr>
																	<tr>
												<th width=20% class="text-right">답변제목</th>
											</tr>
											<tr>
												<td>${dto.board_subject }</td>
											</tr>
											<tr>
												<th width=20% class="text-right">답변내용</th>
											</tr>
											<tr>
												<td width=200%>${dto.board_content }</td>
											</tr>

											<tr>
												<td colspan="2" class="text-center"><input
													type="button" value="목록으로" id="review-list-btn"
													onclick="location.href='./QNA.bo?pageNum=${pageNum}'"></td>
											</tr>
										</table>
									</form>
									<c:if test="${sessionScope.mdto.id eq 'admin' }">
										<div class="col-md-4">
											<input type="button" value="수정하기" id="center-app"
												onclick="location.href='./QNAUpdate.bo?board_bno=${dto.board_bno}&pageNum=${pageNum }&id=${sessionScope.id }&board_subject=${dto.board_subject }&board_content=${dto.board_content }';">
										</div>
										<div class="col-md-4">
											<input type="button" value="삭제하기" id="center-app"
												onclick="location.href='./QNADelete.bo?board_bno=${dto.board_bno}&pageNum=${pageNum }';">
										</div>
										<div class="col-md-4">
											<input type="button" value="답글쓰기" id="center-app"
												onclick="location.href='./QNAReWrite.bo?board_bno=${dto.board_bno}&pageNum=${pageNum }&re_ref=${dto.re_ref}&re_lev=${dto.re_lev}&re_seq=${dto.re_seq }';">
										</div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>