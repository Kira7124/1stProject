<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수리 내역</title>
</head>
<body>

	<div class="card-container">

		<div class="card-header pb-0">
			
			<div class="center-body px-0 pt-0 pb-2">
				<h3 style="text-align: center;">수리 내역</h3>
				<div class="table-responsive p-0">
				
					<table class="table align-items-center mb-0">
								
						<c:choose>
							<c:when test="${not empty centerList }">
								<thead>
									<tr>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">신청번호</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">신청일</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">기종</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">휴대폰번호</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">신청자</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">수리희망일</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">수리요청사항</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">우편번호</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">주소</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">상세주소</th>
										<th class="text-center text-secondary text-xxs font-weight-bolder opacity-7">참고항목</th>
									</tr>
								</thead>
								
								<c:forEach var="centerList" items="${centerList }">
									<tbody>
										<tr class="profile">
											<td class="align-middle text-center text-sm "><a href="./CenterEditAction.as?as_id=${centerList.as_id }">${centerList.as_id }</a></td>
											<td class="align-middle text-center text-sm ">${centerList.as_created_date }</td>
											<td class="align-middle text-center text-sm ">${centerList.as_model }</td>
											<td class="align-middle text-center text-sm ">${centerList.as_phone_num }</td>
											<td class="align-middle text-center text-sm ">${centerList.as_name }</td>
											<td class="align-middle text-center text-sm ">${centerList.as_due_date }</td>
											<td class="align-middle text-center text-sm ">${centerList.as_comment }</td>
											<td class="align-middle text-center text-sm ">${centerList.as_zip_code }</td>
											<td class="align-middle text-center text-sm ">${centerList.as_address }</td>
											<td class="align-middle text-center text-sm ">${centerList.as_address_detail }</td>
											<td class="align-middle text-center text-sm ">${centerList.as_reference }</td>
										</tr>
									</tbody>
								</c:forEach>
							</c:when>
							
							<c:otherwise>
								<h4>신청한 내역이 없습니다.</h4>
							</c:otherwise>
							
						</c:choose>
					
					</table>
						
				</div>	
	
			</div>
		
		</div>
										
	</div>
											
</body>
</html>