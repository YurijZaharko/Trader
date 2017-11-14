<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld" %>

<%--
  Created by IntelliJ IDEA.
  User: SC
  Date: 11.10.2017
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <p class="text-center bg-primary"><strong>Game setup</strong></p>
    <div class="row">
        <div class="col-lg-6">
            <form:form action="/admin/setup/saveGame" method="post" modelAttribute="gameTypeForm"
                       enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="container-fluid">
                    <div class="row" style="padding-top: 10pt">
                        <div class="col-md-6">
                            <label for="gameName">Game name</label>
                        </div>
                        <div class="col-md-6">
                            <form:hidden path="id"/>
                            <form:input path="gameName" id="gameName" size="30"/>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 10pt">
                        <div class="col-md-6">
                            <label for="countries">Countries</label>
                        </div>
                        <div class="col-md-6">
                            <form:checkboxes path="countriesId" items="${countries}" itemValue="id" itemLabel="fullName"
                                             id="countries" delimiter="<br/>"/>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 10pt">
                        <div class="col-md-6">
                            <label for="gameAdditionsName">Game additions</label>
                        </div>
                        <div class="col-md-6">
                            <form:checkboxes path="gameAdditionsId" items="${gameAdditionsList}" itemValue="id"
                                             itemLabel="gameAdditionsName" id="gameAdditionsName" delimiter="<br/>" />
                        </div>
                    </div>
                    <div class="row" style="padding-top: 10pt">
                        <div class="col-md-6">
                            <label for="gameName">Upload image</label>
                        </div>
                        <div class="col-md-6">
                            <label class="btn btn-default btn-file">Browse <input type="file"
                                                                                  name="multipartFile"
                                                                                  style="display: none;"></label>
                        </div>
                    </div>
                    <span class="divider"></span>
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
                        <th>Pic</th>
                        <th>Game name</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items="${games.content}" var="game">
                        <tr>
                            <td>.</td>
                            <td>${game.gameName}</td>
                            <td><a href="/admin/game/edit/${game.id}" class="btn btn-info">Edit</a></td>
                            <td><a href="/admin/game/delete/${game.id}" class="btn btn-danger">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>

                <div class="col-md-offset-4 col-md-4">
                    <custom:size posibleSizes="1,2,5,10" size="${games.size}" title="Page size "/>
                </div>
                <div class="col-md-offset-3 col-md-6 ">
                    <custom:pageable page="${games}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
                </div>
            </div>
        </div>
    </div>
</div>



