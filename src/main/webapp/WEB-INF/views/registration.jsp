<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card card-register mx-auto mt-2">
<div class="card-header">Register an Account</div>
<div class="card-body">
<form:form method="POST" modelAttribute="user" class="form-horizontal">
			<form:input type="hidden" path="id" id="id"/>
			<div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
                    <form:input type="text" path="firstName" id="firstName" class="form-control" placeholder="First name" required="required" autofocus="autofocus"/>
					<div class="has-error"><form:errors path="firstName" class="help-inline"/></div>
					<label for="firstName">First name</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-label-group">
				  <form:input type="text" path="lastName" id="lastName" class="form-control" placeholder="Last name" required="required" />
				  <div class="has-error"><form:errors path="lastName" class="help-inline"/></div>
                    <label for="lastName">Last name</label>
                  </div>
                </div>
              </div>
            </div>
			<div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
                    <c:choose>
					<c:when test="${edit}"><form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" disabled="true"/></c:when>
					<c:otherwise><form:input type="text" path="ssoId" id="ssoId" class="form-control" placeholder="ssoId" required="required" />
					<div class="has-error"><form:errors path="ssoId" class="help-inline"/></div>
					</c:otherwise></c:choose>
					<label for="ssoId">SSO ID</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-label-group">
				  <form:input type="password" path="password" id="password" class="form-control" placeholder="password" required="required" />
				  <div class="has-error"><form:errors path="password" class="help-inline"/></div>
                    <label for="password">Password</label>
                  </div>
                </div>
              </div>
            </div>
			<div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
                    <form:input type="text" path="email" id="email" class="form-control" placeholder="email" required="required" />
					<div class="has-error"><form:errors path="email" class="help-inline"/></div>
					<label for="email">Email</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-label-group">
                </div>
              </div>
            </div>
			</div>
			<div class="col-md-6">Roles</div>
			<div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
				  <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="transId" itemLabel="roleType" class="form-control input-sm" />
                    <div class="has-error"><form:errors path="userProfiles" class="help-inline"/></div>
					
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-label-group">
                </div>
              </div>
            </div>
			</div>
			
			<div align="center">
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


		