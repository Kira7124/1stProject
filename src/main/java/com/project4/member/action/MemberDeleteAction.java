package com.project4.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		

		// 전달정보 저장(id,pw) -> DTO에 저장
		MemberDTO mdto = new MemberDTO();
		mdto.setId(id);
		
		// MemberDAO 객체 생성 - 회원정보 삭제 메서드
		MemberDAO mdao = new MemberDAO();
		
		mdao.deleteMember(mdto);
		
		request.setAttribute("mdao", mdao);
		
		JSMoveFunction.alertLocation(response, "삭제 성공!", "./MemberListAction.me");		
		
		
		return null;
	}

}
