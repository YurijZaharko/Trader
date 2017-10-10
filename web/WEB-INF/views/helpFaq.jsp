<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SC
  Date: 04.10.2017
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <div class="form-group">
                <form:form action="/save" modelAttribute="textForm" method="post">
                    <div class="form-group">
                        <form:errors id="templateName"/>
                        <form:label path="templateName">Template name</form:label>
                        <form:input path="templateName" id="templateName"/>
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="control-label">Message:</label>
                        <form:textarea path="mainText" class="form-control" id="message-text" rows="10" cols="10"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" role="button" class="btn btn-success">Save template</button>
                    </div>
                </form:form>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <table class="table">
                    <tr>
                        <th>File name</th>
                        <th>Show file</th>
                        <th>Delete file</th>
                    </tr>
                    <c:forEach items="${listFiles}" var="fileName">
                        <tr>
                            <td>${fileName}</td>
                            <td><a href="/showFile/${fileName}" class="btn btn-info">Show file</a></td>
                            <td><a href="/deleteFile/${fileName}" class="btn btn-danger">Delete file</a></td>
                        </tr>

                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
