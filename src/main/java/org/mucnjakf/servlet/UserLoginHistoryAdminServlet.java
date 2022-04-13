package org.mucnjakf.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mucnjakf.model.UserLoginHistory;
import org.mucnjakf.repository.UserLoginHistoryRepository;
import org.mucnjakf.utilities.SecurityUtilities;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserLoginHistoryAdminServlet", value = "/user-login-history-admin")
public class UserLoginHistoryAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityUtilities.validateAdmin(request, response)) return;

        UserLoginHistoryRepository userLoginHistoryRepository = new UserLoginHistoryRepository();
        List<UserLoginHistory> userLoginHistory = userLoginHistoryRepository.getUserLoginHistory();
        request.setAttribute("user-login-history-admin", userLoginHistory);
        request.getRequestDispatcher("admin/userLoginHistoryAdmin.jsp").forward(request, response);
    }
}
