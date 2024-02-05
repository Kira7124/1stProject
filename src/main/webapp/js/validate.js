	
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
	
	    if (inputDate > currentDate) {
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
	
	
	

	    
	    
	    