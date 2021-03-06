<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: SC
  Date: 20.10.2017
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <p class="text-center bg-primary"><strong>Game additions setup</strong></p>
    <div class="row">
        <div class="col-lg-6">
            <form:form action="/admin/gameAdditions/save" method="post" modelAttribute="gameAdditionsForm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-6">
                            <label for="gameAdditionsName">Game additions name</label>
                        </div>
                        <div class="col-md-6">
                            <form:hidden path="gameAdditionsId"/>
                            <form:input path="gameAdditionsName" id="gameAdditionsName"/>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 10pt">
                        <div class="col-md-offset-5 col-md-2">
                            <button type="submit" class="btn btn-success">Save</button>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
        <div class="col-lg-6">
            <div class="form-group">
                <table class="table">
                    <tr>
                        <th>Addition name</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items="${additions.content}" var="addition">
                        <tr>
                            <td>${addition.gameAdditionsName}</td>
                            <td><a href="/admin/addition/edit/${addition.id}" class="btn btn-info">Edit</a></td>
                            <td><a href="/admin/addition/delete/${addition.id}" class="btn btn-danger">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>

                <div class="col-md-offset-4 col-md-4">
                    <custom:size posibleSizes="1,2,5,10" size="${additions.size}" title="Page size "/>
                </div>
                <div class="col-md-offset-3 col-md-6 ">
                    <custom:pageable page="${additions}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
                </div>
            </div>
        </div>
    </div>
</div>




