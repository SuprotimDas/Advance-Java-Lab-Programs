package com.ems.servlet;

import com.ems.dao.EmployeeDAO;
import com.ems.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewEmployeeServlet")
public class ViewEmployeeServlet extends HttpServlet {

    private final EmployeeDAO dao = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Employee> employees = dao.getAllEmployees();
            request.setAttribute("employees", employees);
        } catch (Exception e) {
            request.setAttribute("error", "Could not load employees: " + e.getMessage());
        }
        request.getRequestDispatcher("/viewEmployees.jsp").forward(request, response);
    }
}
