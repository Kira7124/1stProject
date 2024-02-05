package com.project4.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.product.db.ProductDAO;
import com.project4.product.db.ProductDTO;

import com.project4.util.Action;
import com.project4.util.ActionForward;

public class SaleVisitAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M: SaleVisitAction_execute() 호출");
		
		// 전달정보 저장(bno)
		int id = Integer.parseInt(request.getParameter("id"));
		String price = request.getParameter("price");
		
		ProductDAO pdao = new ProductDAO();
		
		
		// DAO - 특정 글 정보를 가져오는 메서드 호출
		ProductDTO pdto = pdao.getSale(id);
		
		// 글정보를 request 영역에 저장
		request.setAttribute("pdto", pdto);
		request.setAttribute("price", price);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./product/saleVisitForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
