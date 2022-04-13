package org.mucnjakf.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mucnjakf.model.Invoice;
import org.mucnjakf.model.User;
import org.mucnjakf.repository.InvoiceRepository;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InvoiceListUserServlet", value = "/invoice-list-user")
public class InvoiceListUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityUtilities.validateUser(request, response)) return;

        InvoiceRepository invoiceRepository = new InvoiceRepository();

        User user = (User) request.getSession().getAttribute("user");

        List<Invoice> invoices = invoiceRepository.getInvoices();
        List<Invoice> userInvoices = new ArrayList<>();

        for (Invoice invoice : invoices) {
            if (invoice.getUser().getId() == user.getId()) {
                userInvoices.add(invoice);
            }
        }

        request.setAttribute("invoice-list-user", userInvoices);
        request.getRequestDispatcher("user/invoiceListUser.jsp").forward(request, response);
    }
}
