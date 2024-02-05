package com.project4.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.product.db.ProductDAO;
import com.project4.product.db.ProductDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class LentalDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : OrderDetailAction_execute() 실행");
		
		// 전달정보 저장(bno)
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		ProductDAO pdao = new ProductDAO();
		
		
		// DAO - 특정 글 정보를 가져오는 메서드 호출
		ProductDTO pdto = pdao.getLental(product_id);
		
		// 글정보를 request 영역에 저장
		request.setAttribute("pdto", pdto);
		

		// 페이지 이동 준비(./product/saleDetail.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./product/lentalDetail.jsp");
		forward.setRedirect(false);
		
		return forward;

	}

}
