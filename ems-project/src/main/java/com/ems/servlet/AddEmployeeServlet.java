package com.ems.servlet;

import com.ems.dao.EmployeeDAO;
import com.ems.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {

    private final EmployeeDAO dao = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/addEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Employee emp = new Employee();
            emp.setName(request.getParameter("name"));
            emp.setEmail(request.getParameter("email"));
            emp.setDepartment(request.getParameter("department"));
            emp.setSalary(Double.parseDouble(request.getParameter("salary")));

            dao.addEmployee(emp);
            response.sendRedirect("ViewEmployeeServlet?status=added");
        } catch (Exception e) {
            request.setAttribute("error", "Could not add employee: " + e.getMessage());
            request.getRequestDispatcher("/addEmployee.jsp").forward(request, response);
        }
    }
}
