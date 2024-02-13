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

@WebServlet(name = "CategoryUpdateAdminServlet", value = "/category-update-admin")
public class CategoryUpdateAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        CategoryRepository categoryRepository = new CategoryRepository();

        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryRepository.getCategory(id);

        request.setAttribute("category-update-admin", category);
        request.getRequestDispatcher("admin/categoryUpdateAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        CategoryRepository categoryRepository = new CategoryRepository();

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Category category = new Category();
        category.setId(id);
        category.setName(name);

        boolean success = categoryRepository.updateCategory(category);

        if (success) {
            response.sendRedirect("category-list-admin");
        } else {
            response.sendRedirect("category-update-admin?id=" + id);
        }
    }
}
