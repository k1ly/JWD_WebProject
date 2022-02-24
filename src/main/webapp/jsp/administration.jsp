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
    <title><fmt:message key="page.title.administration"/></title>
</head>
<body>
<div class="page-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="content-wrap" style="width: 90%">
        <div class="admin-grid-wrapper">
            <div class="sidebar">
                <a <c:if test="${requestScope.tab=='dishes'}"> class="active" </c:if> href="?tab=dishes">
                    <fmt:message key="admin.link.dishes"/>
                </a>
                <a <c:if test="${requestScope.tab=='dish_categories'}"> class="active" </c:if>
                        href="?tab=dish_categories">
                    <fmt:message key="admin.link.categories"/>
                </a>
                <a <c:if test="${requestScope.tab=='users'}"> class="active" </c:if> href="?tab=users">
                    <fmt:message key="admin.link.users"/>
                </a>
            </div>
            <div>
                <c:if test="${requestScope.tab.equals(CommandConstants.DISH_TAB)}">
                    <div id="ad-content">
                        <c:if test="${sessionScope.errorMessage!=null}">
                            <p class="errorMessage"><fmt:message key="${sessionScope.errorMessage}"/></p>
                            ${sessionScope.remove(ControllerConstants.ERROR_MESSAGE)}
                        </c:if>
                        <form method="get">
                            <ul class="pagination" data-page="${requestScope.page}"
                                data-page-count="${requestScope.pageCount}" data-indent="2">
                            </ul>
                            <div style="display: inline-block; float: right;">
                                <label for="sort-dishes-attr"><fmt:message key="admin.label.order_by"/></label>
                                <select name="${CommandConstants.ORDER}" id="sort-dishes-attr">
                                    <option value="name" <c:if test="${'name'.equals(param.o)}">selected</c:if>>
                                        <fmt:message key="admin.select.name"/>
                                    </option>
                                    <option value="price" <c:if test="${'price'.equals(param.o)}">selected</c:if>>
                                        <fmt:message key="admin.select.price"/>
                                    </option>
                                    <option value="discount" <c:if test="${'discount'.equals(param.o)}">selected</c:if>>
                                        <fmt:message key="admin.select.discount"/>
                                    </option>
                                    <option value="dish_category_id"
                                            <c:if test="${'dish_category_id'.equals(param.o)}">selected</c:if>>
                                        <fmt:message key="admin.select.category"/>
                                    </option>
                                </select>
                                <label for="sort-dishes"></label>
                                <select name="${CommandConstants.ASC}" id="sort-dishes">
                                    <option value="true" <c:if test="${'true'.equals(param.asc)}">selected</c:if>>
                                        <fmt:message key="admin.select.order_asc"/>
                                    </option>
                                    <option value="false" <c:if test="${'false'.equals(param.asc)}">selected</c:if>>
                                        <fmt:message key="admin.select.order_desc"/>
                                    </option>
                                </select>
                            </div>
                        </form>
                        <div id="edit-dish-window" class="modal-window">
                            <div class="edit-dish-content">
                                <span id="close-edit-dish" class="close-modal-window">&times;</span>
                                <div>
                                    <form action="${pageContext.request.contextPath}/index" method="post"
                                          enctype="multipart/form-data">
                                        <input type="hidden" name="${ControllerConstants.COMMAND}" value="${CommandName.EDIT_USER}">
                                        <input id="edit-dish-id" type="hidden" name="${CommandConstants.DISH_ID}">
                                        <div style="display: flex; justify-content: center">
                                            <div>
                                                <div style="display: flex; justify-content: center"
                                                     class="image-preview">
                                                    <div class="preview-text"><fmt:message key="admin.label.drag"/></div>
                                                    <div class="preview-warning"></div>
                                                    <img class="preview-img"/>
                                                </div>
                                                <label for="edit-photo-upload"><fmt:message key="admin.label.load"/></label>
                                                <input id="edit-photo-upload" type="file" accept=".png, .jpg, .jpeg"
                                                       name="${CommandConstants.IMAGE}"
                                                       hidden data-disabled="true"/>
                                            </div>
                                        </div>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label for="edit-dish-name"><fmt:message key="admin.label.name"/></label>
                                                </td>
                                                <td>
                                                    <input id="edit-dish-name" type="text" name="${CommandConstants.NAME}"
                                                           pattern="^(\p{L})+([. '-](\p{L})+)*$" data-disabled="true"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label for="edit-dish-composition"><fmt:message key="admin.label.composition"/></label>
                                                </td>
                                                <td>
                                                    <textarea id="edit-dish-composition" name="${CommandConstants.COMPOSITION}"
                                                              data-disabled="true"></textarea>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label for="edit-dish-weight"><fmt:message key="admin.label.weight"/></label>
                                                </td>
                                                <td>
                                                    <input id="edit-dish-weight" type="text" name="${CommandConstants.WEIGHT}"
                                                           pattern="^\d{1,3}$" value="0"
                                                           data-disabled="true"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label for="edit-dish-price"><fmt:message key="admin.label.price"/></label>
                                                </td>
                                                <td>
                                                    <input id="edit-dish-price" type="text" name="${CommandConstants.PRICE}"
                                                           pattern="^\d{1,3}(.\d{1,2})?$" value="0"
                                                           data-disabled="true"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label for="edit-dish-discount"><fmt:message key="admin.label.discount"/></label>
                                                </td>
                                                <td>
                                                    <input id="edit-dish-discount" type="text" name="${CommandConstants.DISCOUNT}"
                                                           pattern="^\d{1,3}$" value="0"
                                                           data-disabled="true"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label for="edit-dish-category"><fmt:message key="admin.label.category"/></label>
                                                </td>
                                                <td>
                                                    <select id="edit-dish-category" name="${CommandConstants.CATEGORY_ID}"
                                                            data-disabled="true">
                                                    </select>
                                                </td>
                                            </tr>
                                        </table>
                                        <input id="edit-dish-submit" type="submit" value="<fmt:message key="admin.button.edit"/>">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <button id="add-dish" style="background-color: #07d36d; color: white"><fmt:message key="admin.button.add_dish"/></button>
                        <div id="add-dish-window" class="modal-window">
                            <div class="add-dish-content">
                                <span id="close-add-dish" class="close-modal-window">&times;</span>
                                <div>
                                    <form action="${pageContext.request.contextPath}/index" method="post"
                                          enctype="multipart/form-data">
                                        <input type="hidden" name="${ControllerConstants.COMMAND}" value="${CommandName.ADD_DISH}">
                                        <div style="display: flex; justify-content: center">
                                            <div>
                                                <div style="display: flex; justify-content: center"
                                                     class="image-preview">
                                                    <div class="preview-text"><fmt:message key="admin.label.drag"/></div>
                                                    <div class="preview-warning"></div>
                                                    <img class="preview-img"/>
                                                </div>
                                                <label for="add-photo-upload"><fmt:message key="admin.label.load"/></label>
                                                <input id="add-photo-upload" type="file" accept=".png, .jpg, .jpeg"
                                                       name="${CommandConstants.IMAGE}"
                                                       hidden required/>
                                            </div>
                                        </div>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label for="add-dish-name"><fmt:message key="admin.label.name"/></label>
                                                </td>
                                                <td>
                                                    <input id="add-dish-name" type="text" name="${CommandConstants.NAME}"
                                                           pattern="^(\p{L})+([. '-](\p{L})+)*$" required/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label for="add-dish-composition"><fmt:message key="admin.label.composition"/></label>
                                                </td>
                                                <td>
                                                    <textarea id="add-dish-composition" name="${CommandConstants.COMPOSITION}"
                                                              required></textarea>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label for="add-dish-weight"><fmt:message key="admin.label.weight"/></label>
                                                </td>
                                                <td>
                                                    <input id="add-dish-weight" type="text" name="${CommandConstants.WEIGHT}"
                                                           pattern="^\d{1,3}$" value="0"
                                                           required/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label for="add-dish-price"><fmt:message key="admin.label.price"/></label>
                                                </td>
                                                <td>
                                                    <input id="add-dish-price" type="text" name="${CommandConstants.PRICE}"
                                                           pattern="^\d{1,3}(.\d{1,2})?$" value="0"
                                                           required/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label for="add-dish-discount"><fmt:message key="admin.label.discount"/></label>
                                                </td>
                                                <td>
                                                    <input id="add-dish-discount" type="text" name="${CommandConstants.DISCOUNT}"
                                                           pattern="^\d{1,3}$" value="0"
                                                           required/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label for="add-dish-category"><fmt:message key="admin.label.category"/></label>
                                                </td>
                                                <td>
                                                    <select id="add-dish-category" name="${CommandConstants.CATEGORY_ID}" required>
                                                    </select>
                                                </td>
                                            </tr>
                                        </table>
                                        <input id="add-dish-submit" type="submit" value="<fmt:message key="admin.button.add"/>">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <c:if test="${requestScope.dishList.size()>0}">
                            <table>
                                <c:forEach var="dish" items="${requestScope.dishList}">
                                    <tr class="dish"
                                        style="padding:10px; border-bottom: 1px #545454 solid; background-color: white"
                                        data-id="${dish.id}">
                                        <td>${dish.id}</td>
                                        <td>${dish.name}</td>
                                        <td>${dish.category.name}</td>
                                        <td>${dish.price}</td>
                                        <td>
                                            <button class="edit-dish" style="float: right"><fmt:message key="admin.button.edit"/></button>
                                        </td>
                                        <td>
                                            <button class="delete-dish" style="float: right"><fmt:message key="admin.button.delete"/></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
                </c:if>
                <c:if test="${requestScope.tab.equals(CommandConstants.DISH_CATEGORY_TAB)}">
                    <div id="ac-content">
                        <c:if test="${sessionScope.errorMessage!=null}">
                            <p class="errorMessage"><fmt:message key="${sessionScope.errorMessage}"/></p>
                            ${sessionScope.remove(ControllerConstants.ERROR_MESSAGE)}
                        </c:if>
                        <div id="edit-category-window" class="modal-window">
                            <div class="edit-category-content">
                                <span id="close-edit-category" class="close-modal-window">&times;</span>
                                <div>
                                    <form action="${pageContext.request.contextPath}/index" method="post"
                                          enctype="multipart/form-data">
                                        <input type="hidden" name="${ControllerConstants.COMMAND}" value="${CommandName.EDIT_CATEGORY}">
                                        <input id="edit-category-id" type="hidden" name="${CommandConstants.CATEGORY_ID}">
                                        <table>
                                            <tr>
                                                <td>
                                                    <label for="edit-category-name"><fmt:message key="admin.label.name"/></label>
                                                </td>
                                                <td>
                                                    <input id="edit-category-name" type="text" name="${CommandConstants.NAME}"
                                                           pattern="^(\p{L})+([. '-](\p{L})+)*$" data-disabled="true"/>
                                                </td>
                                            </tr>
                                        </table>
                                        <input id="edit-category-submit" type="submit" value="<fmt:message key="admin.button.edit"/>">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <button id="add-category" style="background-color: #07d36d; color: white"><fmt:message key="admin.button.add_category"/></button>
                        <div id="add-category-window" class="modal-window">
                            <div class="add-category-content">
                                <span id="close-add-category" class="close-modal-window">&times;</span>
                                <div>
                                    <form action="${pageContext.request.contextPath}/index" method="post"
                                          enctype="multipart/form-data">
                                        <input type="hidden" name="${ControllerConstants.COMMAND}" value="${CommandName.ADD_CATEGORY}">
                                        <table>
                                            <tr>
                                                <td>
                                                    <label for="add-category-name"><fmt:message key="admin.label.name"/></label>
                                                </td>
                                                <td>
                                                    <input id="add-category-name" type="text" name="${CommandConstants.NAME}"
                                                           pattern="^(\p{L})+([. '-](\p{L})+)*$" required/>
                                                </td>
                                            </tr>
                                        </table>
                                        <input id="add-category-submit" type="submit" value="<fmt:message key="admin.button.add"/>">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <c:if test="${requestScope.categoryList.size()>0}">
                            <table>
                                <c:forEach var="category" items="${requestScope.categoryList}">
                                    <tr class="category"
                                        style="padding:10px; border-bottom: 1px #545454 solid; background-color: white"
                                        data-id="${category.id}">
                                        <td>${category.id}</td>
                                        <td>${category.name}</td>
                                        <td>
                                            <button class="edit-category" style="float: right"><fmt:message key="admin.button.edit"/></button>
                                        </td>
                                        <td>
                                            <button class="delete-category" style="float: right">><fmt:message key="admin.button.delete"/></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
                </c:if>
                <c:if test="${requestScope.tab.equals(CommandConstants.USER_TAB)}">
                    <div id="au-content">
                        <c:if test="${sessionScope.errorMessage!=null}">
                            <p class="errorMessage"><fmt:message key="${sessionScope.errorMessage}"/></p>
                            ${sessionScope.remove(ControllerConstants.ERROR_MESSAGE)}
                        </c:if>
                        <form method="get">
                            <ul class="pagination" data-page="${requestScope.page}"
                                data-page-count="${requestScope.pageCount}" data-indent="2">
                            </ul>
                            <div style="display: inline-block; float: right;">
                                <label for="sort-users-attr"><fmt:message key="admin.label.order_by"/></label>
                                <select name="${CommandConstants.ORDER}" id="sort-users-attr">
                                    <option value="name" <c:if test="${'name'.equals(param.o)}">selected</c:if>>
                                        <fmt:message key="admin.select.name"/>
                                    </option>
                                    <option value="user_role_id"
                                            <c:if test="${'user_role_id'.equals(param.o)}">selected</c:if>>
                                        <fmt:message key="admin.select.role"/>
                                    </option>
                                    <option value="user_status_id"
                                            <c:if test="${'user_status_id'.equals(param.o)}">selected</c:if>>
                                        <fmt:message key="admin.select.status"/>
                                    </option>
                                </select>
                                <label for="sort-users"></label>
                                <select name="${CommandConstants.ASC}" id="sort-users">
                                    <option value="true" <c:if test="${'true'.equals(param.asc)}">selected</c:if>>
                                        <fmt:message key="admin.select.order_asc"/>
                                    </option>
                                    <option value="false" <c:if test="${'false'.equals(param.asc)}">selected</c:if>>
                                        <fmt:message key="admin.select.order_desc"/>
                                    </option>
                                </select>
                            </div>
                        </form>
                        <div id="edit-user-window" class="modal-window">
                            <div class="edit-user-content">
                                <span id="close-edit-user" class="close-modal-window">&times;</span>
                                <div>
                                    <form action="${pageContext.request.contextPath}/index" method="post"
                                          enctype="multipart/form-data">
                                        <input type="hidden" name="${ControllerConstants.COMMAND}" value="${CommandName.EDIT_USER}">
                                        <input id="user-id" type="hidden" name="${CommandConstants.USER_ID}">
                                        <table>
                                            <tr>
                                                <td>
                                                    <label for="user-role"><fmt:message key="admin.label.role"/></label>
                                                </td>
                                                <td>
                                                    <select id="user-role" name="${CommandConstants.ROLE_ID}" data-disabled="true">
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label for="user-status"><fmt:message key="admin.label.status"/></label>
                                                </td>
                                                <td>
                                                    <select id="user-status" name="${CommandConstants.STATUS_ID}" data-disabled="true">
                                                    </select>
                                                </td>
                                            </tr>
                                        </table>
                                        <input id="edit-user-submit" type="submit" value="<fmt:message key="admin.button.edit"/>">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <c:if test="${requestScope.categoryList.size()>0}">
                            <table>
                                <c:forEach var="user" items="${requestScope.userList}">
                                    <tr class="user"
                                        style="padding:10px; border-bottom: 1px #545454 solid; background-color: white"
                                        data-id="${user.id}">
                                        <td>${user.id}</td>
                                        <td>${user.name}</td>
                                        <td>${user.email}</td>
                                        <td>${user.phone}</td>
                                        <td>${user.role.name}</td>
                                        <td>${user.status.name}</td>
                                        <td>
                                            <button class="edit-user" style="float: right"><fmt:message key="admin.button.edit"/></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
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