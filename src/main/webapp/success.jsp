<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Success</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        .message {
            color: green;
            font-size: 24px;
            margin-bottom: 20px;
        }
        .home-link {
            font-size: 18px;
        }
    </style>
</head>
<body>
<div class="message">
    <%= session.getAttribute("message") %>
</div>
<a href="index.jsp" class="home-link">Return to Home</a>
</body>
</html>