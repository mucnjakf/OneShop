package org.mucnjakf.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mucnjakf.model.Invoice;
import org.mucnjakf.model.Product;
import org.mucnjakf.model.User;
import org.mucnjakf.repository.InvoiceRepository;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@WebServlet(name = "ExecuteCashPaymentUserServlet", value = "/execute-cash-payment-user")
public class ExecuteCashPaymentUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityUtilities.validateUser(request, response)) return;

        InvoiceRepository invoiceRepository = new InvoiceRepository();

        User user = (User) request.getSession().getAttribute("user");
        List<Product> basket = (List<Product>) request.getSession().getAttribute("basket");
        String methodOfPayment = request.getParameter("methodOfPayment");

        Invoice invoice = new Invoice();
        invoice.setDate(new java.sql.Date(new java.util.Date().getTime()));
        invoice.setTime(Time.valueOf(LocalTime.now()));

        double totalPrice = 0.0;
        for (Product product : basket) {
            totalPrice += product.getPrice() * product.getAmount();
        }
        invoice.setTotalPrice(totalPrice);

        invoice.setUser(user);
        invoice.setMethodOfPayment(methodOfPayment);
        invoice.setProducts(null);

        boolean success = invoiceRepository.insertInvoice(invoice, basket);

        if (success) {
            request.getSession().removeAttribute("basket");
            response.sendRedirect("user/paymentCompleteUser.jsp");
        }
    }
}
