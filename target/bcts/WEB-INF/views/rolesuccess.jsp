<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



		
		<div class="alert alert-success lead">
	    	${success}
		</div>
		
		<span class="well floatRight">
			<a href="${pageContext.request.contextPath}/list" class="btn btn-primary btn-sm">Users List</a>
		</span>
