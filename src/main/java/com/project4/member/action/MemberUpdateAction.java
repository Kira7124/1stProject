package com.project4.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String phoneNum = request.getParameter("phoneNum");
		String address = request.getParameter("address");

		
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./main.ma");
			forward.setRedirect(true);
			return forward;
		}
		
		request.setCharacterEncoding("UTF-8");
		
		MemberDTO mdto = new MemberDTO();
		
		mdto.setId(id);
		mdto.setPw(pw);
		mdto.setEmail(email);
		mdto.setName(name);
		mdto.setPhoneNum(phoneNum);
		mdto.setAddress(address);
		
		
		MemberDAO mdao = new MemberDAO();
		
		int result = mdao.updateMember(mdto);
		
		if(result == 1) { // 수정완료 -> 메인페이지로 이동
			JSMoveFunction.alertLocation(response, "회원정보 수정완료!", "./MemberListAction.me");
			return null;
		}else if(result == 0) { // 수정실패 -> 비밀번호 오류 -> 뒤로가기 이동
			JSMoveFunction.alertBack(response, "수정실패X- 비밀번호 오류");
			return null;
		}else { //result == -1
			JSMoveFunction.alertBack(response, "회원정보가 없음!!!");
		}
			
		return null;
	}

}
