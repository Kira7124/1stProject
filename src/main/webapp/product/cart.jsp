<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css" />
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link id="pagestyle" href="./css/board.css" rel="stylesheet" />
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="./js/jquery.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	
	// 전체 선택
	window.onload = function() {
	    document.getElementById('selectAll').onclick = function() {
	        var checkboxes = document.getElementsByName('c_id');
	        for (var checkbox of checkboxes) {
	            checkbox.checked = this.checked;
	        }
	    }
	}
	
	 function confirmDelete() {
	        var selectedItems = document.querySelectorAll('input[name="c_id"]:checked').length;

	        if (selectedItems === 0) {
	            alert("삭제할 항목을 선택하세요.");
	            return false;
	        } else if (selectedItems == 1) {
	            return confirm("선택한 상품을 삭제하시겠습니까?");
	        } else {
	            return confirm("장바구니에 담긴 상품을 전체 삭제하시겠습니까?");
	        }
	    }

</script>



</head>
<body>
	<!-- 왼쪽 메뉴 -->
	<jsp:include page="../inc/left.jsp" />
	<!-- 상단메뉴 -->
	<jsp:include page="../inc/top.jsp" />
	<!-- 메인페이지 -->
	<div class="row">
			<div class="card mb-4">
				<div class="card-header pb-0">
						<h6>장바구니</h6>
						<form action="./CartDeleteAction.pd" method="post">
							<input type="submit" id="product-btn" value="삭제하기" onclick="return confirmDelete();"> 

							<div class="card-body px-0 pt-0 pb-2">
								<div class="table-responsive p-0">
										<table class="table align-items-center mb-0">
										<thead>
											<tr>
												<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7"><input type="checkbox" id="selectAll"></th>
												<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">상품번호</th>
												<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">상품이미지</th>
												<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">모델명</th>
												<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">가격</th>
												<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">색상</th>
												<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">용량</th>
												<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">구매/대여</th>
												<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">등급</th>
													<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">대여 시작일</th>
													<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">대여 종료일</th>
											</tr>
										</thead>
										<tbody>
										<c:if test="${not empty cartList}">
											<c:forEach items="${sessionScope.cartList}" var="cdto">		
											<c:if test="${mdto.id == sessionScope.mdto.id}"> 		
												<tr>
													<td class="align-middle text-center text-sm ">
														<p class="text-xs font-weight-bold mb-0">
															<input type="checkbox" name="c_id" value="${cdto.c_id}">
														</p>
													</td>
													<td class="align-middle text-center text-sm ">
													<c:if test="${cdto.title == '구매' }">
														<a href="./purchaseOrder.od?name=${cdto.name }&img=${cdto.image}&status1=${cdto.capacity}&status2=${cdto.color}&status3=${cdto.grade}&price=${cdto.price}">
															<span class="text-xs font-weight-bold mb-0">${cdto.p_id}</span>
														</a>
													</c:if>
													<c:if test="${cdto.title == '대여' }">
														<a href="./rentalOrder.od?name=${cdto.name }&img=${cdto.image}&status1=${cdto.capacity}&status2=${cdto.color}&price=${cdto.price}&rentalStartDate=${cdto.start_date}&rentalEndDate=${cdto.end_date}&rentalDuration=${cdto.total_date}">
															<span class="text-xs font-weight-bold mb-0">${cdto.p_id}</span>
														</a>
													</c:if>
													</td>
													<td class="align-middle text-center text-sm ">
														<c:if test="${cdto.title == '구매' }">
														<a href="./purchaseOrder.od?name=${cdto.name }&img=${cdto.image}&status1=${cdto.capacity}&status2=${cdto.color}&status3=${cdto.grade}&price=${cdto.price}">
															<span class="text-xs font-weight-bold mb-0">
																<img src="./img/${cdto.image}" class="cart-img">
															</span>
														</a>
														</c:if>
														<c:if test="${cdto.title == '대여' }">
														<a href="./rentalOrder.od?name=${cdto.name }&img=${cdto.image}&status1=${cdto.capacity}&status2=${cdto.color}&price=${cdto.price}&rentalStartDate=${cdto.start_date}&rentalEndDate=${cdto.end_date}&rentalDuration=${cdto.total_date}">
															<span class="text-xs font-weight-bold mb-0">
																<img src="./img/${cdto.image}" class="cart-img">
															</span>
														</a>
														</c:if>
													</td>
													<td class="align-middle text-center text-sm ">
													<c:if test="${cdto.title == '구매' }">
														<a href="./purchaseOrder.od?name=${cdto.name }&img=${cdto.image}&status1=${cdto.capacity}&status2=${cdto.color}&status3=${cdto.grade}&price=${cdto.price}">
															<span class="text-xs font-weight-bold mb-0">${cdto.model}</span>
														</a>
													</c:if>
													<c:if test="${cdto.title == '대여' }">
														<a href="./rentalOrder.od?name=${cdto.name }&img=${cdto.image}&status1=${cdto.capacity}&status2=${cdto.color}&price=${cdto.price}&rentalStartDate=${cdto.start_date}&rentalEndDate=${cdto.end_date}&rentalDuration=${cdto.total_date}">
															<span class="text-xs font-weight-bold mb-0">${cdto.model}</span>
														</a>
													</c:if>
													</td>
													<td class="align-middle text-center text-sm ">
														<p class="text-xs font-weight-bold mb-0"><fmt:formatNumber value="${cdto.price}" pattern="#,###" />원</p>
													</td>
													<td class="align-middle text-center text-sm ">
														<p class="text-xs font-weight-bold mb-0">${cdto.color}</p>
													</td>
													<td class="align-middle text-center text-sm ">
														<p class="text-xs font-weight-bold mb-0">${cdto.capacity}</p>
													</td>
													<td class="align-middle text-center text-sm">
												 		<p class="text-xs font-weight-bold mb-0">${cdto.title}</p>
													</td>
													<td class="align-middle text-center text-sm ">
														<p class="text-xs font-weight-bold mb-0">${cdto.grade}</p>
													</td>
													<td class="align-middle text-center text-sm">
												 		<p class="text-xs font-weight-bold mb-0">${cdto.start_date}</p>
													</td>
													<td class="align-middle text-center text-sm">
												 		<p class="text-xs font-weight-bold mb-0">${cdto.end_date}</p>
													</td>
												</tr>
												</c:if>
											</c:forEach>
										</c:if>
										<c:if test="${empty cartList }">
											<td class="text-center"></td>
											<td class="text-center"></td>
											<td class="text-center"></td>
											<td class="text-center"></td>
											<td class="text-center"></td>
											<td class="text-center"><h4>장바구니에 담긴 상품이 없습니다!</h4></td>
										</c:if>
										</tbody>
										</table>			
								</div>
						</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>