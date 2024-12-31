package com.onlinebanking.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L; // Added for serialization compatibility

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Collect form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String accountNumber = request.getParameter("accountNumber");

        // Validate form data (example: basic checks)
        if (name == null || name.isEmpty() ||
            email == null || email.isEmpty() || !email.contains("@") ||
            accountNumber == null || accountNumber.isEmpty()) {
            request.setAttribute("error", "Invalid form data. Please fill in all fields correctly.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp"); // Redirect to an error page
            dispatcher.forward(request, response);
            return;
        }

        // Optional: Store form data in session or database if needed
        // Example: Store data in session
        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        session.setAttribute("accountNumber", accountNumber);

        // Store data in request attributes for display
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("accountNumber", accountNumber);

        // Forward the request to the userProfile.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userProfile.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests, if necessary
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userProfile.jsp");
        dispatcher.forward(request, response);
    }
}
