package com.project4.order.action;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;

import com.project4.member.db.MemberDTO;
import com.project4.order.db.OrderDAO;
import com.project4.order.db.OrderDTO;
import com.project4.order.db.OrderType;
import com.project4.product.db.ProductDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class orderSuccessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		OrderDTO odto = new OrderDTO();
		ProductDTO pdto = new ProductDTO();
		MemberDTO mdto = (MemberDTO) request.getSession().getAttribute("mdto");
		String id =  mdto.getId();
		
	
		
		mdto.setId(id); // 유저 id
		odto.setPhone_num(request.getParameter("phone_num"));
		odto.setZip_code(request.getParameter("zip_code"));
		odto.setAddress(request.getParameter("address"));
		odto.setAddress_detail(request.getParameter("address_detail"));
		odto.setPrice(Integer.parseInt(request.getParameter("price")));
		odto.setStatus1(request.getParameter("status1")); // 상품옵션1
		odto.setStatus2(request.getParameter("status2")); // 상품옵션2
		odto.setStatus3(request.getParameter("status3")); // 상품옵션3
		odto.setImg(request.getParameter("img"));
		odto.setOrder_email(request.getParameter("email"));		
		odto.setOrder_category(OrderType.PURCHASE);
		odto.setName(request.getParameter("name")); // 상품이름
		
		String encodedStatus1 = URLEncoder.encode(odto.getStatus1(), "UTF-8");
		String encodedStatus2 = URLEncoder.encode(odto.getStatus2(), "UTF-8");
		String encodedStatus3 = URLEncoder.encode(odto.getStatus3(), "UTF-8");
		String encodedId = URLEncoder.encode(mdto.getId(), "UTF-8");
		String encodedPhoneNum = URLEncoder.encode(odto.getPhone_num(), "UTF-8");
		String encodedZipCode = URLEncoder.encode(String.valueOf(odto.getZip_code()), "UTF-8");
		String encodedAddress = URLEncoder.encode(String.valueOf(odto.getAddress()), "UTF-8");
		String encodedAddressDetail = URLEncoder.encode(String.valueOf(odto.getAddress_detail()), "UTF-8");
		String encodedName = URLEncoder.encode(String.valueOf(odto.getName()), "UTF-8");
		String encodedEmail = URLEncoder.encode(String.valueOf(odto.getOrder_email()), "UTF-8");

		String encodedImgName = URLEncoder.encode(odto.getImg(),"UTF-8");
		
		response.setContentType("text/html;charset=utf-8");
		
		OrderDAO odao = new OrderDAO();
		odao.insertOrder(odto,mdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./orderSuccess.od" + 
				"?id=" + encodedId +
			    "&phone_num=" + encodedPhoneNum +
        "&zip_code=" + encodedZipCode +
        "&address=" + encodedAddress +
        "&address_detail=" + encodedAddressDetail +
			    "&price=" + String.valueOf(odto.getPrice()) +
			    "&status1=" + encodedStatus1 +
			    "&status2=" + encodedStatus2 +
			    "&status3=" + encodedStatus3 +
			    "&name=" + encodedName+
			    "&email="+encodedEmail+
			    "imageURL="+encodedImgName
			);
		forward.setRedirect(true);
		
		return forward;
	
	
	}

}
