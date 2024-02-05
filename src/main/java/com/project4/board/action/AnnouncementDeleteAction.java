package com.project4.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.board.db.BoardDAO;
import com.project4.board.db.BoardDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class AnnouncementDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardDTO bdto = new BoardDTO();
		bdto.setBoard_bno(Integer.parseInt(request.getParameter("board_bno")));
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO bdao = new BoardDAO();
		bdao.deleteBoard(bdto);
		
		request.setAttribute("bdao", bdao);
		

		ActionForward forward = new ActionForward();
		forward.setPath("./Announcement.bo?pageNum="+pageNum);
		forward.setRedirect(true);
		return forward;
	}

}
