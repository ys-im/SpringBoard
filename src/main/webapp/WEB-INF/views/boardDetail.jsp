<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
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
					<h1 class="h4 mb-2 text-gray-800">게시글 상세보기</h1>
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								
								<!-- Toast UI viewer Start -->
								<div id="viewer">
								</div>
								<!-- End of Toast UI viewer-->
								
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
	
	<script src="/resources/js/md-default.js"></script>
	
	<!-- Viewer -->
    <script src="/resources/js/toastui-editor-viewer.js"></script>
	<!-- Page level custom scripts -->
	<script >    
		var viewer = new toastui.Editor({
			el : document.querySelector('#viewer'),
			viewer: true,
			height : '500px',
			initialValue : content
		});
		
		/************************************************** viewer 데이터 */		
		window.onload = function() {
			var boardNo = getParameterByName("boardNo");
			var data = {"boardNo":boardNo*1};
			
			$.ajax({
				url : "/ajax/toastBoardDetail.do",
				method : "POST",
				data : data,
				dataType : "json",
				success : function(result) {
					console.log(result);				
				},
				error : function(xhr, status, error){
					console.log(error);
				}
			});
		};
		

		/************************************************** function parameter값 받기  */		
		function getParameterByName(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		        results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
		}
		
	</script>
</body>
</html>