package com.project4.product.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project4.product.db.ProductDAO;
import com.project4.product.db.ProductDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class ProductWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("\t M : ProductWriteAction_execute() 호출 ");
		
		// 한글 처리 -> 필터 설정
		// upload 가상의 폴더 생성
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath("/img");
		/* String realPath = request.getRealPath("/img"); */
		System.out.println("\t M : realPath : " + realPath);
		// 첨부 파일의 크기 설정 (5MB)
		int maxSize = 5 * 1024 * 1024;
		// 첨부 파일의 업로드 -> 객체 생성
		MultipartRequest multi = 
					new MultipartRequest(
						request,
						realPath,
						maxSize,
						"UTF-8",
						new DefaultFileRenamePolicy()					
						);
		System.out.println("\t M : 파일 업로드 성공! ");
		
		// 전달 정보 저장 -> ProductDTO 객체에 저장
		ProductDTO dto = new ProductDTO();
				
		dto.setModel(multi.getParameter("model"));
		dto.setCompany(multi.getParameter("company"));
		dto.setName(multi.getParameter("name"));
		dto.setCategory(multi.getParameter("category"));
		dto.setColor(multi.getParameter("color"));
		
		dto.setColor2(multi.getParameter("color2"));
		dto.setColor3(multi.getParameter("color3"));
		dto.setCapacity(multi.getParameter("capacity"));
		dto.setCapacity2(multi.getParameter("capacity2"));
		dto.setCapacity3(multi.getParameter("capacity3"));
		
		dto.setOption1(Integer.parseInt(multi.getParameter("option1")));
		
		String option2Str = multi.getParameter("option2");
		if (option2Str != null && !option2Str.isEmpty()) {
		    dto.setOption2(Integer.parseInt(option2Str));
		} else {
		    // option2Str이 비어 있을 경우 처리
			dto.setOption2(0);
		}
		
		String option3Str = multi.getParameter("option3");
		if (option3Str != null && !option3Str.isEmpty()) {
		    dto.setOption3(Integer.parseInt(option3Str));
		} else {
		    // option3Str이 비어 있을 경우 처리
			dto.setOption3(0);
		}
		
		String option4Str = multi.getParameter("option4");
		if (option4Str != null && !option4Str.isEmpty()) {
		    dto.setOption4(Integer.parseInt(option4Str));
		} else {
		    // option4Str이 비어 있을 경우 처리
			dto.setOption4(0);
		}
		
		String option5Str = multi.getParameter("option5");
		if (option5Str != null && !option5Str.isEmpty()) {
		    dto.setOption5(Integer.parseInt(option5Str));
		} else {
		    // option5Str이 비어 있을 경우 처리
			dto.setOption5(0);
		}
		
		String option6Str = multi.getParameter("option6");
		if (option6Str != null && !option6Str.isEmpty()) {
		    dto.setOption6(Integer.parseInt(option6Str));
		} else {
		    // option6Str이 비어 있을 경우 처리
			dto.setOption6(0);
		}
		
		dto.setImage(multi.getFilesystemName("image"));
		dto.setPrice(Integer.parseInt(multi.getParameter("price")));
		dto.setTitle(multi.getParameter("title"));
		
		System.out.println("\t M : " + dto);
		
		// ProductDAO 객체 생성 - 상품 글쓰기 메서드
		ProductDAO pdao = new ProductDAO();
		pdao.insertProduct(dto);
		
		// 페이지 이동 준비
		ActionForward forward = new ActionForward();
		
		if(multi.getParameter("title").equals("구매")) {
			forward.setPath("./orderList.pd");
		} else if (multi.getParameter("title").equals("대여")) {
			forward.setPath("./lentalList.pd");
		} else if (multi.getParameter("title").equals("판매")) {
			forward.setPath("./saleList.pd");
		}
		
		forward.setRedirect(true);
		
		return forward;
	}

}