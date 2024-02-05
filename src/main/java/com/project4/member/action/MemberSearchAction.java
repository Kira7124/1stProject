package com.project4.member.action;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.member.db.MemberDAO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class MemberSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String search = request.getParameter("search");
		System.out.println(" M : 검색어 : "+search);
		
		// 기존에 저장된 글정보를 가져와서 화면에 출력
		MemberDAO mdao = new MemberDAO();
		
		int count = 0;
		ArrayList searchMembers = null;
		
		if(search == null) {
			System.out.println("검색어 없음!!");
			count = mdao.getMemberCount(search);
		}else {
			System.out.println("검색어 있음!!");
			count = mdao.getMemberCount(search);
			if(count > 0) {
				searchMembers = mdao.searchMembers(search);
				System.out.println(searchMembers + "있음");
			}
		}
		
		request.setAttribute("searchMembers", searchMembers);
		
		
		// 페이지 이동 준비
		ActionForward forward = new ActionForward();
		forward.setPath("./member/memberSearchList.jsp");
		forward.setRedirect(false);
		
		
		return forward;
		
		
	}

}
