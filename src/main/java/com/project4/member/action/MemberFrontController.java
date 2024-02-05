package com.project4.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : MemberFrontController_doProcess() 실행");
		System.out.println("\n------------------1. 가상주소 계산-------------------");
		
		String requestURI = request.getRequestURI();
		System.out.println("requestURI : " + requestURI);
		String CTXPath = request.getContextPath();
		System.out.println("CTXPath : "+CTXPath);
		String command = requestURI.substring(CTXPath.length());
		System.out.println("command : " +command);		
		
		System.out.println("--------------------1. 가상주소 끝-------------------");
		
		
		System.out.println("\n------------------2. 가상주소 맵핑 시작-------------------");
		ActionForward forward = null;
		Action action = null;
		
		if (command.equals("./join.me")) {
			System.out.println(" C : /join.me 맵핑");
			System.out.println(" C : 패턴1 데이터처리 X, 페이지 이동 O");
			forward = new ActionForward();
			forward.setPath("./member/join.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/loginForm.me")) {
			System.out.println(" C : /loginForm.me 맵핑");
			System.out.println(" C : 패턴1 데이터처리 X, 페이지 이동 O");
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/join.me")) {
			System.out.println(" C : /join.me 맵핑");
			System.out.println(" C : 패턴1 데이터처리 X, 페이지 이동 O");
			forward = new ActionForward();
			forward.setPath("./member/join.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/JoinAction.me")) {
			try {
				System.out.println(" C : /joinAction.me 맵핑");
				System.out.println(" C : 패턴2 데이터처리 O, 페이지 이동 O");
				
				action = new JoinAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				MemberDAO mdao = new MemberDAO();
				mdao.closeDB();
			}
			
		} else if (command.equals("/LoginAction.me")) {
			try {
				System.out.println(" C : /loginAction.me 맵핑");
				System.out.println(" C : 패턴2 데이터처리 O, 페이지 이동 O");
				
				action = new LoginAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/LogoutAction.me")) {
			System.out.println(" C : /LogoutAction.me 맵핑");
			System.out.println(" C : 패턴2 - DB사용 O , 페이지 이동");
			
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/emailAuthAction.me")) {
			
			try {
				System.out.println(" C : /emailAuthAction.me 맵핑");
				System.out.println(" C : 패턴 1 - 데이터 처리 X , 페이지 이동");
				action = new emailAuthAction();
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (command.equals("/emailAuthSendAction.me")) {
			System.out.println(" C : emailAuthSendAction.me 맵핑");
			System.out.println(" C : 패턴 1 - 데이터 처리 X , 페이지 이동");
			try {
				HttpSession session = request.getSession();
				session.setAttribute("email", request.getParameter("newEmail"));
				action = new emailSendAction();
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/joinEmailAuthSendAction.me")) {
			
			System.out.println(" C : joinEmailAuthSendAction.me 맵핑");
			System.out.println(" C : 패턴 1 - 데이터 처리 X , 페이지 이동");
			try {
				action = new joinEmailSendAction();
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (command.equals("/myPage.me")) {
			System.out.println(" C : myPage.me 맵핑");
			System.out.println(" c : 패턴 3 - 데이터 처리 O, 페이지 출력");
			try {
				action = new getMemberInfo();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (command.equals("/checkDuplicateId.me")) {
			System.out.println(" C : /checkDuplicateIdAction.me 맵핑");
			System.out.println(" C : checkDuplicateId.js 실행");
			try {
				action = new checkDuplicateIdAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else if (command.equals("/changePhoneNum.me")) {
			System.out.println(" C : /changePhoneNum.me 맵핑");
			System.out.println(" C : 패턴 : ajax");
			
			// 전달받은 newPhoneNum 데이터 mdto로 전달 + session id 값
			String newPhoneNum = request.getParameter("newPhoneNum");
			request.setAttribute("newPhoneNum", newPhoneNum);
			
			HttpSession session = request.getSession();
			MemberDTO mdto = new MemberDTO();
			mdto = (MemberDTO) session.getAttribute("mdto");
			request.setAttribute("id", mdto.getId());
			
			changePhoneNumAction change = new changePhoneNumAction();
			
			try {
				
				int result = change.execute(request, response);
				String resultMsg = "";
				response.setContentType("text/plain;charset=utf-8");
				PrintWriter out = response.getWriter();
				if (result == 1) {
					resultMsg = "1"; // 성공
				} else if (result == 0){
					resultMsg = "0"; // 오류
				} else {
					resultMsg = "-1"; // 중복
				}
				out.write(resultMsg);
				out.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (command.equals("/updateEmailAction.me")) {
			System.out.println(" C : updateEmailAction.me 맵핑");
			System.out.println(" C : 패턴2 - 데이터처리 O, 페이지 이동");
			action = new updateEmailAction();
			try {
				action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (command.equals("/updatePassword.me")) {
			
			System.out.println(" C : updatePassword.me 맵핑");
			System.out.println(" C : 패턴1 - 데이터처리 X, 페이지 이동");
			forward = new ActionForward();
			forward.setPath("./member/updatePassword.jsp");
			forward.setRedirect(false);
			
		} else if (command.equals("/checkPasswordAction.me")) {
			System.out.println(" C : checkPasswordAction.me 맵핑");
			System.out.println(" C : Ajax");
			action = new checkPasswordAction();
			try {
				action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (command.equals("/updatePasswordAction.me")) {
			System.out.println(" C : updatePasswordAction.me 맵핑");
			System.out.println(" C : 패턴 2 - 데이터처리 O, 페이지이동 O");
			
			action = new updatePasswordAction();
			try {
				action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/orderSuccess.od")) {
			System.out.println(" C : checkMemberGrade.me 맵핑");
			
			action = new checkMemberGradeAction();
			try {
				forward= action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/findMemberId.me")) {
			System.out.println(" C : findMemberId.me 맵핑");
			
			forward = new ActionForward();
			forward.setPath("./member/findMemberId.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/findMemberIdAction.me")) {
			
			System.out.println(" C : findMemberIdAction.me 맵핑");
			
			action = new findMemberIdAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		else if (command.equals("/findMemberPw.me")) {
			System.out.println(" C : findMemberId.me 맵핑");
			
			forward = new ActionForward();
			forward.setPath("./member/findMemberPw.jsp");
			forward.setRedirect(false);
			
		} 
		
		else if (command.equals("/findMemberPwAction.me")) {
			
			System.out.println(" C : /findMemberPwAction.me 맵핑");
			
			try {
				action = new findMemberPwAction();
				action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (command.equals("/findMemberPwView.me")) {
			
			System.out.println(" C : findMemberPwView.me 맵핑");
			
			forward = new ActionForward();
			forward.setPath("./member/findPwView.jsp");
			forward.setRedirect(false);
			
		}
		
		else if (command.equals("/AuthNumberRequest.me")) {
			
			System.out.println(" C : AuthNumberRequest.me 맵핑");
			
			AuthNumberRequestAction anra = new AuthNumberRequestAction();
			
			
			try {
				
				String randomNum = anra.execute(request, response); // 난수
				
				// 난수 ajax에 다시 보내주기
				
				System.out.println(" C : 난수 = " + randomNum);
				response.setContentType("text");
				PrintWriter out = response.getWriter();
				out.write(randomNum);
				out.close();
				
			    return ;    
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (command.equals("/findIdView.me")) {
			
			System.out.println(" C : findIdView.me 맵핑");
			forward = new ActionForward();
			forward.setPath("./member/findIdView.jsp");
			forward.setRedirect(false);
			
		} else if (command.equals("/beforeAuthNumberRequest.me")) {
			
			System.out.println(" C : /beforeAuthNumberRequest.me 맵핑");
			
			action = new beforeAuthNumberRequestAction();
			
			try {
				forward = new ActionForward();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/setNewPasswordAction.me")) {
			
			System.out.println(" C : /setNewPasswordAction.me 맵핑");
			
			action = new setNewPasswordAction();
			try {
				action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		// 회원정보 목록 띄우기 컨트롤러
		else if (command.equals("/MemberListAction.me")) {
			System.out.println("C : /MemberListAction.me 맵핑");
			try {
				action = new MemberListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	
		else if(command.equals("/MemberDelete.me")) {
			System.out.println("C : /MemberDelete.me 맵핑");
			System.out.println("C : 패턴 1 - DB 사용 X , 페이지이동");
			
			forward = new ActionForward();
			forward.setPath("./member/adminDelete.jsp");
			forward.setRedirect(false);
		}
		
		else if(command.equals("/MemberDeleteAction.me")) {
			System.out.println("C : /MemberDeleteAction.me 맵핑");
			System.out.println("C : 패턴 2 DB 사용  O 페이지이동");
			
			action = new MemberDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		else if(command.equals("/MemberUpdate.me")) {
			System.out.println("C : /MemberUpdate.me 맵핑");
			System.out.println("C : 패턴 1 - DB 사용 X , 페이지이동");
			
			forward = new ActionForward();
			forward.setPath("./member/adminUpdate.jsp");
			forward.setRedirect(false);
		}
		
		else if(command.equals("/MemberUpdateAction.me")) {
			System.out.println("C : /MemberDeleteAction.me 맵핑");
			System.out.println("C : 패턴 2 DB 사용  O 페이지이동");
			
			action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		else if(command.equals("/MemberSearchAction.me")) {
			System.out.println("C : /MemberSearchAction.me 맵핑");
			System.out.println("C : 패턴2 DB 사용 페이지이동");
			
			action = new MemberSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		System.out.println("--------------------2. 가상주소 맵핑 끝-------------------");
		
		
		System.out.println("\n------------------3. 가상주소 이동 시작-------------------");
		
		if(forward != null) {
			
			if(forward.isRedirect()) {
				System.out.println("C : sendRedirect(), 경로 : " + forward.getPath());
				response.sendRedirect(forward.getPath());
				return;
			} else {
				System.out.println("C : forward, 경로 : " + forward.getPath());
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
			
			
			
		}
		
		
		System.out.println("--------------------3. 가상주소 이동 끝-------------------");
		
		
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 실행");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 실행");
		doProcess(request, response);
	}
	
}
