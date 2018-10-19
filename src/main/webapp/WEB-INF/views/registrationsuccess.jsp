<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Registration Confirmation Page</title>
	<link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet"></link>
	<link href="${pageContext.request.contextPath}/static/css/app.css" rel="stylesheet"></link>
</head>
<body>
	
		
		<div class="alert alert-success lead">
	    	${success}
		</div>
		
		<span class="well floatRight">
			<a href="${pageContext.request.contextPath}/list" class="btn btn-primary btn-sm">Users List</a>
		</span>
</body>

</html>