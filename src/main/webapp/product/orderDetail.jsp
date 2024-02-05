<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>${pdto.name }</title>
<link rel="stylesheet" type="text/css" href="./css/reset.css" />
<link rel="stylesheet" type="text/css" href="./css/layout.css" />
<link id="pagestyle" href="./css/board.css" rel="stylesheet" />
<script src="./js/jquery.js"></script>

<script>
 		
            // 창 사이즈에 따라 움직이는 제이쿼리문
            $(document).ready(function(){ 
                $('selector').css('width', $(window).width()); 
                $('selector').css('height', $(window).height()); 
                $(window).resize(function() { 
                    $('selector').css('width', $(window).width()); 
                    $('selector').css('height', $(window).height()); 
                }); 
            });
            

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
                    $btn.closest('.mn_box').css('box-shadow', 'rgba(0, 0, 0, 0.05) 14px 14px 14px');
                    $btn.find('.mn_icon').css('background-color', '#3182ce');
                    $btn.find('.mn_icon img').css('filter', 'invert(100%) sepia(0%) saturate(0%) hue-rotate(93deg) brightness(103%) contrast(103%)');
                    $btn.find('.clickable-span').css('color', '#000');
                    currentBtn = $btn;
                }
            });
            
       </script>
</head>
<body>
	<!-- 왼쪽 메뉴 -->
	<jsp:include page="../inc/left.jsp"/>
	       <!-- 상단메뉴 -->
		<jsp:include page="../inc/top.jsp"/>
		<!-- 메인페이지 -->
		<div class="row">
			<div class="card mb-4">
				<div class="card-header pb-0">
	               <h6>내 기기 구매</h6>
	               <C:if test="${sessionScope.mdto.id eq 'admin' }">
					   <button type="submit" class="update-btn" value="수정하기" onclick="location.href='ProductUpdate.pd?product_id=${pdto.product_id}'">수정하기</button>
		               <button type="submit" class="delete-btn" value="삭제하기" onclick="confirmDelete('${pdto.product_id}' , '${pdto.title }')">삭제하기</button>	 
		           </C:if>              
	            </div>
			<div id="sale-img">
				<img src="./img/${pdto.image }" alt="${pdto.name }">
			</div>
			
			<div class="saledetail-write">
				<div class="saledetail-title">
	              <h2>${pdto.name }</h2>
            	</div>
            	<div id="item_option">
	            <table class="saledetail-table">
	            	<tbody>
	            		<tr class="saledetail-tr">
	            			<th>
	            				<span>용량</span>
	            			</th>
	            			<td class="td_sit_sel light">
	            				<C:if test="${not empty pdto.capacity }">
	            				<input type="radio" id="sale-status1-0" name="sale-status1" value="${pdto.capacity },-0" checked="checked">
	            				<label class="ss1" for="sale-status1-0">${pdto.capacity }</label>
	            				</C:if>
	            				<C:if test="${not empty pdto.capacity2 }">
	            				<input type="radio" id="sale-status1-1" name="sale-status1" value="${pdto.capacity2 },-${pdto.option1}">
	            				<label class="ss1" for="sale-status1-1">${pdto.capacity2 }</label>
	            				</C:if>
	            				<C:if test="${not empty pdto.capacity3 }">
	            				<input type="radio" id="sale-status1-2" name="sale-status1" value="${pdto.capacity3 },-${pdto.option2}">
	            				<label class="ss1" for="sale-status1-2">${pdto.capacity3 }</label>
	            				</C:if>
	            			</td>
	            		</tr>
	            		<tr class="saledetail-tr">
	            			<th>
	            				<span>색상</span>
	            			</th>
	            			<td class="td_sit_sel light">
	            				<C:if test="${not empty pdto.color }">
	            				<input type="radio" id="sale-status2-1" name="sale-status2" value="${pdto.color },0" checked="checked">
	            				<label class="ss2" for="sale-status2-1">${pdto.color }</label>
	            				</C:if>
	            				<C:if test="${not empty pdto.color2 }">
	            				<input type="radio" id="sale-status2-2" name="sale-status2" value="${pdto.color2 },0">
	            				<label class="ss2" for="sale-status2-2">${pdto.color2 }</label>
	            				</C:if>
	            				<C:if test="${not empty pdto.color3 }">
	            				<input type="radio" id="sale-status2-3" name="sale-status2" value="${pdto.color3 },0">
	            				<label class="ss2" for="sale-status2-3">${pdto.color3 }</label>
	            				</C:if>
	            			</td>
	            		</tr>
	            		<tr class="saledetail-tr">
	            			<th>
	            				<span>등급</span>
	            			</th>
	            			<td class="td_sit_sel light">
	            				<input type="radio" id="sale-status3-0" name="sale-status3" value="S등급,-0" checked="checked">
	            				<label class="ss3" for="sale-status3-0">S등급</label>
	            				<input type="radio" id="sale-status3-1" name="sale-status3" value="A등급,-${pdto.option3}">
	            				<label class="ss3" for="sale-status3-1">A등급</label>
	            				<input type="radio" id="sale-status3-2" name="sale-status3" value="B등급,-${pdto.option4}">
	            				<label class="ss3" for="sale-status3-2">B등급</label>
	            			</td>
	            		</tr>
	            	</tbody>
	            </table>
            	</div>
	            <div style="color:#999;vertical-align:top;border:0">
	            	<p>
	            		<span>최신기종 및 특정기기는 차감액이 달라 적용될 수 있습니다.</span>
	            		<span style="font-family:'나눔고딕', NanumGothic;font-size:12pt;">&nbsp;</span>
	            	</p>
	            	<p>
	            		<span>문의주시면 친절한 상담 도와드리겠습니다.</span>
	            		<span style="font-family:'나눔고딕', NanumGothic;font-size:12pt;">&nbsp;</span>
	            	</p>
	            	<p>
	            		<span>최고의 실매입가, 폰미쳤다에서 만나보세요.</span>
	            		<span style="font-family:'나눔고딕', NanumGothic;font-size:12pt;">&nbsp;</span>
	            	</p>
	            </div>
	            <div class="price-box">
	            	<span class="it_tot_price-wrapper">
	            		구매가격 : 
	            		<span id="it_tot_price" class="number-animation"><fmt:formatNumber value="${pdto.price}" pattern="#,###" />원</span>
	            	</span>
	            </div>
	            <div class="item-total-buttons">
	           		<C:choose>
						<C:when test="${not empty sessionScope.mdto }">
			            	<button type="submit" id="CartButton" class="item-vi" value="장바구니">장바구니 담기</button>
			            	<button type="submit" id="PayButton" class="item-de" value="구매">바로 구매하기</button>
		            	</C:when>
		            	<C:otherwise>
			            	<button type="submit" class="item-vi" value="장바구니" onclick="loginForm()">장바구니 담기</button>
			            	<button type="submit" class="item-de" value="구매" onclick="loginForm()">바로 구매하기</button>	            		
		            	</C:otherwise>
	            	</C:choose>
	            </div>
			</div>
	        </div>
		</div>

		<script>
		
		document.addEventListener("DOMContentLoaded", function() {
		      // 가격을 표시하는 요소를 가져옵니다.
		      var itTotPriceElement = document.getElementById("it_tot_price");

		      // 라디오 버튼 그룹들을 가져옵니다.
		      var radioGroups = document.querySelectorAll(".td_sit_sel input[type='radio']");

		      // 예상 매입 가격을 저장할 변수를 초기화합니다.
		      var currentPrice = parseInt(itTotPriceElement.innerText.replace(/[^0-9]/g, ''));

		      // 라디오 버튼 그룹들의 이전 상태를 저장할 변수를 초기화합니다.
		      var previousState = {};

		      // 각 라디오 버튼 그룹의 변경 이벤트를 처리합니다.
		      radioGroups.forEach(function(radio) {
		        radio.addEventListener("change", function() {
		          // 이전 상태와 현재 상태를 저장합니다.
		          var groupName = this.name;
		          previousState[groupName] = previousState[groupName] || 0;
		          var previousPriceChange = previousState[groupName];
		          var priceChange = parseInt(this.value.split(",")[1]);

		          // 예상 매입 가격을 업데이트합니다.
		          currentPrice = currentPrice - previousPriceChange + priceChange;

		          // 이전 상태를 업데이트합니다.
		          previousState[groupName] = priceChange;

		          // 예상 매입 가격을 화면에 업데이트합니다.
		          itTotPriceElement.innerText = currentPrice.toLocaleString() + "원";
		          // 숫자 애니메이션 클래스를 추가하여 애니메이션 효과 부여
		          itTotPriceElement.classList.add('number-animation');
			          setTimeout(function() {
			            itTotPriceElement.classList.remove('number-animation');
			            }, 1000); // 애니메이션 지속 시간 (1초)
			          });
		        
			    });
		    	 // 버튼 클릭 이벤트 핸들러 등록
			    CartButton.addEventListener('click', function () {
			        // 선택된 라디오 버튼 값 가져오기
			        var status1Value = document.querySelector('input[name="sale-status1"]:checked').value.split(',')[0];
			        var status2Value = document.querySelector('input[name="sale-status2"]:checked').value.split(',')[0];
			        var status3Value = document.querySelector('input[name="sale-status3"]:checked').value.split(',')[0];
			        // currentPrice 및 pdto.id 값을 가져와서 URL에 추가
			        var newUrl =
			            "./CartInsertAction.pd?price=" +
			            currentPrice +
			            "&id=" +
			            "${pdto.product_id}" +
			            "&u_id="+
			            userId+
			            "&img="+
			            "${pdto.image}"+
			            "&model="+
			            "${pdto.model}"+
			            "&name="+
			            "${pdto.name}"+
			            "&title="+
			            "${pdto.title}"+
			            "&status1=" +
			            encodeURIComponent(status1Value) +
			            "&status2=" +
			            encodeURIComponent(status2Value) +
			            "&status3=" +
			            encodeURIComponent(status3Value);

			        console.log("New URL:", newUrl); // 로그 추가
			        console.log(userId)

			        
			        // 확인 대화 상자 띄우기
			        var addToCart = confirm("장바구니에 추가하시겠습니까?");
			        
			        // 사용자가 확인을 선택한 경우
			        if (addToCart) {
			            // 페이지 이동
			            window.location.href = newUrl;
			        } else {
			            // 사용자가 취소를 선택한 경우
			            // 여기에 필요한 작업 추가
			            console.log("사용자가 취소를 선택했습니다.");
			        }
		     	});
		    	 
			    // 버튼 클릭 이벤트 핸들러 등록
			    PayButton.addEventListener('click', function () {
			        // 선택된 라디오 버튼 값 가져오기
			        var status1Value = document.querySelector('input[name="sale-status1"]:checked').value.split(',')[0];
			        var status2Value = document.querySelector('input[name="sale-status2"]:checked').value.split(',')[0];
			        var status3Value = document.querySelector('input[name="sale-status3"]:checked').value.split(',')[0];
			        // currentPrice 및 pdto.id 값을 가져와서 URL에 추가
			        var newUrl =
			            "./purchaseOrder.od?price=" +
			            currentPrice +
			            "&name="+
			            "${pdto.name}"+
			            "&img="+
			            "${pdto.image}"+
			            "&status1=" +
			            encodeURIComponent(status1Value) +
			            "&status2=" +
			            encodeURIComponent(status2Value) +
			            "&status3=" +
			            encodeURIComponent(status3Value);

			        console.log("New URL:", newUrl); // 로그 추가

			        // 페이지 이동
			        window.location.href = newUrl;
		     });
	      });
		      

		var userId = "${sessionScope.mdto.id}";
		
		
		function confirmDelete(productId, title) {
	        var confirmation = confirm("정말로 삭제하시겠습니까?");
	        if (confirmation) {
	            location.href = 'ProductDeleteAction.pd?product_id=' + productId + '&title=' + encodeURIComponent(title);
	        } else {
	            // 사용자가 취소한 경우에 대한 처리 (optional)
	        }
	    }

		function loginForm() {
	        var confirmation = confirm("로그인이 필요한 페이지입니다. 로그인페이지로 이동하시겠습니까?.");
	        if (confirmation) {
	            location.href = 'loginForm.me';
	        } else {
	            // 사용자가 취소한 경우에 대한 처리 (optional)
	        }
	    }
		</script>
</body>
</html>