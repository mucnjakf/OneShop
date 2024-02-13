package org.mucnjakf.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mucnjakf.model.User;
import org.mucnjakf.repository.UserRepository;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;

@WebServlet(name = "AccountDeleteServlet", value = "/account-delete")
public class AccountDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!SecurityUtilities.validateUser(request, response)) return;

        UserRepository userRepository = new UserRepository();

        User user = (User) request.getSession().getAttribute("user");

        boolean success = userRepository.deleteUser(user.getId());

        if (success) {
            request.getSession().invalidate();
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            response.sendRedirect("account-details");
        }
    }
}
