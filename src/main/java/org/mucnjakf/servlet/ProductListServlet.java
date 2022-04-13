package org.mucnjakf.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mucnjakf.model.Product;
import org.mucnjakf.repository.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductListServlet", value = "/product-list")
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductRepository productRepository = new ProductRepository();

        int categoryId = Integer.parseInt(request.getParameter("id"));

        List<Product> products = productRepository.getProducts();
        List<Product> productsFromCategory = new ArrayList<>();

        for (Product product : products) {
            if (product.getCategory().getId() == categoryId) {
                productsFromCategory.add(product);
            }
        }

        request.setAttribute("product-list", productsFromCategory);
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }
}
