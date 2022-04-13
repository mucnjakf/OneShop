<%@ page import="org.mucnjakf.utilities.SecurityUtilities" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <title>Account edit</title>
</head>
<body class="bg-secondary bg-opacity-50">

<%
    if (!SecurityUtilities.validateUser(request, response)) return;
    User user = (User) session.getAttribute("user");
%>

<%@ include file="navmenu.jsp" %>

<div class="container text-center my-5">
    <div class="card mx-auto shadow-lg p-3 mb-5 bg-body rounded" style="width: 32rem;">
        <div class="card-title my-3">
            <h1>Account edit</h1>
        </div>
        <hr/>
        <div class="card-body">
            <form method="POST" action="account-update">
                <input type="hidden" id="id" name="id" value="<%= user.getId() %>">
                <input type="hidden" id="isAdmin" name="isAdmin" value="<%= user.getIsAdmin() %>">

                <div class="form-group mb-3">
                    <label for="username" class="form-label">Username:</label>
                    <input type="text" class="form-control" id="username" name="username"
                           value="<%= user.getUsername() %>" required minlength="3">
                </div>
                <div class="form-group mb-3">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" required minlength="3">
                </div>
                <div class="form-group mb-3">
                    <label for="firstName" class="form-label">First name:</label>
                    <input type="text" class="form-control" id="firstName" name="firstName"
                           value="<%= user.getFirstName() %>" required>
                </div>
                <div class="form-group mb-3">
                    <label for="lastName" class="form-label">Last name:</label>
                    <input type="text" class="form-control" id="lastName" name="lastName"
                           value="<%= user.getLastName() %>" required>
                </div>
                <div class="form-group mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" class="form-control" id="email" name="email"
                        value="<%= user.getEmail() %>" required>
                </div>
                <button type="submit" class="btn btn-outline-dark mt-5">Edit</button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>
</body>
</html>
