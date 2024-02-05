package com.project4.member.action;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// IF ( 아이디없을 때 ) ELSE IF ( 아이디 있으면서 ADMIN 일때 둘다 TURE 로 담아주기 ! )
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
	
		ActionForward forward = new ActionForward();
		

				
			MemberDAO mdao = new MemberDAO();
			ArrayList memberList = mdao.getMemberList();
			
			request.setAttribute("memberList", memberList);
			
			
			forward.setPath("./member/memberList.jsp");
			forward.setRedirect(false);
		
		
		return forward;
	}

}
