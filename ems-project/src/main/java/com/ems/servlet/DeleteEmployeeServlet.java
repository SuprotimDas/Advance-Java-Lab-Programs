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

@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {

    private final EmployeeDAO dao = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        try {
            if (idParam == null || idParam.trim().isEmpty()) {
                // No id yet: show the list so the user can pick who to delete
                List<Employee> employees = dao.getAllEmployees();
                request.setAttribute("employees", employees);
                request.setAttribute("mode", "delete");
                request.getRequestDispatcher("/selectEmployee.jsp").forward(request, response);
            } else {
                dao.deleteEmployee(Integer.parseInt(idParam));
                response.sendRedirect("ViewEmployeeServlet?status=deleted");
            }
        } catch (Exception e) {
            request.setAttribute("error", "Could not delete employee: " + e.getMessage());
            request.getRequestDispatcher("/viewEmployees.jsp").forward(request, response);
        }
    }
}
