<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants"
         import="by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants"
         import="by.epamtc.lyskovkirill.restaurant.command.CommandName"
         import="by.epamtc.lyskovkirill.restaurant.bean.UserStatus" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title><fmt:message key="page.title.settings"/></title>
</head>
<body>
<div class="page-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="content-wrap">
        <div style="display: flex; justify-content: center; margin: 30px">
            <c:if test="${UserStatus.PENALIZED.equals(sessionScope.user.statusName)}">
                <div style="text-align: center"><fmt:message key="settings.label.restriction"/>.</div>
            </c:if>
            <c:if test="${!UserStatus.PENALIZED.equals(sessionScope.user.statusName)}">
                <form id="settings-form" action="${pageContext.request.contextPath}/index" method="post">
                    <input type="hidden" name="${ControllerConstants.COMMAND}" value="${CommandName.EDIT_SETTINGS}">
                    <table>
                        <tr>
                            <td><fmt:message key="register.label.login"/></td>
                            <td>${requestScope.userInfo.login}</td>
                        </tr>
                        <tr>
                            <td><label for="settings-password"><fmt:message key="register.label.password"/></label></td>
                            <td>
                                <input id="settings-password" type="password" name="${CommandConstants.PASSWORD}"
                                       placeholder="<fmt:message key="register.label.enter_password"/>"
                                       pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$"
                                       title="<fmt:message key="register.title.password"/>" disabled/>
                            </td>
                            <td>
                                <button type="button" id="change-password"><fmt:message
                                        key="settings.button.edit"/></button>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="settings-name"><fmt:message key="register.label.name"/></label></td>
                            <td>
                                <input id="settings-name" type="text" name="${CommandConstants.NAME}"
                                       value="${requestScope.userInfo.name}"
                                       placeholder="<fmt:message key="register.label.enter_name"/>"
                                       pattern="^(\p{L})+([. '-](\p{L})+)*$"
                                       title="<fmt:message key="register.title.password"/>" disabled/>
                            </td>
                            <td>
                                <button type="button" id="change-name"><fmt:message key="settings.button.edit"/></button>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="settings-email"><fmt:message key="register.label.email"/></label></td>
                            <td>
                                <input id="settings-email" type="text" name="${CommandConstants.EMAIL}"
                                       value="${requestScope.userInfo.email}"
                                       placeholder="<fmt:message key="register.label.enter_email"/>"
                                       pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,6}$"
                                       title="<fmt:message key="register.title.email"/>" disabled/>
                            </td>
                            <td>
                                <button type="button" id="change-email"><fmt:message key="settings.button.edit"/></button>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="settings-phone"><fmt:message key="register.label.phone"/></label></td>
                            <td>
                                <input id="settings-phone" type="text" name="${CommandConstants.PHONE}"
                                       value="${requestScope.userInfo.phone}"
                                       placeholder="<fmt:message key="register.label.enter_phone"/>"
                                       pattern="^(\+\d{1,3}( )?)?((\(\d{1,3}\))|\d{1,3})[- .]?\d{3,4}[- .]?\d{4}$"
                                       title="<fmt:message key="register.title.phone"/>" disabled/>
                            </td>
                            <td>
                                <button type="button" id="change-phone"><fmt:message key="settings.button.edit"/></button>
                            </td>
                        </tr>
                    </table>
                    <div id="settings-inputs" style="display: none; color: red;"><fmt:message
                            key="settings.label.warning"/></div>
                    <input style="margin: 0 auto" type="submit" value="<fmt:message key="settings.button.save"/>">
                </form>
            </c:if>
        </div>
    </div>
    <div id="back-to-top" class="on-top"><fmt:message key="page.button.up"/></div>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>