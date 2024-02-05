package com.project4.center.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.util.Action;
import com.project4.util.ActionForward;

@WebServlet("*.as")
public class CenterFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/************************ 1. 가상주소 계산 시작 ************************/
		String requestURI = request.getRequestURI();
		String CTXPath = request.getContextPath();
		String command = requestURI.substring(CTXPath.length());

		/************************ 1. 가상주소 계산 끝 ************************/

		/************************ 2. 가상주소 매핑 시작 ************************/
		Action action = null;
		ActionForward forward = null;

		if(command.equals("/center.as")) {
			// CenterWriteAction() 객체 생성
			action = new CenterWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/CenterWriteActionPro.as")) {
			// CenterWriteActionPro() 객체 생성
			action = new CenterWriteActionPro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/CenterHistory.as")) {
			// CenterHistoryAction() 객체 성생
			action = new CenterHistoryAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/CenterEditAction.as")) {
			
			// CenterEditAction() 객체 생성
			action = new CenterEditAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/CenterEditActionPro.as")) {
			
			// CenterEditActionPro() 객체 생성
			action = new CenterEditActionPro();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/CenterDeleteAction.as")) {
			
			// CenterDeleteAction() 객체 생성
			action = new CenterDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/************************ 2. 가상주소 매핑 끝 ************************/

		/************************ 3. 가상주소 이동 시작 ************************/

		if (forward != null) {
			if (forward.isRedirect()) { // true

				response.sendRedirect(forward.getPath());
			} else { // false

				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}

		/************************ 3. 가상주소 이동 끝 *************************/

	}// doProcess 끝

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}