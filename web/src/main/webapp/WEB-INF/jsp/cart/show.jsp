<%--
  Created by IntelliJ IDEA.
  User: 433372@mail.muni.cz
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="cart.show.title"/>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">

    <c:forEach items="${order.orderItems}" var="orderItem" varStatus="ic">
        <div class="row">
        <c:choose>
            <c:when test="${empty orderItem.tire}">
                <%--service part of the row (orderItem.service.name)--%>
            </c:when>
            <c:otherwise>
                <%--tire part of the row (orderItem.tire.name)--%>
            </c:otherwise>
        </c:choose>
            <%--show quantity in a input box (orderItem.quantity)--%>
            <%--show price--%>
        </div>
    </c:forEach>

    <%--actualize button (cart.show.actualize)--%>
    <%--total price--%>
    <%--buy button--%>

</jsp:attribute>
</my:pagetemplate>