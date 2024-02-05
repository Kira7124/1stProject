package com.project4.order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDTO;
import com.project4.order.db.OrderDAO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class RentalHistoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M: RentalHistoryAction_execute() 호출");
		
		// 전달 정보 저장(user_id)
		HttpSession session = request.getSession();
		MemberDTO mdto = new MemberDTO();
		mdto = (MemberDTO) session.getAttribute("mdto");
		
		// 사용자 아이디 확인
		String user_id = (mdto != null) ? mdto.getId() : null;
		
		// OrderDAO 객체 생성 & getHistory() 메서드 실행
		OrderDAO odao = new OrderDAO();
		ArrayList orderList = odao.getRentalHistory(user_id);
		request.setAttribute("orderList", orderList);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./product/rentalHistory.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
