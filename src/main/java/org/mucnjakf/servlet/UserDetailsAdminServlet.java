package org.mucnjakf.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mucnjakf.model.User;
import org.mucnjakf.repository.UserRepository;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;

@WebServlet(name = "UserDetailsAdminServlet", value = "/user-details-admin")
public class UserDetailsAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        UserRepository userRepository = new UserRepository();

        int id = Integer.parseInt(request.getParameter("id"));

        User userFromDb = userRepository.getUser(id);

        request.setAttribute("user-details-admin", userFromDb);
        request.getRequestDispatcher("admin/userDetailsAdmin.jsp").forward(request, response);
    }
}
