<%-- From seminars edited by Jakub Palenik --%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="shopping.show.filter"/>
<my:pagetemplate title="${title}">
    <jsp:attribute name="body">

        <c:forEach items="${tires}" var="tire">
            <div class="col-md-4 ">
                <a href="${pageContext.request.contextPath}/shopping/product/${tire.id}">
                    <div class="thumbnail">
                        <img src="${tire.imageUrl}"><br>
                        <div class="caption">
                            <h3><c:out value="${tire.name}"/></h3>
                            <span style="color: red; font-weight: bold;"><c:out value="${tire.price}"/>&nbsp;</span>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
        <div id="col-md-12">
            <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/shopping/show/"
                  role="button">Back</a></p>  
        </div>
    </jsp:attribute>
</my:pagetemplate>