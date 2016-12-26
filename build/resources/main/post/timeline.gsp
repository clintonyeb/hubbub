<%--
  Created by IntelliJ IDEA.
  User: clinton
  Date: 19/7/16
  Time: 7:54 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Timeline: ${user.profile ? user.profile.fullName : user.userName}</title>
    <meta content="main" name="layout" >
</head>

<body>
    <h1>Timeline for ${user.profile ? user.profile.fullName : user.userName}</h1>
    <div id="new-post">
        <g:form action="addPost" id="${params.id}">
            What is ${user.profile ? user.profile.fullName : user.userName} hacking on now?
            <g:textArea id="post-content" name="content" rows="3" cols="50" /><br/>
            <g:submitButton name="post" value="Post" />
            <g:if test="${flash.message}">
                <div class="flash">
                    ${flash.message}
                </div>
            </g:if>
        </g:form>
    </div>
    <div id="all-posts">
        <g:each var="post" in="${user.posts}">
            <h4 class="post-content">${post.content}</h4>
            <p class="post-date">On ${post.dateCreated}</p>
        </g:each>
    </div>
</body>
</html>