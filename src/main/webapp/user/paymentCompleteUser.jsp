<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.mucnjakf.utilities.SecurityUtilities" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <title>Payment complete</title>
</head>
<body class="bg-secondary bg-opacity-50">

<%
    if (!SecurityUtilities.validateUser(request, response)) return;
%>

<%@ include file="../navmenu.jsp" %>

<div class="container text-center my-5">
    <div class="card mx-auto shadow-lg p-3 mb-3 bg-body rounded" style="width: 32rem;">
        <div class="card-title my-3">
            <h1>Payment complete</h1>
        </div>
        <hr/>
        <div class="card-body">
            <h4>Thank you for your purchase.</h4>

            <div class="mt-5">
                <a href="http://localhost:8080/OneShop/invoice-list-user" class="btn btn-outline-dark">Invoices</a>
                <a href="http://localhost:8080/OneShop/index.jsp" class="btn btn-outline-dark">Home</a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>
</body>
</html>