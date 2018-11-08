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
		
		<style>
/* @import url(https://fonts.googleapis.com/css?family=Roboto:300); */

.login-page {
  width: 360px;
  padding: 8% 0 0;
  margin: auto;
}
.form {
  position: relative;
  z-index: 1;
 /*  background: #FFFFFF; */
  background:#0b01158f;
  max-width: 360px;
  margin: 1px 1px;
  padding: 20px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #4CAF50;
  width: 100%;
  border: 0;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.form button:hover,.form button:active,.form button:focus {
  background: #43A047;
}
.form .message {
  margin: 15px 0 0;
  color: #b3b3b3;
  font-size: 12px;
}
.form .message a {
  color: #4CAF50;
  text-decoration: none;
}
.form .register-form {
  display: none;
}
.container {
  position: relative;
  z-index: 1;
  max-width: 300px;
  margin: 0 auto;
}
.container:before, .container:after {
  content: "";
  display: block;
  clear: both;
}
.container .info {
  margin: 50px auto;
  text-align: center;
}
.container .info h1 {
  margin: 0 0 15px;
  padding: 0;
  font-size: 36px;
  font-weight: 300;
  color: #1a1a1a;
}
.container .info span {
  color: #4d4d4d;
  font-size: 12px;
}
.container .info span a {
  color: #000000;
  text-decoration: none;
}
.container .info span .fa {
  color: #EF3B3A;
}

.login-page .form .btn-black{ background:#346babb8; }
.login-page .form .btn-black:hover{ background:#3448abb8; }
/* body {
  background: #76b852; /* fallback for old browsers */
  background: -webkit-linear-gradient(right, #76b852, #8DC26F);
  background: -moz-linear-gradient(right, #76b852, #8DC26F);
  background: -o-linear-gradient(right, #76b852, #8DC26F);
  background: linear-gradient(to left, #76b852, #8DC26F);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;      
} */
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
/* Eric Added: Login Page Navbar White */
.navbar.navbar-inverse {
    background-color: white; /* Changed from #303136; to white */
    border-radius: 0 ;
    height: auto;
    min-height: 56px; /* To match the Logo height */
}
.navbar-custom {
	background-color: white;
}
/* Eric Added: Login Page Navbar left right padding */
.container {
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: 0px; /* Change from auto to 0px */
}

/* Eric Added: Change margin-bottom from 20px to 0px */
.navbar {
	margin-bottom: 0px;	
}

 .footer { 
background:white; /* Footer color according to GETS UI guidelines */
padding: 8px 20px 20px 20px;
}

.footer .footer-inner {
    margin-top: 12px;
	color: #666; /* Set Footer Text Color to GETS UI guidelines */
}

.footer .footer-tools .go-top {
    background: #d71635 none repeat scroll 0 0;
    padding: 10px 16px;
}

.footer .footer-tools .go-top .fa{ color:#fff;}
#footer {
	margin-left:0%;
	margin-top: 10px;
    left: 0px;
    bottom: 0px;
    width: 100%;
	height: 7%;
	position: fixed;
	font-family: "Roboto", sans-serif;
	/* font-family: 'bgMed',arial,helvetica,sans-serif; */
    font-size: 0.8em;
    color: #666;
    z-index: 15;
    /* text-transform: uppercase; */
    /* background-color: #1E90FF; */
	padding-top:20px;   
    text-align:center;
    background:white
}

.bg-home{
background-color:#3d8e0f!important;
background-image: url("${pageContext.request.contextPath}/static/vendor/bootstrap/css/BctsWeb.jpg");
background-repeat: no-repeat;
background-size:cover;
height:-webkit-fill-available
}

/* .login-box{ width:382px; padding:30px 30px 0 30px; background:rgba(0,0,0,0.6); min-height:200px; float:right; margin-top:100px; margin-right:100px;} */ 
</style>
		
	
	
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