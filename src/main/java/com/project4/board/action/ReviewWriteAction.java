package com.project4.board.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project4.board.db.BoardDAO;
import com.project4.board.db.BoardDTO;
import com.project4.board.db.BoardType;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class ReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		String realPath = request.getRealPath("/upload");
		int maxSize = 5*1024*1024;
		MemberDTO mdto = (MemberDTO) request.getSession().getAttribute("mdto");
		String id =  mdto.getId();
		MultipartRequest multi = new MultipartRequest(
				request,
				realPath,
				maxSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
				);
		String filePath = (new StringBuilder(String.valueOf(realPath))).append(File.separator).append(multi.getFilesystemName("board_file")).toString();
		
		
		request.setCharacterEncoding("UTF-8");
		BoardDTO dto = new BoardDTO();
		mdto.setId(id);
		dto.setBoard_subject(multi.getParameter("board_subject"));
		dto.setBoard_content(multi.getParameter("board_content"));		
		dto.setBoard_file(multi.getFilesystemName("board_file"));
		dto.setBoard_category(BoardType.REVIEW);
		
		BoardDAO bdao = new BoardDAO();
		bdao.insertBoard(dto,mdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./Review.bo");
		forward.setRedirect(true);
		
		return forward;
	
	
	}

}
