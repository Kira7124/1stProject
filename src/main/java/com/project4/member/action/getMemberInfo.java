package com.project4.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class getMemberInfo implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = new MemberDTO();
		
		HttpSession session = request.getSession();
		mdto = (MemberDTO) session.getAttribute("mdto");
		String id = mdto.getId();
		
		mdto = mdao.getMemberInfo(id);
		
		request.setAttribute("mdto", mdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./member/myPage.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
