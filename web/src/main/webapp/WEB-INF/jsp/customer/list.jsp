<%--
  Created by IntelliJ IDEA.
  User: Zuzka
  Date: 13.01.2018
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="customer.list.title"/>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">

    <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="order.date"/></th>
            <th><fmt:message key="order.id"/></th>
            <th><fmt:message key="order.customer"/></th>
            <th><fmt:message key="order.price"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customer}" var="customer">
                <%--<tr>
                    <td><c:out value="${order.date.toString()}"/></td>
                    <td><a href="${pageContext.request.contextPath}/order/${order.id}"><c:out value="${order.id}"/></a></td>
                    <td><c:out value="${order.customer.name} ${order.customer.name}"/></td>
                    <td><c:out value="${order.getPrice()}"/></td>
                </tr>--%>
            </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
