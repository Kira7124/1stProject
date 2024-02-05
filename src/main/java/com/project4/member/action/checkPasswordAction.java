package com.project4.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class checkPasswordAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : updatePasswordAction.execute() 실행");
		// 입력받은 비밀번호와 DB에 저장된 비밀번호 일치여부 판단
		
		request.setCharacterEncoding("utf-8"); 
		String inputPassword = request.getParameter("inputPassword"); // 입력받은 현재 비밀번호
		
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("mdto"); // 세션 값 mdto 저장
		System.out.println(mdto);
		String id = mdto.getId(); // 아이디 저장
		
		MemberDAO mdao = new MemberDAO();
		mdto = mdao.getMemberInfo(id); // 회원정보 저장
		
		if(mdto.getPw().equals(inputPassword)) {
			System.out.println("입력 비밀번호 : " +inputPassword);
			System.out.println("DB 비밀번호 : " +mdto.getPw());
			System.out.println("결과 : 일치");
			
			// Ajax로 데이터 전송
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("1");
		} else {
			System.out.println("입력 비밀번호 : " +inputPassword);
			System.out.println("DB 비밀번호 : " +mdto.getPw());
			System.out.println("결과 : 불일치");
			
			//Ajax로 데이터 전송
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("0");
		}
		
		
		
		
		return null;
	}

}
