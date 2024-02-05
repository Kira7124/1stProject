<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>A/S 센터</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css" />
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link id="pagestyle" href="./css/board.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="./css/productWriteForm.css"/>
<script type="text/javascript"
	src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=f8p8fgvkm7"></script>
<script src="./js/jquery.js"></script>
<script src="./js/center.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>

	$(function() {
		let currentBtn = null; // 현재 선택된 메뉴 아이콘 저장 변수
		let currentBox = null; // 현재 선택된 Box 저장 변수
		let currentSpan = null; // 현재 선택된 span 태그 저장 변수

		// 페이지 로드 시 HOME 버튼을 클릭한 상태로 초기화
		const homeButton = $('.mn_btn:eq(4)');
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

	var inputField = document.getElementById("input2");
	inputField.addEventListener("input", function() {
		// 입력 필드 내의 텍스트가 맨 위에서 시작하도록 조절
		inputField.style.paddingTop = "0";
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
	<script type="text/javascript">
		function check() {
			var as_model = document.fr.as_model.value;
			var as_phone_num = document.fr.as_phone_num.value;
			var as_name = document.fr.as_name.value;
			var as_zip_code = document.fr.as_zip_code.value;
			var as_address = document.fr.as_address.value;
			var as_address_detail = document.fr.as_address_detail.value;
			if (as_model == "") {
	 			alert('모델명을 입력하세요');
	 			document.fr.as_model.focus();
	 			return false;	
	 		}
			if (as_phone_num == "") {
	 			alert('휴대폰 번호를 입력하세요');
	 			document.fr.as_phone_num.focus();
	 			return false;	
	 		}
			if (as_name == "") {
	 			alert('이름을 입력하세요');
	 			document.fr.as_name.focus();
	 			return false;	
	 		}
			if (as_zip_code == "") {
	 			alert('주소를 입력하세요');
	 			document.fr.as_zip_code.focus();
	 			return false;	
	 		}
			if (as_address == "") {
	 			alert('주소를 입력하세요');
	 			document.fr.as_address.focus();
	 			return false;	
	 		}
			if (as_address_detail == "") {
	 			if(confirm('상세주소가 입력되지 않았습니다. 그대로 신청하시겠습니까?') == true){
	 				document.fr.submit();
	 			}else {
	 				document.fr.as_address_detail.focus();
		 			return false;	
				}
	 		}
		}
	</script>
	<%
    String[] phoneNums = {"010", "011", "016", "017", "018", "019"};
	%>
	<!-- 왼쪽 메뉴 -->
	<jsp:include page="../inc/left.jsp" />
	<!-- 상단메뉴 -->
	<jsp:include page="../inc/top.jsp" />
	<!-- 메인페이지 -->
	<div id="product-row">
		<form action="CenterEditActionPro.as" method="post" name="fr" onsubmit="return check();">
		<input type="hidden" name="as_id" value="${cdto.as_id }">
		<div class="card">
			<div class="card-header pb-0 mb-4">
				<h6>A/S 센터</h6>
			</div>
				<div id="map"></div>
				<div id="center-container">
				<table>
				<tr class="writeForm">
					<th colspan="2" id="th">수리 신청서</th>	
				</tr>
				<tr class="writeForm">
					<td>기종 : </td>
					<td>
						<input id="input1" type="text" name="as_model" value="${cdto.as_model }">
					</td>		
				</tr>
				<tr class="writeForm">
					<td>휴대폰 번호 : </td>
					<td>
						<select id="input3" name="first_phone_num">
			    				<option value="${cdto.as_phone_num.substring(0, 3)}">${cdto.as_phone_num.substring(0, 3)}</option>
			    				<c:forEach var="first_num" items="<%=phoneNums %>">
			        				<c:if test="${first_num != cdto.as_phone_num.substring(0, 3)}">
			          					<option value="<c:out value='${first_num}'/>"><c:out value='${first_num}'/></option>
			       					</c:if>
			    				</c:forEach>
							</select>
						 <a id="hyphen">-</a>
						<input id="input3" type="text" name="mid_phone_num"  maxlength="4" placeholder="휴대폰 번호를" value="${cdto.as_phone_num.substring(3, 7)}">
						<a id="hyphen">-</a>
						<input id="input3" type="text" name="last_phone_num"  maxlength="4" placeholder="작성하세요"  value="${cdto.as_phone_num.substring(7)}">
			 	 	</td>		
				</tr>
				<tr class="writeForm">
					<td>신청자 :  </td>
					<td><input id="input1" type="text" name="as_name" value="${cdto.as_name }"></td>		
				</tr>
				<tr class="writeForm">
					<td>수리희망일 : </td>
					<td>
						<input id="input1" type="date" name="as_due_date" value="${cdto.as_due_date }">
					</td>
				</tr>
				<tr class="writeForm">
					<td>수리요청사항 : </td>
					<td>
						<textarea id="input-req"  name="as_comment" rows="4" cols="30">${cdto.as_comment }</textarea>
					</td>		
				</tr>			
				<tr class="writeForm">
					<td>주소 : </td>
					<td>
						
						<input type="text" id="sample6_postcode" name="as_zip_code" value="${cdto.as_zip_code }" >
						<input type="button" id="sample6_button" onclick="sample6_execDaumPostcode()" value="주소 검색" style="outline: none;"><br>
						<input type="text" id="sample6_address" name="as_address" value="${cdto.as_address }"><br>
						<input type="text" id="sample6_detailAddress" name="as_address_detail" value="${cdto.as_address_detail }">
						<input type="text" id="sample6_extraAddress" name="as_reference" value="${cdto.as_reference }">
					</td>		
				</tr>					
			</table>
			<div>
				<input type="submit" value="수정하기" id="button">
				<input type="button" id="button" value="삭제하기" onclick="location.href='./CenterDeleteAction.as?as_id=${cdto.as_id }';">
			</div>	
			</div>
			</div>
		</form>
	</div>
	<script>
		// 네이버 지도 API 스크립트 로드 완료 후 실행
		var map = new naver.maps.Map('map', {
			center : new naver.maps.LatLng(35.1584043, 129.0620349), // 부산 아이티윌 좌표
			zoom : 15
		});

		var marker = new naver.maps.Marker({
			position : new naver.maps.LatLng(35.1584043, 129.0620349),
			map : map
		});
	</script>

	<script>
		function sample6_execDaumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var addr = ''; // 주소 변수
							var extraAddr = ''; // 참고항목 변수

							//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
							if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
								addr = data.roadAddress;
							} else { // 사용자가 지번 주소를 선택했을 경우(J)
								addr = data.jibunAddress;
							}

							// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
							if (data.userSelectedType === 'R') {
								// 법정동명이 있을 경우 추가한다. (법정리는 제외)
								// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
								if (data.bname !== ''
										&& /[동|로|가]$/g.test(data.bname)) {
									extraAddr += data.bname;
								}
								// 건물명이 있고, 공동주택일 경우 추가한다.
								if (data.buildingName !== ''
										&& data.apartment === 'Y') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								if (extraAddr !== '') {
									extraAddr = ' (' + extraAddr + ')';
								}
								// 조합된 참고항목을 해당 필드에 넣는다.
								document.getElementById("sample6_extraAddress").value = extraAddr;

							} else {
								document.getElementById("sample6_extraAddress").value = '';
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('sample6_postcode').value = data.zonecode;
							document.getElementById("sample6_address").value = addr;
							// 커서를 상세주소 필드로 이동한다.
							document.getElementById("sample6_detailAddress")
									.focus();
						}
					}).open();
		}
	</script>

</body>
</html>