package com.project4.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class updatePasswordAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : updatePasswordAction.execute() 실행");
		
		// 세션 값 저장
		HttpSession session = request.getSession();
		MemberDTO mdto = new MemberDTO();
		mdto = (MemberDTO) session.getAttribute("mdto");
		
		
		// 전달받은 newPassword mdto에 저장
		request.setCharacterEncoding("utf-8");
		String newPassword = request.getParameter("newPassword");
		System.out.println(newPassword);
		mdto.setPw(request.getParameter("newPassword"));
		
		// 비밀번호 변경 메서드
		MemberDAO mdao = new MemberDAO();
		int result = mdao.updatePassword(mdto);
		
		if(result == 1) {
			session.invalidate();
			JSMoveFunction.alertLocation(response, "비밀번호 변경에 성공했습니다.", "./main.ma");
		} else {
			JSMoveFunction.alertBack(response, "오류가 발생했습니다. 다시 시도해주세요");
		}
		
		return null;
	}

}
