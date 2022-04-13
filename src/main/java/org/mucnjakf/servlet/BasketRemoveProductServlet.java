package org.mucnjakf.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mucnjakf.model.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BasketRemoveProductServlet", value = "/basket-remove-product")
public class BasketRemoveProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        List<Product> basket = (List<Product>) request.getSession().getAttribute("basket");
        basket.removeIf(product -> product.getId() == id);

        request.getSession().setAttribute("basket", basket);
        response.sendRedirect("basket");
    }
}
