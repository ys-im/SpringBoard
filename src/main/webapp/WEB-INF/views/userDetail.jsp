<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 상세보기</title>
<!-- Custom fonts for this template -->
<link href="/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/styles/github.min.css">

<!-- Custom styles for this template -->
<link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
<link rel="stylesheet" href="/resources/css/toastui-editor-viewer.css" />
</head>
<body id="page-top">
	<div id="wrapper">
		<!-- Sidebar -->
		<%@include file="include/_sidebar.jsp"%>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<%@include file="include/_topbar.jsp"%>
				<!-- End of Topbar -->
				
				<div class="container-fluid">
					<h1 class="h4 mb-2 text-gray-800">사용자 상세보기</h1>
					<div class="card shadow mb-4">
						<div class="card-body">
							<div>
								<div class="form-inline mr-auto w-100 navbar-search mb-2">						
									<button class="btn btn-primary" onclick="fnc_delete('${user.userID}');"> 
										<i class="fa fa-trash-alt"></i>&nbsp;삭제
									</button>
									<button class="btn btn-primary mr-0 ml-auto" onclick="fnc_save('${user.userID}');"> 
										<i class="fa fa-save"></i>&nbsp;저장
									</button>
								</div>	
								<form name="registForm" method="POST" action="/regist.do">
									<div class="form-group row">
										<div class="form-inline col-sm-2">아이디</div>	
										<div class="col-sm-10">
											<input type="text" class="form-control form-control-user" readonly 
												id="userID" name="userID" value="${user.userID}">
										</div>
									</div>
									
									<div class="form-group row">
										<div class="form-inline col-sm-2">비밀번호 변경</div>
										<div class="col-sm-10">
											<input type="password" class="form-control form-control-user" 
												id="password" name="password" oninput="checkPwd()" onkeyup="fnc_spacebar(this);" onchange="fnc_spacebar(this);" placeholder="Password">
										</div>
									</div>

									<div class="form-group row">
										<div class="form-inline col-sm-2">비밀번호 변경 확인</div>
										<div class="col-sm-10">
											<input type="password" class="form-control form-control-user" 
												id="repeatPassword" name="repeatPassword" oninput="checkPwd()" placeholder="Repeat Password">
										</div>
									</div>

									<div class="form-group row">
										<div class="form-inline col-sm-2">이름</div>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="name" name="name" value="${user.name}">
										</div>
									</div>
									
									<div class="form-group row">
										<div class="form-inline col-sm-2">이메일</div>
										<div class="col-sm-10">
											<input type="email" class="form-control" id="email" name="email" oninput="checkEmail()" 
												 onkeyup="fnc_spacebar(this);" onchange="fnc_spacebar(this);" value="${user.email}">
										</div>
									</div>
									
									<div class="form-group row">
										<div class="form-inline col-sm-2">활동상태</div>
										<div class="col-sm-10">
											<select id="active" class="form-control">
												<option value="Y">활동</option>
												<option value="N">비활동</option>
											</select>
										</div>
									</div>
									
									<div class="form-group row">
										<label class="form-inline col-sm-2">관리자 입니까?</label> 
										<label class="radio-container m-r-55 col-sm-2">네 
											<input type="radio" name="role" value="1"> 
											<span class="checkmark"></span>
										</label> 
										<label class="radio-container">아니오
											<input type="radio" name="role" value="2"> 
											<span class="checkmark"></span>
										</label>
									</div>
								</form>						
							</div>
						</div>						
					</div>					
				</div>
			</div>
			<!-- End of Main Content -->
			
			<!-- Footer -->
			<%@include file="include/_footer.jsp"%>
			<!-- End of Footer -->
		
		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->
	
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> 
		<i class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<%@include file="include/_logoutModal.jsp" %>

	<!-- Bootstrap core JavaScript-->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin-2.min.js"></script>
	
	<!-- <script src="/resources/js/md-default.js"></script> -->
	
	<!-- Viewer -->
    <script src="/resources/js/toastui-editor-viewer.js"></script>
	<!-- Page level custom scripts -->
	<script > 
		$(document).ready(function(){
			var role = ${user.role};
			if(role==1){
				$("input:radio[name='role']:radio[value='1']").prop("checked", true);
			}else if(role==2){
				$("input:radio[name='role']:radio[value='2']").prop("checked", true);
			}
		});
	
		function fnc_save(userID){
			if(confirm("사용자 '"+userID+"'의 정보를 변경하시겠습니까?")){
				location.href = "/userEdit.do?userID="+userID;
			}
		}
	
		function fnc_delete(userID){
			if(confirm("사용자 '"+userID+"' 를 삭제하시겠습니까?")){							
				location.href = "/userDelete.do?userID="+userID;
			}
		}
		
	</script>
</body>
</html>