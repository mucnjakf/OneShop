package org.mucnjakf.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mucnjakf.model.Invoice;
import org.mucnjakf.repository.InvoiceRepository;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;

@WebServlet(name = "InvoiceDetailsAdminServlet", value = "/invoice-details-admin")
public class InvoiceDetailsAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        InvoiceRepository invoiceRepository = new InvoiceRepository();

        int id = Integer.parseInt(request.getParameter("id"));

        Invoice invoice = invoiceRepository.getInvoice(id);

        request.setAttribute("invoice-details-admin", invoice);
        request.getRequestDispatcher("admin/invoiceDetailsAdmin.jsp").forward(request, response);
    }
}
