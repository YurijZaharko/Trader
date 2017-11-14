<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SC
  Date: 13.09.2017
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <div class="row">
        <form:form action="/find" method="get" modelAttribute="findIndexForm" cssClass="form-inline">
            <div class="form-group">
                <label for="search" class="sr-only">Search</label>
                <form:input path="search" class="form-control" id="search"/>
            </div>
            <button type="submit" class="btn btn-default" value="find">Search</button>
        </form:form>
    </div>
    <div class="row">
        <c:forEach items="${listGames}" var="oneGame">
            <div class="col-lg-3 col-md-3" style="padding-left: 0px;padding-right: 0px;">
                <div style="margin: 2pt" class="bg-info">
                    <p>
                        <a href="/game/show/${oneGame.id}"><span class="lead text-muted">${oneGame.gameName}</span></a>
                        <c:forEach items="${oneGame.countries}" var="country">
                            <sup>${country.shortName}</sup>
                        </c:forEach>
                    </p>

                        <c:forEach items="${oneGame.gameAdditions}" var="gameAddition">
                            <a href="/game/show/${oneGame.id}/addition/${gameAddition.id}">${gameAddition.gameAdditionsName}</a>
                        </c:forEach>

                </div>
            </div>
        </c:forEach>
    </div>
</div>
