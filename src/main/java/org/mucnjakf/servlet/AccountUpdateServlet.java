package org.mucnjakf.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mucnjakf.model.User;
import org.mucnjakf.repository.UserRepository;
import org.mucnjakf.utilities.PasswordUtilities;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;

@WebServlet(name = "AccountUpdateServlet", value = "/account-update")
public class AccountUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (!SecurityUtilities.validateUser(request, response)) return;

        request.getRequestDispatcher("accountUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserRepository userRepository = new UserRepository();

        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));

        try {
            byte[] passwordSalt = PasswordUtilities.getSalt();
            Base64.Encoder encoder = Base64.getEncoder();
            String passwordSaltString = encoder.encodeToString(passwordSalt);

            String passwordHash = PasswordUtilities.getHashedPassword(password, passwordSalt);

            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setPasswordSalt(passwordSaltString);
            user.setPasswordHash(passwordHash);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setIsAdmin(isAdmin);

            boolean success = userRepository.updateUser(user);

            if (success) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect("account-details");
            } else {
                response.sendRedirect("account-update");
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            response.sendRedirect("account-update");
        }
    }
}
