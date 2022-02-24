<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/vnd.microsoft.icon"
          href="${pageContext.request.contextPath}/static/favicon/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title><fmt:message key="page.title.penalized"/></title>
</head>
<body>
${sessionScope.remove(ControllerConstants.ACCESS_ALLOWED)}
<div style="display: flex; justify-content: center; align-items: center; width: 100vw; height: 100vh; background: white">
    <div style="display: flex; justify-content: center;">
        <div style="font-size: x-large; text-align: center"><fmt:message key="penalized.label.blocked"/>!</div>
        <div style="text-align: center"><fmt:message key="penalized.label.restriction"/>.</div>
        <div>
            <a style="background: #9f0606; color: white; font-size: 20px" href="${pageContext.request.contextPath}">
                <fmt:message key="penalized.link.okay"/>
            </a>
        </div>
    </div>
</div>
</body>
</html>
