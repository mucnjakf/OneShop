package org.mucnjakf.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mucnjakf.model.User;
import org.mucnjakf.model.UserLoginHistory;
import org.mucnjakf.repository.UserLoginHistoryRepository;
import org.mucnjakf.repository.UserRepository;
import org.mucnjakf.utilities.PasswordUtilities;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Base64;
import java.util.List;

@WebServlet(name = "AccountLoginServlet", value = "/account-login")
public class AccountLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("accountLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserRepository userRepository = new UserRepository();
        UserLoginHistoryRepository userLoginHistoryRepository = new UserLoginHistoryRepository();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        List<User> users = userRepository.getUsers();

        String passwordHash = "";
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                byte[] salt = Base64.getDecoder().decode(user.getPasswordSalt());
                passwordHash = PasswordUtilities.getHashedPassword(password, salt);
            }
        }

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPasswordHash().equals(passwordHash)) {
                request.getSession().setAttribute("user", user);

                UserLoginHistory userLoginHistory = new UserLoginHistory();
                userLoginHistory.setDate(new java.sql.Date(new java.util.Date().getTime()));
                userLoginHistory.setTime(Time.valueOf(LocalTime.now()));
                userLoginHistory.setAddress(request.getLocalAddr());
                userLoginHistory.setUser(user);

                userLoginHistoryRepository.insertUserLoginHistory(userLoginHistory);

                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
        }

        response.sendError(401);
    }
}
