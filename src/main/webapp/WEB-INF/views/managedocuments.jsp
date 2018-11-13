<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 

		<script type="text/javascript" >
	    	var getMAX_REQUEST_SIZE = ${propertiesConfig.getMAX_REQUEST_SIZE()};
	    </script>
	    
	    <div class="card mb-3">
<div class="card-header">Search Permit No</div>
<div class="card-body">

<div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                <input id="MAX_REQUEST_SIZE" value="${propertiesConfig.getMAX_REQUEST_SIZE()}" hidden="true"/>
				<input id="MAX_FILE_SIZE" value="${propertiesConfig.getMAX_FILE_SIZE()}" hidden="true"/>
                  <div class="form-label-group">
                    <input type="text" path="permitNoSearch" id="permitNoSearch" value="${permitNo}" class="form-control" placeholder="Permit No" required="required" autofocus="autofocus"/>
					<div class="has-error"><form:errors path="permitNoSearch" class="help-inline"/></div>
					<label for="permitNoSearch">Permit No</label>
                  </div>
                </div>
              </div>
</div>
<form:form id="formUpload" method="POST" action="${pageContext.request.contextPath}/docs/add-document-${user.ssoId}" 
					modelAttribute="uploadFile" enctype="multipart/form-data" class="form-horizontal">
					
					<form:hidden id="permitNo" path="permitNo" name="permitNo" value="${permitNo}"/>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table "  width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>#</th>
                      <th>Document Type</th>
                      <th>Document Name</th>
                      <th>Size</th>
					  
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
									<th ></th>
									<td><input id="totalFilesSize" value="${totalFilesSize}" hidden="true"/></td>
									<td>Total File Size</td>
									<td>${totalFilesSize} Kb</td>
								</tr>
								
								<tr>
							      <th ></th>
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
							      <td><form:input type="file" path="file" id="file" name="file" required="required" class="form-control input-sm"/></td>
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
            
            </form:form>
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
</div>



	    
	    
