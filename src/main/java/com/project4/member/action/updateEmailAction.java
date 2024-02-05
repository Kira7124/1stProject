package com.project4.member.action;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project4.member.db.MemberDAO;
import com.project4.member.db.MemberDTO;
import com.project4.util.Action;
import com.project4.util.ActionForward;
import com.project4.util.Gmail;
import com.project4.util.SHA256;

public class updateEmailAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : updateEmailAction.execute() 실행");
		request.setCharacterEncoding("utf-8");
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// 세션 id 값 저장
				MemberDTO mdto = new MemberDTO();
				HttpSession session = request.getSession();
				mdto = (MemberDTO) session.getAttribute("mdto");
				MemberDAO mdao = new MemberDAO();	
				boolean isDuplicateEmail= mdao.isDuplicateEmail(mdto);
				System.out.println(mdto);
				// 입력 값 저장
				mdto.setEmail(request.getParameter("newEmail"));
				session.setAttribute("email", mdto.getEmail());
				System.out.println(" M : email : " + mdto.getEmail());
				mdto.setEmailHash(SHA256.getSHA256(mdto.getEmail()));
				System.out.println(" M : 이메일 정보 저장 완료");
				System.out.println(mdto);
				
				
				//Ajax에 응답==============================================
				
				if(!isDuplicateEmail) { // 이메일 변경 성공
					try {
						System.out.println(" M : 이메일 변경 완료");
						response.setContentType("text/plain");
						response.setCharacterEncoding("utf-8");
						response.getWriter().write("1");
						mdao.updateEmail(mdto);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if (isDuplicateEmail) { // 중복 이메일 존재
					try {
						System.out.println(" M : 중복 이메일 존재");
						response.setContentType("text/plain");
						response.setCharacterEncoding("utf-8");
						response.getWriter().write("-1");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
					
				} else { // 오류 발생
					try {
						System.out.println(" M : 이메일 변경 중 오류 발생");
						response.setContentType("text/plain");
						response.setCharacterEncoding("utf-8");
						response.getWriter().write("0");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
				
				//Ajax에 응답==============================================
				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpSession session = request.getSession();
				// 이메일 전송=====================================================
				
				// 홈페이지 주소
				String host = "http://localhost:8088/Project4/";
				// 사용자에게 이메일을 보내는 구글계정
				String from = "klop12111@gmail.com";
				// 사용자 이메일
				String to = (String)session.getAttribute("email");
				
				// 안내 문구
				String subject = "7강 1차 프로젝트,이메일 인증 메일입니다.";
				String content = "다음 링크에 접속해 이메일 인증을 완료하세요."
						+ "<a href='" + host + "emailAuthAction.me?code=" + new SHA256().getSHA256(to) + "'>이메일 인증하기</a>";		
				
				// 이메일 전송 : SMTP 이용을 위함
				Properties p = new Properties();
				p.put("mail.smtp.user", from);
				p.put("mail.smtp.host", "smtp.googlemail.com"); // google SMTP 주소
				p.put("mail.smtp.port", "465");
				p.put("mail.smtp.starttls.enable", "true");
				p.put("mail.smtp.auth", "true");
				p.put("mail.smtp.debug", "true");
				p.put("mail.smtp.socketFactory.port", "465");
				p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				p.put("mail.smtp.socketFactory.fallback", "false");
				p.put("mail.smtp.ssl.protocols", "TLSv1.2"); 
				p.put("mail.smtp.ssl.enable", "true");  
				
				try{
					Authenticator auth = new Gmail();
					Session ses = Session.getInstance(p, auth);
					ses.setDebug(true);
					MimeMessage msg = new MimeMessage(ses); 
					msg.setSubject(subject);		// 메일 제목
					Address fromAddr = new InternetAddress(from); 	// 보내는 사람 정보
					msg.setFrom(fromAddr);
					Address toAddr = new InternetAddress(to);		// 받는 사람 정보
					msg.addRecipient(Message.RecipientType.TO, toAddr);
					msg.setContent(content, "text/html;charset=UTF-8");
					Transport.send(msg); // 메세지 전송
					
					
					
					
				}catch(Exception e){
					e.printStackTrace();
					
				}
				
				// 이메일 전송=====================================================
				
			}
		});
		
		t1.start();
		t1.join();
		t2.start();
		
		
		
		
		return null;
	}

}
