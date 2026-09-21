package com.regsys.servlet;

import com.regsys.dao.UserDAO;
import com.regsys.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewUsersServlet")
public class ViewUsersServlet extends HttpServlet {

    private final UserDAO dao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<User> users = dao.getAllUsers();
            request.setAttribute("users", users);
        } catch (Exception e) {
            request.setAttribute("error", "Could not load users: " + e.getMessage());
        }
        request.getRequestDispatcher("/viewUsers.jsp").forward(request, response);
    }
}
