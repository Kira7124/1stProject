package com.project4.center.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class CenterWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 세션 값에서 회원 정보를 가져옴
		HttpSession session = request.getSession();
		MemberDTO mdto = new MemberDTO();
		mdto = (MemberDTO) session.getAttribute("mdto");
		
		// 사용자 아이디 확인
		String user_id = (mdto != null) ? mdto.getId() : null;
//		System.out.println("Session ID: " + user_id);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./center/center.jsp");
		forward.setRedirect(false);	
		
		return forward;
	}

}
