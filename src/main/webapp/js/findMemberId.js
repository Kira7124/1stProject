
var AuthNum;
var inputEmail;
var inputNum;
var checkClick = 0;

function AuthNumberRequest() { // 인증번호 받기 버튼 클릭 시 발생 이벤트
	
	inputEmail = $('#find-email').val();
	inputNum = $('#num-input').val();
	checkClick = 1;
	
	if(inputEmail === "") {
		alert('이메일을 입력하세요 !');
		return false;
	}
	
	$('#msg-randomNum').text("인증번호를 발송합니다.").css('color','Black');
	$.ajax({
			
			type: "POST",
			url: "./AuthNumberRequest.me",
			data: {inputEmail: inputEmail}, 
			dataType: "text",
			success: function(randomNum){
				$('#msg-randomNum').text("인증번호를 발송합니다").css('color','black');
				alert("인증번호를 발송했습니다.");
				AuthNum = randomNum;
				
			},
			error: function(error){
				$('#msg-randomNum').text("오류가 발생했습니다. 다시 시도하세요").css('color','red');
				
			}
			
			
		});
			
}

function clickFindId() {
	
	$(function () {
	
	var msg = $('#msg-randomNum').text();
		
	if(msg === '일치합니다.') {
		
		return true;
		
	} else if (msg === '일치하지않습니다.') {
		
		alert('인증번호가 일치하지않습니다. 다시 확인하세요!');
		
	} else if(checkClick === 0) {
		
		alert('인증을 먼저 진행하세요!');
		
	} else {
		
		alert('오류가 발생했습니다. 다시 시도해주세요!');
		
	}
		
	return false;
	
	});
	
	
}

$(function() {
	
	$('#num-input').keyup(function(){
		
		inputNum = $('#num-input').val();
		if(checkClick === 0) {
			$('#msg-randomNum').text("인증번호 받기를 먼저 진행하세요!").css('color','red');
		} else {
			if(inputNum === AuthNum && inputNum !== "") {
				$('#msg-randomNum').text("일치합니다.").css('color','green');
			} else {
				$('#msg-randomNum').text("일치하지않습니다.").css('color','red');
			}
		}
		
		
	});
	
});