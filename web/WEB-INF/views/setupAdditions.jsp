<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: SC
  Date: 20.10.2017
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <p class="text-center bg-primary"><strong>Game setup</strong></p>
    <div class="row">
        <div class="col-lg-6">
            <form:form action="/admin/setup/saveAdditions" method="post" modelAttribute="gameAdditionsForm"
                       enctype="multipart/form-data">
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
                    <div class="row">
                        <div class="col-md-offset-5 col-md-2">
                            <button type="submit" class="btn btn-success">Save</button>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>




