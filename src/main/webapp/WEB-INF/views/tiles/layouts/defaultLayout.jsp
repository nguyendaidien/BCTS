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

    <title>BCTS</title>
	<link href="static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


    <!-- Custom fonts for this template-->
    <link href="static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="static/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="static/css/sb-admin.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/app.css" rel="stylesheet">
    <script src="static/vendor/jquery/jquery.min.js"></script>
    <script src="static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="static/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Page level plugin JavaScript-->
    <!--<script src="static/vendor/chart.js/Chart.min.js"></script>-->
    <script src="static/vendor/datatables/jquery.dataTables.js"></script>
    <script src="static/vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="static/js/sb-admin.min.js"></script>

    <!-- Demo scripts for this page-->
    <script src="static/js/demo/datatables-demo.js"></script>
    <!--<script src="static/js/demo/chart-area-demo.js"></script>-->
    
    <title><tiles:getAsString name="title" /></title>		
	
</head>
 
 <body >
		
				<div class="header-inner">
					<div class="navbar-header">				
					  <img src="static/vendor/bootstrap/css/etrade_logo_small.jpg" id="logoimg" alt="" class=""><tiles:insertAttribute name="header" />
					</div>
					 
				</div>		    

      
        <div class="bg-home">
            <tiles:insertAttribute name="body" />
        </div>
        
     	<!-- <div class="footer"> -->
     	<div id="footer">
            <tiles:insertAttribute name="footer" />
        </div>
        <!-- </div>   
             -->
		
        
</body> 

</html>