<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<title>home</title>
</head>

<body>
	<h2>Welcome to site</h2>
	<hr>
	<p>welcome</p>
	
	<!-- displaying user name and role -->
	<p>
	User: <security:authentication property="principal.username"/>
	<br>
	Roles: <security:authentication property="principal.authorities"/>
	</p>
	
	<!-- add logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout">
	</form:form>
</body>
</html>