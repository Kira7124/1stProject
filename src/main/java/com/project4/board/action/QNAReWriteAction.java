package com.project4.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.board.db.BoardDAO;
import com.project4.board.db.BoardDTO;
import com.project4.board.db.BoardType;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class QNAReWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
				
		request.setCharacterEncoding("UTF-8");
				BoardDTO dto = new BoardDTO();
				MemberDTO mdto = (MemberDTO) request.getSession().getAttribute("mdto");
				String id =  mdto.getId();
				dto.setBoard_bno(Integer.parseInt(request.getParameter("board_bno")));
				dto.setBoard_subject(request.getParameter("board_subject"));
				mdto.setId(id);
				dto.setBoard_content(request.getParameter("board_content"));
				dto.setBoard_pass(request.getParameter("board_pass"));
				dto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
				dto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
				dto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
				dto.setBoard_category(BoardType.QNA답변);
				String pageNum = request.getParameter("pageNum");
				
				// BoardDAO객체 생성 -> 답글쓰기 메서드 
				BoardDAO bdao = new BoardDAO();
				bdao.reInsertBoard(dto, mdto);

				// 페이지 이동준비(list)
				ActionForward forward = new ActionForward();
				forward.setPath("./QNA.bo?pageNum="+pageNum);
				forward.setRedirect(true);
				
				return forward;
	}

}
