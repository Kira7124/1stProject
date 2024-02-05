package com.project4.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.product.db.ProductDAO;
import com.project4.product.db.ProductDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class ProductUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("\t M : ProductUpdateAction_execute() 호출");
		
		// 전달 정보 product_id 받기
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		
		// 기존에 저장된 상품 정보를 가져오기
		ProductDAO pdao = new ProductDAO();
		
		ProductDTO pdto = pdao.getProduct(product_id);
		
		// request 영역에 정보를 저장 후 전달
		request.setAttribute("pdto", pdto);
		
		// 페이지 이동 준비
		ActionForward forward = new ActionForward();
		forward.setPath("./product/productUpdateForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
