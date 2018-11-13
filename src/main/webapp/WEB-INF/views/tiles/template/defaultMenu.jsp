<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

 
		<sec:authorize access="hasRole('DBA') || hasRole('USER') || hasRole('ADMIN')">
	    <li class="nav-item active">
          <a class="nav-link" href="${pageContext.request.contextPath}/">
            <i class="fas fa-fw fa-search"></i>
            <span>Permit List</span>
          </a>
        </li>
        
		
	   </sec:authorize>
		
		<sec:authorize access="hasRole('ADMIN')">
		 <li class="nav-item active">
          <a class="nav-link" href="${pageContext.request.contextPath}/newuser">
            <i class="fas fa-fw fa-user-plus"></i>
            <span>Add New User</span></a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="${pageContext.request.contextPath}/newrole">
            <i class="fas fa-fw fa-universal-access"></i>
            <span>Add New Role</span></a>
        </li>
	   </sec:authorize>

	   <sec:authorize access="hasRole('USER')">
	   <li class="nav-item active">
          <a class="nav-link" href="#">
            <i class="fas fa-fw fa-credit-card"></i>
            <span>License validity</span></a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="#">
            <i class="fas fa-fw fa-bell"></i>
            <span>Create Case</span></a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="${pageContext.request.contextPath}/docs/managedocuments-sam">
            <i class="fas fa-fw far fa-file-alt"></i>
            <span>Document Pouch</span></a>
        </li>
	   </sec:authorize>