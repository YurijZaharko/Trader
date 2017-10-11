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
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#gameType" aria-controls="home" role="tab" data-toggle="tab">Game
            type</a></li>
        <li role="presentation"><a href="#gameAdditions" aria-controls="profile" role="tab" data-toggle="tab">Game
            additions</a></li>
        <li role="presentation"><a href="#countries" aria-controls="messages" role="tab" data-toggle="tab">Countries</a>
        </li>
        <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">null</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="gameType">

            <p class="text-center bg-primary"><strong>Game setup</strong></p>
            <div class="row">
                <div class="col-md-6">
                    <form:form action="admin/gameType/save" method="post" modelAttribute="gameTypeForm" enctype="multipart/form-data">
                        <form class="form-inline">
                            <div class="form-group">
                                <form:hidden path="id"/>
                                <label for="gameName" >Game name</label>
                                <form:input path="gameName" id="gameName"/>
                            </div>
                        </form>
                        <form class="form-inline">
                            <div class="form-group">
                                <label for="gameName">Upload image</label>
                                <label class="btn btn-default btn-file">Browse <input type="file" name="multipartFile" style="display: none;"></label>
                            </div>
                        </form>
                        <form class="form-inline">
                            <div class="form-group">
                                <label for="countries">Countries</label>
                            </div>
                            <div class="form-group">
                                <form:checkboxes path="countries" items="${countries}"
                                                 itemValue="id"
                                                 itemLabel="fullName"
                                                 id="countries"/>
                            </div>
                        </form>
                        <form class="form-inline">
                            <div class="form-group">
                                <label for="gameAdditionsName">Game additions</label>
                                <div class="col-md-9">
                                    <form:checkboxes path="gameAdditions" items="${gameAdditions}"
                                                     itemValue="id"
                                                     itemLabel="gameAdditionsName"
                                                     id="gameAdditionsName"/>
                                </div>
                            </div>
                        </form>
                        <form class="form-inline">
                            <div class="form-group">
                                <button type="submit" class="btn btn-info">Save</button>
                            </div>
                        </form>
                    </form:form>
                </div>
                <div class="col-md-6">
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
                                    <td></td>
                                    <td>${game.gameName}</td>
                                    <td><a href="admin/game/edit/${game.id}" class="btn btn-info">Edit</a></td>
                                    <td><a href="admin/game/delete/${game.id}" class="btn btn-danger">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </table>

                        <div class="col-md-offset-5 col-md-2">
                            <custom:size posibleSizes="1,2,5,10" size="${games.size}" title="Page size"/>
                        </div>
                        <div class="col-md-offset-4 col-md-4 ">
                            <custom:pageable page="${games}" cell="<li></li>" container="<ul class='pagination'></ul>" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="gameAdditions">...</div>
        <div role="tabpanel" class="tab-pane" id="countries">...</div>
        <div role="tabpanel" class="tab-pane" id="settings">...</div>
    </div>

</div>
