package com.project4.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{

	// Gmail SMTP를 이용하기 위한 Gmail 계정 정보
	// SMTP 를 이용하기 위해 외부 라이브러리 JavaMail API를 사용
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		
		// 관리자 Gmail 계정정보 입력
		return new PasswordAuthentication("klop12111@gmail.com","rchx gibr ycek kifn");
		
	}
	
	
	
}
