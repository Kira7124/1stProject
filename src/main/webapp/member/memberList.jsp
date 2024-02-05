<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원정보리스트관리페이지</title>
<style>
      body {
           background-image: url('./img/memberList.jpg'); /* 이미지 파일의 경로를 지정하세요 */
           background-size: cover; /* 이미지를 화면에 꽉 채우도록 설정 */
           background-position: center; /* 이미지를 가운데 정렬 */
           background-repeat: no-repeat; /* 이미지 반복 없음 */
           text-align: center; /* 텍스트 등을 가운데 정렬 */
           padding: 20px; /* 내용과 배경 이미지 사이에 여백 추가 */
           color: #666;
  		   font: 14px/24px "Open Sans", "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", Sans-Serif;
        }




  table {
            margin: auto;
            border-collapse: collapse;
            width: 80%;
            margin-top: 20px;
            text-align: center; /* 테이블 안의 내용도 가운데 정렬 */
            border-spacing: 0;
 			width: 100%;
            
        }

        th, td {
            border: 1px solid black;
            padding: 6px 15px;
  
        }
          
        th {
  		background: #42444e;
  		color: #fff;
  		text-align: center;
		}  
        tr:first-child th:first-child {
  			border-top-left-radius: 6px;
			}
		tr:first-child th:last-child {
  			border-top-right-radius: 6px;
		}
	td {
  		border-right: 1px solid #c6c9cc;
  		border-bottom: 1px solid #c6c9cc;
		}
	td:first-child {
  		border-left: 1px solid #c6c9cc;
		}
	tr:nth-child(even) td {
  		background: #eaeaed;
		}
	tr:last-child td:first-child {
  		border-bottom-left-radius: 6px;
		}
	tr:last-child td:last-child {
  		border-bottom-right-radius: 6px;
	}  
              
 button {
         color: #0A6EFF;
         padding: 5px;
         margin: 2px;
      }

 #myTable #bt2 {
         color: #FF0000;
      }
        
        
        
.memberSearch{
	 float: right;

}


.btn-search{
  width: 50px;
  height: 35px;
  background: black;
  border-radius: 6px;
  color : white;
}

#main-btn{

border-radius: 6px;
color : white;

}        
 
#top-btn{

border-radius: 6px;
color : white;

}  
      
</style>

<script src ="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type = "text/javascript">
	$(document).ready(function(){
		$('button').css('color','#0A6EFF');
		$('#myTable #bt2').css('color','#FF0000');
		$('#myTable').css('text-align','center');
		
	});
		
</script>
</head>
<body>


   
              
	<h1>회원정보리스트</h1><br>
	
	<div id = "memberSearch" style="display: inline-block; margin: 0 5px;  float: right;">
	  <form action="./MemberSearchAction.me">
             <input type="text" name="search" size="15" placeholder="아이디정보/아이디키워드 검색" 
                style="border: 2px solid black;width: 300px; height: 30px; font-size: 17px;border-radius: 10px 0 0 10px">
           	  <input type="submit" value="검색" class="btn-search">
       </form>
    </div><br>
         
		<table border = "1" id ="myTable">
	<tr>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>전화번호</th>
		<th>주소</th>
		<th>이메일</th>
		<th style ="width :70px">삭제</th>
		<th style ="width :70px">수정</th>
	</tr>
		
  <c:forEach var="dto" items="${memberList }">
	<tr>
		<td>${dto.id }</td>
		<td>${dto.pw }</td>
		<td>${dto.name }</td>
		<td>${dto.phoneNum }</td>
		<td>${dto.address }</td>
		<td>${dto.email }</td>
		<td style ="width :70px">

		<button type="submit" id ="bt2" style ="width : 60px" onclick="confirmDelete('${dto.id}')">삭제</button>

		</td>
		<td style ="width :70px">

		 <a href="./MemberUpdate.me?id=${dto.id}&pw=${dto.pw}&name=${dto.name}&phoneNum=${dto.phoneNum}&email=${dto.email}">
			<button style ="width : 60px">수정</button>
		</a>
		</td>		
	</tr>
  </c:forEach>
 
</table><br>

	<button id="main-btn" onclick="location.href='./main.ma'">메인페이지로 이동</button>
	
	<button onclick="window.scrollTo(0,0);" id="top-btn">
	페이지최상단(TOP)이동
	</button>

	<script type="text/javascript">
		function confirmDelete(userId) {
	        var confirmation = confirm("정말로 삭제하시겠습니까?");
	        if (confirmation) {
	            location.href = 'MemberDeleteAction.me?id=' + userId;
	        } else {
	            // 사용자가 취소한 경우에 대한 처리 (optional)
	        }
	    }
	</script>
</body>
</html>