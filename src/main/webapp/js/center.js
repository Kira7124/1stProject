function check() {
	
	var as_model = document.fr.as_model.value;
	var mid_phone_num = document.fr.mid_phone_num.value;
	var last_phone_num = document.fr.last_phone_num.value;
	var as_name = document.fr.as_name.value;
	var as_due_date = document.fr.as_due_date.value;
	var as_zip_code = document.fr.as_zip_code.value;
	var as_address = document.fr.as_address.value;
	var as_address_detail = document.fr.as_address_detail.value;
	
	if (as_model == "") {
		alert('모델명을 입력하세요');
		document.fr.as_model.focus();
		return false;
	}
	if (mid_phone_num == "") {
		alert('휴대폰 번호를 입력하세요');
		document.fr.mid_phone_num.focus();
		return false;
	}
	if (last_phone_num == "") {
		alert('휴대폰 번호를 입력하세요');
		document.fr.last_phone_num.focus();
		return false;
	}
	if (as_name == "") {
		alert('이름을 입력하세요');
		document.fr.as_name.focus();
		return false;
	}
	if (as_due_date == "") {
		alert('날짜를 입력하세요');
		document.fr.as_due_date.focus();
		return false;
	} else {
		// 입력일 
		var inputDate = new Date(as_due_date);
		// 오늘 날짜를 자정으로 설정
		var today = new Date();
		today.setHours(0, 0, 0, 0); 
		// 다음달
		var nextMonth = new Date();
		nextMonth.setMonth(nextMonth.getMonth() + 1);

		if (inputDate < today || inputDate > nextMonth) {
			alert('수리 희망일은 금일부터 한 달 동안만 신청 가능합니다.');
			document.fr.as_due_date.focus();
			return false;
		}
	}
	if (as_zip_code == "") {
		alert('주소를 입력하세요');
		document.fr.as_zip_code.focus();
		return false;
	}
	if (as_address == "") {
		alert('주소를 입력하세요');
		document.fr.as_address.focus();
		return false;
	}
	if (as_address_detail == "") {
		if (confirm('상세주소가 입력되지 않았습니다. 그대로 신청하시겠습니까?') == true) {
			document.fr.submit();
		} else {
			document.fr.as_address_detail.focus();
			return false;
		}
	}
}