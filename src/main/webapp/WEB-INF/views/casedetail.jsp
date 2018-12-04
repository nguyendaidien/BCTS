<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<div class="card mb-3">
<div class="card-header"><b>Case Detail</b></div>
<div class="card-body">
		<c:if test="${caseDetail.category eq 'LV'}">
			<c:import url="./../../casedetail_lv.jsp"></c:import>
		</c:if>
		<c:if test="${caseDetail.category eq 'PC'}">
			<c:import url="./../../casedetail_pc.jsp"></c:import>
		</c:if>
		<c:if test="${caseDetail.category eq 'CM'}">
			<c:import url="./../../casedetail_cm.jsp"></c:import>
		</c:if>
</div> 
<div class="card-header"><b>Comments</b></div>
<div class="card-body">
<!-- <div class="container"> -->
    <div class="row">
    	<c:forEach items="${comments}" var="comment">
	        <div class="col-sm-8">
	            <div class="panel panel-white post">
	                <div class="post-heading">
	                    <div class="pull-left image">
	                        <img src="http://bootdey.com/img/Content/user_1.jpg" class="img-circle avatar" alt="user profile image">
	                    </div>
	                    <div class="pull-left meta">
	                        <div class="title h5">
	                            <a href="#"><b>${comment.user.firstName} ${comment.user.lastName}</b></a>
	                            made a post.
	                        </div>
	                        <h6 class="text-muted time">at <fmt:formatDate value="${comment.updateTime}" pattern="dd/MM/yyyy HH:mm"/></h6>
	                    </div>
	                </div> 
	                <div class="post-description"> 
	                    <p>${comment.content}</p>
	                </div>
	            </div>
	        </div>    
        </c:forEach>    
     </div>  
     <c:if test="${caseDetail.status ne 'C'}">  
    	<div class="row">
    	<div class="col-md-6">
    						<div class="widget-area no-padding blank">
								<div class="status-upload">
									<form:form method="POST" action="${pageContext.request.contextPath}/cases/update/${caseDetail.caseId}" 
					modelAttribute="caseComment" class="form-horizontal">
										<%-- <form:hidden path="bctsAlert" id="bctsAlert" name="bctsAlert" value="${caseDetail}"/> --%>
										<form:textarea placeholder="add a comment" path="content" id="content" name="content"></form:textarea>
										<button type="submit" name="complete" class="btn btn-success green"> Complete</button>
										<button type="submit" name="addComment" class="btn btn-success green"> Add Comment</button> 
									</form:form>
								</div><!-- Status Upload  -->
							</div><!-- Widget Area -->
						</div>
        
    	</div>
    </c:if>
</div>
</div>



	    
	    
