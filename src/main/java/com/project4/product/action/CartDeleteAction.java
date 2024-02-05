package com.project4.product.action;

import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.product.db.CartDAO;
import com.project4.product.db.CartDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class CartDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 현재 세션을 가져오기
		HttpSession session = request.getSession();

		// 선택한 상품들의 c_id를 배열로 받기
		String[] c_ids = request.getParameterValues("c_id");

		if (c_ids != null) {
			// CartDAO 객체를 생성
			CartDAO cdao = new CartDAO();

			// 세션에서 장바구니 리스트를 가져옴
			ArrayList cartList = (ArrayList) session.getAttribute("cartList");

			for (String c_id : c_ids) {
				int id = Integer.parseInt(c_id);

				// 세션에서 해당 상품을 삭제
				for (Iterator iterator = cartList.iterator(); iterator.hasNext();) {
					CartDTO cdto = (CartDTO) iterator.next();
					if (cdto.getC_id() == id) {
						iterator.remove();
						break;
					}
				}

				// 데이터베이스에서 해당 상품을 삭제
				CartDTO cdto = new CartDTO();
				cdto.setC_id(id);
				cdao.deleteCart(cdto);

			}

			// request에 삭제 후 정보를 저장
			request.setAttribute("cdao", cdao);
			request.setAttribute("cartList", cartList);

		}
		// 삭제 후 장바구니 페이지로 리다이렉트
		ActionForward forward = new ActionForward();
		forward.setPath("./Cart.pd");
		forward.setRedirect(true);
		return forward;

	}
}
