package com.project4.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project4.board.db.BoardDAO;
import com.project4.board.db.BoardDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;

public class QNAContentProValidationAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();

        int board_bno = Integer.parseInt(request.getParameter("board_bno")); // 문의글 번호
        String enteredPassword = request.getParameter("board_pass");
        String pageNum = request.getParameter("pageNum"); // 페이지 번호

        BoardDAO bdao = new BoardDAO();
        BoardDTO bdto = bdao.getBoard(board_bno); // 해당 문의글 정보 가져오기

        String board_pass = bdto.getBoard_pass(); // 저장된 비밀번호
        int re_lev = bdto.getRe_lev();
        int re_ref = bdto.getRe_ref();
        int re_seq = bdto.getRe_seq();
        
       

        // 입력된 비밀번호와 저장된 비밀번호가 일치하는지 확인
        if (enteredPassword.equals(board_pass)) {
            if (re_lev == 0) {
                // 비밀번호가 일치하고 레벨이 0이면 QNAContent.bo로 이동
                forward.setPath("./QNAContent.bo?board_bno=" + board_bno + "&pageNum=" + pageNum);
            } else {
                // 비밀번호가 일치하고 레벨이 1 이상이면 QNAReContent.bo로 이동
                forward.setPath("./QNAReContent.bo?board_bno=" + board_bno + "&pageNum=" + pageNum);
            }
            forward.setRedirect(true); // Redirect 사용
        } else {
            // 비밀번호가 일치하지 않으면 다시 비밀번호 입력 페이지로 이동
            String alertMessage = "비밀번호가 틀렸습니다!";
            String encodedMessage = new String(alertMessage.getBytes("EUC-KR"), "ISO-8859-1");
            String alertScript = "<script>alert('" + encodedMessage + "'); window.location.href='./QNA.bo?board_bno=" + board_bno + "&pageNum=" + pageNum + "';</script>";
            response.getWriter().println(alertScript);
        }
        return forward;
    }
}