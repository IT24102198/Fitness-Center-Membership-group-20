<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Fitness Center Portal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #e3f2fd;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .box {
            background: #ffffff;
            padding: 40px;
            border-radius: 8px;
            text-align: center;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }
        a.button {
            display: inline-block;
            margin: 15px;
            padding: 10px 20px;
            text-decoration: none;
            background-color: #007BFF;
            color: white;
            border-radius: 5px;
        }
        a.button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="box">
    <h1>Welcome to Fitness Center</h1>
    <p>Select an option below:</p>
    <a class="button" href="paymentForm.jsp">Make a Payment</a>
    <a class="button" href="viewPayments.jsp">View Payments</a>
</div>
</body>
</html>
