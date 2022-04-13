<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <title>OneShop</title>
</head>
<body class="bg-secondary bg-opacity-50">

<%@ include file="../navmenu.jsp" %>

<div class="container text-center my-5">
    <div class="card mx-auto shadow-lg p-3 mb-5 bg-body rounded w-75">
        <div class="card-title my-3">
            <h1>500 - An error occurred</h1>
        </div>
        <hr/>
        <div class="card-body">
            <h5 class="text-muted">An error occurred and we are working to fix the problem. In the meantime try
                refreshing the page or try again later.</h5>
        </div>
        <hr class="mt-3"/>
        <a href="http://localhost:8080/OneShop" class="btn btn-outline-dark my-3">Home</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>
</body>
</html>
