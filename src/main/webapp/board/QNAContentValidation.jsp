<%@page import="com.project4.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>문의글 비밀번호 검증</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .password-form {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 300px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-group input[type="password"] {
            width: calc(100% - 12px);
            padding: 6px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }

        .form-group input[type="submit"] {
            background-color: #3182ce;
            color: #fff;
            border: none;
            padding: 8px 20px;
            border-radius: 3px;
            cursor: pointer;
        }

        .form-group input[type="submit"]:hover {
            background-color: #265999;
        }
    </style>
</head>
<body>
    <div class="password-form">
        <h2>문의글 비밀번호</h2>
        <form action="./QNAContentProValidationAction.bo?board_bno=${dto.board_bno }&pageNum=${pageNum}&search=${param.search}&board_file=${dto.board_file }" method="post">
            <div class="form-group">
                <label for="password">비밀번호를 입력하세요:</label>
                <input type="password" id="password" name="board_pass" required>
            </div>
            <div class="form-group">
                <input type="submit" value="제출">
            </div>
        </form>
    </div>
     
</body>
</html>