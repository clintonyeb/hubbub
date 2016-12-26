<%--
  Created by IntelliJ IDEA.
  User: clinton
  Date: 17/7/16
  Time: 12:06 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Search Results</title>
</head>

<body>
    <h1>Search Results</h1>
    <p>
    	Searched ${totalUsers} records for
    	items matching your query <em>${term}</em>
    	and found <strong>${selectedUsers.size()}</strong>
    	hits  
    </p>

    <ul>
    	<g:each var="user" in="${selectedUsers}">
    		<li>${user.userName}</li>
    	</g:each>
    </ul>
    <g:link action="search">Search again</g:link>
</body>
</html>