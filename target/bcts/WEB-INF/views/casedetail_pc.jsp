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
      <td>${pc.jobNo}</td>
    </tr>
    <tr>
      <th>Permit No</th>
      <td>${pc.permitNo}</td>
    </tr>
    <tr>      
      <th>Agency Code</th>
      <td>${pc.agencyCode}</td>
    </tr>
    <tr>      
      <th>Condition Code</th>
      <td>${pc.conditionCode}</td>
    </tr>
    <tr>      
      <th>Condition Description</th>
      <td>${pc.conditionDescription}</td>
    </tr>
  </tbody>
</table>
</div>


	    
	    
