<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

          <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table"></i>
              Licence Validity List</div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Licence No.</th>
                      <th>Expiry Date</th>
                      <th>Count Down</th>
                      <th>Remider</th>
					  <th>Action</th>
                    </tr>
                  </thead>
                  
                  <tbody>
				  <c:forEach items="${lvList}" var="lv">
				  <tr>
						<td>${lv.licenceNo}</td>
						<td><fmt:formatDate value="${lv.licenceEndDate}" pattern="dd/MM/yyyy"/></td>
						<td></td>
						
						<td><fmt:formatDate value="${lv.reminderDate}" pattern="dd/MM/yyyy"/></td>
						<%-- <td>${lv.reminderDate}</td> --%>
						<td align="center">
						<a class="btn btn-primary" href="${pageContext.request.contextPath}/cases/lv/update/${lv.caseId}">Edit</a>
						<a class="btn btn-primary" href="${pageContext.request.contextPath}/cases/lv/delete/${lv.caseId}">Delete</a>
						<a class="btn btn-primary" href="${pageContext.request.contextPath}/docs/upload/${lv.caseId}">Upload</a>
						</td>
					</tr>
				</c:forEach>
                    </tbody>
                </table>
              </div>
            </div>
           <!-- <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>-->
          </div>

       

