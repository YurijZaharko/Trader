<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: SC
  Date: 16.09.2017
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <form:form action="/registration" method="post" modelAttribute="registrationForm">
        <div class="col-md-offset-3 col-md-6">
            <div class="form-horizontal">
                <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 col-md-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <form:errors path="email"/>
                        <form:input path="email" placeholder="Email" class="form-control" id="inputEmail"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="nickNameId" class="col-sm-2 col-md-2 control-label">Nick name</label>
                    <div class="col-sm-10">
                        <form:errors path="nickName"/>
                        <form:input path="nickName" placeholder="Nick name" class="form-control" id="nickNameId"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassId" class="col-sm-2 col-md-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <form:errors path="password"/>
                        <form:input path="password" type="password" placeholder="********" class="form-control"
                                    id="inputPassId"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassIdRepeat" class="col-sm-2 col-md-2 control-label">Repeat password</label>
                    <div class="col-sm-10">
                        <form:errors path="passwordRepeat"/>
                        <form:input path="passwordRepeat" type="password" placeholder="********" class="form-control"
                                    id="inputPassIdRepeat"/>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary center-block">Ok</button>
            </div>
        </div>
    </form:form>
</div>
