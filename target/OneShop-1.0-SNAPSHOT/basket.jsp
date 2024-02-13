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
    <title>Basket</title>
</head>
<body class="bg-secondary bg-opacity-50">

<%
    List<Product> basket = (List<Product>) session.getAttribute("basket");
%>

<%@ include file="navmenu.jsp" %>

<div class="container text-center my-5">
    <div class="card mx-auto shadow-lg p-3 mb-5 bg-body rounded" style="width: 64rem;">
        <div class="card-title my-3">
            <h1>Basket</h1>
        </div>
        <hr/>
        <div class="card-body">
            <%
                if (basket == null) {
            %>
            <h3>
                There are no products in the basket.
            </h3>
            <%
            } else {
            %>
            <c:choose>
                <c:when test="<%= basket.isEmpty() %>">
                    <h3>
                        There are no products in the basket.
                    </h3>
                </c:when>
                <c:when test="<%= !basket.isEmpty() %>">
                    <%
                        Double totalPrice = 0.0;
                        for (Product product : basket) {
                            totalPrice += product.getPrice() * product.getAmount();
                        }
                    %>
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
                            <th scope="col">Category</th>
                            <th scope="col">Amount</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="table">
                        <c:forEach var="product" items="<%= basket %>">
                            <tr>
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
                                <td>
                                        ${product.category.name}
                                </td>
                                <td>
                                        ${product.amount}
                                </td>
                                <td class="w-25">
                                    <a href="basket-remove-product?id=${product.id}" class="btn btn-outline-dark">Remove</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="my-3">
                        <a href="basket-clear" class="btn btn-outline-dark">Empty basket</a>
                    </div>
                    <ul class="list-group mt-5 w-50 mx-auto">
                        <li class="list-group-item p-3">
                            <h5 class="fw-bold">
                                <fmt:setLocale value="en_US"/>
                                Total price: <fmt:formatNumber value="<%= totalPrice %>" type="currency"/>
                            </h5>

                            <form method="POST" action="checkout">
                                <div class="mt-5 w-75 mx-auto">
                                    <label class="form-label fw-bold" for="methodOfPayment">Method of payment:</label>
                                    <select class="form-select" id="methodOfPayment" name="methodOfPayment" required>
                                        <option selected disabled value="">Select method of payment</option>
                                        <option value="1">Cash</option>
                                        <option value="2">PayPal</option>
                                    </select>
                                </div>

                                <input type="submit" class="btn btn-outline-dark mt-4 mb-1"
                                       value="Proceed to checkout"/>
                            </form>
                        </li>
                    </ul>

                    <a href="product-list?id=<%= session.getAttribute("basket-categoryId") %>"
                       class="btn btn-outline-dark mt-5">
                        Back to products
                    </a>
                </c:when>
            </c:choose>
            <%
                }
            %>
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
