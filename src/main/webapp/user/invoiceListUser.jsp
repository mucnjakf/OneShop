<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.mucnjakf.model.Invoice" %>
<%@ page import="java.util.List" %>
<%@ page import="org.mucnjakf.utilities.SecurityUtilities" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <title>Invoice list</title>
</head>
<body class="bg-secondary bg-opacity-50">

<%
    if (!SecurityUtilities.validateUser(request, response)) return;
    List<Invoice> invoices = (List<Invoice>) request.getAttribute("invoice-list-user");
%>

<%@ include file="../navmenu.jsp" %>

<div class="container text-center my-5">
    <div class="card mx-auto shadow-lg p-3 mb-5 bg-body rounded" style="width: 64rem;">
        <div class="card-title my-3">
            <h1>Invoice list</h1>
        </div>
        <hr/>
        <div class="card-body">
            <c:choose>
                <c:when test="<%= invoices.isEmpty() %>">
                    <h3>There are no invoices.</h3>
                </c:when>
                <c:when test="<%= !invoices.isEmpty() %>">
                    <div class="input-group w-25 mb-4">
                        <span class="input-group-text">Search:</span>
                        <input type="text" class="form-control" id="search"/>
                    </div>

                    <table class="table table-stripped table-hover">
                        <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Date</th>
                            <th scope="col">Time</th>
                            <th scope="col">Total price</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="table">
                        <%
                            for (Invoice invoice : invoices) {
                        %>
                        <tr>
                            <th>
                                <%= invoice.getId() %>
                            </th>
                            <td>
                                <fmt:setLocale value="en_US"/>
                                <fmt:formatDate value="<%= invoice.getDate() %>" dateStyle="full"/>
                            </td>
                            <td>
                                <%= invoice.getTime().toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss a")) %>
                            </td>
                            <td>
                                <fmt:setLocale value="en_US"/>
                                <fmt:formatNumber value="<%= invoice.getTotalPrice() %>" type="currency"/>
                            </td>
                            <td class="w-25">
                                <a href="invoice-details-user?id=<%= invoice.getId() %>"
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

<script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>

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
