<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대여 내역</title>
</head>
<body>


	<div class="card-container">

		<div class="card-header pb-0">
			
			<div class="rental-body px-0 pt-0 pb-2">
				<h3 style="text-align: center;">대여 내역</h3>
				<div class="table-responsive p-0">
				
					<table class="table align-items-center mb-0">
					
						<c:choose>
							<c:when test="${not empty orderList }">
								<thead>
									<tr>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">주문번호</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">이미지</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">분류</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">시작일</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">종료일</th>
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
											<td class="align-middle text-center text-sm ">${orderList.order_id }</td>
											<td class="align-middle text-center text-sm "><span class="text-xs font-weight-bold mb-0"> <img src="./img/${orderList.img}" class="cart-img"></span></td>
											<td class="align-middle text-center text-sm ">대여</td>
											<td class="align-middle text-center text-sm ">${orderList.rentalStartDate }</td>
											<td class="align-middle text-center text-sm ">${orderList.rentalEndDate }</td>
											<td class="align-middle text-center text-sm ">${orderList.name }</td>
											<td class="align-middle text-center text-sm ">${orderList.price }</td>
											<td class="align-middle text-center text-sm ">${orderList.status1 }</td>
											<td class="align-middle text-center text-sm ">${orderList.status2 }</td>
											<td class="align-middle text-center text-sm ">${orderList.zip_code }</td>
											<td class="align-middle text-center text-sm ">${orderList.address }</td>
											<td class="align-middle text-center text-sm ">${orderList.address_detail }</td>
											<td class="align-middle text-center text-sm "><input type="button" id="product-btn" value="취소하기" onclick="location.href='./OrderDeleteAction.od?order_id=${orderList.order_id }';"></td>
										</tr>
									</tbody>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<h4>대여한 내역이 없습니다.</h4>
							</c:otherwise>
						</c:choose>
					</table>
						
				</div>	
	
			</div>
		
		</div>
										
	</div>																		

</body>
</html>