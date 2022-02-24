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
    <title><fmt:message key="page.title.account_orders"/></title>
</head>
<body>
<div class="page-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="content-wrap">
        <div class="top-nav">
            <a href="${pageContext.request.contextPath}/account/profile">
                <fmt:message key="account.link.profile"/>
            </a>
            <a class="active" href="${pageContext.request.contextPath}/account/orders">
                <fmt:message key="account.link.orders"/>
            </a>
        </div>
        <div class="ao-grid-wrapper">
            <div class="sidebar">
                <a style="background-color: #ccc8c8" <c:if
                        test="${requestScope.tab=='awaiting'}"> class="active" </c:if> href="?tab=awaiting">
                    <fmt:message key="orders.link.awaiting"/>
                </a>
                <a style="background-color: #eeeeb0" <c:if
                        test="${requestScope.tab=='preparing'}"> class="active" </c:if> href="?tab=preparing">
                    <fmt:message key="orders.link.preparing"/>
                </a>
                <a style="background-color: #b7efc0" <c:if test="${requestScope.tab=='ready'}"> class="active" </c:if>
                   href="?tab=ready">
                    <fmt:message key="orders.link.ready"/>!
                </a>
                <a style="background-color: #de3434" <c:if
                        test="${requestScope.tab=='not_paid'}"> class="active" </c:if> href="?tab=not_paid">
                    <fmt:message key="orders.link.not_paid"/>
                </a>
                <a style="background-color: #b1ceee" <c:if
                        test="${requestScope.tab=='finished'}"> class="active" </c:if> href="?tab=finished">
                    <fmt:message key="orders.link.finished"/>
                </a>
            </div>
            <div id="ao-content">
                <form method="get">
                    <ul class="pagination" data-page="${requestScope.page}"
                        data-page-count="${requestScope.pageCount}" data-indent="2">
                    </ul>
                </form>
                <c:if test="${requestScope.orderList.size()==0}">
                    <div id="order-history-empty"><fmt:message key="account.label.empty_history"/></div>
                </c:if>
                <c:if test="${requestScope.orderList.size()>0}">
                    <table>
                        <c:forEach var="order" items="${requestScope.orderList}">
                            <c:if test="${requestScope.tab.equals('awaiting')}">
                                <tr class="awaiting order" style="padding:10px; border-bottom: 1px #545454 solid"
                                    data-id="${order.id}">
                                    <td>${order.orderDate}</td>
                                    <td>${order.status.name}</td>
                                    <td>${order.totalPrice}&dollar;</td>
                                    <td>
                                        <button class="cancel-order" style="float: right"><fmt:message key="account.button.cancel"/></button>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${!requestScope.tab.equals('awaiting')}">
                                <tr class="order" style="padding:10px; border-bottom: 1px #545454 solid">
                                    <td>${order.orderDate}</td>
                                    <td>${order.status.name}</td>
                                    <td>${order.totalPrice}&dollar;</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
    </div>
    <div id="back-to-top" class="on-top"><fmt:message key="page.button.up"/></div>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>