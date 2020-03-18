<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>게시판</title>

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
.btn-wrapper {
	margin-bottom: 10px;
}
</style>
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<%@include file="include/_sidebar.jsp" %>	
		<!-- End of Sidebar -->	

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<%@include file="include/_topbar.jsp" %>				
				<!-- End of Topbar -->

				<!-- Toast UI grid Start -->
				<div class="container-fluid">
					<h1 class="h4 mb-2 text-gray-800">답글게시판</h1>
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<div class="form-inline mr-auto w-100 navbar-search mb-2">
									<input type="search" class="form-control bg-light small"
										placeholder="검색어를 입력하세요." aria-label="Search">
									<div class="input-group-append">
										<a class="btn btn-primary" href="#">
											<i class="fas fa-search fa-sm"></i>
										</a>
									</div>
									
									<a class="btn btn-primary mr-0 ml-auto" href="/write">
										<i class="fa fa-edit"></i>&nbsp;글쓰기
									</a>
								</div>
								<div class="code-html contents">
									<div id="grid"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- End of Toast UI grid-->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->			
			<%@include file="include/_footer.jsp" %>	
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
	<%@include file="include/_logoutModal.jsp" %>

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
	<script src="/resources/js/data/basic-dummy.js"></script>

	<!-- Page level custom scripts -->
	<script type="text/javascript">
		var grid = new tui.Grid({
			el : document.getElementById('grid'),
			data : gridData,
			scrollX : false,
			scrollY : false,
			columns : [ {
				header : 'Name',
				name : 'name',
				sortable : true
			}, {
				header : 'Artist',
				name : 'artist',
				sortable : true
			}, {
				header : 'Type',
				name : 'type',
				sortable : true
			}, {
				header : 'Release',
				name : 'release',
				sortable : true
			}, {
				header : 'Genre',
				name : 'genre',
				sortable : true
			} ],

			rowHeaders : [ 'rowNum' ],
			pageOptions : {
				useClient : true,
				perPage : 20
			}
		});
		
		tui.Grid.applyTheme('default', {
			outline: {
				border: '#C0C0C0',
				showVerticalBorder: true
			},
			cell: {
				normal: {
					background: '#FFF',
					border: '#C0C0C0',
					showVerticalBorder: true,
					showHorizontalBorder: true,
					text: '#000'
				},
				header: {
					background: '#E0E0E0',
					border: '#A0A0A0',
					showVerticalBorder: true,
					showHorizontalBorder: true
				},
				rowHeader:{
					background: '#E0E0E0',
					border: '#A0A0A0',
					showVerticalBorder: true,
					showHorizontalBorder: true
				}
			}
		});
		
		
	</script>

</body>

</html>