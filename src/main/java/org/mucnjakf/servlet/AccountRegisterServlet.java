package org.mucnjakf.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.mucnjakf.model.AdminPin;
import org.mucnjakf.model.User;
import org.mucnjakf.repository.AdminPinRepository;
import org.mucnjakf.repository.UserRepository;
import org.mucnjakf.utilities.PasswordUtilities;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;

@WebServlet(name = "AccountRegisterServlet", value = "/account-register")
public class AccountRegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("accountRegister.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRepository userRepository = new UserRepository();
        AdminPinRepository adminPinRepository = new AdminPinRepository();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String adminPin = request.getParameter("adminPin");

        try {
            byte[] passwordSalt = PasswordUtilities.getSalt();
            Base64.Encoder encoder = Base64.getEncoder();
            String passwordSaltString = encoder.encodeToString(passwordSalt);

            String passwordHash = PasswordUtilities.getHashedPassword(password, passwordSalt);

            User user = new User();
            user.setUsername(username);
            user.setPasswordSalt(passwordSaltString);
            user.setPasswordHash(passwordHash);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);

            if (adminPin.isEmpty()) {
                user.setIsAdmin(false);
            } else {
                AdminPin adminPinFromDb = adminPinRepository.getAdminPin();
                byte[] pinSalt = Base64.getDecoder().decode(adminPinFromDb.getPinSalt());
                String adminPinHash = PasswordUtilities.getHashedPassword(adminPin, pinSalt);

                if (adminPinHash.equals(adminPinFromDb.getPinHash())) {
                    user.setIsAdmin(true);
                } else {
                    user.setIsAdmin(false);
                }
            }

            boolean success = userRepository.insertUser(user);

            if (success) {
                request.setAttribute("username", username);
                request.setAttribute("password", password);

                request.getRequestDispatcher("account-login").forward(request, response);
            } else {
                response.sendRedirect("account-register");
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            response.sendRedirect("account-register");
        }
    }
}
