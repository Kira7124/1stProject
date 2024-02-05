package com.project4.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.board.db.BoardDAO;
import com.project4.board.db.BoardDTO;
import com.project4.board.db.BoardType;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class AnnouncementUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDTO bdto = new BoardDTO();
		
		request.setCharacterEncoding("UTF-8");
		
		bdto.setBoard_bno(Integer.parseInt(request.getParameter("board_bno")));
		bdto.setBoard_subject(request.getParameter("board_subject"));
		bdto.setBoard_content(request.getParameter("board_content"));
		
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO bdao = new BoardDAO();
		int result = bdao.updateBoard(bdto);
		
		if(result == 0) {
			//JS사용 페이지 이동
			JSMoveFunction.alertBack(response, " 비밀번호 오류! ");
			return null;
		}
		
		if(result == -1) {
			JSMoveFunction.alertBack(response, " 게시판 글 없음! ");
			 return null;
		}
		
		//result == 1
		JSMoveFunction.alertLocation(response, "수정 성공!", "./Announcement.bo?pageNum="+pageNum);
		
		return null;
		
		
	}

}
