<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants" %>
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
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <title><fmt:message key="page.title.menu"/></title>
</head>
<body>
<div class="page-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="content-wrap">
        <div class="menu-grid-wrapper">
            <div class="sidebar">
                <c:forEach var="category" items="${requestScope.categoryList}">
                    <a <c:if test="${category.id==requestScope.category.id}"> class="active" </c:if>
                            href="?category=${category.id}">${category.name}</a>
                </c:forEach>
            </div>
            <div id="menu-content">
                <div class="category-name">${requestScope.category.name}</div>
                <form style="padding: 10px;" method="get">
                    <ul class="pagination" data-page="${requestScope.page}"
                        data-page-count="${requestScope.pageCount}" data-indent="2">
                    </ul>
                    <div style="display: inline-block; float: right;">
                        <label for="sort-menu-attr"><fmt:message key="menu.label.order_by"/></label>
                        <select name="${CommandConstants.ORDER}" id="sort-menu-attr">
                            <option value="name" <c:if test="${'name'.equals(param.o)}">selected</c:if>>
                                <fmt:message key="menu.select.name"/>
                            </option>
                            <option value="price" <c:if test="${'price'.equals(param.o)}">selected</c:if>>
                                <fmt:message key="menu.select.price"/>
                            </option>
                            <option value="discount" <c:if test="${'discount'.equals(param.o)}">selected</c:if>>
                                <fmt:message key="menu.select.discount"/>
                            </option>
                        </select>
                        <label for="sort-menu"></label>
                        <select name="${CommandConstants.ASC}" id="sort-menu">
                            <option value="true" <c:if test="${'true'.equals(param.asc)}">selected</c:if>>
                                <fmt:message key="menu.select.order_asc"/>
                            </option>
                            <option value="false" <c:if test="${'false'.equals(param.asc)}">selected</c:if>>
                                <fmt:message key="menu.select.order_desc"/>
                            </option>
                        </select>
                    </div>
                </form>
                <c:if test="${requestScope.dishList.size()==0}">
                    <div id="menu-empty" class="menu-grid-content">
                        <fmt:message key="menu.label.empty_menu"/> :(
                    </div>
                </c:if>
                <dv:menuViewer dishList="${requestScope.dishList}"/>
            </div>
        </div>
    </div>
    <div id="back-to-top" class="on-top"><fmt:message key="page.button.up"/></div>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
