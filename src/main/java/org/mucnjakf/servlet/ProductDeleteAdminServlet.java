package org.mucnjakf.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mucnjakf.repository.ProductRepository;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;

@WebServlet(name = "ProductDeleteAdminServlet", value = "/product-delete-admin")

public class ProductDeleteAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        ProductRepository productRepository = new ProductRepository();

        int id = Integer.parseInt(request.getParameter("id"));

        productRepository.deleteProduct(id);

        response.sendRedirect("product-list-admin");
    }
}
