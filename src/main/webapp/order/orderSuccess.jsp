<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>결제 완료</title>
    <style>
        body {
            background-color: #3182ce;
            color: white;
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }

        h1 {
            font-size: 36px;
            margin-bottom: 20px;
        }

        p {
            font-size: 18px;
            margin-bottom: 40px;
        }

        .main-button {
            background-color: #fff;
            color: #3182ce;
            border: none;
            padding: 10px 20px;
            text-decoration: none;
            font-size: 16px;
            cursor: pointer;
        }

        .main-button:hover {
            background-color: #eee;
        }
    </style>
</head>
<body>
    <h1>결제가 성공적으로 완료되었습니다!</h1>
    <p>고객님의 주문이 성공적으로 처리되었습니다. 감사합니다!</p>
    <a href="/Project4/main.ma" class="main-button">메인 화면으로 돌아가기</a>
</body>
</html>