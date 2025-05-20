<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = request.getAttribute("name") != null ? request.getAttribute("name").toString() : "User";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Payment Confirmation</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f0f0; padding: 30px; }
        .container { background: white; padding: 20px; border-radius: 8px; width: 400px; margin: auto; text-align: center; }
    </style>
</head>
<body>
<div class="container">
    <h2>Payment Successful!</h2>
    <p>Thank you, <b><%= name %></b>. Your payment has been recorded.</p>
    <br>
    <a href="viewPayments.jsp">View All Payments</a> |
    <a href="index.jsp">Home</a>
</div>
</body>
</html>
