package com.project4.member.action;

import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.member.db.MemberDAO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.Gmail;
import com.project4.util.JSMoveFunction;

public class beforeAuthNumberRequestAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : beforeAuthNumberRequestAction.execute() 실행");
		
		// 전달받은 inputId, inputEmail 저장
		String inputId = request.getParameter("inputId");
		String inputEmail = request.getParameter("inputEmail");
		System.out.println(" M : inputId : " +inputId+ ", inputEmail : " +inputEmail);
		// DB에서 확인
		MemberDAO mdao = new MemberDAO();
		String isExist = mdao.findMemberPw(inputId, inputEmail);

		// ajax에 결과값 전송 ("true" or "false")
			response.setContentType("text");
			PrintWriter out = response.getWriter();
			out.write(isExist);
			out.close();
						
		return null;
		
	}

}
