<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: SC
  Date: 20.10.2017
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<form:form  action="/admin/testJsp" method="post" modelAttribute="testForm">
    <form:checkboxes path="gameAdditionsSet" items="${testAdd}" itemValue="id" itemLabel="gameAdditionsName"/>
    <button type="submit" class="btn btn-primary">Ok</button>
</form:form>

