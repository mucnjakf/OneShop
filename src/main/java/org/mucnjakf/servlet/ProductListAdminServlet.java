package org.mucnjakf.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mucnjakf.model.Product;
import org.mucnjakf.repository.ProductRepository;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductListAdminServlet", value = "/product-list-admin")
public class ProductListAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        ProductRepository productRepository = new ProductRepository();
        List<Product> products = productRepository.getProducts();
        request.setAttribute("product-list-admin", products);
        request.getRequestDispatcher("admin/productListAdmin.jsp").forward(request, response);
    }
}
