<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기글 수정하기</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css" />
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link id="pagestyle" href="./css/board.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="./css/productWriteForm.css"/>
<script src="./js/jquery.js"></script>

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
	<jsp:include page="../inc/left.jsp" />
	<!-- 상단메뉴 -->
	<jsp:include page="../inc/top.jsp" />
	<!-- 메인페이지 -->
	<div id="product-row">
		<form action="./ReviewUpdateProAction.bo?pageNum=${pageNum }" method="post" id="updateForm" enctype="multipart/form-data">
		<input type="hidden" name="board_bno" value="${bdto.board_bno }">
		<div class="productWrite-bg mb-4">
			<div class="card-header pb-0">
			</div>
			<table>
				<tr class="writeForm">
					<th colspan="2" id="th">포토후기 수정하기</th>	
				</tr>
				<tr class="writeForm">
					<td>작성자 :</td>
					<td>${sessionScope.mdto.id }</td>		
				</tr>
				<tr class="writeForm">
					<td>후기 제목 : </td>
					<td><input type="text" name="board_subject" id="sub" placeholder="${bdto.board_subject }" required="required"></td>		
				</tr>
				<tr class="writeForm">
					<td>후기 내용 : </td>
					<td>
						<textarea id="cont"  name="board_content" rows="20" cols="100" placeholder="${bdto.board_content }" required="required"></textarea>
					</td>
				</tr>	
				<tr class="writeForm">
					<td>이미지 첨부</td>
					<td>
						<label class="input-file-button" for="im">업로드</label>	
						<input type="file" name="board_file" id="im">
						<span id="file-name-display"></span>
					</td>		
				</tr>			
			</table>
			<div>
				<input type="submit" value="수정하기" id="button">
				<input type="button" value="목록으로" id="button" onclick="location.href='Review.bo'">
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