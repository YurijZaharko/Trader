<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SC
  Date: 23.10.2017
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <p class="text-center bg-primary"><strong>Game additions setup</strong></p>
    <div class="row">
        <div class="col-lg-6">
            <form:form action="/admin/country/save" method="post" modelAttribute="gameCountryForm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="container-fluid">
                    <div class="row" style="padding-top: 10pt">
                        <div class="col-md-6">
                            <label>Country short name</label>
                        </div>
                        <div class="col-md-6">
                            <form:hidden path="countryId"/>
                            <form:errors path="shortName"/>
                            <form:input path="shortName"/>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 10pt">
                        <div class="col-md-6">
                            <label>Country full name</label>
                        </div>
                        <div class="col-md-6">
                            <form:errors path="fullName"/>
                            <form:input path="fullName"/>
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
                        <th>Short name</th>
                        <th>Full name</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items="${countries.content}" var="country">
                        <tr>
                            <td>${country.shortName}</td>
                            <td>${country.fullName}</td>
                            <td><a href="/admin/country/edit/${country.id}" class="btn btn-info">Edit</a></td>
                            <td><a href="/admin/country/delete/${country.id}" class="btn btn-danger">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>

                <div class="col-md-offset-4 col-md-4">
                    <custom:size posibleSizes="1,2,5,10" size="${countries.size}" title="Page size "/>
                </div>
                <div class="col-md-offset-3 col-md-6 ">
                    <custom:pageable page="${countries}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
                </div>
            </div>
        </div>
    </div>
</div>
