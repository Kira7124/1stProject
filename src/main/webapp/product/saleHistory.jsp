<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매 내역</title>
</head>
<body>


	<div class="card-container">

		<div class="card-header pb-0">
			
			<div class="sale-body px-0 pt-0 pb-2">
				<h3 style="text-align: center;">판매 내역</h3>
				<div class="table-responsive p-0">
				
					<table class="table align-items-center mb-0">

						<c:choose>
							<c:when test="${not empty saleList }">
								<thead>
									<tr>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">신청번호</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">신청일</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">기종</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">예상금액</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">신청자</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">휴대폰번호</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">은행</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">계좌번호</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">예금주명</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">방문예정일</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">배송예정일</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">판매취소</th>
									</tr>
								</thead>
								
								<c:forEach var="saleList" items="${saleList }">
									<tbody>
										<tr class="profile">
											<td class="align-middle text-center text-sm ">${saleList.sale_id }</td>
											<td class="align-middle text-center text-sm ">${saleList.sale_created_date }</td>
											<td class="align-middle text-center text-sm ">${saleList.sale_model }</td>
											<td class="align-middle text-center text-sm "><fmt:formatNumber value="${saleList.sale_expected_price }" pattern="#,###" />원</td>
											<td class="align-middle text-center text-sm ">${saleList.sale_name }</td>
											<td class="align-middle text-center text-sm ">${saleList.sale_phone_num }</td>
											<td class="align-middle text-center text-sm ">${saleList.sale_bank_name }</td>
											<td class="align-middle text-center text-sm ">${saleList.sale_account_num }</td>
											<td class="align-middle text-center text-sm ">${saleList.sale_owner_name }</td>
											<td class="align-middle text-center text-sm ">${saleList.sale_visit_date }</td>
											<td class="align-middle text-center text-sm ">${saleList.sale_delivery_date }</td>
											<td class="align-middle text-center text-sm "><input type="button" id="product-btn" value="취소하기" onclick="location.href='./SaleDeleteAction.pd?sale_id=${saleList.sale_id }';"></td>
										</tr>
									</tbody>
								</c:forEach>
							</c:when>
							
							<c:otherwise>
								<h4>판매한 내역이 없습니다.</h4>
							</c:otherwise>
							
						</c:choose>
						
					</table>
						
				</div>	
	
			</div>
		
		</div>
										
	</div>													
	
</body>
</html>