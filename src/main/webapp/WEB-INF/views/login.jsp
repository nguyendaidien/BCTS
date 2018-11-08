<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="login-page">
  <div class="form">
  <c:url var="loginUrl" value="/login" />
  <form action="${loginUrl}" method="post"  class="login-form">
  
  <style>
  .error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #f50c07;
	background-color: #f2dede8f;
	border-color: #f2dede8f;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}
  </style>
  
  <c:if test="${not empty error}">
	<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
	
      <input type="text" id="username" name="ssoId" placeholder="username" required="required" autofocus="autofocus"/>
      <input type="password" id="password" name="password" placeholder="password" required="required"/>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      <input type="submit" class="btn-black" value="Login">
      <!--<input type="checkbox" id="rememberme" name="remember-me"> Remember Me-->
      <!--<p class="message">Not registered? <a href="#">Create an account</a></p>-->
    </form>
  </div>
</div>