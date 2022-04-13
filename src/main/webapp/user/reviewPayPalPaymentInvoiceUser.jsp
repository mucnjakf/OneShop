<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.mucnjakf.utilities.SecurityUtilities" %>
<%@ page import="com.paypal.api.payments.PayerInfo" %>
<%@ page import="com.paypal.api.payments.Transaction" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <title>Review PayPal payment</title>
</head>
<body class="bg-secondary bg-opacity-50">

<%
    if (!SecurityUtilities.validateUser(request, response)) return;

    String paymentId = (String) request.getAttribute("paymentId-user");
    String payerId = (String) request.getAttribute("payerId-user");
    Transaction transaction = (Transaction) request.getAttribute("transaction-user");
%>

<%@ include file="../navmenu.jsp" %>

<div class="container text-center my-5">
    <div class="card mx-auto shadow-lg p-3 mb-3 bg-body rounded" style="width: 32rem;">
        <div class="card-title my-3">
            <h1>Review PayPal payment</h1>
        </div>
        <hr/>
        <div class="card-body">
            <div class="row">
                <div class="col fw-bold">Description:</div>
                <div class="col">
                    <p>
                        <%= transaction.getDescription() %>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col fw-bold">Total price:</div>
                <div class="col">
                    <p>
                        <fmt:setLocale value="en_US"/>
                        <fmt:formatNumber value="<%= transaction.getAmount().getTotal() %>" type="currency"/>
                    </p>
                </div>
            </div>

            <form method="POST" action="execute-paypal-payment-user">
                <input type="hidden" name="paymentId" value="<%= paymentId %>"/>
                <input type="hidden" name="PayerID" value="<%= payerId %>"/>
                <input type="submit" class="btn btn-outline-primary" value="Confirm purchase" />
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>
</body>
</html>