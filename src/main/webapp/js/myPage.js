

// 이전에 열린 토글을 저장할 변수
var previousToggle = null;

// 토글 이벤트 
function toggleHistory(historyName) {
	var container = document.getElementById(historyName);

	// 이전에 열렸던 토글이 있고, 현재 토글과 다르다면 닫음
	if (previousToggle && previousToggle !== container) {
		previousToggle.style.display = "none";
	}

	if (container.style.display === "none") {
		container.style.display = "block";
	} else {
		container.style.display = "none";
	}

	// 현재 토글을 이전에 열린 토글로 저장
	previousToggle = container;
}

$(function() {

	// 파라미터를 전달받아 내역을 불러오는 메서드
	function loadHistory(url, outputHistory) {
		$.ajax({
			type: "POST",
			url: url,
			success: function(data) {
				$("#" + outputHistory).html(data);
			},
			error: function(xhr, status, error) {
				alert("내역을 불러오는 데 실패했습니다.");
			}
		});
	}

	// A/S 내역 호출
	$("#centerHistory").click(function() {
		loadHistory("CenterHistory.as", "outputCenterHistory");
	});

	// 판매 내역 호출
	$("#saleHistory").click(function() {
		loadHistory("SaleHistory.pd", "outputSaleHistory");
	});

	// 구매 내역 호출
	$("#purchaseHistory").click(function() {
		loadHistory("PurchaseHistory.od", "outputPurchaseHistory");
	});

	// 대여 내역 호출
	$("#rentalHistory").click(function() {
		loadHistory("RentalHistory.od", "outputRentalHistory");
	});

	// 장바구니 목록 호출
	$("#cartHistory").click(function() {
		loadHistory("CartHistory.pd", "outputCartHistory");
	});

	   // 페이지 로드 시 구매 내역 호출
   loadHistory("PurchaseHistory.od", "outputPurchaseHistory");
   toggleHistory('purchaseHistoryContainer');
   loadHistory("CenterHistory.as", "outputCenterHistory");
   toggleHistory('centerHistoryContainer');
   loadHistory("SaleHistory.pd", "outputSaleHistory");
   toggleHistory('saleHistoryContainer');
   loadHistory("RentalHistory.od", "outputRentalHistory");
   toggleHistory('rentalHistoryContainer');
   loadHistory("CartHistory.pd", "outputCartHistory");
   toggleHistory('cartHistoryContainer');
   
   // 구매 토글 이벤트 실행
   toggleHistory('purchaseHistoryContainer');

	$(function() {

		// 슬라이드 토글
		$("#currentEmailBtn").click(function() {

			$("#changeEmailTr").slideToggle("slow");

		});

		$("#currentPhoneNumBtn").click(function() {

			$("#changePhoneNumtr").slideToggle("slow");

		});
		// 슬라이드 토글

		// 비밀번호 변경

		$("#changePasswordBtn").click(function() {

			alert('비밀번호 변경 페이지로 이동합니다');
			window.location.href = "./updatePassword.me";

		});


		// 비밀번호 변경


		// 휴대전화 변경
		$("#changePhoneNumBtn").click(function() {



			var newPhoneNum = $("#newPhoneNum").val(); // 입력받은 새 전화번호

			var pattern = /^\d{11}$/;

			if (!pattern.test(newPhoneNum) || newPhoneNum.includes("-")) {

				alert("휴대폰 번호는 11자리 숫자이어야 하며, '-'를 포함해서는 안됩니다.");

				return false;

			}

			$.ajax({

				type: "POST",
				url: "./changePhoneNum.me",
				data: { newPhoneNum: newPhoneNum }, // newPhoneNum 이라는 이름으로 newPhoneNum 데이터 전송
				dataType: "text",
				success: function(result) {
					// 성공 후 페이지 새로고침
					if (result === "1") {
						alert('성공적으로 변경했습니다.');
						location.reload();
						console.log(result);
					} else if (result === "0") {
						alert('오류가 발생했습니다. 다시 시도해주세요');
						alert(newPhoneNum);
						alert(result);
						console.log(result);
					} else { // -1			
						alert('해당 번호로 등록된 아이디가 존재합니다.')
						location.reload();
					}

				},
				error: function(error) {
					console.log("Error:", error);
				}

			});

		});
		// 휴대전화 변경

		// 이메일 변경	
		$("#changeEmailBtn").click(function() {

			var newEmail = $("#newEmail").val();
			$.ajax({

				type: "POST",
				url: "./updateEmailAction.me",
				data: { newEmail: newEmail },
				datatype: "text/plain",
				success: function(data) {
					console.log(data);
					if (data === "1") {
						alert('인증 메일 발송이 완료됐습니다. 이메일 인증을 진행하세요!');
						window.location.href = "./main.ma";
						return true;
					}
					if (data === "0") {
						alert("메일 전송 중 오류가 발생했습니다. 마이페이지에서 다시 진행해주세요.");
						window.location.href = "./myPage.me";
						return false;
					}
					if (data === "-1") {
						alert("해당 이메일로 계정이 존재합니다.");
						window.location.reload();

					}


					return false;

				},
				error: function(error) {
					alert('서버와 통신 중 오류가 발생했습니다.');
					history.back();
				}

			});

			return false;

		});
		// 이메일 변경	

	});

});