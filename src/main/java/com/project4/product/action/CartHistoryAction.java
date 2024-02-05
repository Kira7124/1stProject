package com.project4.product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.center.db.CenterDAO;
import com.project4.member.db.MemberDTO;
import com.project4.product.db.CartDAO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class CartHistoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// 세션 값에서 회원 정보를 가져옴
		MemberDTO mdto = (MemberDTO) request.getSession().getAttribute("mdto");
		
		// 사용자 아이디 확인
		String u_id = mdto.getId();
		
		// DAO 객체 생성 및 getHistory() 메서드 실행
		CartDAO cdao = new CartDAO();
		ArrayList cartList = cdao.getCartList(u_id);
		
		// 데이터를 세션에 저장
		HttpSession session = request.getSession();
		
		session.setAttribute("cartList", cartList);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./product/cartHistory.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
