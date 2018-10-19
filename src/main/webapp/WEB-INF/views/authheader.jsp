<%-- 	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
	<div class="authbar">
		<span>Dear <strong>${loggedinuser}</strong>, Welcome to BCTS (GeTS). 
		<sec:authorize access="hasRole('ADMIN')">
		 You have logged in using Admin Role.
		</sec:authorize>
		<sec:authorize access="hasRole('DBA')">
		 You have logged in using DBA Role.
		</sec:authorize>
		<sec:authorize access="hasRole('USER')">
		 You have logged in using User Role.
		</sec:authorize>
		</span>
		<span class="floatRight"><a href="<c:url value='/changepwd' />">ChangePassword</a>&nbsp;|&nbsp;<a href="<c:url value="/logout" />">Logout</a></span>
	</div>
 --%>
 
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div align="left">
<sec:authorize access="hasRole('ADMIN') || hasRole('DBA') || hasRole('USER')">
<span>Dear <strong>${loggedinuser}</strong>, Welcome to BCTS (GeTS).
<span class="floatRight"><a href="${pageContext.request.contextPath}/changepwd">ChangePassword</a>&nbsp;|&nbsp;
<a href="${pageContext.request.contextPath}/logout" /> Logout</a></span>
</sec:authorize>
</div>
 