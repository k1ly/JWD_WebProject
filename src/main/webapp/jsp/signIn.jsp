<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants"
         import="by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants"
         import="by.epamtc.lyskovkirill.restaurant.command.CommandName" %>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title><fmt:message key="page.title.sign_in"/></title>
</head>
<body>
<div class="auth-page">
    <div class="auth-container">
        <c:if test="${sessionScope.errorMessage!=null}">
            <p class="errorMessage"><fmt:message key="${sessionScope.errorMessage}"/></p>
            ${sessionScope.remove(ControllerConstants.ERROR_MESSAGE)}
        </c:if>
        <form action="${pageContext.request.contextPath}/index" method="post">
            <input type="hidden" name="${ControllerConstants.COMMAND}" value="${CommandName.SIGN_IN}">
            <div style="padding: 16px;">
                <label for="login"><fmt:message key="sign_in.label.login"/><span style="color: red">*</span></label>
                <input id="login" type="text" name="${CommandConstants.LOGIN}"
                       placeholder="<fmt:message key="sign_in.label.enter_login"/>"
                       pattern="^[A-Za-z]\w{5,20}$"
                       title="<fmt:message key="register.title.login"/>" required/>
                <label for="password"><fmt:message key="sign_in.label.password"/></label>
                <input id="password" type="password" name="${CommandConstants.PASSWORD}"
                       placeholder="<fmt:message key="sign_in.label.enter_password"/>"
                       pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$"
                       title="<fmt:message key="register.title.password"/>" required/>
                <input id="auth-submit" type="submit" value="<fmt:message key="sign_in.button.sign_in"/>">
                <div>
                    <label>
                        <input type="checkbox" name="${CommandConstants.REMEMBER}" checked="checked"> <fmt:message
                            key="sign_in.label.remember"/>
                    </label>
                </div>
            </div>
        </form>
        <div class="auth-footer">
            <a class="cancel-auth" href="${pageContext.request.contextPath}">
                <fmt:message key="sign_in.link.cancel"/>
            </a>
            <div class="auth-actions">
                <div>
                    <fmt:message key="sign_in.label.forget"/>
                    <a href="${pageContext.request.contextPath}/recover">
                        <fmt:message key="sign_in.link.password"/>?
                    </a>
                </div>
                <div>
                    <a href="${pageContext.request.contextPath}/register">
                        <fmt:message key="sign_in.link.register"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>