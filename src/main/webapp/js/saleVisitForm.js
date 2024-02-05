function check() {

	var sale_name = document.fr.sale_name.value;
	var mid_phone_num = document.fr.mid_phone_num.value;
	var last_phone_num = document.fr.last_phone_num.value;
	var sale_bank_name = document.fr.sale_bank_name.value;
	var sale_account_num = document.fr.sale_account_num.value;
	var sale_owner_name = document.fr.sale_owner_name.value;
	var sale_visit_date = document.fr.sale_visit_date.value;

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
	if (sale_visit_date == "") {
		alert('날짜를 입력하세요');
		document.fr.sale_visit_date.focus();
		return false;
	} else {
		// 입력일 
		var inputDate = new Date(sale_visit_date);
		// 오늘 날짜를 자정으로 설정
		var today = new Date();
		today.setHours(0, 0, 0, 0);
		// 다음달
		var nextMonth = new Date();
		nextMonth.setMonth(nextMonth.getMonth() + 1);

		if (inputDate < today || inputDate > nextMonth) {
			alert('방문 예정일은 금일부터 한 달 동안만 신청 가능합니다.');
			document.fr.sale_visit_date.focus();
			return false;
		}
	}
}