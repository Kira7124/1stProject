package com.project4.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.salewrite.db.SaleWirteDTO;
import com.project4.salewrite.db.SaleWriteDAO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class SaleDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M: SaleDeleteAction_execute() 호출");
		
		// 전달 정보 저장(sale_id)
		SaleWirteDTO swdto = new SaleWirteDTO();
		swdto.setSale_id(Integer.parseInt(request.getParameter("sale_id")));
		
		// DB에서 해당 내역 삭제
		SaleWriteDAO swdao = new SaleWriteDAO();
		swdao.deleteForm(swdto);
		
		// 페이지 이동
		JSMoveFunction.confirmLocation(response, "판매 신청을 취소하시겠습니까?", "판매 내역 취소 완료!", "./myPage.me");
		
		return null;
	}

}
