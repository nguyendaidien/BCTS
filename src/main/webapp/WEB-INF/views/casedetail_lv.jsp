<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="table-responsive-sm">
<table class="table">  
  <tbody>
    <tr>      
      <th>Job No</th>
      <td>${caseDetail.jobNo}</td>
    </tr>
    <tr>
      <th>Permit No</th>
      <td>${caseDetail.permitNo}</td>
    </tr>
    <tr>      
      <th>Licence No</th>
      <td>${caseDetail.licence.licenceNo}</td>
    </tr>
    <tr>      
      <th>Licence Start Date</th>
      <td><fmt:formatDate value="${caseDetail.licence.licenceStartDate}" pattern="dd/MM/yyyy"/></td>
    </tr>
    <tr>      
      <th>Licence End Date</th>
      <td><fmt:formatDate value="${caseDetail.licence.licenceEndDate}" pattern="dd/MM/yyyy"/></td>
    </tr>
    <tr>      
      <th>Remarks</th>
      <td>${caseDetail.alertContent}</td>
    </tr>
  </tbody>
</table>
</div>
