package com.project4.payment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.payment.db.paymentDAO;
import com.project4.payment.db.paymentDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class paymentInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		
		ActionForward forward = new ActionForward();
		
		paymentDAO dao = new paymentDAO();
		paymentDTO dto = dao.getPaymentInfo("갤럭시s4");
		request.setAttribute("dto", dto);
		System.out.println(dto);
		forward.setPath("./payment/testindex2.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
