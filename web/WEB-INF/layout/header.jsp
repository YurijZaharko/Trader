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
<div class="navbar navbar-default">
    <div class="container-fluid">
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <div class="dropdown">
                        <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenu1"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            Help
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="/help/1">Link</a></li>
                            <li><a href="/help/2">Link</a></li>
                            <li><a href="/help/3">Link</a></li>

                        </ul>
                    </div>
                </div>
                <div class="col-md-offset-6 col-md-3">
                    <security:authorize access="!isAuthenticated()">
                        <div class="form-inline">
                            <a href="/login" class="btn btn-success" role="button">Login</a>
                            <a href="/registration" class="btn btn-success" role="button">Registration</a>
                        </div>
                    </security:authorize>
                </div>
                <div class="col-md-offset-6 col-md-4">
                    <div class="form-inline">
                        <security:authorize access="isAuthenticated()">
                            <security:authentication property="principal" var="user"/>
                            <div class="form-group center-block">
                                    ${user.username}
                            </div>
                            <form:form action="/logout" method="post" class="form-group">
                                <button type="submit" class="btn btn-default">Logout</button>
                            </form:form>
                            <div class="form-group">
                                <security:authorize access="hasRole('ROLE_ADMIN')">
                                    <a href="/admin" class="btn btn-default" role="button">Admin</a>
                                </security:authorize>
                            </div>
                        </security:authorize>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
