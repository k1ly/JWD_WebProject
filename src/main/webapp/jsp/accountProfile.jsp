<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
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
    <title><fmt:message key="page.title.account_profile"/></title>
</head>
<body>
<div class="page-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="content-wrap">
        <div class="top-nav">
            <a class="active" href="${pageContext.request.contextPath}/account/profile">
                <fmt:message key="account.link.profile"/>
            </a>
            <a href="${pageContext.request.contextPath}/account/orders">
                <fmt:message key="account.link.orders"/>
            </a>
        </div>
        <div style="display: flex; justify-content: center; margin: 30px">
            <div>
                <table>
                    <tr>
                        <td><fmt:message key="account.label.login"/></td>
                        <td>${requestScope.userInfo.login}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="account.label.name"/></td>
                        <td>${requestScope.userInfo.name}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="account.label.email"/></td>
                        <td>${requestScope.userInfo.email}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="account.label.phone"/></td>
                        <td>${requestScope.userInfo.phone}</td>
                    </tr>
                </table>
                <a href="${pageContext.request.contextPath}/settings" style="color: white">
                    <fmt:message key="account.link.settings"/>
                </a>
            </div>
        </div>
    </div>
    <div id="back-to-top" class="on-top"><fmt:message key="page.button.up"/></div>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>