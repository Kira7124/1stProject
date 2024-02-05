<%@page import="com.project4.util.SHA256"%>
<%@page import="com.project4.util.Gmail"%>
<%@page import="com.project4.util.JSMoveFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="javax.mail.Authenticator"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>이메일 인증</title>
</head>
<body>
	<%
	String id = null;
	HttpSession session1 = request.getSession();
	
	// 홈페이지 주소
	String host = "http://localhost:8088/Project4/";
	// 사용자에게 이메일을 보내는 구글계정
	String from = "klop12111@gmail.com";
	// 사용자 이메일
	String to = (String)session1.getAttribute("email");
	
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
	    
	    JSMoveFunction.alert(response, "이메일 인증 완료 이후 로그인이 가능합니다.\n 메인페이지로 이동합니다.");
	    response.sendRedirect(request.getContextPath()+"/main.ma");
	    
	    
	}catch(Exception e){
		e.printStackTrace();
		JSMoveFunction.alertBack(response, "메일 전송 중 오류가 발생했습니다.");
	}
	
	%>
</body>
</html>