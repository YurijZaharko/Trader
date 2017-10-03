<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: SC
  Date: 13.09.2017
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-fluid">
    <div class="container">
        <div class="row">
            <div class="col-md-offset-8 col-md-3">
                <security:authorize access="!isAuthenticated()">
                    <a href="/login" class="btn btn-success" role="button">Login</a>
                    <a href="/registration" class="btn btn-success" role="button">Registration</a>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <security:authentication property="principal" var="user"/>
                    ${user.username}
                    <form:form action="/logout" method="post"
                               class="navbar-form navbar-right">
                        <button type="submit" class="btn btn-default">Logout</button>
                    </form:form>
                </security:authorize>
            </div>
        </div>
    </div>
</div>
