<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: SC
  Date: 15.09.2017
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://apis.google.com/js/platform.js" async defer></script>

<div class="container">

    <form:form action="/login" method="post">

        <div class="col-md-offset-3 col-md-6">
            <div class="form-horizontal">
                <div class="form-group">

                    <label for="inputEmail" class="col-sm-2 col-md-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input name="login" placeholder="Login" class="form-control" id="inputEmail"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPass" class="col-sm-2 col-md-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input name="password" type="password" placeholder="********" class="form-control" id="inputPass"/>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary center-block">Ok</button>
                <c:if test="${param.fail}">
                    <div class="col-md-12 col-xs-12">
                        <p style="color: red;">Fail!!!   ${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
                    </div>
                </c:if>

            </div>
        </div>
    </form:form>
</div>



