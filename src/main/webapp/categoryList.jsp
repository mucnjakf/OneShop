<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.mucnjakf.model.Category" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <title>Category list</title>
</head>
<body class="bg-secondary bg-opacity-50">

<%
    List<Category> categories = (List<Category>) request.getAttribute("category-list");
%>

<%@ include file="navmenu.jsp" %>

<div class="container text-center my-5">
    <div class="card mx-auto shadow-lg p-3 mb-5 bg-body rounded" style="width: 64rem;">
        <div class="card-title my-3">
            <h1>Category list</h1>
        </div>
        <hr/>
        <div class="card-body">
            <c:choose>
                <c:when test="<%= categories.isEmpty() %>">
                    <h3>There are no categories.</h3>
                </c:when>
                <c:when test="<%= !categories.isEmpty() %>">
                    <ul class="list-group">
                        <c:forEach var="category" items="<%= categories %>">
                            <a href="product-list?id=${category.id}" class="list-group-item text-decoration-none">
                                <h4 class="fw-bold">${category.name}</h4>
                            </a>
                        </c:forEach>
                    </ul>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
        crossorigin="anonymous"></script>
</body>
</html>
