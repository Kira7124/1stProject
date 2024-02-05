<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제실패</title>
<script src="https://js.stripe.com/v3/"></script>
<link rel = "stylesheet" type="text/css" href="<%= request.getContextPath()%>failStyle.css">
</head>
<body>
	<h1>결제가 취소되었습니다.</h1>
	<p>죄송합니다 다음에 더 좋은 서비스와 제품으로<br>
	보답드리도록 최선을다하겠습니다.
	</p>
	
	
	
	
	<button id = "cancel-check" onclick = "javascript:alert('이용해주셔서 감사드립니다!')">확인</button>
	<script type = "text/javascript">
		document.getElementById("cancel-check").addEventListener("click",function(){
			var jspPageUrl = "../main.jsp";
			window.location.href = jspPageUrl;
		});
		
	</script> 
</body>
</html>