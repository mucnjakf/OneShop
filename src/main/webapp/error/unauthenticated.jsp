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
            <h1>401 - You are not authenticated</h1>
        </div>
        <hr/>
        <div class="card-body">
            <h5 class="text-muted">You have to be logged in with correct credentials to view this page. Make sure the
                URL is correct and that you are logged in with correct credentials.</h5>
        </div>
        <hr/>
        <div class="card mx-auto shadow-lg p-3 my-3 bg-body rounded" style="width: 32rem;">
            <div class="card-title my-3">
                <h1>Account login</h1>
            </div>
            <hr/>
            <div class="card-body">
                <form method="POST" action="account-login">
                    <div class="form-group mb-3">
                        <label for="username" class="form-label">Username:</label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>
                    <div class="form-group mb-3">
                        <label for="password" class="form-label">Password:</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <button type="submit" class="btn btn-outline-dark mt-5">Login</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>
</body>
</html>
