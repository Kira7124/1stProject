package com.project4.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.project4.board.db.BoardDAO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class AnnouncementListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		String search = request.getParameter("search");
		
		BoardDAO bdao = new BoardDAO();
		
		int count = 0;

		if(search == null) {
			 count = bdao.getBoardCount();
		}else {
			count = bdao.getBoardCount(search);
		}		
		/********************* 페이징처리 1 *******************/
		// 한 페이지에 출력할 글의 개수 설정
		int pageSize = 20;

		// 현 페이지가 몇페이지 인지확인
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		// 시작행 번호 계산하기
		// 1 11 21 31 41 .....
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;

		// 끝행 번호 계산
		// 10 20 30 40 50 .....
		int endRow = currentPage * pageSize;

		/********************* 페이징처리 1 *******************/

		// DAO - 글정보 모두(list)를 가져오는 메서드 호출
		ArrayList Announcement = null;
		ArrayList iAnnouncement = null;
		if (count > 0 && search ==null) {
			Announcement = bdao.getAnnouncementBoardList(startRow, pageSize);
			iAnnouncement = bdao.getiAnnouncementBoardList(startRow, pageSize);
		}else if(count>0 && search !=null) {
			Announcement = bdao.getAnnouncementBoardList(startRow, pageSize, search);
			iAnnouncement = bdao.getiAnnouncementBoardList(startRow, pageSize);
		}else { // 검색 해당되는 데이터 가 없을때
			
			Announcement = bdao.getAnnouncementBoardList(startRow, pageSize, search);
			iAnnouncement = bdao.getiAnnouncementBoardList(startRow, pageSize);
		}
		
		
		

		// 리스트를 출력 => 연결된 뷰페이지에서 출력하도록 정보 전달
		request.setAttribute("Announcement", Announcement);
		request.setAttribute("iAnnouncement", iAnnouncement);

		/******************* 페이징처리 2 *********************/
		// 페이지 블럭(1,2,3,.....,10) 생성

		// 전체 페이지수
		// 글 15 / 페이지당 10 => 2개
		// 글 78 / 페이지당 10 => 8개
		int pageCount = (count + pageSize - 1) / pageSize; 
		// 한 화면에 보여줄 페이지 블럭개수
		int pageBlock = (pageCount + pageSize - 1) / pageSize;

		// 페이지 블럭의 시작번호 계산
		// 1페이지 => 1 , 11페이지 => 11
		// 5페이지 => 1 , 25페이지 => 21
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

		// 페이지 블럭의 마지막번호 계산
		// 1페이지 => 10, 13페이지 => 20
		int endPage = startPage + pageBlock - 1;
		// 페이지의 글이 없는경우
		if (endPage > pageCount) {
			endPage = pageCount;
		}

		/******************* 페이징처리 2 *********************/

		// 페이징 처리에 필요한 정보 모두를 request영역에 저장해서 전달
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("count", count);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		// 페이지 이동준비 
		ActionForward forward = new ActionForward();
		forward.setPath("./board/Announcement.jsp");
		forward.setRedirect(false);
		
		

		return forward;
	}

}