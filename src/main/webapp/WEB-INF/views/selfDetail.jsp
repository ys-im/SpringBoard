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
								<form name="updateForm" method="POST" action="/selfDetail.do">
									<div class="form-inline mr-auto w-100 navbar-search mb-2">						
										<button class="btn btn-primary" onclick="fnc_delete('${user.userID}');"> 
											<i class="fa fa-trash-alt"></i>&nbsp;삭제
										</button>
										<button class="btn btn-primary mr-0 ml-auto" type="button" onclick="update()"> 
											<i class="fa fa-save"></i>&nbsp;저장
										</button>
									</div>	
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
									<%-- <input type="hidden" id="active" name="active" value="${user.active}">
									<input type="hidden" id="role" name="role" value="${user.role }"> --%>

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
	<script src="/resources/js/custom-common.js"></script>
	<script > 
		/* $(document).ready(function(){
			var role = $("#role").val();
			if(role==1){
				$("input:radio[name='role']:radio[value='1']").prop("checked", true);
			}else if(role==2){
				$("input:radio[name='role']:radio[value='2']").prop("checked", true);
			}			
		
			var active = "${userDetail.active}";
			if(active == "Y"){
				$("#active").val("Y").prop("selected", true);
			}else{
				$("#active").val("N").prop("selected", true);
			}
		}); */
	
		function fnc_delete(userID){
			if(confirm("사용자 '"+userID+"' 를 삭제하시겠습니까?")){							
				location.href = "/userDelete.do?userID="+userID;
			}
		}
		
		function checkPwd(){
			var pw = $("#password").val();
			var r_pw = $("#repeatPassword").val();
			
	        if(r_pw=="" && (pw != r_pw || pw == r_pw)){
	            $("#repeatPassword").css("background-color", "#FFCECE");
	            pwdCheck = 0;
	        }
	        else if (pw == r_pw) {
	            $("#repeatPassword").css("background-color", "#B0F6AC");
	            pwdCheck = 1;
	        } else if (pw != r_pw) {
	            $("#repeatPassword").css("background-color", "#FFCECE");	
	            pwdCheck = 0;            
	        }
		}
		
		function checkEmail(){
			var str = $("#email").val();
			var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			if (regExp.test(str)){
				return true;
			}else {
				return false;
			}
		}
		
		function update(){
			var updateForm = document.updateForm;
			var userID = updateForm.userID.value;
			var password = updateForm.password.value;
			var name = updateForm.name.value;
			var email = updateForm.email.value;			
						
			if(!password){
				alert("비밀번호를 입력해주세요.");
				$("#password").focus();
				return;
			}else if(pwdCheck == 0){
				alert("비밀번호가 일치하지 않습니다.");
				$("#repeatPassword").focus();
				return;
			}
			
			if(!name){
				alert("이름을 입력해주세요.");
				$("#name").focus();
				return;
			}
			
			if(!email){
				alert("이메일을 입력해주세요.");
				$("#email").focus();
				return;
			}
			
			alert("변경 되었습니다");
			updateForm.submit();			
		}
		//공백제거 function
		function fnc_spacebar(obj){
			var str_space = /\s/; //공백체크
			if(str_space.exec(obj.value)){
				alert("해당 항목에는 공백을 사용할 수 없습니다. \n\n공백은 자동적으로 제거 됩니다.");
				obj.focus();
				obj.value = obj.value.replace(' ', '');//공백제거
				return false;
			}
		}
	</script>
</body>
</html>