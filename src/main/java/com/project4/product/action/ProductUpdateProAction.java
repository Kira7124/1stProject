package com.project4.product.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project4.product.db.ProductDAO;
import com.project4.product.db.ProductDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class ProductUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("\t M : ProductUpdateProAction_execute() 호출 ");
		
		// 한글 처리 -> 필터 설정
		// upload 가상의 폴더 생성
		String realPath = request.getRealPath("/img");
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
		System.out.println("\t M : 파일 수정 업로드 성공! ");
		
		File file = new File(realPath + "/" + multi.getFilesystemName("file"));
		file.delete();
		
		// 전달 정보 저장 -> ProductDTO 객체에 저장
		ProductDTO pdto = new ProductDTO();
		pdto.setProduct_id(Integer.parseInt(multi.getParameter("product_id")));		
		pdto.setModel(multi.getParameter("model"));
		pdto.setCompany(multi.getParameter("company"));
		pdto.setName(multi.getParameter("name"));
		pdto.setCategory(multi.getParameter("category"));
		
		pdto.setColor(multi.getParameter("color"));
		pdto.setColor2(multi.getParameter("color2"));
		pdto.setColor3(multi.getParameter("color3"));
		pdto.setCapacity(multi.getParameter("capacity"));
		pdto.setCapacity2(multi.getParameter("capacity2"));

		pdto.setCapacity3(multi.getParameter("capacity3"));
		pdto.setOption1(Integer.parseInt(multi.getParameter("option1")));
		pdto.setOption2(Integer.parseInt(multi.getParameter("option2")));
		pdto.setOption3(Integer.parseInt(multi.getParameter("option3")));
		pdto.setOption4(Integer.parseInt(multi.getParameter("option4")));
		
		pdto.setOption5(Integer.parseInt(multi.getParameter("option5")));
		pdto.setOption6(Integer.parseInt(multi.getParameter("option6")));
		pdto.setImage(multi.getFilesystemName("image"));
		pdto.setPrice(Integer.parseInt(multi.getParameter("price")));
		pdto.setTitle(multi.getParameter("title"));
		
		System.out.println("\t M : " + pdto);
		
		
		// ProductDAO 객체 생성 - 상품 수정하기 메서드
		ProductDAO pdao = new ProductDAO();
		pdao.updateProduct(pdto);
		
		// JS 사용 페이지 이동
		if(multi.getParameter("title").equals("구매")) {
			JSMoveFunction.alertLocation(response, "수정 성공!", "./orderList.pd");
		} else if (multi.getParameter("title").equals("대여")) {
			JSMoveFunction.alertLocation(response, "수정 성공!", "./lentalList.pd");
		} else if (multi.getParameter("title").equals("판매")) {
			JSMoveFunction.alertLocation(response, "수정 성공!", "./saleList.pd");
		}
		
		return null;
	}

}
