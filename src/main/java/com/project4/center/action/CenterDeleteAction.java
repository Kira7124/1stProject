package com.project4.center.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.center.db.CenterDAO;
import com.project4.center.db.CenterDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class CenterDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 전달된 정보 저장(as_id)
		CenterDTO cdto = new CenterDTO();
		cdto.setAs_id(Integer.parseInt(request.getParameter("as_id")));
		
		// DAO 객체 생성 및 deleteForm() 메서드 실행
		CenterDAO cdao = new CenterDAO();
		cdao.deleteForm(cdto);
		
		// 페이지 이동
		JSMoveFunction.confirmLocation(response, "A/S 신청을 취소하시겠습니까?", "A/S 신청 취소 완료!", "./myPage.me");
		
		return null;
	}

}
