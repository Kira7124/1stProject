package com.project4.product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDTO;
import com.project4.salewrite.db.SaleWriteDAO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class SaleHistoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M: SaleHistoryAction_execute() 호출");
		
		// 전달 정보 저장(user_id)
		HttpSession session = request.getSession();
		MemberDTO mdto = new MemberDTO();
		mdto = (MemberDTO) session.getAttribute("mdto");
		
		// 사용자 아이디 확인
		String user_id = (mdto != null) ? mdto.getId() : null;
//		System.out.println("Session ID: " + id);
		
		// SaleWriteDAO 객체 생성 & getHistory() 메서드 실행
		SaleWriteDAO swdao = new SaleWriteDAO();
		ArrayList saleList = swdao.getHistory(user_id);
		request.setAttribute("saleList", saleList);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./product/saleHistory.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
