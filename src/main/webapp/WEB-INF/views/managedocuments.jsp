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
	<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script> -->
	
	
	<script type="text/javascript">
	var $ = jQuery;
	$(document).ready(function() {		
		$('#permitNoSearch').autocomplete({
				minLength: 1,
		        delay: 500,
		        //define callback to format results
		        source: function (request, response) {
		            $.getJSON('${pageContext.request.contextPath}/permit/search', request, function(result) {
		                response($.map(result, function(item) {
		                    return {
		                    	label: item.permitNo,		                    
		                        // following property gets entered in the textbox
		                        value: item.permitNo,
		                    }
		                }));
		            });
		        },
		      	//define select handler
		        select : function(event, ui) {
		            if (ui.item) {
		                event.preventDefault();
		                $("#permitNo").val(ui.item.value);
		                console.log("selected Permit No: " + ui.item.value);
		                //$("#tagQuery").value = $("#tagQuery").defaultValue
		                //var defValue = $("#permitNoSearch").prop('defaultValue');
		                $("#permitNoSearch").val(ui.item.value);
		                $("#permitNoSearch").blur();
		                window.location.href = "${pageContext.request.contextPath}/docs/add-document-sam";
		                return false;
		            }
		        }
		});
	});
</script>
</head>

<body>
	<!-- <div class="generic-container"> -->
		<div class="panel panel-default">
		<div class="row">
						<div class="ui-widget">
							<label class="col-md-3 control-lable" for="permitNoSearch">Permit No.</label>
							<input id="permitNoSearch" type="text" name="permitNoSearch" autocomplete="on"/>				
						</div>
					</div>
			  <!-- Default panel contents -->
		<div class="panel-heading"><span class="lead">Upload New Document</span></div>
			<div class="uploadcontainer">
				<form:form method="POST" action="${pageContext.request.contextPath}/docs/add-document-${user.ssoId}" modelAttribute="uploadFileBucket" enctype="multipart/form-data" class="form-horizontal">
					
					<form:hidden id="permitNo" path="permitNo" name="permitNo" value=""/>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="files">Upload a document</label>
							<div class="col-md-7">
								<form:input type="file" path="files" id="files" class="form-control input-sm"/>
								<form:input type="file" path="files" id="files" class="form-control input-sm"/>
								<div class="has-error">
									<form:errors path="files" class="help-inline"/>
									<c:if test="${not empty error}">
										<h4 class=error>An error occurred: ${error}</h4>
										<br>
									</c:if>
									
								</div>
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
			
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Upload" class="btn btn-primary btn-sm">
						</div>
					</div>
	
				</form:form>
				</div>
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
		</div>
		<!-- <div class="panel panel-default"></div> -->
	 	<div class="well">
	 		Go to <a href="<c:url value='/list' />">Users List</a>
	 	</div>
   	<!-- </div> -->
</body>
</html>