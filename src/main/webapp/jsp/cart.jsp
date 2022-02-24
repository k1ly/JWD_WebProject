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
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title><fmt:message key="page.title.cart"/></title>
</head>
<body>
<div class="page-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="content-wrap">
        <c:if test="${sessionScope.errorMessage!=null}">
            <p class="errorMessage"><fmt:message key="${sessionScope.errorMessage}"/></p>
            ${sessionScope.remove(ControllerConstants.ERROR_MESSAGE)}
        </c:if>
        <div id="cart-empty"><fmt:message key="cart.label.empty_cart"/> :(</div>
        <c:if test="${requestScope.orderItemList.size() > 0}">
            <div id="cart">
                <c:if test="${requestScope.cartItemList.size()>0}">
                    <table>
                        <c:forEach var="cartItem" items="${requestScope.orderItemList}">
                            <tr class="cart-item" data-id="${cartItem.dish.id}"
                                data-price="${cartItem.dish.price}" data-discount="${cartItem.dish.discount}"
                                data-quantity="${cartItem.quantity}">
                                <td>
                                    <div style="width:100px; height: 100px">
                                        <img class="dish-image" src="${cartItem.dish.image}"/>
                                    </div>
                                </td>
                                <td>
                                    <div class="cart-dish-name">${cartItem.dish.name}</div>
                                </td>
                                <td>
                                    <div class="cart-dish-price">${cartItem.dish.price}&dollar;</div>
                                </td>
                                <td>
                                    <ul class="cart-item-actions">
                                        <li>
                                            <div>
                                                <button class="cart-minus" style="background-color: red">-</button>
                                            </div>
                                        </li>
                                        <li>
                                            <div>
                                                <input class="cart-item-quantity" style="width: 50px" type="text"
                                                       pattern="\d{1,3}"
                                                       placeholder="quantity" value="${cartItem.quantity}"/>
                                            </div>
                                        </li>
                                        <li>
                                            <div>
                                                <button class="cart-plus" style="background-color: red">+</button>
                                            </div>
                                        </li>
                                    </ul>
                                </td>
                                <td>
                                    <span class="cart-delete">&times;</span>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
            <button id="checkout"><fmt:message key="cart.button.confirm_order"/></button>
            <c:if test="${UserRole.GUEST.equals(sessionScope.user.roleName)}">
                <div id="register-window" class="modal-window">
                    <div class="register-content">
                        <span id="close-register" class="close-modal-window">&times;</span>
                        <div style="margin: 10px">
                            <div><fmt:message key="cart.label.register"/></div>
                            <a href="${pageContext.request.contextPath}/register?ref=cart">
                                <fmt:message key="cart.link.register"/>
                            </a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${!UserRole.GUEST.equals(sessionScope.user.roleName)}">
                <div id="checkout-window" class="modal-window">
                    <div class="checkout-content">
                        <span id="close-checkout" class="close-modal-window">&times;</span>
                        <div>
                            <div id="checkout-info" style="margin: 10px">
                                <div><fmt:message key="cart.label.final_sum"/>:</div>
                                <div id="total-price">0</div>
                            </div>
                            <form action="${pageContext.request.contextPath}/index" method="post">
                                <input type="hidden" name="${ControllerConstants.COMMAND}"
                                       value="${CommandName.CONFIRM_ORDER}">
                                <div style="margin: 10px">
                                    <label for="required-date"><fmt:message key="cart.label.required_date"/></label>
                                    <input id="required-date" type="datetime-local"
                                           name="${CommandConstants.REQUIRED_DATE}">
                                </div>
                                <div style="margin: 10px">
                                    <input style="background-color: orangered; color: white; width: auto"
                                           id="confirm-order" type="submit"
                                           value="<fmt:message key="cart.button.confirm"/>">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:if>
    </div>
    <div id="back-to-top" class="on-top"><fmt:message key="page.button.up"/></div>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>