var inputId ;
var msg ;
var checkClick = 0;
var inputEmail ;
var isCorrect;
var AuthNum;


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

function AuthNumberRequest() { // 인증번호 받기 버튼 클릭 시 발생 이벤트
	
	inputEmail = $('#input-email').val();
	inputId = $('#input-id').val();
	
	if(inputId === "") {
		alert('아이디를 입력하세요 !');
		return false;
	}
	if(inputEmail === "") {
		alert('이메일을 입력하세요 !');
		return false;
	}
	
	
	checkClick = 1;
	
	$('#msg-randomNum').text("인증번호를 발송합니다.").css('color','Black');
	 
	alert("인증번호를 발송했습니다. 인증번호가 오지않는다면, 아이디에 등록한 이메일을 확인하세요!");
	
	// 인증번호 받기 클릭 시 input-id와 input-email로 회원여부 판단 후 일치하면 인증번호 발송하기
	
		// 1. 회원정보 확인
		$.ajax({
				
				type: "POST",
				url: "./beforeAuthNumberRequest.me?inputId="+inputId+"&inputEmail="+inputEmail,
				dataType: "text",
				success: function(isExist){
					isCorrect = isExist;
					sendAuthMail();
				},
				error: function(error){
					console.log(error);
				},

			});
		
			
}

function sendAuthMail() {
	
		// 2. 회원정보가 존재
		
		if(isCorrect === "true") {
		
			$.ajax({
				
				type: "POST",
				url: "./AuthNumberRequest.me",
				data: {inputEmail: inputEmail}, 
				dataType: "text",
				success: function(randomNum){
					$('#msg-randomNum').text("인증번호를 발송합니다").css('color','black');
					
					AuthNum = randomNum;
					
				},
				error: function(error){
					$('#msg-randomNum').text("오류가 발생했습니다. 다시 시도하세요").css('color','red');
					
				}
				
				
			});
			
		} 
	
}

function clickFindPw() {
	
	var msg = $('#msg-randomNum').text();
	
	if(checkClick === 0) {
		
		alert('인증을 먼저 진행하세요!');
		return false;
		
	} else if(msg === '일치합니다.') {
		
		return true;
		
	} else if (msg === '일치하지않습니다.') {
		
		alert('인증번호가 일치하지않습니다. 다시 확인하세요!');
		
	} else {
		
		alert('오류가 발생했습니다. 다시 시도해주세요!');
	}
	
	
	return false;
	
}
