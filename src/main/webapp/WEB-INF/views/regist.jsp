<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>사용자 등록</title>

<!-- Custom fonts for this template -->
<link href="/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
<link href="/resources/css/tui-grid.css" rel="stylesheet">
<link href="/resources/css/tui-pagination.css" rel="stylesheet">

<style>
.input-group {
	margin: 0;
}
</style>
</head>

<body id="page-top">
	<!-- Page Wrapper -->
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
					<h1 class="h4 mb-2 text-gray-800">GI E&S 사용자등록</h1>
					<div class="card shadow mb-4">
						<div class="card-body">
							<div>
								<form name="registForm" method="POST" action="/regist.do">
									<div class="form-group row">
										<div class="form-inline col-sm-2">아이디</div>	
										<div class="col-sm-8">
											<input type="text" class="form-control form-control-user" 
												id="userID" name="userID" placeholder="ID" onkeyup="fnc_spacebar(this);" onchange="fnc_spacebar(this);">												
											<span id="idCheckMessage" class="small">아이디 중복체크 검사 메시지</span>
										</div>
										<button type="button" class="btn btn-primary mr-3 ml-auto" id="idCheck">중복검사</button><br>
									</div>
									<div class="form-group row">
										<div class="form-inline col-sm-2">비밀번호</div>
										<div class="col-sm-10">
											<input type="password" class="form-control form-control-user" 
												id="password" name="password" oninput="checkPwd()" onkeyup="fnc_spacebar(this);" onchange="fnc_spacebar(this);" placeholder="Password">
										</div>
									</div>

									<div class="form-group row">
										<div class="form-inline col-sm-2">비밀번호 확인</div>
										<div class="col-sm-10">
											<input type="password" class="form-control form-control-user" 
												id="repeatPassword" name="repeatPassword" oninput="checkPwd()" placeholder="Repeat Password">
										</div>
									</div>

									<div class="form-group row">
										<div class="form-inline col-sm-2">이름</div>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="name" name="name" placeholder="Name">
										</div>
									</div>
									<div class="form-group row">
										<div class="form-inline col-sm-2">이메일</div>
										<div class="col-sm-10">
											<input type="email" class="form-control" id="email" name="email"
												 placeholder="E-mail / ex) abc@abc.">
										</div>
									</div>
									<div class="form-group row">
										<label class="form-inline col-sm-2">관리자 입니까?</label> 
										<label class="radio-container m-r-55 col-sm-2">네 
											<input type="radio" name="role" value="1"> 
											<span class="checkmark"></span>
										</label> 
										<label class="radio-container">아니오
											<input type="radio" checked="checked" name="role" value="2"> 
											<span class="checkmark"></span>
										</label>
									</div>
									<div class="form-group row">
										<button class="btn btn-primary mr-3 ml-auto" type="button" id="regist" onclick="signUp()">
											<i class="fa fa-user-plus mr-3"></i>등록
										</button>
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
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<%@include file="include/_logoutModal.jsp"%>

	<!-- Bootstrap core JavaScript-->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="/resources/js/tui-code-snippet.js"></script>
	<script src="/resources/js/tui-pagination.js"></script>
	<!--  -->
	<script src="/resources/js/tui-grid.js"></script>

	<!-- Page level custom scripts -->
	<script>
		var idCheck = 0;
		var pwdCheck = 0;
		$("#idCheck").click(function(){
			var idCheckValue;
			
			var userIdValue = $("#userID").val().trim();
			console.log(userIdValue);
				
			
			$.ajax({
				url : "/idCheck.do?userID="+$("#userID").val(),
				method: "GET",
				dataType: "json",
				success : function(result) {
					idCheckValue = result;
					if(idCheckValue == 0){						
						$("#idCheckMessage").text("사용 가능한 ID 입니다.");
						document.getElementById("idCheckMessage").style.color = "green";
						idCheck = 1;
						return true;
					}else{
						$("#idCheckMessage").text("사용할 수 없는 ID 입니다.");
						document.getElementById("idCheckMessage").style.color = "red";
						idCheck = 0;
						return false;
					}
				},
				error : function(xhr, status, error){
					console.log(error);
				}
			});
		});

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
			var str = $("#userEmail").val();
			var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			if (regExp.test(str)){
				return true;
			}else {
				return false;
			}
		}
		
		function signUp(){
			var registForm = document.registForm;
			var userID = registForm.userID.value;
			var password = registForm.password.value;
			var name = registForm.name.value;
			var email = registForm.email.value;
			var role = $("input[name='role']:checked").val();
			
			if(!userID){
				alert("아이디를 입력해주세요.");
				$("#userID").focus();
				return;
			}else if(idCheck == 0){
				alert("아이디 중복검사를 해주세요.");
				$("#idCheck").focus();
				return;
			}
			
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
			
			registForm.submit();
			
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