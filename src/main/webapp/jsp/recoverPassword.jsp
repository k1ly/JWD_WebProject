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
    <title><fmt:message key="page.title.recover_password"/></title>
</head>
<body>
<div class="recover-page">
    <div class="recover-container">
        <c:if test="${sessionScope.errorMessage!=null}">
            <p class="errorMessage"><fmt:message key="${sessionScope.errorMessage}"/></p>
            ${sessionScope.remove(ControllerConstants.ERROR_MESSAGE)}
        </c:if>
        <form id="recover-form" action="${pageContext.request.contextPath}/index" method="post" autocomplete="off">
            <input type="hidden" name="${ControllerConstants.COMMAND}" value="${CommandName.RECOVER_PASSWORD}">
            <div style="padding: 16px;">
                <label for="login"><fmt:message key="recover.label.login"/></label>
                <input id="login" type="text" name="${CommandConstants.LOGIN}"
                       placeholder="<fmt:message key="register.label.enter_login"/>"
                       pattern="^[A-Za-z]\w{5,20}$"
                       title="<fmt:message key="register.title.login"/>" required/>
                <label for="password"><fmt:message key="recover.label.new_password"/></label>
                <div style="display: inline-block; width: 100%">
                    <input id="password" type="password" name="${CommandConstants.PASSWORD}"
                           placeholder="<fmt:message key="register.label.enter_password"/>"
                           pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$"
                           title="<fmt:message key="register.title.password"/>" required/>
                    <button id="show-password" type="button">👁</button>
                </div>
                <div id="password-check">
                    <h5><fmt:message key="register.label.requirement"/>:</h5>
                    <p id="letter" class="invalid"><fmt:message key="register.label.article"/>
                        <b><fmt:message key="register.label.lower"/></b> <fmt:message key="register.label.letter"/></p>
                    <p id="capital" class="invalid"><fmt:message key="register.label.article"/>
                        <b><fmt:message key="register.label.capital"/></b> <fmt:message key="register.label.letter"/>
                    </p>
                    <p id="number" class="invalid"><fmt:message key="register.label.article"/>
                        <b><fmt:message key="register.label.number"/></b></p>
                    <p id="length" class="invalid"><fmt:message key="register.label.minimum"/>
                        <b>8 <fmt:message key="register.label.characters"/></b></p>
                </div>
                <label for="repeat-psw"><fmt:message key="recover.label.repeat"/></label>
                <div style="display: inline-block; width: 100%">
                    <input id="repeat-psw" type="password" name="${CommandConstants.REPEAT_PASSWORD}"
                           placeholder="<fmt:message key="register.label.repeat"/>" required/>
                    <span id="repeat-check"></span>
                </div>
                <input id="reg-submit" type="submit" value="<fmt:message key="recover.button.recover"/>">
            </div>
        </form>
        <div class="recover-footer">
            <a class="cancel-recover" href="${pageContext.request.contextPath}">
                <fmt:message key="recover.link.cancel"/>
            </a>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>