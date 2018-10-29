<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!doctype html> 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
     <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<c:url value='/static/bootstrap/css/bootstrap.min.css' />" crossorigin="anonymous">
    <title><tiles:getAsString name="title" /></title>
    <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    
    <!-- Optional JavaScript -->
	    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	    <script src="<c:url value='/static/js/jquery-3.3.1.min.js' />" crossorigin="anonymous"></script>
	    <script src="<c:url value='/static/js/popper.min.js' />" crossorigin="anonymous"></script>
	    <script src="<c:url value='/static/bootstrap/js/bootstrap.min.js' />" crossorigin="anonymous"></script>
	    <script src="<c:url value='/static/js/jquery-ui-1.12.0.js'/>" type="text/javascript" ></script>
</head>
  
<body>
        <header id="header">
            <tiles:insertAttribute name="header" />
        </header>
     
        <section id="sidemenu">
            <tiles:insertAttribute name="menu" />
        </section>
             
        <section id="site-content">
            <tiles:insertAttribute name="body" />
        </section>
         
        <footer id="footer">
            <tiles:insertAttribute name="footer" />
        </footer>
        
</body>
</html>