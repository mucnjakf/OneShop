<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.mucnjakf.model.Category" %>
<%@ page import="org.mucnjakf.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="org.mucnjakf.utilities.SecurityUtilities" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <title>Edit product</title>
</head>
<body class="bg-secondary bg-opacity-50">

<%
    if (!SecurityUtilities.validateAdmin(request, response)) return;
    Product product = (Product) request.getAttribute("product-update-admin");
    List<Category> categories = (List<Category>) request.getAttribute("product-update-categories-admin");
%>

<%@ include file="../navmenu.jsp" %>

<div class="container text-center my-5">
    <div class="card mx-auto shadow-lg p-3 mb-5 bg-body rounded" style="width: 32rem;">
        <div class="card-title my-3">
            <h1>Edit product</h1>
        </div>
        <hr/>
        <div class="card-body">
            <form method="POST" action="product-update-admin">
                <input type="hidden" id="id" name="id" value="<%= product.getId() %>">

                <div class="form-group mb-3">
                    <label for="name" class="form-label">Name:</label>
                    <input type="text" class="form-control" id="name" name="name" value="<%= product.getName() %>" required>
                </div>
                <div class="form-group mb-3">
                    <label for="price" class="form-label">Price:</label>
                    <input type="number" class="form-control" id="price" name="price" min="0" step="any" value="<%= product.getPrice() %>" required>
                </div>
                <div class="form-group mb-3">
                    <label for="category" class="form-label">Category:</label>
                    <select class="form-select" id="category" name="category" required>
                        <option selected disabled value="">Select category</option>
                        <%
                            for (Category category : categories) {
                        %>
                        <option value="<%= category.getId() %>" <%= category.getId() == product.getCategory().getId() ? "selected" : "" %>>
                            <%= category.getName() %>
                        </option>
                        <%
                            }
                        %>
                    </select>
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
