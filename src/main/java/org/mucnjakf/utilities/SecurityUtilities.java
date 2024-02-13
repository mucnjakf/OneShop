package org.mucnjakf.utilities;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mucnjakf.model.User;

import java.io.IOException;

public class SecurityUtilities {

    public static boolean validateAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendError(401);
            return false;
        } else {
            if (!user.getIsAdmin()) {
                response.sendError(403);
                return false;
            }
        }
        return true;
    }

    public static boolean validateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendError(401);
            return false;
        }
        return true;
    }
}
