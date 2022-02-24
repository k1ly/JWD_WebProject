<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants"
         import="by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants"
         import="by.epamtc.lyskovkirill.restaurant.command.CommandName"
         import="by.epamtc.lyskovkirill.restaurant.bean.UserRole"
         import="by.epamtc.lyskovkirill.restaurant.bean.UserStatus" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/dataView.tld" prefix="dv" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/vnd.microsoft.icon"
          href="${pageContext.request.contextPath}/static/favicon/favicon.ico"/>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title><fmt:message key="page.title.welcome"/>!</title>
</head>
<body>
<div class="page-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="content-wrap">
        <div style="font-size: large; text-align: center">
            <fmt:message key="welcome.label.welcome"/>
        </div>
        <div id="about">
            <div id="about-head" style="color: white; font-size: xxx-large; font-family: 'Arial Rounded MT Bold', sans-serif;">
                <fmt:message key="welcome.label.about"/>
            </div>
            <div id="about-text">${requestScope.aboutText}</div>
            <div id="gallery">
                <div class="gallery-item first-item"></div>
                <div class="gallery-item second-item"></div>
                <div class="gallery-item third-item"></div>
                <div class="gallery-item fourth-item"></div>
            </div>
        </div>
        <div id="reviews-container" style="margin: 15px">
            <c:if test="${!UserRole.GUEST.equals(sessionScope.user.roleName)
            && !UserStatus.PENALIZED.equals(sessionScope.user.statusName)}">
                <div style="margin: 15px">
                    <div>
                        <button id="add-review-btn" style="float: right"><fmt:message key="welcome.button.add_review"/></button>
                    </div>
                    <div id="add-review">
                        <input type="hidden" name="${ControllerConstants.COMMAND}" value="${CommandName.ADD_REVIEW}">
                        <input id="grade" type="hidden" name="${CommandConstants.GRADE}" value="0">
                        <div id="add-review-grade" class="review-grade">
                            <span class="star">&starf;</span>
                            <span class="star">&starf;</span>
                            <span class="star">&starf;</span>
                            <span class="star">&starf;</span>
                            <span class="star">&starf;</span>
                        </div>
                        <input id="comment" type="hidden" name="${CommandConstants.COMMENT}">
                        <div id="add-review-comment" contenteditable="true"></div>
                        <div style="display: flex; float: right;">
                            <button id="review-submit"><fmt:message key="welcome.button.add"/></button>
                            <button id="review-cancel"><fmt:message key="welcome.button.cancel"/></button>
                        </div>
                    </div>
                </div>
            </c:if>
            <div id="review-list" data-count="15">
                <div>
                    <ul class="pagination"></ul>
                    <div style="display: inline-block; float: right;">
                        <label for="sort-reviews"></label>
                        <select id="sort-reviews">
                            <option value="true"><fmt:message key="welcome.select.new_first"/></option>
                            <option value="false"><fmt:message key="welcome.select.old_first"/></option>
                        </select>
                    </div>
                </div>
                <table></table>
                <div id="review-list-empty"><fmt:message key="welcome.label.no_reviews"/></div>
            </div>
        </div>
    </div>
    <div id="back-to-top" class="on-top"><fmt:message key="page.button.up"/></div>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
