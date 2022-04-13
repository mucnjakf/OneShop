<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.mucnjakf.model.Product" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <title>Product list</title>
</head>
<body class="bg-secondary bg-opacity-50">

<%
    List<Product> products = (List<Product>) request.getAttribute("product-list");
%>

<%@ include file="navmenu.jsp" %>

<div class="container text-center my-5">
    <div class="card mx-auto shadow-lg p-3 mb-5 bg-body rounded" style="width: 64rem;">
        <div class="card-title my-3">
            <h1><%= products.get(0).getCategory().getName() %>
            </h1>
        </div>
        <hr/>
        <div class="card-body">
            <c:choose>
                <c:when test="<%= products.isEmpty() %>">
                    <h3>
                        There are no <%= products.get(0).getCategory().getName().toLowerCase() %>.
                    </h3>
                </c:when>
                <c:when test="<%= !products.isEmpty() %>">
                    <div class="input-group w-25 mb-4">
                        <span class="input-group-text">Search:</span>
                        <input type="text" class="form-control" id="search"/>
                    </div>

                    <table class="table table-stripped table-hover">
                        <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Amount</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="table">
                        <c:forEach var="product" items="<%= products %>">
                            <form method="POST" action="basket-add-product">
                                <tr>
                                    <input type="hidden" id="id" name="id" value="${product.id}"/>
                                    <th>
                                            ${product.id}
                                    </th>
                                    <td>
                                            ${product.name}
                                    </td>
                                    <td>
                                        <fmt:setLocale value="en_US"/>
                                        <fmt:formatNumber value="${product.price}" type="currency"/>
                                    </td>
                                    <td style="width: 6rem;">
                                        <input class="form-control" type="number" id="amount" name="amount" min="1"
                                               step="1" value="1">
                                    </td>
                                    <td class="w-25">
                                        <input type="submit" class="btn btn-outline-dark" value="Add to basket"/>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.6.0.slim.js"
        integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>

<script>
    $(document).ready(function () {
        $("#search").on("keyup", function () {
            let value = $(this).val().toLowerCase();
            $("#table tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>
