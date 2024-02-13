package org.mucnjakf.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mucnjakf.model.Category;
import org.mucnjakf.repository.CategoryRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/category-list")
public class CategoryListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryRepository categoryRepository = new CategoryRepository();
        List<Category> categories = categoryRepository.getCategories();
        request.setAttribute("category-list", categories);
        request.getRequestDispatcher("categoryList.jsp").forward(request, response);
    }
}
