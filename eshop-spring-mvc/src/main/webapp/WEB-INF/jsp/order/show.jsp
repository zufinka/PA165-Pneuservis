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

<fmt:message var="title" key="order.show.title"/>
<my:pagetemplate title="${title} + ${order.id}">
<jsp:attribute name="body">

    <c:forEach items="${order.orderItems}" var="orderItem" varStatus="ic">
        <div class="row">
        <c:choose>
            <c:when test="${empty orderItem.tire}">
                <%--service part of the row (orderItem.service.name)--%>
                <%--save price somehow--%>
            </c:when>
            <c:otherwise>
                <%--tire part of the row (orderItem.tire.name)--%>
                <%--save price somehow--%>
            </c:otherwise>
        </c:choose>
            <%--show quantity here (orderItem.quantity)--%>
            <%--show quantity * saved price, save it to total price--%>
        </div>
    </c:forEach>

    <%--show total price--%>

</jsp:attribute>
</my:pagetemplate>