package com.project4.product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.product.db.ProductDAO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class ProductSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 전달정보 검색어 정보 저장
		
		String search = request.getParameter("search");
		
		System.out.println(" M : 검색어 : "+search);
		
		// 기존에 저장된 글정보를 가져와서 화면에 출력
		ProductDAO dao = new ProductDAO();
		
		ArrayList searchProducts = null;
		
		if(search == null) {
			System.out.println("검색어 없음!!");
		}else {
			System.out.println("검색어 있음!!");
			if(search != null) {
				searchProducts = dao.searchProducts(search);
				System.out.println(searchProducts + "있음");	
			}
		}
		
		request.setAttribute("searchProducts", searchProducts);
		
		
		// 페이지 이동 준비
		ActionForward forward = new ActionForward();
		forward.setPath("./product/productSearchList.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
