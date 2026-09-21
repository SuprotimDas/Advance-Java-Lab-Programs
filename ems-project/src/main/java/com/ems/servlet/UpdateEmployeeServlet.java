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

@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {

    private final EmployeeDAO dao = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        try {
            if (idParam == null || idParam.trim().isEmpty()) {
                // No id yet: show the list so the user can pick who to edit
                List<Employee> employees = dao.getAllEmployees();
                request.setAttribute("employees", employees);
                request.getRequestDispatcher("/selectEmployee.jsp").forward(request, response);
            } else {
                Employee emp = dao.getEmployeeById(Integer.parseInt(idParam));
                request.setAttribute("employee", emp);
                request.getRequestDispatcher("/updateEmployee.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Could not load employee: " + e.getMessage());
            request.getRequestDispatcher("/viewEmployees.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Employee emp = new Employee();
            emp.setId(Integer.parseInt(request.getParameter("id")));
            emp.setName(request.getParameter("name"));
            emp.setEmail(request.getParameter("email"));
            emp.setDepartment(request.getParameter("department"));
            emp.setSalary(Double.parseDouble(request.getParameter("salary")));

            dao.updateEmployee(emp);
            response.sendRedirect("ViewEmployeeServlet?status=updated");
        } catch (Exception e) {
            request.setAttribute("error", "Could not update employee: " + e.getMessage());
            request.getRequestDispatcher("/viewEmployees.jsp").forward(request, response);
        }
    }
}
