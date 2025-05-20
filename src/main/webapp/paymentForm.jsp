<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Make a Payment</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f8ff; padding: 30px; }
        form {
            width: 400px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        input, select {
            width: 100%;
            margin: 10px 0;
            padding: 10px;
        }
        input[type="submit"] {
            background-color: #28a745;
            color: white;
            border: none;
        }
    </style>
</head>
<body>
<form action="PaymentServlet" method="post">
    <h2>Make a Payment</h2>
    Name: <input type="text" name="name" required><br>
    Membership ID: <input type="text" name="memberId" required><br>
    Amount: <input type="number" name="amount" step="0.01" required><br>
    Payment Mode:
    <select name="mode" required>
        <option value="Cash">Cash</option>
        <option value="Card">Card</option>
        <option value="UPI">UPI</option>
    </select><br>
    Date: <input type="date" name="date" required><br>
    <input type="submit" value="Pay">
</form>
</body>
</html>
