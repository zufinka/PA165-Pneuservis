<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"
         session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="Texts"/>
<my:pagetemplate>
<jsp:attribute name="body">
    <form:form method="post" action="${pageContext.request.contextPath}/services/create"
               modelAttribute="serviceCreate">
        <div class="form-group ${name_error ? 'has-error' : ''}">
            <form:label path="name">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name"/>
            </div>
        </div>
        <div class="form-group ${serviceType_error ? 'has-error' : ''}">
            <form:label path="serviceType">Service Type</form:label>
            <div class="col-sm-10">
                <form:input path="serviceType" cssClass="form-control"/>
                <form:errors path="serviceType"/>
            </div>
        </div>
        <div class="form-group ${price_error ? 'has-error' : ''}">
            <form:label path="price">Price</form:label>
            <div class="col-sm-10">
                <form:input path="price" cssClass="form-control"/>
                <form:errors path="price"/>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Create</button>
    </form:form>
</jsp:attribute>
</my:pagetemplate>
