var newPassword1;
var newPassword2;


$(function(){
	
		// 입력 중일때 강조
		$('#current-password, #new-password, #new-password2').on('focus', function() {
			$(this).css('border','solid');
		});
		$('#current-password, #new-password, #new-password2').on('blur', function() {
			$(this).css('border', 'none');
		});
				    	        
		// 입력 중일때 강조

		
		// 현재 비밀번호 일치 여부
		
		$("#input-current-password").on("focusout", function() {
			
			var inputPassword = $(this).val();
			
			$.ajax({
				
				type: 'POST',
		        url: './checkPasswordAction.me',
		        data: {inputPassword: inputPassword},
		        success: function (data) {
			
		            if (data === "1") {
		                $("#msg-currentPw").text("일치합니다").css('color','green');
		            } else if(data === "0") {
		                $("#msg-currentPw").text("일치하지않습니다").css('color','red');
		            } else {
						$("#msg-currentPw").text("오류 발생 다시 로그인해주세요").css('color','red');									
					}
		        },
		        error: function () {
		            alert("서버 오류입니다. 잠시 후 다시 시도해주세요.");
		        }
				
			});
			
		});
		
		// 현재 비밀번호 일치 여부
		
		
		// 새 비밀번호의 유효성 검사
		
		$('#input-newPassword').on("keyup", function(){
			
		newPassword1 = $('#input-newPassword').val();
		console.log(newPassword1);

		// 유효성 검사식
		var isValid = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+]).{8,15}$/.test(newPassword1);
		
		if(!isValid || newPassword1 === "" || newPassword1 === null) {
			$('#msg-newPw2').text("사용 불가능한 비밀번호입니다.").css('color','red');
		} else {
			
			$('#msg-newPw2').text("사용 가능한 비밀번호입니다.").css('color','green');
			
			}

		});
		// 새 비밀번호의 유효성 검사
		
		$('#input-newPassword2').on("keyup", function(){
			
		newPassword2 = $('#input-newPassword2').val();
		
		if($('#msg-newPw2').text() != "사용 불가능한 비밀번호입니다." && newPassword1 === newPassword2) {
			
			$('#msg-newPw2').text("새 비밀번호와 일치합니다.").css('color','green');
			
		} else {
			
			$('#msg-newPw2').text("새 비밀번호가 일치하지않습니다.").css('color','red');
			
			}

		});
		
		
	
});

function updatePassword() {
	
	// 비밀번호 유효성 검사, 일치 여부 판단 검사가 끝난 뒤 수행
	var infoNewPassWordMsg = $('#msg-newPw2').text();
	var infoCurrentPasswordMsg = $('#msg-currentPw').text();
	var newPassword = $('#input-newPassword').val();
	console.log(infoNewPassWordMsg + ', ' + infoCurrentPasswordMsg);
	if(infoCurrentPasswordMsg === '일치합니다' && infoNewPassWordMsg === '새 비밀번호와 일치합니다.') {
		window.location.href="./updatePasswordAction.me?newPassword="+newPassword;
	} else {
		alert('조건에 충족하지 못한 사항이 있습니다!');
	} 
	
	
}
