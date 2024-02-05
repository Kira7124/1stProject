package com.project4.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class changePhoneNumAction {

	
	public int execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : changePhoneNumAction.execute() 실행");
		// 입력받은 데이터 저장
		String newPhoneNum = request.getParameter("newPhoneNum");
		// 세션 값 가져오기
		HttpSession session = request.getSession();
		MemberDTO mdto = new MemberDTO();
		mdto = (MemberDTO) session.getAttribute("mdto");
		
		// mdto에 입력받은 휴대전화 번호 저장
		mdto.setPhoneNum(newPhoneNum);
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.changePhoneNum(mdto);
		// 성공 시 result = 1, 오류 시 result = 0
		
		return result;
		
	}

}
