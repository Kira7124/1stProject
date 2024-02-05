package com.project4.payment.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.product.action.SaleListAction;
import com.project4.util.Action;
import com.project4.util.ActionForward;

@WebServlet("*.py")
public class paymentFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" P : paymentFrontController_doProcess()");
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
		
		if(command.equals("/paymentInfoAction.py")) {
			
			// SaleListAction() 객체 생성
			action = new paymentInfoAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
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
		
	}// doProcess 끝
	
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n C : paymentFrontController_doGET()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n C : paymentFrontController_doPOST()");
		doProcess(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
