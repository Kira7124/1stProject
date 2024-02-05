package com.project4.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class findMemberPwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : findMemberPwAction.execute() 실행");

		String id = request.getParameter("inputId");
		JSMoveFunction.alertLocation(response, "새 비밀번호 설정 페이지로 이동합니다.", "./findMemberPwView.me?id="+id);
		
		return null;
	}

}
