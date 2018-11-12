<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Upload/Download/Delete Documents</title>
	<link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet"></link>
	<link href="${pageContext.request.contextPath}/static/css/app.css" rel="stylesheet"></link>	
	<link href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet"></link>
	<script type="text/javascript" >
	    	var getMAX_REQUEST_SIZE = ${propertiesConfig.getMAX_REQUEST_SIZE()};
	    	//var getMAX_REQUEST_SIZE2 = ${applicationScope.propertiesConfig.getMAX_REQUEST_SIZE()};
	    	//var multipartResolver = ${multipartResolver.toString()};
	    </script>
</head>

<body>
	<!-- <div class="generic-container"> -->
		<div class="panel panel-default">
		<input id="MAX_REQUEST_SIZE" value="${propertiesConfig.getMAX_REQUEST_SIZE()}" hidden="true"/>
		<input id="MAX_FILE_SIZE" value="${propertiesConfig.getMAX_FILE_SIZE()}" hidden="true"/>
		<div class="panel-heading"><span class="lead">Search Permit No.</span></div>
		<div class="ui-widget">
							<label class="control-lable" for="permitNoSearch">Permit No.</label>
							<input id="permitNoSearch" type="text" name="permitNoSearch" value="${permitNo}" autocomplete="on"/>				
		</div>
			  <!-- Default panel contents -->
		<div class="panel-heading"><span class="lead">Upload New Document</span></div>
			<div class="uploadcontainer">
				<form:form id="formUpload" method="POST" action="${pageContext.request.contextPath}/docs/add-document-${user.ssoId}" 
					modelAttribute="uploadFile" enctype="multipart/form-data" class="form-horizontal">
					
					<form:hidden id="permitNo" path="permitNo" name="permitNo" value="${permitNo}"/>
					<div class="row">
						<div class="form-group col-md-12">
							<div class="col-md-7">
							<table class="table">
							  <thead>
							    <tr>
							      <th scope="col">#</th>
							      <th scope="col">Document Type</th>
							      <th scope="col">Document Name</th>
							      <th scope="col">Size</th>
							    </tr>
							  </thead>
							  <tbody>
							  <c:forEach items="${uploadedFiles}" var="f" varStatus="count">
							  <tr>
								<th scope="row">${count.index + 1}</th>																
								<td>${f.docType}</td>
								<td>${f.getFile().getOriginalFilename()}</td>
								<td>${f.getDocSize()} Kb</td>
								</tr>
							  </c:forEach>
							  <tr>
									<th scope="row"></th>
									<td><input id="totalFilesSize" value="${totalFilesSize}" hidden="true"/></td>
									<td>Total File Size</td>
									<td>${totalFilesSize} Kb</td>
								</tr>
							    <tr>
							      <th scope="row"></th>
							      <%-- <td><form:input type="text" path="docType" id="docType" class="form-control input-sm"/></td> --%>
							      <td>
							      <div class="dropdown">
							      	  <form:hidden path="docType" id="docType"/>
									  <button class="btn btn-secondary dropdown-toggle" type="button" id="docType-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									    Select document type
									  </button>
									  <ul class="dropdown-menu" aria-labelledby="docType-dropdown">
									    <li><a href="#" data-value="Invoice">Invoice</a></li>
									    <li><a href="#" data-value="Permit">Permit</a></li>
									    <li><a href="#" data-value="something else here">Something else</a></li>
									  </ul>
									</div>
							      </td>
							      <td><form:input type="file" path="file" id="file" name="file" class="form-control input-sm"/></td>
							      <td>
							      	<input type="submit" value="Upload" class="btn btn-primary btn-sm">
								  </td>
							      							      
							    </tr>
							    
								<tr>
									<th scope="row"></th>
									<td></td>
									<td></td>
									<td>
								      <div class="has-error">
										<form:errors path="file" class="help-inline"/>
										<c:if test="${not empty error}">
											<h4 class=error>An error occurred: ${error}</h4>
											<br>
										</c:if>									
									  </div>
							      	</td>
								</tr>
								
							  </tbody>
							</table>
								
							</div>
						</div>
					</div>
					<%-- <div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="file">Description</label>
							<div class="col-md-7">
								<form:input type="text" path="description" id="description" class="form-control input-sm"/>
							</div>
							
						</div>
					</div> --%>
			
					<!-- <div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Upload" class="btn btn-primary btn-sm">
						</div>
					</div> -->
	
				</form:form>
				</div>
				<c:if test="${documents!=null}">
			<div class="panel-heading"><span class="lead">List of Documents </span></div>
		  	<div class="tablecontainer">
				<table class="table table-hover">
		    		<thead>
			      		<tr>
					        <th>No.</th>
					        <th>File Name</th>
					        <th>Uploaded Date</th>
					        <th width="100"></th>
						</tr>
			    	</thead>
		    		<tbody>
					<c:forEach items="${documents}" var="doc" varStatus="counter">
						<tr>
							<td>${counter.index + 1}</td>
							<td>${doc.name}</td>
							<td>${doc.uploadedDate}</td>
							<td><a href="<c:url value='/docs/download-document-${user.id}-${doc.id}' />" class="btn btn-success custom-width">download</a></td>
						</tr>
					</c:forEach>
		    		</tbody>
		    	</table>
		    </div>
		    </c:if>
		</div>		
</body>
</html>