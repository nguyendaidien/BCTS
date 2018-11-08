<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card card-register mx-auto mt-2">
<div class="card-header">Register a Role</div>
<div class="card-body">
<form:form method="POST" modelAttribute="userprofile"  class="form-horizontal">
			<form:input type="hidden" path="transId" id="transId"/>
<div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
				  <form:input type="text" path="roleType" id="roleType" class="form-control" placeholder="Role Type" required="required" autofocus="autofocus"/>
                   
					<div class="has-error"><form:errors path="roleType" class="help-inline"/></div>
					<label for="roleType">Role Type</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-label-group">
				  </div>
                </div>
              </div>
            </div>	
			<div >
					<c:choose>
						<c:when test="${edit}">
						
						<input type="submit" class="btn btn-primary" value="Update"> &nbsp; <a class="btn btn-primary" href="${pageContext.request.contextPath}/list">Cancel</a>
							
						</c:when>
						<c:otherwise>
						<input type="submit" class="btn btn-primary" value="Register"> &nbsp; <a class="btn btn-primary" href="${pageContext.request.contextPath}/list">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			
</form:form>	
</div>
</div>	
