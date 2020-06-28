<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>로그인</title>

<!-- Custom fonts for this template-->
<link href="/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="container-login">
				<!-- Nested Row within Card Body -->
				<div class="bg-login-image js-tilt" data-tilt>
					<img src="/resources/img/img-01.png" alt="IMG">
				</div>
				<form class="user" name="loginForm" method="post" action="/login.do">
					<div class="text-center">
						<h2>로그인</h2>
					</div>
					<hr>
					<div class="form-group">
						<input type="text" class="form-control form-control-user"
							id="userID" name="userID" placeholder="ID">
					</div>
					<div class="form-group">
						<input type="password" class="form-control form-control-user"
							id="password" name="password" placeholder="Password">
					</div>
					<div class="form-group">
						<div class="custom-control custom-checkbox small">
							<input type="checkbox" class="custom-control-input"
								id="customCheck"> <label class="custom-control-label"
								for="customCheck">Remember Me</label>
						</div>
					</div>
					<button class="btn btn-primary btn-user btn-block" type="submit">Login</button>
					<hr>

					<!--					
 					<div class="text-center">
						<a class="small" href="forgot-password.html">Forgot Password?</a>
					</div> 
					-->

					<c:if test="${user != null }">
						<div>
							<p>${user.userId}님환영합니다.</p>
							<button id="logoutBtn" type="button">로그아웃</button>
						</div>
					</c:if>
					<c:if test="${msg == false}">
						<p style="color: red;">로그인 실패! 아이디와 비밀번호 확인해주세요.</p>
					</c:if>


				</form>

			</div>

		</div>

	</div>
	
	
	
	

	<!-- Bootstrap core JavaScript-->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<script src="/resources/vendor/tilt/tilt.jquery.min.js"></script>
	<script>
		$('.js-tilt').tilt({
			scale : 1.1
		})
	</script>
	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin-2.min.js"></script>
	<script src="/resources/js/custom-common.js"></script>

</body>

</html>
