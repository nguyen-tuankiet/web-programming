<%--
  Created by IntelliJ IDEA.
  User: hung
  Date: 23/5/25
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Session Expired</title>
  <style>
    body {
      margin: 0;
      height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
    }

    .container {
      text-align: center;
      padding: 40px;
      background-color: white;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
      width: 800px;
    }

    h1 {
      color: #333;
      margin-bottom: 20px;
      font-size: 24px;
    }

    p {
      color: #666;
      margin-bottom: 30px;
      line-height: 1.5;
    }

    .refresh-btn {
      background-color: #007bff;
      color: white;
      border: none;
      padding: 12px 24px;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px;
      transition: background-color 0.3s;
    }

    .container img {
      width: 500px;
      height: 500px;
      object-fit: cover;
    }
  </style>
</head>
<body>
<div class="container">
  <img src="static/image/warning.jpg" class="img-fluid" alt=" image">
  <h1>Lời mời đã hết hạn</h1>
  <p>Rất tiếc, lời mời bạn nhận được đã không còn hiệu lực.</p>
  <p>Vui lòng liên hệ với đội ngũ của chúng tôi tại <a href="mailto:support@yourcompany.com">support@yourcompany.com</a> để được hỗ trợ thêm.</p>
<%--  <button class="go-back-btn" onclick="window.location.reload()">Refresh</button>--%>
</div>
</body>
</html>