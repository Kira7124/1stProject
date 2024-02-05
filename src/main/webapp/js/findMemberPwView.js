$(function (){
	
		// 입력 중일때만 강조
		
		$('#new-pw, #new-pw2').on('focus', function() {
			$(this).css('border','solid');
		});
		$('#new-pw, #new-pw2').on('blur', function() {
			$(this).css('border', 'none');
		});
				    	        
		// 입력 중일때만 강조
	
		// 비밀번호 유효성 검사
		
		$('#new-pw').keyup(function(){
			
			// 사용자가 입력한 비밀번호
			var pw = $(this).val();
			// 출력할 메시지
			var msgPw = $('#msg-pw');
			// 비밀번호 유효성 검사식
			var isValid = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+]).{8,15}$/.test(pw);
			
			if(!isValid || pw === "" || pw === null) {
				msgPw.text("사용 불가능한 비밀번호입니다.").css('color','red');
			} else {
				msgPw.text("사용 가능한 비밀번호입니다.").css('color','green');
			}
		    
		});
		// 비밀번호 유효성 검사
		
		// 비밀번호 일치 확인
		
		$('#new-pw2').keyup(function (){
			
			// 사용자가 입력한 비밀번호가 불가능한 비밀번호일 때
			if($('#msg-pw').text() === "사용 불가능한 비밀번호입니다.") {
				$('#msg-pw2').text("사용 불가능한 비밀번호입니다.").css('color','red');
			} else if ($('#new-pw').val() === $('#new-pw2').val()) {
				$('#msg-pw2').text("일치합니다!").css('color','green');
			}
			
		});		
		// 비밀번호 일치 확인
		
});   

function clickChangeNewPw() {
	
	if ($('#msg-pw2').text() === "일치합니다!") {
		
		return true;
		
	}
	
	alert('비밀번호를 확인하세요!')
	return false;
	
}