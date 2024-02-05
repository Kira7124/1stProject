package com.project4.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.member.db.MemberDAO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class findMemberIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : findMemberIdAction.execute() 실행");
		
		// 전달받은 inputEmail 저장
		String inputEmail = request.getParameter("inputEmail");
		
		// DB에 해당 이메일로 아이디 존재 유무 판단
		MemberDAO mdao = new MemberDAO();
		String id = mdao.findMemberId(inputEmail);
		
			// 1. 없음 : 해당 이메일로 회원정보 없음 출력, hitsory.back();
			if (id == null) {
				JSMoveFunction.alertBack(response, "해당 이메일로 회원정보가 존재하지않습니다.");
			}
			// 2. 있음 : findMemberId.me 로 이동
			else {
				System.out.println(id);
				JSMoveFunction.alertLocation(response, "아이디 출력 페이지로 이동합니다.", "./findIdView.me?id="+id);
			}
			
		return null;
	}

}
