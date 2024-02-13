<%@ page import="org.mucnjakf.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-dark bg-dark shadow-lg">
    <div class="container-fluid my-1">
        <a href="index.jsp" class="navbar-brand mx-3">OneShop</a>
        <div class="d-flex">
            <%
                User userNav = (User) session.getAttribute("user");
            %>

            <a href="category-list" class="btn btn-outline-light mx-1">Browse</a>
            <a href="basket" class="btn btn-outline-light mx-1">Basket</a>

            <c:choose>
                <c:when test="<%= userNav != null%>">
                    <a href="invoice-list-user" class="btn btn-outline-light mx-1">Invoices</a>

                    <c:if test="<%= userNav.getIsAdmin()%>">
                        <a href="category-list-admin" class="btn btn-outline-light mx-1">Categories</a>
                        <a href="product-list-admin" class="btn btn-outline-light mx-1">Products</a>
                        <a href="user-purchase-history-admin" class="btn btn-outline-light mx-1">User purchase history</a>
                        <a href="user-login-history-admin" class="btn btn-outline-light mx-1">User login history</a>
                    </c:if>

                    <a href="account-details" class="btn btn-outline-light mx-1">
                        Account: <%= userNav.getUsername() %>
                    </a>
                </c:when>
                <c:when test="<%= userNav == null%>">
                    <a href="account-login" class="btn btn-outline-light mx-1">Login</a>
                    <a href="account-register" class="btn btn-outline-light mx-1">Register</a>
                </c:when>
            </c:choose>
        </div>
    </div>
</nav>
