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
import java.util.List;

@WebServlet(name = "CategoryListAdminServlet", value = "/category-list-admin")
public class CategoryListAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        CategoryRepository categoryRepository = new CategoryRepository();
        List<Category> categories = categoryRepository.getCategories();
        request.setAttribute("category-list-admin", categories);
        request.getRequestDispatcher("admin/categoryListAdmin.jsp").forward(request, response);
    }
}
