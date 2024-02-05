
	function checkDuplicateId() {
        var id = document.getElementById("join-id").value;
      	
	    if (id === "") {
	        alert("아이디를 입력하세요");
	        return false;
	    }
	    if (id.includes('admin')) {
	        alert("아이디에 'admin'을 포함할 수 없습니다.");
	        return false;
	    }
	    if (!/^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,15}$/.test(id)) {
	        alert("아이디는 영문과 숫자의 조합으로 8자에서 15자 사이여야 합니다.");
	        return false;
	    }
    
        $.ajax({
            type: 'POST',
            url: './checkDuplicateId.me', // 중복 검사 수행 주소
            data: {id: id},
            success: function (data) {
                if (data === "duplicate") {
                    alert("이미 존재하는 아이디입니다.");
                } else {
                    alert("사용할 수 있는 아이디입니다.");
                }
            },
            error: function () {
                alert("서버 오류입니다. 잠시 후 다시 시도해주세요.");
            }

        });
        
    }