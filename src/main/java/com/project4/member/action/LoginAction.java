package com.project4.member.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class LoginAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : LoginAction_execute 실행");
		
		//한글처리 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 전달받은 정보 저장 (id, pw)
		MemberDTO mdto = new MemberDTO();
		mdto.setId(request.getParameter("id"));
		mdto.setPw(request.getParameter("pw"));
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO mdao = new MemberDAO();
		

			// 회원정보 확인 메서드 
			int result = mdao.memberCheck(mdto);
			if(result == 0) { // 비밀번호 오류
				JSMoveFunction.alertBack(response, "비밀번호 오류");
				
				return null;
			}
			
			if(result == -1) { // 정보 없음
				JSMoveFunction.alertBack(response, "회원정보 없음");
				
				return null;
			}
			if(result == 1) { // 로그인 성공
				if (mdao.isEmailAuthenticate(id)) { 	// 이메일 인증 완료된 계정 : 정상 로그인 처리
					JSMoveFunction.alertLocation(response, "로그인 성공!!", "./main.ma");
					// 세션 값 생성
					HttpSession session = request.getSession();
					session.setAttribute("mdto", mdto);
				} 
				else { // 이메일 인증 완료X : 이메일 인증 먼저 처리 후 로그인 할 수 있도록한다.
					JSMoveFunction.alertBack(response, "이메일 인증을 먼저 진행해주세요.");
				}
			}
		 
		return null;
	}

}
