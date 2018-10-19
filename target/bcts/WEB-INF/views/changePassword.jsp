<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>


<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Change Password Here</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>


<body>
	<div class="generic-container">
		<%@include file="authheader.jsp" %>
  <div class="well lead">Change Password Here</div>
	<form:form method="POST" modelAttribute="cpw" class="form-horizontal">
	
	
	     <div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userid">User ID</label>
					<div class="col-md-7">
						<form:input type="text" path="userid" id="userid" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="userid" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
		<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="password">Password</label>
					<div class="col-md-7">
						<form:input type="password" path="password" id="password" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="password" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>	
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="passwordConf">New Password</label>
					<div class="col-md-7">
						<form:input type="password" path="passwordConf" id="passwordConf" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="passwordConf" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
		<%-- <table>
			<tr>
				<td>UserId:</td>
				<td><form:label path="userid" /></td>
				<td><form:errors path="userid" cssStyle="color: #ff0000;"/></td>
			</tr>	
			
			
			<tr>
				<td>Old Password:</td>
				<td><form:password path="password"  showPassword="true"/></td>
				<td><form:errors path="password" cssStyle="color: #ff0000;"/></td>
			</tr>	
			<tr>
				<td>New Password:</td>
				<td><form:password path="passwordConf" showPassword="true"/></td>
				<td><form:errors path="passwordConf" cssStyle="color: #ff0000;"/></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Submit"></td>
			</tr>
			
		</table> --%>
		
		
		<div class="row">
				<div class="form-actions floatRight">
						<input type="submit" value="Update" class="btn btn-primary btn-sm"/> &nbsp; <a href="<c:url value='/list' />" class="btn btn-primary btn-sm">Cancel</a>
				</div>
			</div>
	</form:form>
</div>
</body>


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="pwstrength.js"></script>                  
<script type="text/javascript">
$(document).ready(function () {
    options = {
        common: {minChar:8},
        ui: {
            showVerdictsInsideProgressBar:true,
            showErrors:true,
            errorMessages:{
                wordLength: '<spring:message code="error.wordLength"/>',
                wordNotEmail: '<spring:message code="error.wordNotEmail"/>',
                wordSequences: '<spring:message code="error.wordSequences"/>',
                wordLowercase: '<spring:message code="error.wordLowercase"/>',
                wordUppercase: '<spring:message code="error.wordUppercase"/>',
                wordOneNumber: '<spring:message code="error.wordOneNumber"/>',
                wordOneSpecialChar: '<spring:message code="error.wordOneSpecialChar"/>'
            }
        }
    };
    $('#password').pwstrength(options);
});
</script>

</html>