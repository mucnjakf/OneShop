package org.mucnjakf.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mucnjakf.model.Invoice;
import org.mucnjakf.repository.InvoiceRepository;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserPurchaseHistoryAdminServlet", value = "/user-purchase-history-admin")
public class UserPurchaseHistoryAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        InvoiceRepository invoiceRepository = new InvoiceRepository();
        List<Invoice> invoices = invoiceRepository.getInvoices();
        request.setAttribute("user-purchase-history-admin", invoices);
        request.getRequestDispatcher("admin/userPurchaseHistoryAdmin.jsp").forward(request, response);
    }
}
