package com.project4.center.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.center.db.CenterDAO;
import com.project4.center.db.CenterDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class CenterEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		// 전달 정보 저장(as_id)
		int as_id = Integer.parseInt(request.getParameter("as_id"));
		
		// DAO, DTO객체 생성 및 getHistory() 메서드 실행
		CenterDAO cdao = new CenterDAO();
		CenterDTO ctdo = cdao.getHistory(as_id);
		
		// 신청 내역을 request 영역에 저장
		request.setAttribute("cdto", ctdo);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./center/centerEdit.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
