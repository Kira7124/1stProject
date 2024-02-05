function check() {

	var sale_name = document.fr.sale_name.value;
	var mid_phone_num = document.fr.mid_phone_num.value;
	var last_phone_num = document.fr.last_phone_num.value;
	var sale_bank_name = document.fr.sale_bank_name.value;
	var sale_account_num = document.fr.sale_account_num.value;
	var sale_owner_name = document.fr.sale_owner_name.value;
	var sale_zip_code = document.fr.sale_zip_code.value;
	var sale_address = document.fr.sale_address.value;
	var sale_address_detail = document.fr.sale_address_detail.value;
	var sale_delivery_date = document.fr.sale_delivery_date.value;

	if (sale_name == "") {
		alert('이름을 입력하세요');
		document.fr.sale_name.focus();
		return false;
	}
	if (mid_phone_num == "") {
		alert('전화번호를 입력하세요');
		document.fr.mid_phone_num.focus();
		return false;
	}
	if (last_phone_num == "") {
		alert('전화번호를 입력하세요');
		document.fr.last_phone_num.focus();
		return false;
	}
	if (sale_bank_name == "0") {
		alert('거래은행을 입력하세요');
		document.fr.sale_bank_name.focus();
		return false;
	}
	if (sale_account_num == "") {
		alert('계좌번호를 입력하세요');
		document.fr.sale_account_num.focus();
		return false;
	}
	if (sale_owner_name == "") {
		alert('예금주명을 입력하세요');
		document.fr.sale_owner_name.focus();
		return false;
	}
	if (sale_zip_code == "") {
		alert('주소를 입력하세요');
		document.fr.sale_zip_code.focus();
		return false;
	}
	if (sale_address == "") {
		alert('주소를 입력하세요');
		document.fr.sale_address.focus();
		return false;
	}
	if (sale_delivery_date == "") {
		alert('날짜를 입력하세요');
		document.fr.sale_delivery_date.focus();
		return false;
	} else {
		// 입력일 
		var inputDate = new Date(sale_delivery_date);
		// 오늘 날짜를 자정으로 설정
		var today = new Date();
		today.setHours(0, 0, 0, 0);
		// 다음주
		var nextWeek = new Date();
		nextWeek.setDate(nextWeek.getDate() + 7);

		if (inputDate < today || inputDate > nextWeek) {
			alert('배송 예정일은 금일부터 일주일 동안만 신청 가능합니다.');
			document.fr.sale_delivery_date.focus();
			return false;
		}
	}
	if (sale_address_detail == "") {
		if (confirm('상세주소가 입력되지 않았습니다. 그대로 신청하시겠습니까?') == true) {
			document.fr.submit();
		} else {
			document.fr.sale_address_detail.focus();
			return false;
		}
	}
}