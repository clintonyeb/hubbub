<%--
  Created by IntelliJ IDEA.
  User: clinton
  Date: 23/7/16
  Time: 11:17 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register New User</title>
    <meta name="layout" content="main"/>
</head>

<body>
    <h2>Register New User</h2>
    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${user}" as="list" />
        </div>
    </g:hasErrors>

    <g:if test="${flash.message}">
        <div class="flash">${flash.message}</div>
    </g:if>

    <g:form action="register">
        <fieldset class="form">
            <div class="field required">
                <label for="userName">Username:</label>
                <g:textField name="userName" value="${user?.userName}"/>
            </div>
            <div class="field required">
                <label for="password">Password</label>
                <g:passwordField name="password" value="${user?.password}" />
            </div>
            <div class="field required">
                <label for="fullName">Full Name:</label>
                <g:textField name="fullName" value="${user?.profile?.fullName}"/>
            </div>
            <div class="field required">
                <label for="bio">Biography:</label>
                <g:textArea name="bio" value="${user?.profile?.bio}"/>
            </div>
            <div class="fieldcontain required">
                <label for="email">Email:</label>
                <g:textField name="email" value="${user?.profile?.email}"/>
            </div>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="register" value="Register"/>
        </fieldset>
    </g:form>
</body>
</html>