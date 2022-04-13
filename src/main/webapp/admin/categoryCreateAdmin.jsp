<%@ page import="org.mucnjakf.utilities.SecurityUtilities" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <title>Create category</title>
</head>
<body class="bg-secondary bg-opacity-50">

<% if (!SecurityUtilities.validateAdmin(request, response)) return; %>

<%@ include file="../navmenu.jsp" %>

<div class="container text-center my-5">
    <div class="card mx-auto shadow-lg p-3 mb-5 bg-body rounded" style="width: 32rem;">
        <div class="card-title my-3">
            <h1>Create category</h1>
        </div>
        <hr/>
        <div class="card-body">
            <form method="POST" action="category-create-admin">
                <div class="form-group mb-3">
                    <label for="name" class="form-label">Name:</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <button type="submit" class="btn btn-outline-dark mt-5">Create</button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>
</body>
</html>
