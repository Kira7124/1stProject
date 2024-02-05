package com.project4.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.QualifiedNameReference;

import com.project4.util.Action;
import com.project4.util.ActionForward;


@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : BoardFrontController_doProcess()");

		/***********************1. 가상주소 계산 시작 **************************/
		String requestURI = request.getRequestURI();
		String CTXPath = request.getContextPath();
		String command = requestURI.substring(CTXPath.length());
		/***********************1. 가상주소 계산 끝 **************************/
		
		
		/***********************2. 가상주소 매핑 시작**************************/
		Action action = null;
		ActionForward forward = null;
		
		
		if(command.equals("/Announcement.bo")) {
			action = new AnnouncementListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AnnouncementWrite.bo")) {
			
			forward = new ActionForward();
			forward.setPath("./board/AnnouncementWrite.jsp");
			forward.setRedirect(false);		
			
		}else if(command.equals("/AnnouncementWriteAction.bo")) {
			action = new AnnouncementWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}else if(command.equals("/AnnouncementContent.bo")) {
			action = new AnnouncementContentAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else if(command.equals("/AnnouncementUpdate.bo")) {
			action = new AnnouncementUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {		
			e.printStackTrace();
			}
		}else if(command.equals("/QNA.bo")) {
			action = new QNAListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}
		    else if(command.equals("/AnnouncementUpdateProAction.bo")) {
				action = new AnnouncementUpdateProAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {				
					e.printStackTrace();
				}
			}else if(command.equals("/AnnouncementDelete.bo")) {
				forward = new ActionForward();
				forward.setPath("./board/AnnouncementDeleteForm.jsp");
				forward.setRedirect(false);
			}else if(command.equals("/AnnouncementDeleteAction.bo")) {
				action = new AnnouncementDeleteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(command.equals("/QNAWrite.bo")) {
				forward = new ActionForward();
				forward.setPath("./board/QNAWrite.jsp");
				forward.setRedirect(false);
			}else if(command.equals("/QNAWriteAction.bo")) {
				action = new QNAWriteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {				
					e.printStackTrace();
				}
			}else if(command.equals("/QNAContent.bo")) {
				action = new QNAContentAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}else if(command.equals("/QNADelete.bo")) {
				forward = new ActionForward();
				forward.setPath("./board/QNADeleteForm.jsp");
				forward.setRedirect(false);				
			}else if(command.equals("/QNADeleteAction.bo")) {
				action = new QNADeleteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}else if(command.equals("/QNAUpdate.bo")) {
				action = new QNAUpdateAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}else if(command.equals("/QNAUpdateProAction.bo")) {
				action = new QNAUpdateProAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}else if(command.equals("/Review.bo")) {
				action = new ReviewListAction();
				
				 try {
					forward = action.execute(request, response);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}else if(command.equals("/ReviewWrite.bo")) {
				forward = new ActionForward();
				forward.setPath("./board/ReviewWrite.jsp");
				forward.setRedirect(false);						
			}else if(command.equals("/ReviewWriteAction.bo")) {
				action = new ReviewWriteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {				
					e.printStackTrace();
				}
			}else if(command.equals("/ReviewContent.bo")) {
				 action = new ReviewContentAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}else if(command.equals("/ReviewUpdate.bo")) {
				action = new ReviewUpdateAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}else if(command.equals("/ReviewUpdateProAction.bo")) {
				action = new ReviewUpdateProAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}else if(command.equals("/ReviewDelete.bo")) {
				forward = new ActionForward();
				forward.setPath("./board/ReviewDelete.jsp");
				forward.setRedirect(false);
			}else if(command.equals("/ReviewDeleteAction.bo")) {
				action = new ReviewDeleteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}else if(command.equals("/QNAContentValidation.bo")) {
				action = new QNAContentValidationAction();
				try {
					forward =action.execute(request, response);
				} catch (Exception e) {				
					e.printStackTrace();
				}
			}else if(command.equals("/QNAContentProValidationAction.bo")) {
				action = new QNAContentProValidationAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			else if(command.equals("/QNAReWrite.bo")) {
				forward = new ActionForward();
				forward.setPath("./board/QNAReWriteForm.jsp");
				forward.setRedirect(false);			
			}
			else if(command.equals("/QNAReWriteAction.bo")) {
				action = new QNAReWriteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
			else if(command.equals("/QNAReContent.bo")) {
				action = new QNAReContentAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
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
		
	
	}//doProcess
	
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
