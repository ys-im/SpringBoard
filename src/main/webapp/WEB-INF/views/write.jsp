<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>게시판 글쓰기</title>

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
<link href="/resources/css/tui-editor.css" rel="stylesheet">
<link href="/resources/css/tui-editor-contents.css" rel="stylesheet">
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
					<h1 class="h4 mb-2 text-gray-800">게시글 작성</h1>
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<div class="form-inline mr-auto w-100 navbar-search mb-2">
									<span>제목 : <input type="text" id="title" name="title" class="form-control" style="width:40rem"/></span>
									<a class="btn btn-primary mr-0 ml-auto" href="#" id="write"> 
										<i class="fa fa-pen"></i>&nbsp;작성
									</a>
								</div>
								
								<!-- Toast UI grid Start -->
								<div class="contents code-html">
									<div id="editSection"></div>
								</div>								
								<!-- End of Toast UI grid-->
								
								<input type="hidden"id="userID" name="userID" value="ys.im"/>
								<input type="hidden"id="pBoardNo" name="pBoardNo" value="0"/>
							</div>
							<div class="upload-btn-wrapper">
								<input type="file" id="input_file" multiple="multiple" class="btn btn-primary" />
								<button class="upload-btn">
									<i class="fa fa-file"></i>&nbsp;파일 선택
								</button>
							</div>
							<br />
							<form name="uploadForm" id="uploadForm"
								enctype="multipart/form-data" method="post">

								<div id="dropZone"
									style="height: 100px; border-style: dashed; border-color: gray;">
									<div id="fileDragDesc">파일을 드래그 해주세요.</div>


									<table id="fileListTable">
										<tbody id="fileTableTbody">

										</tbody>
									</table>
								</div>

							</form>
							
							<br />
							<div class="form-inline mr-auto w-100 navbar-search mb-2">									
									<a class="btn btn-primary mr-0 ml-auto" href="#"> 
										<i class="fa fa-wrench"></i>&nbsp;수정
									</a>
							</div>
							<div class="form-inline mr-auto w-100 navbar-search mb-2">									
									<a class="btn btn-primary mr-0 ml-auto" href="#"> 
										<i class="fa fa-trash-alt"></i>&nbsp;삭제
									</a>
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

	<!-- Page level plugins -->
	<script src="/resources/js/tui-editor-Editor-full.js"></script>

	<!-- Page level custom scripts -->
	<script type="text/javascript">
		var editor = new tui.Editor({
			el : document.querySelector('#editSection'),
			previewStyle : 'vertical',
			height : '500px',
			initialEditType : 'wysiwyg'
		});
		
		$("#write").click(function(){
			var title = $("#title").val();
			var userID = $("#userID").val();
			var pBoardNo = $("#pBoardNo").val()*1;
			var contents = editor.getValue();
			var data = {"title":title, "userID":userID, "pBoardNo":pBoardNo, "contents":contents};
			
			console.log(data);
			
			$.ajax({
				url:"/write.do",
				type: "POST",
				data: data,
				async: false,
				success: function(data){
					console.log(data);
				},
				error: function(xhr, status, error){
					console.log(error);
				}
			});
		});
		
	</script>

</body>

</html>