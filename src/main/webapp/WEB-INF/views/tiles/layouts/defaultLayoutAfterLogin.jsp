<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
 
<html>
 
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><tiles:getAsString name="title" /></title>
	
	<link href="${pageContext.request.contextPath}/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="${pageContext.request.contextPath}/static/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
	<link href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet"></link>
    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/static/css/sb-admin.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/app.css" rel="stylesheet">
    
    <script src="${pageContext.request.contextPath}/static/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/static/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Page level plugin JavaScript-->
    <!--<script src="static/vendor/chart.js/Chart.min.js"></script>-->
    <script src="${pageContext.request.contextPath}/static/vendor/datatables/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}/static/vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/static/js/sb-admin.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-ui-1.12.0.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/additional-methods.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bcts.js"></script>
	
    <!-- Demo scripts for this page-->
    <script src="${pageContext.request.contextPath}/static/js/demo/datatables-demo.js"></script>
    <!--<script src="static/js/demo/chart-area-demo.js"></script>-->
    <script>
    	var contextPath = "${pageContext.request.contextPath}";
    </script>
	
</head>

<body>
	<!-- <div class="header-inner">
		<div class="navbar-header"> -->
		
		<nav class="navbar navbar-expand navbar-dark bg-dark static-top">
		
			<img src="static/vendor/bootstrap/css/etrade_logo_small.jpg"
				id="logoimg" alt="" class="">
			<tiles:insertAttribute name="header" />
			</nav>
		<!-- </div>
	</div> -->
	<div id="wrapper">
	<ul class="sidebar navbar-nav"> 
            <tiles:insertAttribute name="menu" />
      </ul>
       <div id="content-wrapper">
         <div class="container-fluid">
            <tiles:insertAttribute name="body" />
        </div>
        </div>
    </div> 
	<%-- <div class="bg-home">
		<tiles:insertAttribute name="body" />
	</div> --%>
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>

</html>