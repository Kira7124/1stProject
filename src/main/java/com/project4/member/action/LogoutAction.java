package com.project4.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : LogoutAction_execute() 호출");

		// 로그아웃 처리 => 세션 정보 초기화
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		JSMoveFunction.alertLocation(response, "로그아웃 완료!!", "./main.ma");
		
		
		return null;
	}

}
