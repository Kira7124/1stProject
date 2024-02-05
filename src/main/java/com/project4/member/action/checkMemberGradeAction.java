package com.project4.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class checkMemberGradeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : checkMemberGradeAction.execute() 실행");
		MemberDAO mdao = new MemberDAO();
		
		// 세션에 저장된 mdto 저장
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("mdto");
		
		// 전달받은 결제 금액 amount 저장
		int payAmount = Integer.parseInt(request.getParameter("price"));
		mdto.setPayAmount(payAmount);
		
		// amount에 ?% 포인트로 적립 (임의로 5%로 설정)
		mdao.updateMemberPoint(mdto, payAmount);
		
		// DB에서 누적금액 확인 후 회원등급 변경
		
			// DB에서 누적금액 확인
			mdto = mdao.getMemberInfo(mdto.getId());
			// 누적금액 달성 
			if(mdto.getPayAmount() >= 100000) {
				// 브론즈 -> 실버 + 포인트 적립 
				mdao.updateMemberGrade(mdto, "silver");
				JSMoveFunction.alertLocation(response, "회원님의 등급이 실버로 변경됐습니다!", "./main.ma");
			} else if (mdto.getPayAmount() >= 300000) {
				// 실버 -> 골드 + 포인트 적립
				mdao.updateMemberGrade(mdto, "gold");
				JSMoveFunction.alertLocation(response, "회원님의 등급이 골드로 변경됐습니다!", "./main.ma");
			} else if (mdto.getPayAmount() >= 800000) {
				// 골드 -> 다이아 + 포인트 적립
				mdao.updateMemberGrade(mdto, "diamond");
				JSMoveFunction.alertLocation(response, "회원님의 등급이 다이아로 변경됐습니다!", "./main.ma");
			}
			// 회원등급 변경
		

		
		return null;
	}

}
