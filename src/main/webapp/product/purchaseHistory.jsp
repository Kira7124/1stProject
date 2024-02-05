<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 내역</title>
</head>
<body>


	<div class="card-container">

		<div class="card-header pb-0">
			
			<div class="purch-body px-0 pt-0 pb-2">
				<h3 style="text-align: center;">구매 내역</h3>
				<div class="table-responsive p-0">
				
					<table class="table align-items-center mb-0">
					
						<c:choose>
							<c:when test="${empty orderList }">
								<h4> 구매한 내역이 없습니다.</h4>
							</c:when>
							<c:otherwise>
								<thead>
									<tr>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">주문번호</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">이미지</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">분류</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">주문일</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">모델명</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">결제금액</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">용량</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">색상</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">우편번호</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">주소</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">상세주소</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">주문취소</th>
									</tr>
								</thead>
								
								<c:forEach var="orderList" items="${orderList }">
									<tbody>
										<tr class="profile">
											<td class="align-middle text-center text-sm ">
												${orderList.order_id }
											</td>
											<td class="align-middle text-center text-sm ">
												<span class="text-xs font-weight-bold mb-0"> 
													<img src="./img/${orderList.img}" class="cart-img">
												</span>
											</td>
											<td class="align-middle text-center text-sm "><p class="text-xs font-weight-bold mb-0">구매</p></td>
											<td class="align-middle text-center text-sm "><p class="text-xs font-weight-bold mb-0">${orderList.order_regdate }</p></td>
											<td class="align-middle text-center text-sm "><p class="text-xs font-weight-bold mb-0">${orderList.name }</p></td>
											<td class="align-middle text-center text-sm "><p class="text-xs font-weight-bold mb-0">${orderList.price }</p></td>
											<td class="align-middle text-center text-sm "><p class="text-xs font-weight-bold mb-0">${orderList.status1 }</p></td>
											<td class="align-middle text-center text-sm "><p class="text-xs font-weight-bold mb-0">${orderList.status2 }</p></td>
											<td class="align-middle text-center text-sm "><p class="text-xs font-weight-bold mb-0">${orderList.zip_code }</p></td>
											<td class="align-middle text-center text-sm "><p class="text-xs font-weight-bold mb-0">${orderList.address }</p></td>
											<td class="align-middle text-center text-sm "><p class="text-xs font-weight-bold mb-0">${orderList.address_detail }</p></td>
											<td class="align-middle text-center text-sm "><input type="button" id="product-btn" value="취소하기" onclick="location.href='./OrderDeleteAction.od?order_id=${orderList.order_id }';"></td>
										</tr>
									</tbody>
								</c:forEach>
							</c:otherwise>
							
						</c:choose>
																				
					</table>
						
				</div>	
	
			</div>
		
		</div>		
    </div>																	

</body>
</html>