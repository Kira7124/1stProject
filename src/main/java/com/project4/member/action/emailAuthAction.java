package com.project4.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.Gmail;
import com.project4.util.JSMoveFunction;
import com.project4.util.SHA256;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class emailAuthAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println(" M : emailAuthAction.execute() 호출");
		// 이메일로 회원 아이디 값 가져오기
		String email = request.getParameter("email");
		MemberDAO mdao = new MemberDAO();
		String id = mdao.findMemberId(email);
		
		// 이메일 인증이 완료된 회원 처리
				
		if(mdao.isEmailAuthenticate(id)) {
			JSMoveFunction.alertLocation(response, "이메일 인증을 이미 하셨습니다.", "./main.ma");
			return null;
		}
		
		// 이메일 인증 처리
		int result = mdao.updateEmailAuthCheck(id);
		
		if (result == 0) { // 이메일 인증 처리 오류
			JSMoveFunction.alertBack(response, "이메일 인증 도중 오류가 발생했습니다.\\n 다시 시도해주세요.");
			return null;
		}
		
		JSMoveFunction.alertLocation(response, "이메일 인증을 완료했습니다.", "./loginForm.me");
		
		
		return null;
	}

}
