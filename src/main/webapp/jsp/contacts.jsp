<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title><fmt:message key="page.title.contacts"/></title>
</head>
<body>
<div class="page-container">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div style="display: flex; justify-content: center; align-items: center;" class="content-wrap">
        <div class="contacts">
            <div style="font-size: xx-large"><fmt:message key="contacts.label.contacts"/></div>
            <div style="font-size: large; margin-top: 30px">
                <div><fmt:message key="contacts.label.address"/></div>
                <c:forEach var="address" items="${requestScope.contacts.addressList}">
                    <div>${address.country} ${address.locality} ${address.street} ${address.house}</div>
                </c:forEach>
                <div><fmt:message key="contacts.label.email"/></div>
                <c:forEach var="email" items="${requestScope.contacts.emailList}">
                    <div>${email}</div>
                </c:forEach>
                <div><fmt:message key="contacts.label.phone"/></div>
                <c:forEach var="phone" items="${requestScope.contacts.phoneList}">
                    <div>${phone}</div>
                </c:forEach>
                <div><fmt:message key="contacts.label.work_time"/></div>
                <c:forEach var="workTime" items="${requestScope.contacts.workTimeList}">
                    <div>${workTime.dayOfWeek} ${workTime.from} - ${workTime.to}</div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div id="back-to-top" class="on-top"><fmt:message key="page.button.up"/></div>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
