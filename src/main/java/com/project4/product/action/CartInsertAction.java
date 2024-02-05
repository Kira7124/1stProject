package com.project4.product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.product.db.CartDAO;
import com.project4.product.db.CartDTO;
import com.project4.product.db.ProductDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class CartInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : CartInsertAction_execute() 실행");

		String u_id = request.getParameter("u_id");
		
		CartDTO cdto = new CartDTO();
		cdto.setP_id(Integer.parseInt(request.getParameter("id")));
		cdto.setU_id(request.getParameter("u_id"));
		cdto.setPrice(Integer.parseInt(request.getParameter("price")));
		cdto.setImage(request.getParameter("img"));
		cdto.setModel(request.getParameter("model"));
		cdto.setColor(request.getParameter("status2"));
		cdto.setCapacity(request.getParameter("status1"));
		cdto.setTitle(request.getParameter("title"));
		cdto.setGrade(request.getParameter("status3"));
		cdto.setStart_date(request.getParameter("rentalStartDate"));
		cdto.setEnd_date(request.getParameter("rentalEndDate"));
		cdto.setName(request.getParameter("name"));
		cdto.setTotal_date(request.getParameter("rentalDuration"));
		
		System.out.println("M : cdto" + cdto);

		CartDAO cdao = new CartDAO();
		cdao.insertCart(cdto);
		
		ArrayList cartList = cdao.getCartList(u_id);

		
		// 데이터를 세션에 저장
		HttpSession session = request.getSession();

//		session.setAttribute("cdao", cdao);
		session.setAttribute("cartList", cartList);
		
		// request 영역에 정보 저장
//		request.setAttribute("cdto", cdto);
//		request.setAttribute("cdao", cdao);
//		request.setAttribute("cartList", cartList);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./Cart.pd");
		forward.setRedirect(true);

		return forward;
	}

}
