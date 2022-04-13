<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.mucnjakf.utilities.SecurityUtilities" %>
<%@ page import="org.mucnjakf.model.Invoice" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <title>User purchase history</title>
</head>
<body class="bg-secondary bg-opacity-50">

<%
    if (!SecurityUtilities.validateAdmin(request, response)) return;
    List<Invoice> userPurchaseHistory = (List<Invoice>) request.getAttribute("user-purchase-history-admin");
%>

<%@ include file="../navmenu.jsp" %>

<div class="container text-center my-5">
    <div class="card mx-auto shadow-lg p-3 mb-5 bg-body rounded" style="width: 64rem;">
        <div class="card-title my-3">
            <h1>User purchase history</h1>
        </div>
        <hr/>
        <div class="card-body">
            <c:choose>
                <c:when test="<%= userPurchaseHistory.isEmpty() %>">
                    <h3>There is no user purchase history.</h3>
                </c:when>
                <c:when test="<%= !userPurchaseHistory.isEmpty() %>">
                    <div class="input-group w-25 mb-4">
                        <span class="input-group-text">Search:</span>
                        <input type="text" class="form-control" id="search"/>
                    </div>

                    <table class="table table-stripped table-hover">
                        <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">User</th>
                            <th scope="col">Date</th>
                            <th scope="col">Time</th>
                            <th scope="col">Total price</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="table">
                        <%
                            for (Invoice uph : userPurchaseHistory) {
                        %>
                        <tr>
                            <th>
                                <%= uph.getId() %>
                            </th>
                            <td>
                                <a class="text-decoration-none"
                                   href="user-details-admin?id=<%= uph.getUser().getId() %>">
                                    <%= uph.getUser().getUsername() %>
                                </a>
                            </td>
                            <td>
                                <fmt:setLocale value="en_US"/>
                                <fmt:formatDate value="<%= uph.getDate() %>" dateStyle="full"/>
                            </td>
                            <td>
                                <%= uph.getTime().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss a")) %>
                            </td>
                            <td>
                                <fmt:setLocale value="en_US"/>
                                <fmt:formatNumber value="<%= uph.getTotalPrice() %>" type="currency"/>
                            </td>
                            <td style="width: 10rem;">
                                <a href="invoice-details-admin?id=<%= uph.getId() %>"
                                   class="btn btn-outline-dark">Details</a>
                            </td>
                        </tr>
                        <%
                            }
                        %>
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
