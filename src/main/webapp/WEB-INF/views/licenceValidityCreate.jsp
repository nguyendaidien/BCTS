<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<script>
            $(document).ready(function () {
            	$('#caseForm').on('keyup keypress', function(e) {
            		  var keyCode = e.keyCode || e.which;
            		  if (keyCode === 13) { 
            		    e.preventDefault();
            		    return false;
            		  }
            		});
            	
            	$('#alertEmails').multiple_emails();            	
            });
        </script>
        <script type="text/javascript">
        	var alertEmails = ${alertEmails};
        </script>
<div class="card card-register mx-auto mt-2">
<div class="card-header">Create Licence Validity</div>
<div class="card-body">

<form:form method="POST" id="caseForm" modelAttribute="licenceValidity" class="form-horizontal">
			<%-- <form:input type="hidden" path="caseId" id="caseId"/> --%>
			<div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
                    <form:input type="text" path="licenceNo" id="licenceNo" disabled="${editMode}" class="form-control" placeholder="licence No" required="required" autofocus="autofocus"/>
					<div class="has-error"><form:errors path="licenceNo" class="help-inline"/></div>
					<label for="licenceNo">Licence No</label>
                  </div>
                </div>                
              </div>
            </div>
			<div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
				  <form:input type="text" path="jobNo" id="jobNo" class="form-control" placeholder="Job No" required="required" />
				  <div class="has-error"><form:errors path="jobNo" class="help-inline"/></div>
                    <label for="jobNo">Job No</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-label-group">
				  <form:input type="text" path="permitNo" id="permitNo" class="form-control" placeholder="permitNo" required="required" />
				  <div class="has-error"><form:errors path="permitNo" class="help-inline"/></div>
                    <label for="permitNo">Permit No</label>
                  </div>
                </div>
              </div>
            </div>
            
<%-- 			<div class="form-group">
              <div class="form-row">
                 <div class="col-sm-6">
            		<form:input type="date" path="licenceStartDate" class="form-control datetimepicker-input" id="licenceStartDate" data-toggle="datetimepicker" data-target="#licenceStartDate"/>
        			<form:input type="date" path="licenceStartDate" id="licenceStartDate" placeholder="Licence start date"/>
        		</div>
			       <!--  <script type="text/javascript">
			            $(function () {
			                $('#licenceStartDate').datetimepicker({
			                	format: 'DD/MM/YYYY'
			                });
			            });
			        </script> -->
                <div class="col-md-6">
                  <div class="form-label-group">
                </div>
              </div>
            </div>
			</div>
			
			<div class="form-group">
              <div class="form-row">
                 <div class="col-sm-6">
            		<form:input type="date" path="licenceEndDate" id="licenceEndDate" placeholder="Licence end date"/>
        		</div>
			       <!--  <script type="text/javascript">
			            $(function () {
			                $('#licenceEndDate').datetimepicker({
			                	format: 'DD/MM/YYYY'
			                });
			            });
			        </script> -->
                <div class="col-md-6">
                  <div class="form-label-group">
                </div>
              </div>
            </div>
			</div>
			
			<div class="form-group">
              <div class="form-row">
                 <div class="col-sm-6">
            		<form:input type="text" path="reminderDate" class="form-control datetimepicker-input" id="reminderDate" data-toggle="datetimepicker" data-target="#reminderDate"/>
        		</div>
			        <script type="text/javascript">
			            $(function () {
			                $('#reminderDate').datetimepicker({
			                	format: 'DD/MM/YYYY'
			                });
			            });
			        </script>
                <div class="col-md-6">
                  <div class="form-label-group">
                </div>
              </div>
            </div>
			</div> --%>
			<div class="form-group">
              <div class="form-row">
                 <div class='col-sm-6'>
                     <div class="input-group date" id="licenceStartDate" data-target-input="nearest">
                    <input type="text" name="licenceStartDate" value="${licenceStartDate}" placeholder="Licence start date" class="form-control datetimepicker-input" data-target="#licenceStartDate"/>
                    <div class="input-group-append" data-target="#licenceStartDate" data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                </div>
        		</div>
			        <script type="text/javascript">
			            $(function () {
			                $('#licenceStartDate').datetimepicker({
			                	format: 'DD/MM/YYYY'
			                });
			            });
			        </script>
                <div class="col-md-6">
                  <div class="form-label-group">
                </div>
              </div>
            </div>
			</div>
			
			<div class="form-group">
              <div class="form-row">
                 <div class='col-sm-6'>
                     <div class="input-group date" id="licenceEndDate" data-target-input="nearest">
                    <input type="text" name="licenceEndDate" value="${licenceEndDate}" placeholder="Licence end date" class="form-control datetimepicker-input" data-target="#licenceEndDate"/>
                    <div class="input-group-append" data-target="#licenceEndDate" data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                </div>
        		</div>
			        <script type="text/javascript">
			            $(function () {
			                $('#licenceEndDate').datetimepicker({
			                	format: 'DD/MM/YYYY'
			                });
			            });
			        </script>
                <div class="col-md-6">
                  <div class="form-label-group">
                </div>
              </div>
            </div>
			</div>
			<div class="form-group">
              <div class="form-row">
                 <div class='col-sm-6'>
                     <div class="input-group date" id="reminderDate" data-target-input="nearest">
                    <form:input type="text" path="reminderDate" value="${reminderDate}" placeholder="Reminder date" class="form-control datetimepicker-input" data-target="#reminderDate"/>
                    <div class="input-group-append" data-target="#reminderDate" data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                </div>
        		</div>
			        <script type="text/javascript">
			            $(function () {
			                $('#reminderDate').datetimepicker({
			                	format: 'DD/MM/YYYY'
			                });
			            });
			        </script>
                <div class="col-md-6">
                  <div class="form-label-group">
                </div>
              </div>
            </div>
			</div>
			
			<div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
                  	<%-- <form:input type="hidden" path="alertEmails" id="alertEmails"/> --%>
                    <input type="text" id="alertEmails" name="alertEmails" class="form-control" placeholder="Alert Emails" required="required" autofocus="autofocus"/>
					<%-- <c:if test="${alertEmails !=null}">
						<div class="multiple_emails-container"><ul class="multiple_emails-ul">
						<c:forEach items="${alertEmails}" var="email">
							<li class="multiple_emails-email"><a href="#" class="multiple_emails-close" title="Remove">
								<span class="glyphicon glyphicon-remove"></span></a>
								<span class="email_name" data-email="${email}">${email}</span>
							</li>
						</c:forEach>
						</ul>
						<input type="text" class="multiple_emails-input text-left"></div>
					</c:if> --%>
					<div class="has-error"><form:errors path="alertEmails" class="help-inline"/></div>
					<!-- <label for="alertContent">Alert Content</label> -->
                  </div>
                </div>   
              </div>
            </div>
            
            <div class="form-group">
              <div class="form-row">
                <div class="col-md-6">
                  <div class="form-label-group">
                  	<div class="form-check">
					  <form:checkbox  class="form-check-input" value="" id="toAlertCompany" path="toAlertCompany"/>
					  <label class="form-check-label" for="toAlertCompany">
					    Alert company's emails list
					  </label>
					</div>
                  </div>
                </div>   
              </div>
            </div>
            
			<div class="form-group">
              <div class="form-row">
                <div class="col-md-12">
                  <div class="form-label-group">
                    <form:textarea type="text" path="alertContent" id="alertContent" class="form-control" placeholder="Alert Content" required="required" autofocus="autofocus"/>
					<div class="has-error"><form:errors path="alertContent" class="help-inline"/></div>
					<!-- <label for="alertContent">Alert Content</label> -->
                  </div>
                </div>                
              </div>
            </div>
			
			<div align="center">
					<c:choose>
						<c:when test="${edit}">
						
						<input type="submit" class="btn btn-primary" value="Update"> &nbsp; <a class="btn btn-primary" href="${pageContext.request.contextPath}/cases/lvList">Cancel</a>
							
						</c:when>
						<c:otherwise>
						<input type="submit" class="btn btn-primary" value="Submit"> &nbsp; <a class="btn btn-primary" href="${pageContext.request.contextPath}/cases/lvList">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			
			
		</form:form>
		</div>
</div>


		