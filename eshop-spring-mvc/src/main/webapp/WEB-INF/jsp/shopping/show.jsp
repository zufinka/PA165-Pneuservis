<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="shopping.show.title"/>
<my:pagetemplate title="${title}">
    <jsp:attribute name="body">
        <div class="col-md-8">
            <c:forEach items="${manufacturers}" var="manuf" varStatus="ic">
                <h2>${ic.count}. <c:out value="${manuf.name}"/></h2>

                <div class="row">
                    <c:choose>
                        <c:when test="${empty tiresByManufs[manuf]}">
                            <div class="col-xs-12">
                                This category is empty.
                            </div>
                        </c:when>

                        <c:otherwise>
                            <c:forEach items="${tiresByManufs[manuf]}" var="tire">
                                <div class="col-md-4 ">
                                    <a href="${pageContext.request.contextPath}/shopping/product/${tire.id}">
                                        <div class="thumbnail">
                                            <img src="${tire.imageUrl}"><br>
                                            <div class="caption">
                                                <h3><c:out value="${tire.name}"/></h3>
                                                <span style="color: red; font-weight: bold;"><c:out value="${tire.price}"/>&nbsp;</span>
                                                <%--<p><a href="#" class="btn btn-primary" role="button">Detail</a>--%>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>

            </c:forEach>
        </div>
        <h2>Filter</h2>
        <div class="col-md-4">
            <p align="left"><c:out value="vehicle type: "/>
                <select id="s_vehicle">
                    <option selected></option>
                    <c:forEach var="item" items="${vehicle}">
                        <option>${item}</option>
                    </c:forEach>
                </select>
            </p>
            <p align="left"><c:out value="width: "/>
                <select id="s_width">
                    <option selected></option>
                    <c:forEach var="item" items="${width}">
                        <option>${item}</option>
                    </c:forEach>
                </select>
            </p>
            <p align="left"><c:out value="aspect ratio: "/>
                <select id="s_aspectRatio">
                    <option selected></option>
                    <c:forEach var="item" items="${aspectRatio}">
                        <option>${item}</option>
                    </c:forEach>
                </select>
            </p>
            <p align="left"><c:out value="diameter: "/>
                <select id="diameter">
                    <option selected></option>
                    <c:forEach var="item" items="${diameter}">
                        <option>${item}</option>
                    </c:forEach>
                </select>
            </p>
            <p align="left"><c:out value="load index: "/>
                <select id="loadIndex">
                    <option selected></option>
                    <c:forEach var="item" items="${loadIndex}">
                        <option>${item}</option>
                    </c:forEach>
                </select>
            </p>
            <p align="left"><c:out value="speed class: "/>
                <select id="s_speed">
                    <option selected></option>
                    <c:forEach var="item" items="${speed}">
                        <option>${item}</option>
                    </c:forEach>
                </select>
            </p>
            <p align="left"><c:out value="season: "/>
                <select id="s_season">
                    <option selected></option>
                    <c:forEach var="item" items="${season}">
                        <option value="${item}">${item}</option>
                    </c:forEach>
                </select>
            </p>
            <p align="left"><c:out value="manufacturer: "/>
                <select id="s_season">
                    <option selected></option>
                    <c:forEach var="item" items="${manufacturers}">
                        <option value="${item.name}">${item.name}</option>
                    </c:forEach>
                </select>
            </p>

            <!-- repair this crap -->
            <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/shopping/filter/"
                  role="button">Filter</a></p>        

        </div>

    </jsp:attribute>
</my:pagetemplate>