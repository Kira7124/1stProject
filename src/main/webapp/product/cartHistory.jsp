<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
	<script type="text/javascript">

			
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

	<div class="card-container">

		<div class="card-header pb-0">

				<div class="cart-body px-0 pt-0 pb-2">
				<h3 style="text-align: center;">장바구니</h3>
				<form action="./CartHistoryDeleteAction.pd" method="post" >
					<div class="table-responsive p-0">
						<table class="table align-items-center mb-0">
							<thead>
								<tr>
									<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">상품번호</th>
									<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">상품이미지</th>
									<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">모델명</th>
									<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">가격</th>
									<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">색상</th>
									<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">용량</th>
									<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">구매/대여</th>
									<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">등급</th>
									<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">대여시작일</th>
									<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">대여종료일</th>
									<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">
										<input type="submit" id="product-btn" value="삭제하기"onclick="return confirmDelete();">
									</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty cartList}">
									<c:forEach items="${sessionScope.cartList}" var="cdto">
										<c:if test="${mdto.id == sessionScope.mdto.id}">
											<tr>
												<td class="align-middle text-center text-sm ">
													<p class="text-xs font-weight-bold mb-0">${cdto.p_id}</p>
												</td>
												<td class="align-middle text-center text-sm "><c:if
														test="${cdto.title == '구매' }">
														<a href="./purchaseOrder.od?name=${cdto.name }&img=${cdto.image}&status1=${cdto.capacity}&status2=${cdto.color}&status3=${cdto.grade}&price=${cdto.price}">
															<span class="text-xs font-weight-bold mb-0"> <img src="./img/${cdto.image}" class="cart-img"></span>
														</a>
													</c:if></td>
												<td class="align-middle text-center text-sm ">
													<p class="text-xs font-weight-bold mb-0">${cdto.model}</p>
												</td>
												<td class="align-middle text-center text-sm ">
													<p class="text-xs font-weight-bold mb-0">
														<fmt:formatNumber value="${cdto.price}" pattern="#,###" />
														원
													</p>
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
												<td class="align-middle text-center text-sm ">
													<p class="text-xs font-weight-bold mb-0">
														<input type="checkbox" name="c_id" value="${cdto.c_id}">
													</p>
												</td>
											</tr>
										</c:if>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>