package com.regsys.servlet;

import com.regsys.dao.UserDAO;
import com.regsys.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    private final UserDAO dao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setEnrollId(request.getParameter("enrollId"));
            user.setBatch(request.getParameter("batch"));
            user.setDob(request.getParameter("dob"));
            user.setOfficialEmail(request.getParameter("officialEmail"));
            user.setAddress(request.getParameter("address"));
            user.setMobileNo(request.getParameter("mobileNo"));
            user.setUserId(request.getParameter("userId"));

            String password = request.getParameter("password");

            boolean created = dao.registerUser(user, password);
            if (created) {
                response.sendRedirect("LoginServlet?status=registered");
            } else {
                request.setAttribute("error", "That User ID is already taken. Please choose another.");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Could not register: " + e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
