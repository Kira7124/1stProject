package com.project4.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.board.db.BoardDAO;
import com.project4.board.db.BoardDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.JSMoveFunction;

public class ReviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardDTO bdto = new BoardDTO();
		bdto.setBoard_bno(Integer.parseInt(request.getParameter("board_bno")));
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO bdao = new BoardDAO();
		bdao.deleteBoard(bdto);
		
		
		request.setAttribute("bdao", bdao);
		/*
		 * if(result == 0) { JSMoveFunction.alertBack(response, "비밀번호 오류!"); return
		 * null; }
		 * 
		 * if(result == -1) { JSMoveFunction.alertBack(response, "게시판 글없음!"); return
		 * null; }
		 * 
		 * JSMoveFunction.alertLocation(response, "삭제 완료!",
		 * "./Review.bo?pageNum="+pageNum);
		 */
		
		ActionForward forward = new ActionForward();
		forward.setPath("./Review.bo?pageNum="+pageNum);
		forward.setRedirect(true);
		return forward;
	}

}
