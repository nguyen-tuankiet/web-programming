<%--
  Created by IntelliJ IDEA.
  User: hung
  Date: 23/5/25
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lời mời được chấp nhận</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f9f9f9;
            text-align: center;
            padding: 50px;
        }

        .container {
            background: #fff;
            padding: 40px;
            width: 800px;
            margin: 0 auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 8px;
        }

        img {
            width: 300px;
            height: 300px;
            object-fit: cover;
            margin-bottom: 20px;
        }

        h2 {
            color: #666;
        }

        p {
            color: #333;
            margin: 15px 0;
        }

        .btn {
            display: inline-block;
            padding: 12px 24px;
            background: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            margin-top: 20px;
        }

        .btn:hover {
            background: rgba(0, 123, 255, 0.85);
        }
    </style>
</head>
<body>
<div class="container">
    <img src="static/image/success.jpg" alt="Success">
    <h2>Chào mừng bạn đã tham gia!</h2>
    <p>Lời mời của bạn đã được xác nhận thành công.</p>
    <p>Hãy quay lại trang đăng nhập để bắt đầu sử dụng hệ thống.</p>
    <a href="login" class="btn">Quay về trang đăng nhập</a>
</div>
</body>
</html>
