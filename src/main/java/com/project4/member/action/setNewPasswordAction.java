package com.project4.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class setNewPasswordAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : setNewPasswordAction.execute() 실행");
		
		// 전달받은 id, pw 저장
		String id = request.getParameter("id");
		String pw = request.getParameter("newPw");
		MemberDTO mdto = new MemberDTO();
		mdto.setId(id);
		mdto.setPw(pw);
		
		// DB 연결 후 비밀번호 변경
		MemberDAO mdao = new MemberDAO();
		int result = mdao.updatePassword(mdto); // result : 1 성공 , result : 0 실패
		
		if (result == 1) {
			
			JSMoveFunction.alertLocation(response, "비밀번호 변경에 성공했습니다. 로그인 페이지로 이동합니다.", "./loginForm.me");
			
		} else {
			
			JSMoveFunction.alertBack(response, "오류가 발생했습니다. 다시 시도해주세요");
			
		}
		
		
		return null;
	}

}
