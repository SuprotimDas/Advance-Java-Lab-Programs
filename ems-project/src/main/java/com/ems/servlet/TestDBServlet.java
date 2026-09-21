package com.ems.servlet;

import com.ems.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message;
        boolean success;
        try (Connection conn = DBConnection.getConnection()) {
            success = conn != null && !conn.isClosed();
            message = "Connected successfully to: " + conn.getMetaData().getURL();
        } catch (Exception e) {
            success = false;
            message = "Connection failed: " + e.getMessage();
        }
        request.setAttribute("success", success);
        request.setAttribute("message", message);
        request.getRequestDispatcher("/dbStatus.jsp").forward(request, response);
    }
}
