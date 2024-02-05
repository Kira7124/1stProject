
	$(function() {
		
		// 입력 중일때만 강조
		$('#join-id, #join-pw').on('focus', function() {
			$(this).css('border','solid');
		});
		$('#join-id, #join-pw').on('blur', function() {
			$(this).css('border', 'none');
		});
				    	        
		// 입력 중일때만 강조
		
		// 아이디 유효성 검사
		
		$('#join-id').focusout(function() {
									
			// 사용자가 입력한 아이디
			var id = $(this).val();
			// 출력할 메시지 
			var msgId = $('#msg-id');
			// 유효성 검사 정규식
			var isValid = !/^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,15}$/.test(id);
			
			
		
		
		if(isValid || id.includes(' ') || id.includes('admin')) {
			
			msgId.text("사용 불가능한 아이디입니다.").css('color', 'red');
			return;
		} 
		else {
			
			$.ajax({
								
		        type: 'POST',
		        url: './checkDuplicateId.me', // 중복 검사 수행 주소
		        data: {id: id},
                success: function (data) {
			
					 if (data === "duplicate") {
						msgId.text("이미 존재하는 아이디입니다.").css('color', 'red');
					} else {
						msgId.text("사용 가능한 아이디입니다.").css('color', 'green');
					}
		            
	        	},
		        error: function () {
		            alert("서버 오류입니다. 잠시 후 다시 시도해주세요.");
		        	}
	           
	
	   			 });
	   		 
		}
			
		});
		// 아이디 유효성 검사
				
		
		// 비밀번호 유효성 검사
		
		$('#join-pw').keyup(function(){
			
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
			$('#join-pw').on('input', function() {
		        $(this).focus();
		    });
		    
		// 비밀번호 유효성 검사
		    
		
		});	
		
	
	
		function checkAll() {
	        // 다른 필드들에 대한 유효성 검사
	       	if (!checkId() || !checkPassword() || !validateBirthDate() || !validatePhoneNum() || !validateName()) {
	       		return false;
	    	}
	    	
	        return true;
	    }
		
	    function checkId() {
	        var id = document.getElementById("join-id").value;
	        if (id === "") {
	            alert("아이디를 입력하세요");
	            return false;
	        }
	        if (id.length < 8 || id.length > 15 || id.includes('admin')) {
	            alert("8자 이상 15자 이하여야 하며, admin을 포함할 수 없습니다.");
	            return false;
	        }
	        return true;
	    }
	
	    function checkPassword() {
	        var pw = document.getElementById("join-pw").value;
	        if (pw === "") {
	            alert("비밀번호를 입력하세요");
	            return false;
	        }
	        if (!pw.match(/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+]).{8,15}$/)) {
	            alert("비밀번호는 영문, 숫자, 특수문자를 조합한 8자 이상 15자 이하여야 합니다.");
	            return false;
	        }
	        return true;
	    }
	    
	   function validateBirthDate() {
		
		    var inputDate = new Date(document.getElementById("join-birth").value);
		    var currentDate = new Date();
		    var maxDate = new Date();
		    maxDate.setFullYear(maxDate.getFullYear() - 150);
		
		    if (isNaN(inputDate) > currentDate) {
		        alert("생년월일은 미래일 수 없습니다.");
		        return false;
		    }
		
		    if (inputDate < maxDate) {
		        alert("생년월일은 현재로부터 150년 이내로 설정해야 합니다.");
		        return false;
		    }
		
		    var pattern = /^(19|20)\d\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/;
		    if (!pattern.test(document.getElementById("join-birth").value)) {
		        alert("생년월일은 YYYY-MM-DD 형식으로 입력해야 합니다.");
		        return false;
		    }
	
	    return true;
	    
		}
	
	    
	    function validatePhoneNum() {
		
		    var phoneNum = document.getElementById("join-phoneNum").value;
		    var pattern = /^\d{11}$/;
		
		    if (!pattern.test(phoneNum) || phoneNum.includes("-")) {
		        alert("휴대폰 번호는 11자리 숫자이어야 하며, '-'를 포함해서는 안됩니다.");
		        return false;
		    }
	    
	    return true;
		}
		
		function validateName() {
			
		    var name = document.getElementById("join-name").value;
		    var pattern = /^[가-힣]+$/;
		
		    if (!pattern.test(name)) {
			
		        alert("올바르지 않은 이름입니다.");
		        return false;
		    }
		
		    return true;
		}
		
		
	    
	    
	    
	});
        