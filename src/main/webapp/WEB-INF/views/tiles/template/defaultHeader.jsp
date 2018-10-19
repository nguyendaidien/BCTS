<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="authbar" align="left">
<sec:authorize access="hasRole('ADMIN') || hasRole('DBA') || hasRole('USER')">
<span>Dear <strong>${loggedinuser}</strong>, Welcome to BCTS (GeTS).
<span class="floatRight"><a href="${pageContext.request.contextPath}/changepwd">ChangePassword</a>&nbsp;|&nbsp;
<a href="${pageContext.request.contextPath}/logout" /> Logout</a></span>
</sec:authorize>
</div>
