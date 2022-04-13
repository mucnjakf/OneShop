package org.mucnjakf.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mucnjakf.model.Product;
import org.mucnjakf.repository.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BasketAddProductServlet", value = "/basket-add-product")
public class BasketAddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductRepository productRepository = new ProductRepository();

        int id = Integer.parseInt(request.getParameter("id"));
        int amount = Integer.parseInt(request.getParameter("amount"));

        Product product = productRepository.getProduct(id);
        product.setAmount(amount);

        HttpSession session = request.getSession();

        if (session.getAttribute("basket") == null) {
            List<Product> basket = new ArrayList<>();
            basket.add(product);
            session.setAttribute("basket", basket);
        } else {
            List<Product> basket = (List<Product>) session.getAttribute("basket");
            basket.add(product);
            session.setAttribute("basket", basket);
        }

        session.setAttribute("basket-categoryId", product.getCategory().getId());

        response.sendRedirect("basket");
    }
}
