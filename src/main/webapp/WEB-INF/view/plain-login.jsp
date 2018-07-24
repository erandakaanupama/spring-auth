<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>login</title>
<style>
.failed {
	color: red;
}
</style>
</head>
<body>
	<h3>My custom login page</h3>
	<form:form
		action="${pageContext.request.contextPath}/authenticateTheUser"
		method="POST">

		<c:if test="${param.error !=null }">
			<i class="failed">Incorrect login credentials</i>
		</c:if>
		<p>
			User name: <input type="text" name="username" />
		</p>
		<p>
			Password: <input type="password" name="password" />
		</p>
		<br>
		<input type="submit" value="Login">
	</form:form>
</body>
</html>