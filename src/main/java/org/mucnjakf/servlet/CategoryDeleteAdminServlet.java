package org.mucnjakf.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mucnjakf.repository.CategoryRepository;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;

@WebServlet(name = "CategoryDeleteAdminServlet", value = "/category-delete-admin")
public class CategoryDeleteAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        CategoryRepository categoryRepository = new CategoryRepository();

        int id = Integer.parseInt(request.getParameter("id"));

        categoryRepository.deleteCategory(id);

        response.sendRedirect("category-list-admin");
    }
}
