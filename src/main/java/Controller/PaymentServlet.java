package controller;

import models.Payment;
import utils.FileUtil;

//Imports the Packages
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

//HTTP request to the URL
@WebServlet("/PaymentServlet")
// Allowing it to handle HTTP requests  I.
public class PaymentServlet extends HttpServlet {
    // Handles HTTP POST requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String memberId = request.getParameter("memberId");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String mode = request.getParameter("mode");
        String date = request.getParameter("date");

        // Basic validation
        if (amount <= 0 || name == null || name.isEmpty()) {
            response.getWriter().println("Invalid input!");
            return;
        }

        // Create Payment object using setters A
        Payment payment = new Payment();
        payment.setName(name);
        payment.setMemberId(memberId);
        payment.setAmount(amount);
        payment.setMode(mode);
        payment.setDate(date);

        // Save Payment to File D
        FileUtil.savePayment(payment.toFileString());

        // Store success message in session
        HttpSession session = request.getSession();
        session.setAttribute("message", "Thank you, " + payment.getName() + ". Your payment has been recorded.");

        // Forward to success.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
        dispatcher.forward(request, response);
    }
}
