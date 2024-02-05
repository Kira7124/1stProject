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
import com.project4.util.JSMoveFunction;

public class ReviewUpdateProAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String realPath = request.getRealPath("/upload");
        int maxSize = 5 * 1024 * 1024;
        String id = (String) request.getSession().getAttribute("id");
        MemberDTO mdto = new MemberDTO();

        MultipartRequest multi = new MultipartRequest(
            request,
            realPath,
            maxSize,
            "UTF-8",
            new DefaultFileRenamePolicy()
        );

        request.setCharacterEncoding("UTF-8");
        BoardDTO dto = new BoardDTO();
        mdto.setId(id);
        dto.setBoard_bno(Integer.parseInt(multi.getParameter("board_bno")));
        dto.setBoard_subject(multi.getParameter("board_subject"));
        dto.setBoard_content(multi.getParameter("board_content"));

        BoardDAO bdao = new BoardDAO();

        // 기존 이미지 가져오기
        String existingImageFileName = bdao.getImageFileName(dto.getBoard_bno());

        // 새 이미지가 업로드되었는지 확인
        String newImageFileName = multi.getFilesystemName("board_file");
        if (newImageFileName != null) {
            dto.setBoard_file(newImageFileName); // 새 이미지가 업로드되었다면 새 이미지로 설정
        } else {
            dto.setBoard_file(existingImageFileName); // 새 이미지가 업로드되지 않았을 때, 기존 이미지를 그대로 유지
        }

        int result = bdao.updateBoard(dto);

        ActionForward forward = new ActionForward();
        if (result > 0) {
            forward.setPath("./Review.bo");
            forward.setRedirect(true);
        } else {
            // 실패했을 경우 처리할 부분
            // 에러 메시지를 설정하거나 다른 페이지로 이동하도록 설정할 수 있습니다.
        }

        return forward;
    }
}