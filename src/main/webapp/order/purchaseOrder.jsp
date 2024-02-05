<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,javax.servlet.http.*"%>
<%@ page import="java.text.NumberFormat" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문/결제</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css" />
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link id="pagestyle" href="./css/board.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<script src="./js/jquery.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>


	$(function() {
		let currentBtn = null; // 현재 선택된 메뉴 아이콘 저장 변수
		let currentBox = null; // 현재 선택된 Box 저장 변수
		let currentSpan = null; // 현재 선택된 span 태그 저장 변수

		// 페이지 로드 시 HOME 버튼을 클릭한 상태로 초기화
		const homeButton = $('.mn_btn:eq(1)');
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
	<%
	String price = request.getParameter("price");
	String name = request.getParameter("name");
	String status1 = request.getParameter("status1");
	String status2 = request.getParameter("status2");
	String status3 = request.getParameter("status3");
	String imageURL = request.getParameter("img");
	session.getAttribute("id");
	NumberFormat formatter = NumberFormat.getInstance();
    String formattedPrice = formatter.format(Integer.parseInt(price));
	
	%>
	<!-- 왼쪽 메뉴 -->
	<jsp:include page="../inc/left.jsp" />
	<!-- 상단메뉴 -->
	<jsp:include page="../inc/top.jsp" />
	<!-- 메인페이지 -->
	<div class="row">
		<div class="card mb-4">
			<div class="card-header pb-0">
				<h6>주문/결제</h6>
			</div>
		<div class="purchase-container">
			<div class="purchaseOrder-write">
				<div class="purchaseOrder-title">
					<h6>주문 정보</h6>
			<!-- 		<button id="toggleButton">
						<i class="fas fa-chevron-down"></i>
					</button> -->
				</div>
					<div class="purchaseOrder-detail">
						신청자 : <input id="input1" type="text" name="name" placeholder="신청자 이름을 작성하세요" required>
					</div>
					<div class="purchaseOrder-detail">
						이메일 : <input id="input3" type="text" name="phone_num" placeholder="이메일을 작성하세요" required>
					</div>
					<div class="purchaseOrder-detail">
						휴대폰 번호 : <input id="input2" type="text" name="phone_num" placeholder="휴대폰 번호를 작성하세요 ex)010-1234-5678" required>
					</div>
	
					<div class="purchaseOrder-detail">
						주소 :
						<div style="float: right;">
							<input type="text" id="sample6_postcode" name="zip_code" placeholder="우편번호" required>
							 <input type="button" id="sample6_button" onclick="sample6_execDaumPostcode()" value="주소 검색" style="outline: none;"><br> 
							 <input type="text" id="sample6_address" name="address" placeholder="주소"><br> 
							 <input type="text" id="sample6_detailAddress" name="address_detail" placeholder="상세주소"> 
							 <input type="text" id="sample6_extraAddress" name="refrence" placeholder="참고항목">
						</div>
					</div>
			</div>
			<div class="order-notice-table">
				<div class="order-notice-title">
					<h6>상품정보</h6>
			<!-- 		<button id="toggleButton2">
						<i class="fas fa-chevron-down"></i>
					</button> -->
				</div>
				<div class="order-notice">
					<img src="./img/<%=imageURL%>" class="purchaseOrder-img" alt="Product Image">
				</div>
				<div class="order-notice1">
					<p>
						상품명:
						<%=name%></p>
					<p>
						용량:
						<%=status1%></p>
					<p>
						색상:
						<%=status2%></p>
					<p>
						등급:
						<%=status3%></p>
					<p>
						가격:
						<%=formattedPrice%>원
					</p>
				</div>
				<div class="order-pay">
					<h3>
						상품이름:<%=name%>
					</h3>
					<span id="payment-method">
						가격:<%=formattedPrice%>원
					</span>
					<p>-------------------------------</p>
					<div>
						<label><input type="radio" name="method" value="카드" checked>신용카드</label>
					</div>
					<div>
						<label><input type="radio" name="method" value="가상계좌">가상계좌</label>
					</div>
					<p>-------------------------------</p>
					<button id="payment-request-button" onclick="handlePayment()" data-id="<%= session.getAttribute("id") %>">결제하기</button>
					<script type="text/javascript"> 
					// 위의 결제를취소하시겠습니까? -> 결제가취소되었습니다! 라고 뜨게끔 ????
		      		</script>
				</div>
			 </div>
		   </div>
		</div>
	</div>


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
				if (confirm('상세주소가 입력되지 않았습니다. 그대로 신청하시겠습니까?') == true) {
					document.fr.submit();
				} else {
					document.fr.as_address_detail.focus();
					return false;
				}
			}
		}
	</script>

	<script src="https://js.tosspayments.com/v1/payment-widget"></script>
	<script>

	function handlePayment() {
		var id = document.getElementById("payment-request-button").getAttribute("data-id");
	    var name = document.getElementById("input1").value;
	    var phoneNum = document.getElementById("input2").value;
	    var zipCode = document.getElementById("sample6_postcode").value;
	    var address = document.getElementById("sample6_address").value;
	    var addressDetail = document.getElementById("sample6_detailAddress").value;
	    var email = document.getElementById("input3").value;

	    // Check if any of the fields are empty
	    if (name === "" || phoneNum === "" || zipCode === "" || address === "" || addressDetail === "") {
	        alert('주문정보를 채워주세요!');
	    } else {
	        console.log("All fields filled, proceeding with payment.");
	        // 여기에 결제 코드를 실행할 수 있도록 함수 호출
	        initiatePayment();
	    }
	}

	function initiatePayment() {
		var id = '<%= session.getAttribute("id") %>';
		
	    var name = document.getElementById("input1").value;
	    var phoneNum = document.getElementById("input2").value;
	    var zipCode = document.getElementById("sample6_postcode").value;
	    var address = document.getElementById("sample6_address").value;
	    var addressDetail = document.getElementById("sample6_detailAddress").value;
	    var email = document.getElementById("input3").value;

	    var clientKey = 'test_ck_oEjb0gm23PONwM6GppD48pGwBJn5';
	    var customerKey = 'geaekhuFzPibxy0mUJzkr';
	    var paymentWidget = PaymentWidget(clientKey, customerKey);
	    paymentWidget.renderPaymentMethods("#payment-method", { value: <%=price%> });
	    const paymentRequestButton = document.getElementById("payment-request-button");

	    paymentRequestButton.addEventListener("click", () => {
	        paymentWidget.requestPayment({
	            orderId: generateRandomString(),
	            orderName: "<%=name%>",
 				successUrl: window.location.origin + "/Project4/orderSuccessAction.od?price=<%=price%>&name=<%=name%>&status1=<%=status1%>&status2=<%=status2%>&status3=<%=status3%>&img=<%=imageURL%>&id=" + id + "&name=" + name + "&phone_num=" + phoneNum + "&zip_code=" + zipCode + "&address=" + address + "&address_detail=" + addressDetail+"&email="+email,
	           
	            
	            failUrl: window.location.origin + "/fail",
	        });
	    });

	    function generateRandomString() {
	        return window.btoa(Math.random()).slice(0, 20);
	    }
	    
	   

	}
	
	
	
</script>
</body>
</html>