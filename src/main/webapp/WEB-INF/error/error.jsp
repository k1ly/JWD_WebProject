<%@ page import="java.io.PrintWriter" %>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/vnd.microsoft.icon"
          href="${pageContext.request.contextPath}/static/favicon/favicon.ico"/>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title>Error</title>
</head>
<body style="background-image: none">
<div style="display: flex; justify-content: center; align-items: center; width: 100%; height: 100%; text-align: center">
    <div>
        <h1>${pageContext.errorData.statusCode}</h1>
        <h1><fmt:message key="error.label.wrong"/>!</h1>
    </div>
</div>
<%--<%--%>
<%--    if (exception != null) {--%>
<%--        while (exception.getCause() != null)--%>
<%--            exception = exception.getCause();--%>
<%--        exception.printStackTrace(new PrintWriter(out));--%>
<%--    }--%>
<%--%>--%>
</body>
</html>
