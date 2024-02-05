package com.project4.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.board.db.BoardDAO;
import com.project4.board.db.BoardDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class ReviewContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int board_bno = Integer.parseInt(request.getParameter("board_bno"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO bdao = new BoardDAO();
		
		bdao.updateReadcount(board_bno);
		
		BoardDTO dto = bdao.getBoard(board_bno);
		
		request.setAttribute("dto", dto);
		
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/ReviewContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
