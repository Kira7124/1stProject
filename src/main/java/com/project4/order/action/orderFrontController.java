package com.project4.order.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.member.action.checkMemberGradeAction;
import com.project4.util.Action;
import com.project4.util.ActionForward;


@WebServlet("*.od")
public class orderFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		/***********************1. 가상주소 계산 시작 **************************/
		System.out.println("\n\n C : 1. 가상주소 계산 시작---------------");
		String requestURI = request.getRequestURI();
		System.out.println("\t requestURI : "+requestURI );
		String CTXPath = request.getContextPath();
		System.out.println("\t CTXPath : "+CTXPath );
		String command = requestURI.substring(CTXPath.length());
		System.out.println("\t command : "+command );
		System.out.println(" C : 1. 가상주소 계산 끝------------------");
		/***********************1. 가상주소 계산 끝 **************************/
		
		
		/***********************2. 가상주소 매핑 시작**************************/
		System.out.println("\n\n C : 2. 가상주소 매핑 시작------------------");
		Action action = null;
		ActionForward forward = null;
		
	    if(command.equals("/purchaseOrder.od")) {
			forward = new ActionForward();
			forward.setPath("./order/purchaseOrder.jsp");
			forward.setRedirect(false);		
		}else if(command.equals("/rentalOrder.od")) {
			forward = new ActionForward();
			forward.setPath("./order/rentalOrder.jsp");
			forward.setRedirect(false);	
		}
		else if(command.equals("/orderSuccessAction.od")) {
			action = new orderSuccessAction();
			
			try {
				forward =action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        
		}else if(command.equals("/rentalSuccessAction.od")) {
			action = new rentalSuccessAction();
			try {
				forward =action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
		else if (command.equals("/orderSuccess.od")) {
			System.out.println(" C : orderSuccess.od 맵핑");
			
			action = new checkMemberGradeAction();
			try {
				forward= action.execute(request, response);
				forward = new ActionForward();
		        forward.setPath("./order/orderSuccess.jsp");
		        forward.setRedirect(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/PurchaseHistory.od")) {
			System.out.println("\t C : /PurchaseHistory.od 호출 ");
			System.out.println("\t C : 패턴 3 - DB사용 O, 페이지 출력");
			
			// PurchaseHistoryAction() 객체 생성
			action = new PurchaseHistoryAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/RentalHistory.od")) {
			System.out.println("\t C : /RentalHistory.od 호출 ");
			System.out.println("\t C : 패턴 3 - DB사용 O, 페이지 출력");
			
			// RentalHistoryAction() 객체 생성
			action = new RentalHistoryAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (command.equals("/OrderDeleteAction.od")) {
			System.out.println("\t C : /OrderDeleteAction.od 호출 ");
			System.out.println("\t C : 패턴 2 - DB사용 O, 페이지 이동");
			
			// PurchaseDeleteAction() 객체 생성
			action = new OrderDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(" C : 2. 가상주소 매핑 끝------------------");
		/***********************2. 가상주소 매핑 끝**************************/
		
		/***********************3. 가상주소 이동 시작**************************/
		System.out.println("\n\n C : 3. 가상주소 이동 시작------------------");
		if(forward != null) {
			if(forward.isRedirect()) { // true
				System.out.println("\t C : 이동주소 : "+forward.getPath());
				System.out.println("\t C : 이동방법 : sendRedirect() 방식 ");
				response.sendRedirect(forward.getPath());
			}else { // false
				System.out.println("\t C : 이동주소 : "+forward.getPath());
				System.out.println("\t C : 이동방법 : forward() 방식 ");
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}			
		}		
		System.out.println(" C : 3. 가상주소 이동 끝------------------");
		/***********************3. 가상주소 이동 끝**************************/
		
	
	}//doProcess
	
	
	
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		doProcess(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		doProcess(request, response);
	}

}
