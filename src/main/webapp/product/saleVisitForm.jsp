<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 기기 판매</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css" />
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link id="pagestyle" href="./css/board.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="./css/productWriteForm.css"/>
<script src="./js/jquery.js"></script>
<script src="./js/saleVisitForm.js"></script>
<script>

	$(function() {
		let currentBtn = null; // 현재 선택된 메뉴 아이콘 저장 변수
		let currentBox = null; // 현재 선택된 Box 저장 변수
		let currentSpan = null; // 현재 선택된 span 태그 저장 변수

		// 페이지 로드 시 HOME 버튼을 클릭한 상태로 초기화
		const homeButton = $('.mn_btn:eq(3)');
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
	<script type="text/javascript">
		function check() {
			var sale_name = document.fr.sale_name.value;
			var mid_phone_num = document.fr.mid_phone_num.value;
			var last_phone_num = document.fr.last_phone_num.value;
			var sale_bank_name = document.fr.sale_bank_name.value;
			var sale_account_num = document.fr.sale_account_num.value;
			var sale_owner_name = document.fr.sale_owner_name.value;
			var sale_visit_date = document.fr.sale_visit_date.value;
			
			if (sale_name == "") {
	 			alert('이름을 입력하세요');
	 			document.fr.sale_name.focus();
	 			return false;	
	 		}
		}
	</script>
	<!-- 왼쪽 메뉴 -->
	<jsp:include page="../inc/left.jsp" />
	<!-- 상단메뉴 -->
	<jsp:include page="../inc/top.jsp" />

	<!-- 메인페이지 -->
	<div id="product-row">
		<form action="SaleVisitActionPro.pd" method="post">
		<input type="hidden" value="${pdto.name }" name="sale_model">
		<input type="hidden" value="${price}" name="sale_expected_price">
		<div class="card">
			<div class="card-header pb-0">
				<h6>내 기기 판매</h6>
			</div>
			<div id="saleVisit-img">
				<img src="./img/${pdto.image }" alt="${pdto.name }">
			</div>
				<div id="saleVisitForm-container">
				<table>
				<tr class="writeForm">
					<th colspan="2" id="th">${pdto.name } 방문 판매 신청서</th>	
				</tr>
				<tr class="writeForm">
					<td>이름 : </td>
					<td>
						<input id="input1" type="text" name="sale_name" placeholder="이름을 작성하세요." required>
					</td>		
				</tr>
				<tr class="writeForm">
					<td>휴대폰번호 : </td>
					<td>
						<select id="input3" name="first_phone_num">
					 		<option value="010">010</option>
					 		<option value="011">011</option>
					 		<option value="016">016</option>
					 		<option value="017">017</option>
					 		<option value="018">018</option>
					 		<option value="019">019</option>
						 </select>
						 <input id="input3" type="text" name="mid_phone_num"  maxlength="4" placeholder="휴대폰 번호를" required>
					 	 <input id="input3" type="text" name="last_phone_num"  maxlength="4" placeholder="작성하세요" required>
			 	 	</td>		
				</tr>
				<tr class="writeForm">
					<td>계좌정보 :  </td>
					<td class="select-td">
						<select id="input4" name="sale_bank_name">
							<option value="0">은행선택</option>
							<option value="국민은행">국민은행</option>
							<option value="기업은행">기업은행</option>
							<option value="농협은행">농협은행</option>
							<option value="산업은행">산업은행</option>
							<option value="수협은행">수협은행</option>
							<option value="신한은행">신한은행</option>
							<option value="우리은행">우리은행</option>
							<option value="우체국">우체국</option>
							<option value="하나은행">하나은행</option>
							<option value="한국씨티은행">한국씨티은행</option>
							<option value="SC제일은행">SC제일은행</option>
							<option value="경남은행">경남은행</option>
							<option value="광주은행">광주은행</option>
							<option value="대구은행">대구은행</option>
							<option value="부산은행">부산은행</option>
							<option value="전북은행">전북은행</option>
							<option value="제주은행">제주은행</option>
							<option value="새마을금고">새마을금고</option>
							<option value="신협중앙회">신협중앙회</option>
							<option value="카카오뱅크">카카오뱅크</option>
							<option value="케이뱅크">케이뱅크</option>
							<option value="토스뱅크">토스뱅크</option>
						</select>
						<input id="input4" type="text" name="sale_account_num" placeholder="계좌번호를 작성하세요." required>
						<input id="input4" type="text" name="sale_owner_name" placeholder="예금주명을 작성하세요." required>
					</td>		
				</tr>
				<tr class="writeForm">
					<td>방문예정일 : </td>
					<td>
						<input id="input1" type="date" name="sale_visit_date" required>
					</td>
				</tr>	
				<tr class="writeForm">
					<td>예상 판매가 : </td>
					<td>
						<h3 class="sale-price"><fmt:formatNumber value="${price}" pattern="#,###" />원</h3>
					</td>
				</tr>		
			</table>
			<div>
				<input type="submit" value="신청하기" id="button">
				<input type="button" value="목록으로" id="button" onclick="location.href='./saleList.pd'">
			</div>	
			</div>
			</div>
			</form>
		</div>

</body>
</html>