package org.mucnjakf.servlet;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mucnjakf.utilities.PayPalUtilities;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;

@WebServlet(name = "ReviewPayPalPaymentUserServlet", value = "/review-paypal-payment-user")
public class ReviewPayPalPaymentUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityUtilities.validateUser(request, response)) return;

        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");

        PayPalUtilities paypal = new PayPalUtilities();
        Payment payment = paypal.getPaymentDetails(paymentId);

        PayerInfo payerInfo = payment.getPayer().getPayerInfo();
        Transaction transaction = payment.getTransactions().get(0);

        request.setAttribute("paymentId-user", paymentId);
        request.setAttribute("payerId-user", payerId);
        request.setAttribute("transaction-user", transaction);

        request.getRequestDispatcher("user/reviewPayPalPaymentInvoiceUser.jsp").forward(request, response);
    }
}
