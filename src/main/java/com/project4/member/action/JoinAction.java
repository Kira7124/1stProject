package com.project4.member.action;

import java.io.PrintWriter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;
import com.project4.util.SHA256;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : JoinAction.execute() 실행");
		request.setCharacterEncoding("utf-8"); 
		MemberDTO mdto = new MemberDTO();
		String address = request.getParameter("address")+"-"+request.getParameter("address_detail")
						+"-"+request.getParameter("zip_code");
		mdto.setId(request.getParameter("id"));
		mdto.setPw(request.getParameter("pw"));
		mdto.setName(request.getParameter("name"));
		mdto.setBirthDate(request.getParameter("birthDate"));
		mdto.setPhoneNum(request.getParameter("phoneNum"));
		mdto.setAddress(address);
		mdto.setEmail(request.getParameter("email"));
		mdto.setAdCheck(request.getParameter("adCheck"));
		mdto.setEmailHash(SHA256.getSHA256(mdto.getEmail()));
		System.out.println(" M : 회원정보 저장 완료");
		System.out.println(mdto);
		if(mdto.getId() == "" || mdto.getPw() == "" ||
				mdto.getBirthDate() == ""||
				mdto.getPhoneNum() == "" ||mdto.getAddress() == "--" ||
				mdto.getEmail() == "") {
			JSMoveFunction.alertBack(response, "입력하지 않은 사항이 있습니다.");
			return null;
		} 
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.insertMember(mdto);
		
		
		if ( result == 1) { // 회원가입 성공
			System.out.println(" M : 회원가입 완료");
			
			System.out.println(" M : id, email 세션 값 저장 완료");
			JSMoveFunction.alertLocation(response, "회원가입 성공, 이메일 인증 메일을 발송합니다. 이메일을 확인하세요."
					, "./joinEmailAuthSendAction.me?email="+request.getParameter("email"));
			
		} else { // result = 0 오류 
			if(mdao.isDuplicateEmail(mdto)) {
				System.out.println(" M : 이미 존재하는 이메일");
				JSMoveFunction.alertLocation(response, "해당 이메일로 계정이 존재합니다. 로그인해주세요", "./loginForm.me");
				return null;
			}
			if (mdao.isDuplicateId(mdto.getId()))
			System.out.println(" M : 이미 존재하는 아이디");
			JSMoveFunction.alertLocation(response, "이미 존재하는 아이디입니다.","./loginForm.me");
		}
		
		return null;
	}

}
