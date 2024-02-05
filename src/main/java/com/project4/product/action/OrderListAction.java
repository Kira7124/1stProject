package com.project4.product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.product.db.ProductDAO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : OrderListAction_execute() 실행");
		
		String category = request.getParameter("category");
		String company = request.getParameter("company");
		
		// 기존에 저장된 글정보를 가져와서 화면에 출력
		ProductDAO dao = new ProductDAO();
		
		int count = dao.getProductCount("구매");
		System.out.println(" M : 글 개수 :"+ count);
		
		// DAO - 글정보 모두(list)를 가져오는 메서드 호출
        ArrayList productList = null;


		/*
		 * if (category != null && !category.isEmpty()) { // 'title'은 '구매'로 고정
		 * productList = dao.getProductList("구매", category); } else { productList =
		 * dao.getProductList("구매"); }
		 */
        
        if (category != null && !category.isEmpty()) {
            productList = dao.getProductList("구매", category);
            System.out.println(category + " 있음");
        }
        else if (company != null && !company.isEmpty()) {
        	productList = dao.getProductList1("구매", company);
        	System.out.println(company + " 있음");
        }
        else {
            productList = dao.getProductList("구매");
            System.out.println("카테고리 없음!!");
        }
        
        
		// 리스트를 출력 => 연결된 뷰페이지에서 출력하도록 정보 전달
		request.setAttribute("productList", productList);

		
		
		// 페이지 이동준비
		ActionForward forward = new ActionForward();
		forward.setPath("./product/orderList.jsp");
		forward.setRedirect(false);
		
		return forward;
			

	}

}
