<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!-- Topbar -->
<nav
	class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

	<!-- Sidebar Toggle (Topbar) -->
	<button id="sidebarToggleTop"
		class="btn btn-link d-md-none rounded-circle mr-3">
		<i class="fa fa-bars"></i>
	</button>

	<!-- Topbar Search -->
	<form
		class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
		<div class="input-group">
			<span class="h2 my-4 mb-2 text-gray-800">Spring Board</span>
		</div>
	</form>

	<!-- Topbar Navbar -->
	<ul class="navbar-nav ml-auto">
		<!-- Nav Item - User Information -->
		<li class="nav-item dropdown no-arrow">
			<a class="nav-link dropdown-toggle" href="#" id="userDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
			<span class="text-gray-800" style="font-weight: bold">${user.userID}
				<i class="fa fa-user fa-fw"></i>
			</span>
		</a> <!-- Dropdown - User Information -->
			<div
				class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
				aria-labelledby="userDropdown">
				<a class="dropdown-item" href="/selfDetailView.do"> <i
					class="fas fa-user fa-sm fa-fw mr-2 text-gray-800"></i> 프로필</a>
				<a class="dropdown-item" href="/loginHistory.do?userID=${user.userID}"> <i
					class="fas fa-history fa-sm fa-fw mr-2 text-gray-800"></i> 로그기록
				</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="#" data-toggle="modal"
					data-target="#logoutModal"> <i
					class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-800"></i>
					로그아웃
				</a>
			</div>
		</li>
	</ul>
</nav>
<!-- End of Topbar -->