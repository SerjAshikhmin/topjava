<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="app.title"/></title>
    <base href="${pageContext.request.contextPath}/"/>

    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="webjars/bootstrap/4.4.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/noty/3.1.4/demo/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.20/css/dataTables.bootstrap4.min.css">
    <link rel="shortcut icon" href="resources/images/icon-meal.png">

    <%--http://stackoverflow.com/a/24070373/548473--%>
    <script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js" defer></script>
    <script type="text/javascript" src="webjars/bootstrap/4.4.0/dist/js/bootstrap.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.20/js/jquery.dataTables.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.20/js/dataTables.bootstrap4.min.js" defer></script>
</head>
