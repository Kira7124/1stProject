package com.project4.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.product.db.ProductDAO;
import com.project4.product.db.ProductDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class ProductDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("\t M : ProductDeleteAction_execute() 호출 ");
		
		// 한글 처리 => web.xml
		// 전달 정보 product_id 받기
		ProductDTO pdto = new ProductDTO();
		pdto.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
		pdto.setTitle(request.getParameter("title"));
		
		// 전달 정보 이용하여 디비에 가서 해당 글정보를 삭제
		ProductDAO pdao = new ProductDAO();
		pdao.deleteProduct(pdto);
		
		// JS 사용 페이지 이동	
		if(request.getParameter("title").equals("판매")) {
			JSMoveFunction.alertLocation(response, "삭제 성공!", "./saleList.pd");
		}
		if(request.getParameter("title").equals("구매")) {
			JSMoveFunction.alertLocation(response, "삭제 성공!", "./orderList.pd");
		}
		if(request.getParameter("title").equals("대여")) {
			JSMoveFunction.alertLocation(response, "삭제 성공!", "./lentalList.pd");
		}
		return null;
	}

}
