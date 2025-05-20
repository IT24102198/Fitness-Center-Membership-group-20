<%@ page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
  <title>All Payments</title>
  <style>
    body { font-family: Arial, sans-serif; background-color: #eef2f3; padding: 30px; }
    table { width: 80%; margin: auto; border-collapse: collapse; }
    th, td { padding: 10px; text-align: center; border: 1px solid #ccc; }
    th { background-color: #4CAF50; color: white; }
    tr:nth-child(even) { background-color: #f2f2f2; }
    h2 { text-align: center; }
  </style>
</head>
<body>
<h2>Payment Records</h2>
<table>
  <tr>
    <th>Name</th>
    <th>Member ID</th>
    <th>Amount</th>
    <th>Mode</th>
    <th>Date</th>
  </tr>
  <%
    String filePath = "C:/Users/user/OneDrive/Desktop/OOP Project/payments.txt";
    File file = new File(filePath);
    if (file.exists()) {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length == 5) {
  %>
  <tr>
    <td><%= parts[0] %></td>
    <td><%= parts[1] %></td>
    <td><%= parts[2] %></td>
    <td><%= parts[3] %></td>
    <td><%= parts[4] %></td>
  </tr>
  <%
      }
    }
    reader.close();
  } else {
  %>
  <tr><td colspan="5">No payment records found.</td></tr>
  <%
    }
  %>
</table>
<div style="text-align: center; margin-top: 20px;">
  <a href="index.jsp">Back to Home</a>
</div>
</body>
</html>
