<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0,shrink-to-fit=no" />
<title>Insert title here</title>
</head>
<body>
	<section>
		<h3>가칭제품</h3>
		<span>임시가격 : 1234 원</span>
		<p>-------------------------------</p>
		<div><label><input type = "radio" name = "method" value="카드" checked>신용카드</label></div>
		<div><label><input type ="radio" name ="method" value ="가상계좌">가상계좌</label></div>
		<p>-------------------------------</p>
		<button id="payment-request-button">결제하기</button>     	
      	<script type="text/javascript"> 
			// 위의 결제를취소하시겠습니까? -> 결제가취소되었습니다! 라고 뜨게끔 ????
      	</script>	
	</section>	
<script src="https://js.tosspayments.com/v1/payment-widget"></script>	
<script>

	var clientKey = 'test_ck_oEjb0gm23PONwM6GppD48pGwBJn5'
	var customerKey = 'geaekhuFzPibxy0mUJzkr'
	var paymentWidget = PaymentWidget(clientKey, customerKey);
	
	paymentWidget.renderPaymentMethods("#payment-method", {value: 1234});
	const paymentRequestButton = document.getElementById("payment-request-button");
	
	paymentRequestButton.addEventListener("click", () => {
        paymentWidget.requestPayment({
        orderId: generateRandomString(),
        orderName: "스팀팩",
        successUrl: window.location.origin + "/success",
        failUrl: window.location.origin + "/fail",
       });
     });
	
		function generateRandomString() {
        return window.btoa(Math.random()).slice(0, 20);
      }
	
	
	
	
	
</script>	
</body>
</html>