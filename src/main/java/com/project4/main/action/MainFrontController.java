package com.project4.main.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.util.Action;
import com.project4.util.ActionForward;

@WebServlet("*.ma")
public class MainFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/***********************1. 가상주소 계산 시작 **************************/
		String requestURI = request.getRequestURI();
		String CTXPath = request.getContextPath();
		String command = requestURI.substring(CTXPath.length());
		/***********************1. 가상주소 계산 끝 **************************/
		
		/***********************2. 가상주소 매핑 시작**************************/
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/main.ma")) {
			forward = new ActionForward();
			forward.setPath("./main.jsp");
			forward.setRedirect(false);
		}
		
		/***********************2. 가상주소 매핑 끝**************************/
		
		
		
		
		/***********************3. 가상주소 이동 시작**************************/
		if(forward != null) {
			if(forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath());
			}else { // false
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}			
		}		

		/***********************3. 가상주소 이동 끝**************************/
		
		
		
	}//doProcess 끝
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
		
		
	}
	
}
