package com.project4.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDTO;
import com.project4.product.db.ProductDAO;
import com.project4.product.db.ProductDTO;
import com.project4.salewrite.db.SaleWirteDTO;
import com.project4.salewrite.db.SaleWriteDAO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class SaleVisitActionPro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M: saleWriteActionPro_execute() 호출");
		
		// 전달 정보 저장(user_id)
		HttpSession session = request.getSession();
		MemberDTO mdto = new MemberDTO();
		mdto = (MemberDTO) session.getAttribute("mdto");
		String user_id = (mdto != null) ? mdto.getId() : null;
		System.out.println("Session ID: " + user_id);
		
		// phone_num 연결
		String sale_phone_num = request.getParameter("first_phone_num") + request.getParameter("mid_phone_num") + request.getParameter("last_phone_num");
		
		// DTO에 객체 저장
		SaleWirteDTO swdto = new SaleWirteDTO();
		swdto.setSale_name(request.getParameter("sale_name"));
		swdto.setSale_phone_num(sale_phone_num);
		swdto.setSale_bank_name(request.getParameter("sale_bank_name"));
		swdto.setSale_account_num(request.getParameter("sale_account_num"));
		swdto.setSale_owner_name(request.getParameter("sale_owner_name"));
		
		swdto.setSale_zip_code(request.getParameter("sale_zip_code"));
		swdto.setSale_address(request.getParameter("sale_address"));
		swdto.setSale_address_detail(request.getParameter("sale_address_detail"));
		swdto.setSale_reference(request.getParameter("sale_reference"));
		swdto.setSale_visit_date(request.getParameter("sale_visit_date"));
		swdto.setSale_user_id(user_id);
		swdto.setSale_model(request.getParameter("sale_model"));
		swdto.setSale_expected_price(Integer.parseInt(request.getParameter("sale_expected_price")));
		
		System.out.println("M: swdto" + swdto);
		
		SaleWriteDAO swdao = new SaleWriteDAO();
		swdao.saleVisit(swdto);
		
		// 페이지 이동
		JSMoveFunction.confirmLocation(response, "판매 신청서를 제출하시겠습니까?", "판매 신청 완료!", "./main.ma");
		
		return null;
	}

}
