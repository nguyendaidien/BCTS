<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<style>    
.margin-top-50{ margin:50px 0;}
.bodywrapper {
    margin:0 auto;
    padding: 0;
	/*max-width:1400px;*/ /* Test comment off */
} 

.bodywrapper.bg
{  
	background:url(static/vendor/bootstrap/css/BctsWeb.jpg) no-repeat top center; 
	background-size:cover; 
	min-height:850px; 
	/*min-width:1200px;*/ /* Test comment off */
}

.header{ background:#000000; padding:10px 0; }

.wrapper{ width:auto; margin:0 auto;} /* change width from 1100 to auto*/

.logo-heading{ color:#fff;}

.login-box{ width:382px; padding:30px 30px 0 30px; background:rgba(0,0,0,0.6); min-height:200px; float:right; margin-top:100px; margin-right:100px;}
/*Added margin-right: 100px;*/

/* .login-box .form-group{ margin:10px 0;}
.login-box .form-group input{ width:320px; height:45px; padding:0 10px; line-height:45px; border:none; border-radius:0;}
.login-box .form-group input.username{ background:#fff url(../img/username.jpg) no-repeat left; padding-left:50px;}
.login-box .form-group input.password{ background:#fff url(../img/password.jpg) no-repeat left; padding-left:50px;} */

.form-box {
    margin-top: 20px;
}

.login-box .form-group .btn{ width:100%; margin:10px 0; text-align:center; color:#fff; font-size:17px; text-transform:capitalize;}
.login-box .form-group .btn.black{ background:#131313; }
.login-box .form-group .btn.black:hover{ background:#000000; }
.login-box .form-group a{ text-transform:capitalize; color:#fff;}
.login-box .form-group a.sign-up{ float:right;}
.login-box .form-group a:hover{ text-decoration:underline}
.login-box .login-box-footer{ text-align:right; padding:25px 0; border-top:1px solid #fff; color:#fff; margin-top:25px;}
    
 
    </style>


<!-- <nav class="navbar navbar-expand navbar-dark  static-top"> -->
<sec:authorize access="hasRole('ADMIN') || hasRole('DBA') || hasRole('USER')">
     <!--  <a class="navbar-brand mr-1" href="index.html">BCTS</a> -->

      <!-- <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
        <i class="fas fa-bars"></i>
      </button> -->
	  <%-- <span>Dear <strong>${loggedinuser}</strong>, Welcome to BCTS (GeTS).</span> --%>
      <!-- Navbar -->
      <div class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
      </div>
       <ul class="navbar-nav ml-auto ml-md-0">
       <li class="nav-item dropdown no-arrow">
          <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-user-circle fa-fw"></i>
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/changepwd">Change password</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout" data-toggle="modal" data-target="#logoutModal">Logout</a>
          </div>
        </li>
      </ul>
      
      <%-- <ul class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0" >
        <li class="nav-item dropdown no-arrow">
          <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fas fa-user-circle fa-fw"></i>
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/changepwd">Change password</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout" data-toggle="modal" data-target="#logoutModal">Logout</a>
          </div>
        </li>
      </ul> --%>
</sec:authorize>
   <!--  </nav> -->


    
     <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/logout">Logout</a>
          </div>
        </div>
      </div>
    </div>
 