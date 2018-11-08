<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

          <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Permit List</div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>First Name</th>
                      <th>Last Name</th>
                      <th>Email</th>
                      <th>User Id</th>
					  <th>Action</th>
                    </tr>
                  </thead>
                  
                  <tbody>
				  <c:forEach items="${users}" var="user">
				  <tr>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td>${user.ssoId}</td>
						<td align="center">
					    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
						<a class="btn btn-primary" href="${pageContext.request.contextPath}/edit-user-${user.ssoId}">Edit</a>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
						<a class="btn btn-primary" href="${pageContext.request.contextPath}/delete-user-${user.ssoId}">Delete</a>
        				</sec:authorize>
						</td>
					</tr>
				</c:forEach>
                    </tbody>
                </table>
              </div>
            </div>
           <!-- <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>-->
          </div>

       

