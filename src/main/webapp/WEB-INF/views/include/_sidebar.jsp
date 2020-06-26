<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	window.onload = function(){
		var role = "${user.role}";
		if(role == 1){
			$("#nav2").attr("style", "visibility:visible;");
		}else{
			$("#nav2").attr("style", "visibility:hidden;");
		}
	}
</script>
	
	<ul
		class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
		id="accordionSidebar">

		<!-- Sidebar - Brand -->
		<li>
			<a class="sidebar-brand d-flex align-items-center justify-content-center" href="#">
				<span>GI-ENS</span>
			</a>
		</li>

		<!-- Divider -->
		<li><hr class="sidebar-divider my-0"></li>

		<!-- Nav Item - Dashboard -->
		<li class="nav-item" id="nav1">
			<a class="nav-link" href="/board.do"> 
				<i class="fa fa-fw fa-list"></i> <span>게시판</span>
			</a>
		</li>

		<!-- Divider -->
		<li><hr class="sidebar-divider my-0"></li>

		<!-- Nav Item - Pages Collapse Menu -->
		<li class="nav-item" id="nav2">
		<a class="nav-link collapsed" href="#"
			data-toggle="collapse" data-target="#collapseTwo"
			aria-expanded="true" aria-controls="collapseTwo"> 
			<i class="fa fa-fw fa-cog"></i> <span>관리</span>
		</a>
			<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
				data-parent="#accordionSidebar">
				<div class="bg-white py-2 collapse-inner rounded">
					<a class="collapse-item" href="/user.do">
						<i class="fa fa-users-cog fa-fw"></i>&nbsp; 사용자 관리
					</a> 
					<a class="collapse-item" href="/loginHistory.do">
						<i class="fa fa-user-clock"></i>&nbsp; 로그인 이력
					</a>
				</div>
			</div>
		</li>
	</ul>