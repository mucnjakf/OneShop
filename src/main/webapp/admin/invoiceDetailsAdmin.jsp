<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.mucnjakf.model.Invoice" %>
<%@ page import="org.mucnjakf.utilities.SecurityUtilities" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <title>Invoice details</title>
</head>
<body class="bg-secondary bg-opacity-50">

<%
    if (!SecurityUtilities.validateAdmin(request, response)) return;

    Invoice invoice = (Invoice) request.getAttribute("invoice-details-admin");
%>

<%@ include file="../navmenu.jsp" %>

<div class="container text-center my-5">
    <div class="card mx-auto shadow-lg p-3 mb-3 bg-body rounded" style="width: 32rem;">
        <div class="card-title my-3">
            <h1>Invoice details</h1>
        </div>
        <hr/>
        <div class="card-body">
            <div class="row">
                <div class="col fw-bold">ID:</div>
                <div class="col">
                    <p>
                        <%= invoice.getId() %>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col fw-bold">User:</div>
                <div class="col">
                    <p>
                        <a class="text-decoration-none" href="user-details-admin?id=<%= invoice.getUser().getId() %>">
                            <%= invoice.getUser().getUsername() %>
                        </a>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col fw-bold">Date:</div>
                <div class="col">
                    <p>
                        <fmt:setLocale value="en_US"/>
                        <fmt:formatDate value="<%= invoice.getDate() %>" dateStyle="full"/>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col fw-bold">Time:</div>
                <div class="col">
                    <p>
                        <%= invoice.getTime().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss a")) %>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col fw-bold">Method of payment:</div>
                <div class="col">
                    <p>
                        <%= invoice.getMethodOfPayment() %>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col fw-bold">Total price:</div>
                <div class="col">
                    <p>
                        <fmt:setLocale value="en_US"/>
                        <fmt:formatNumber value="<%= invoice.getTotalPrice() %>" type="currency"/>
                    </p>
                </div>
            </div>
            <div class="row mt-3">
                <b>Products:</b>
                <ul class="list-group mt-3">
                    <c:forEach var="product" items="<%= invoice.getProducts() %>">
                        <li class="list-group-item list-group-item-action">
                            <p>
                                    ${product.id} -
                                    ${product.name} -
                                <fmt:setLocale value="en_US"/>
                                <fmt:formatNumber value="${product.price}" type="currency"/> -
                                    ${product.category.name}
                            </p>
                        </li>
                    </c:forEach>
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