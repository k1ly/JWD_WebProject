<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants"
         import="by.epamtc.lyskovkirill.restaurant.bean.OrderStatus" %>
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
    <title><fmt:message key="page.title.manage_orders"/></title>
</head>
<body>
<div class="page-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="content-wrap">
        <div class="mo-grid-wrapper">
            <div class="sidebar">
                <a <c:if test="${requestScope.tab=='awaiting'}"> class="active" </c:if> href="?tab=awaiting">
                    <fmt:message key="orders.link.awaiting"/>
                </a>
                <a <c:if test="${requestScope.tab=='preparing'}"> class="active" </c:if> href="?tab=preparing">
                    <fmt:message key="orders.link.preparing"/>
                </a>
                <a <c:if test="${requestScope.tab=='ready'}"> class="active" </c:if> href="?tab=ready">
                    <fmt:message key="orders.link.ready"/>!
                </a>
                <a <c:if test="${requestScope.tab=='not_paid'}"> class="active" </c:if> href="?tab=not_paid">
                    <fmt:message key="orders.link.not_paid"/>
                </a>
                <a <c:if test="${requestScope.tab=='finished'}"> class="active" </c:if> href="?tab=finished">
                    <fmt:message key="orders.link.finished"/>
                </a>
            </div>
            <div id="mo-content">
                <c:if test="${sessionScope.errorMessage!=null}">
                    <p class="errorMessage"><fmt:message key="${sessionScope.errorMessage}"/></p>
                    ${sessionScope.remove(ControllerConstants.ERROR_MESSAGE)}
                </c:if>
                <form method="get">
                    <ul class="pagination" data-page="${requestScope.page}"
                        data-page-count="${requestScope.pageCount}" data-indent="2">
                    </ul>
                </form>
                <c:if test="${requestScope.orderList.size()==0}">
                    <div id="order-list-empty"><fmt:message key="manage.label.no_orders"/>!</div>
                </c:if>
                <c:if test="${requestScope.orderList.size()>0}">
                    <table>
                        <c:forEach var="order" items="${requestScope.orderList}">
                            <tr class="order" style="padding:10px; border-bottom: 1px #545454 solid"
                                data-id="${order.id}">
                                <td>${order.customer.name}</td>
                                <td>${order.totalPrice}&dollar;</td>
                                <td>${order.orderDate}</td>
                                <td>${order.requiredDate}</td>
                                <c:if test="${!OrderStatus.AWAITING.equals(order.status.name)}">
                                    <td>${order.manager.name}</td>
                                </c:if>
                                <c:if test="${OrderStatus.FINISHED.equals(order.status.name)}">
                                    <td>${order.deliveryDate}</td>
                                </c:if>
                                <c:if test="${requestScope.tab.equals('awaiting')}">
                                    <td>
                                        <button class="accept-order" style="float: right">
                                            <fmt:message key="manage.button.accept"/>
                                        </button>
                                    </td>
                                </c:if>
                                <c:if test="${requestScope.tab.equals('preparing')}">
                                    <td>
                                        <button class="ready-order" style="float: right">
                                            <fmt:message key="manage.button.ready"/>
                                        </button>
                                    </td>
                                </c:if>
                                <c:if test="${requestScope.tab.equals('ready')}">
                                    <td>
                                        <button class="finish-order" style="float: right">
                                            <fmt:message key="manage.button.finish"/>
                                        </button>
                                    </td>
                                    <td>
                                        <button class="deny-order" style="float: right">
                                            <fmt:message key="manage.button.not_paid"/>
                                        </button>
                                    </td>
                                </c:if>
                            </tr>
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