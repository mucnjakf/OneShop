<%@ page import="org.mucnjakf.utilities.SecurityUtilities" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <title>Account details</title>
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
            <h1>Account details</h1>
        </div>
        <hr/>
        <div class="card-body">
            <div class="row">
                <div class="col fw-bold">
                    ID:
                </div>
                <div class="col">
                    <p>
                        <%= user.getId() %>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col fw-bold">
                    Username:
                </div>
                <div class="col">
                    <p>
                        <%= user.getUsername() %>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col fw-bold">
                    First name:
                </div>
                <div class="col">
                    <p>
                        <%= user.getFirstName() %>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col fw-bold">
                    Last name:
                </div>
                <div class="col">
                    <p>
                        <%= user.getLastName() %>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col fw-bold">
                    Email:
                </div>
                <div class="col">
                    <p>
                        <%= user.getEmail() %>
                    </p>
                </div>
            </div>
            <div class="row mt-5">
                <div class="col">
                    <a href="account-update" class="btn btn-outline-dark">Edit</a>
                </div>
                <div class="col">
                    <a href="account-logout" class="btn btn-outline-dark">Logout</a>
                </div>
                <div class="col">
                    <a href="account-delete" class="btn btn-outline-dark">Delete</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>
</body>
</html>
