<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<nav >
    	<a href="${pageContext.request.contextPath}/"><img class="logo" src="${pageContext.request.contextPath}/static/img/GeTS.jpg" height="100" width="100" align="right"></a>
		<sec:authorize access="hasRole('DBA') || hasRole('USER') || hasRole('ADMIN')">
	    <ul id="menu" >
        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
		</ul>
	   </sec:authorize>
		
		<sec:authorize access="hasRole('ADMIN')">
	    <ul id="menu" >
       <li><a href="${pageContext.request.contextPath}/newuser">Add New User</a></li>
       <li><a href="${pageContext.request.contextPath}/newrole">Add New Role</a></li>
       <li><a href="${pageContext.request.contextPath}/docs/add-document-${loggedinuser}">Documents</a></li>
		</ul>
	   </sec:authorize>
</nav>