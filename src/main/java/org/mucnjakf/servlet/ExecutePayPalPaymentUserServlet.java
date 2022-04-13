package org.mucnjakf.servlet;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mucnjakf.utilities.PayPalUtilities;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;

@WebServlet(name = "ExecutePayPalPaymentUserServlet", value = "/execute-paypal-payment-user")
public class ExecutePayPalPaymentUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityUtilities.validateUser(request, response)) return;

        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");

        PayPalUtilities paypal = new PayPalUtilities();
        Payment payment = paypal.executePayment(paymentId, payerId);

        PayerInfo payerInfo = payment.getPayer().getPayerInfo();
        Transaction transaction = payment.getTransactions().get(0);

        request.setAttribute("payer-info-user", payerInfo);
        request.setAttribute("transaction-user", transaction);

        request.getRequestDispatcher("paymentCompleteUser.jsp").forward(request, response);
    }
}
