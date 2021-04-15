<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>

	<title>luv2code Company Home Page</title>


</head>

<body>

	<h2>Luv2code Company Home Page - Yoohoo!!!</h2>
	<hr>
	<p>
	Welcome to the luv2code company home page!
	</p>
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	
		<input type="submit" value="Logout">
	
	</form:form>
	
</body>


</html>