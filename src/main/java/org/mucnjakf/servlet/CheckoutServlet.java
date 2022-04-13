package org.mucnjakf.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mucnjakf.model.Invoice;
import org.mucnjakf.model.Product;
import org.mucnjakf.model.User;
import org.mucnjakf.utilities.PayPalUtilities;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@WebServlet(name = "CheckoutServlet", value = "/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!SecurityUtilities.validateUser(request, response)) return;

        User user = (User) request.getSession().getAttribute("user");
        List<Product> basket = (List<Product>) request.getSession().getAttribute("basket");
        int methodOfPayment = Integer.parseInt(request.getParameter("methodOfPayment"));

        Invoice invoice = new Invoice();
        invoice.setDate(new java.sql.Date(new java.util.Date().getTime()));
        invoice.setTime(Time.valueOf(LocalTime.now()));

        double totalPrice = 0.0;
        for (Product product : basket) {
            totalPrice += product.getPrice() * product.getAmount();
        }
        invoice.setTotalPrice(totalPrice);

        invoice.setUser(user);
        invoice.setProducts(basket);

        switch (methodOfPayment) {
            case 1:
                invoice.setMethodOfPayment("Cash");
                request.setAttribute("review-cash-payment-invoice-user", invoice);
                request.getRequestDispatcher("user/reviewCashPaymentInvoiceUser.jsp").forward(request, response);
                break;
            case 2:
                invoice.setMethodOfPayment("PayPal");
                PayPalUtilities paypal = new PayPalUtilities();
                String approvalLink = paypal.authorizePayment(invoice);
                response.sendRedirect(approvalLink);
                break;
        }
    }
}
