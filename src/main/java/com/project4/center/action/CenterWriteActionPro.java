package com.project4.center.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.center.db.CenterDAO;
import com.project4.center.db.CenterDTO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class CenterWriteActionPro implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션 값에서 회원 정보를 가져옴
		HttpSession session = request.getSession();
		MemberDTO mdto = new MemberDTO();
		mdto = (MemberDTO) session.getAttribute("mdto");
		
		// 사용자 아이디 확인
		String user_id = (mdto != null) ? mdto.getId() : null;
//		System.out.println("Session ID: " + user_id);
		
		// 전화번호 연결
		String as_phone_num = request.getParameter("first_phone_num") + request.getParameter("mid_phone_num") + request.getParameter("last_phone_num");
		
		// DTO에 객체 저장
		CenterDTO cdto = new CenterDTO();
		cdto.setAs_model(request.getParameter("as_model"));
		cdto.setAs_phone_num(as_phone_num);
		cdto.setAs_name(request.getParameter("as_name"));
		cdto.setAs_comment(request.getParameter("as_comment"));
		cdto.setAs_zip_code(request.getParameter("as_zip_code"));
		cdto.setAs_address(request.getParameter("as_address"));
		cdto.setAs_address_detail(request.getParameter("as_address_detail"));
		cdto.setAs_reference(request.getParameter("as_reference"));
		cdto.setAs_due_date(request.getParameter("as_due_date"));
		cdto.setAs_user_id(user_id);
//		System.out.println("M: " + cdto);
		
		// DAO객체 생성 및 asRequest() 메서드 실행
		CenterDAO cdao = new CenterDAO();
		cdao.asRequest(cdto);
		
		// 페이지 이동
		JSMoveFunction.confirmLocation(response,"A/S 신청을 제출하시겠습니까?", "A/S 신청 완료!", "./main.ma");
		
		return null;
	}

}
