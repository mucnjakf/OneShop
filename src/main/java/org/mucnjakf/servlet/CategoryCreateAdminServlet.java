package org.mucnjakf.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mucnjakf.model.Category;
import org.mucnjakf.repository.CategoryRepository;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;

@WebServlet(name = "CategoryCreateAdminServlet", value = "/category-create-admin")
public class CategoryCreateAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        request.getRequestDispatcher("admin/categoryCreateAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        CategoryRepository categoryRepository = new CategoryRepository();

        String name = request.getParameter("name");

        Category category = new Category();
        category.setName(name);

        boolean success = categoryRepository.insertCategory(category);

        if (success) {
            response.sendRedirect("category-list-admin");
        } else {
            response.sendRedirect("category-create-admin");
        }
    }
}
