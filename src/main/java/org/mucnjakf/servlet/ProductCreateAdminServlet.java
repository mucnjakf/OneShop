package org.mucnjakf.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mucnjakf.model.Category;
import org.mucnjakf.model.Product;
import org.mucnjakf.repository.CategoryRepository;
import org.mucnjakf.repository.ProductRepository;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductCreateAdminServlet", value = "/product-create-admin")
public class ProductCreateAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        CategoryRepository categoryRepository = new CategoryRepository();
        List<Category> categories = categoryRepository.getCategories();

        request.setAttribute("product-create-categories-admin", categories);
        request.getRequestDispatcher("admin/productCreateAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        ProductRepository productRepository = new ProductRepository();
        CategoryRepository categoryRepository = new CategoryRepository();

        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("category"));

        Category category = categoryRepository.getCategory(categoryId);

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);

        boolean success = productRepository.insertProduct(product);

        if (success) {
            response.sendRedirect("product-list-admin");
        } else {
            response.sendRedirect("product-create-admin");
        }
    }
}
