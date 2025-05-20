package Controller;

import models.Payment;
import utils.FileUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;


@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
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

        Payment payment = new Payment(name, memberId, amount, mode, date);
        FileUtil.savePayment(payment.toFileString());

        // Store success message in session
        HttpSession session = request.getSession();
        session.setAttribute("message", "Thank you, " + name + ". Your payment has been recorded.");

        // Forward to success.jsp to display the message
        RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
        dispatcher.forward(request, response);
    }
}
