package com.project4.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.member.db.MemberDAO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class checkDuplicateIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : checkDuplicateIdAction.execute() 호출");
		
		// 전달받은 정보 저장
		String id = request.getParameter("id");
		
		boolean result ;
		
    	// DB와 연동해 입력받은 아이디와 DB에 존재하는 아이디 중복검사
    	MemberDAO mdao = new MemberDAO();
    	result = mdao.isDuplicateId(id);
    	
    	if (result) { // 중복
    		response.getWriter().write("duplicate");
    	} else {
            response.getWriter().write("not-duplicate");
        }
    		     
		return null;
	}

}
