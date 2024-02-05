package com.project4.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.order.db.OrderDAO;
import com.project4.order.db.OrderDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class OrderDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M: OrderDeleteAction_execute() 호출");
		
		// 전달 정보 저장(order_id)
		OrderDTO odto = new OrderDTO();
		odto.setOrder_id(Integer.parseInt(request.getParameter("order_id")));
		
		// DB에서 해당 내역 삭제
		OrderDAO odao = new OrderDAO();
		odao.deleteOrder(odto);
		
		// 페이지 이동
		JSMoveFunction.confirmLocation(response, "주문을 취소하시겠습니까??", "주문 취소 완료!", "./myPage.me");
		
		return null;
	}

}
