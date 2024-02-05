package com.project4.product.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.util.Action;
import com.project4.util.ActionForward;

@WebServlet("*.pd")
public class ProductFrontController extends HttpServlet{

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
		
		if(command.equals("/orderDetail.pd")) {	
			// OrderDetailAction() 객체 생성
			action = new OrderDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/productSearchList.pd")) {
			
			// ProductSearchAction() 생성
			action = new ProductSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProductSearchAction.pd")) {
			
			// SaleListAction() 객체 생성
			action = new ProductSearchAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/saleList.pd")) {
			
			// SaleListAction() 객체 생성
			action = new SaleListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/SaleListAction.pd")) {
			
			// SaleListAction() 객체 생성
			action = new SaleListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/saleDetail.pd")) {
			// SaleDetailAction() 객체 생성
			action = new SaleDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/saleVisitForm.pd")) {				
			
			// SaleWriteAction() 객체 생성
			action = new SaleVisitAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/saleDeliveryForm.pd")) {				
			
			// SaleWriteAction() 객체 생성
			action = new SaleDeliveryAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/SaleVisitActionPro.pd")) {
			
			// SaleVisitActionPro() 객체 생성
			action = new SaleVisitActionPro();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/SaleDeliveryActionPro.pd")) {
			
			// SaleDeliveryActionPro() 객체 생성
			action = new SaleDeliveryActionPro();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/orderList.pd")) {
			
			// OrderListAction() 객체생성
			action = new OrderListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/OrderListAction.pd")) {
			
			action = new OrderListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/lentalList.pd")) {
			
			// LentalListAction() 객체생성
			action = new LentalListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/LentalListAction.pd")) {
			
			// LentalListAction() 객체생성
			action = new LentalListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/lentalDetail.pd")) {
			
			// LentalListAction() 객체생성
			action = new LentalDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/CartInsertAction.pd")) {
			// CartInsertAction() 객체 생성
			action = new CartInsertAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/Cart.pd")) {
			
			forward = new ActionForward();
			forward.setPath("./product/cart.jsp");
			forward.setRedirect(false);
		}
		else if (command.equals("/CartHistory.pd")) {
			  
			  // SaleHistoryAction() 객체 생성
			  action = new CartHistoryAction();
			  try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // 상품 삭제 가상 주소 매핑
	    else if(command.equals("/CartHistoryDeleteAction.pd")) {
			
			// ProductDeleteAction() 객체 생성
			action = new CartHistoryDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		  } // 상품 삭제 가상 주소 매핑
		else if(command.equals("/ProductWrite.pd")) { // 상품 등록 가상 주소 매핑
			
			forward = new ActionForward();
			forward.setPath("./product/productWriteForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/ProductWriteAction.pd")) {
				
				// ProductWriteAction() 객체 생성
				action = new ProductWriteAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		 } // 상품 등록 가상 주소 매핑
		 else if(command.equals("/ProductUpdate.pd")) { // 상품 수정 가상 주소 매핑
			
			// ProductUpdateAction() 객체 생성
			action = new ProductUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		  }
		  else if(command.equals("/ProductUpdateProAction.pd")) {
			
			// ProductUpdateProAction() 객체 생성
			action = new ProductUpdateProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		  }
		  else if(command.equals("/ProductDeleteAction.pd")) {
			
			// ProductDeleteAction() 객체 생성
			action = new ProductDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		  } // 상품 삭제 가상 주소 매핑
		  else if(command.equals("/CartDeleteAction.pd")) {
				
				// ProductDeleteAction() 객체 생성
				action = new CartDeleteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			  } // 상품 삭제 가상 주소 매핑
		  else if (command.equals("/SaleHistory.pd")) {
			  
			  // SaleHistoryAction() 객체 생성
			  action = new SaleHistoryAction();
			  try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (command.equals("/SaleDeleteAction.pd")) {
		
			// SaleDeleteAction() 객체 생성
			action = new SaleDeleteAction();
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
		
	}// doProcess 끝
	
	
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
