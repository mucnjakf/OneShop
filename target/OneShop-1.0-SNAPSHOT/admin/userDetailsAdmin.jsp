<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.mucnjakf.model.User" %>
<%@ page import="org.mucnjakf.utilities.SecurityUtilities" %>
<%@ page import="org.mucnjakf.model.UserLoginHistory" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <title>Account details</title>
</head>
<body class="bg-secondary bg-opacity-50">

<%
    if (!SecurityUtilities.validateAdmin(request, response)) return;
    User user = (User) request.getAttribute("user-details-admin");
%>

<%@ include file="../navmenu.jsp" %>

<div class="container text-center my-5">
    <div class="card mx-auto shadow-lg p-3 mb-5 bg-body rounded" style="width: 32rem;">
        <div class="card-title my-3">
            <h1>User details</h1>
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
            <div class="row">
                <div class="col fw-bold">
                    Is admin:
                </div>
                <div class="col">
                    <p>
                        <%= user.getIsAdmin() ? "Yes" : "No" %>
                    </p>
                </div>
            </div>
            <div class="row mt-3">
                <b>Invoices:</b>
                <ul class="list-group mt-3">
                    <c:forEach var="invoice" items="<%= user.getInvoices() %>">
                        <li class="list-group-item d-flex justify-content-between">
                            <div>
                                    ${invoice.id} -
                                <fmt:setLocale value="en_US"/>
                                <fmt:formatNumber value="${invoice.totalPrice}" type="currency"/> -
                                    ${invoice.methodOfPayment}
                            </div>
                            <div>
                                <a href="invoice-details-admin?id=${invoice.id}" class="btn btn-outline-dark">
                                    Details
                                </a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="row mt-3">
                <b>User login history:</b>
                <ul class="list-group mt-3">
                    <%
                        for (UserLoginHistory ulh : user.getUserLoginHistory()) {
                    %>
                    <li class="list-group-item">
                        <p>
                            <%= ulh.getAddress() %> -
                            <fmt:setLocale value="en_US"/>
                            <fmt:formatDate value="<%= ulh.getDate() %>" dateStyle="full"/>
                            - <%= ulh.getTime().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss a")) %>
                        </p>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>
</body>
</html>
