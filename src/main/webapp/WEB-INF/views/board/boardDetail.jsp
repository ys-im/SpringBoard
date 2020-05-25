<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<%@include file="../include/_sidebar.jsp"%>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<%@include file="../include/_topbar.jsp"%>
				<!-- End of Topbar -->
				
				<div class="container-fluid">
					<h1 class="h4 mb-2 text-gray-800">게시글 상세보기</h1>
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<div class="form-inline mr-auto w-100 navbar-search mb-2">								
									<button class="btn btn-primary mr-2" onclick="fnc_editView()"> 
										<i class="fa fa-wrench"></i>&nbsp;수정
									</button>							
									<button class="btn btn-primary" onclick="fnc_delete()"> 
										<i class="fa fa-trash-alt"></i>&nbsp;삭제
									</button>
									<button class="btn btn-primary mr-0 ml-auto" onclick="fnc_writeReply()"> 
										<i class="fa fa-pen"></i>&nbsp;답글 작성
									</button>
								</div>
								<hr class="mb-1"/>
								<div class="form-inline mr-auto w-100 mb-2">
									<strong class="ml-2" id="title"></strong>
									<a class="mr-0 ml-auto" id="regDate"></a>
								</div>	
								<div class="form-inline mr-auto w-100 mb-2">
									<a class="mr-0 ml-auto" id="userName"></a>
								</div>
								<hr class="mb-1">
								<!-- 첨부파일목록 -->
								<c:forEach var="file" items="${fileList}">
									<div class="form-inline mr-auto w-100 mb-2 small">
										<a href="#" class="mr-0 ml-auto" onclick="fnc_fileDown(${file.FILE_NO}); return false;">${file.FILE_NAME}</a>
									</div>
								</c:forEach>
								
								<hr class="mb-1">
								<!-- Toast UI viewer Start -->								
								<div id="viewer" class="form-control border-bottom-primary mb-2">
								</div>	
								<!-- End of Toast UI viewer-->								
							</div>
							
							<hr class="mb-1"/>
							<div class="table-responsive">
								<div id="replyArea" class="mr-4 ml-4">
									<strong>답글</strong> 
									<div id="replyList">
									</div>
								</div>
							</div>
						</div>						
					</div>					
				</div>
			</div>
			<!-- End of Main Content -->
			
			<!-- download form -->
			<section id="container">
				<form name="readForm" role="form" method="post">
					<input type="hidden" id="page" name="page" value="${searchCriteria.page}">
					<input type="hidden" id="perPageNum" name="perPageNum" value="${searchCriteria.perPageNum}">
					<input type="hidden" id="searchType" name="searchType" value="${searchCriteria.searchType}">
					<input type="hidden" id="keyword" name="keyword" value="${searchCriteria.keyword}">
				</form>
			</section>
			
			<!-- Footer -->
			<%@include file="../include/_footer.jsp"%>
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
	<%@include file="../include/_logoutModal.jsp" %>

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
		var today = new Date();
		var year = today.getFullYear();
		var month = today.getMonth()+1;
		if(month.toString().length === 1){
			month = '0'+month;
		}
		var date = today.getDate();
		today = year+"-"+month+"-"+date;
	
		var title;
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
					title = result[0].title;
					var contents = result[0].contents;
					var regDate = result[0].regDate;
					for(var i in result){						
						if(i > 0){
							var str='<div class="form-control w-100 mb-1" id="reply_'+i+'">'
									+'<div class="form-inline">';
							var replyCnt = result[i].replyCnt;
							var boardNo = result[i].boardNo;
							var fileCnt = result[i].fileCnt;
							var replyRegDate = result[i].regDate.substr(0, 10);
							
							if(replyCnt > 0){
								str += '<a href="/boardDetail.do?boardNo='+boardNo+'" id="replyTitle_'+boardNo+'"><b>'+result[i].title+'</b></a>'
										+'&nbsp&nbsp<b>['+replyCnt+']</b>';									
							}else{
								str += '<a href="/boardDetail.do?boardNo='+boardNo+'" id="replyTitle_'+boardNo+'">'+result[i].title+'</b>';
							}
							
							if(fileCnt > 0){
								str += "&nbsp;<i class='fas fa-paperclip'></i>";
							}
							
							if(today == replyRegDate){
								str += "&nbsp;<img src='/resources/img/icons/icon_new_cmt.gif' alt='새글'>";
							}
							str += '<a class="mr-0 ml-auto">'+result[i].userID+'&nbsp&nbsp'+result[i].regDate+'</a>'
									+'</div><hr>'
									+'<div id="viewer_'+boardNo+'">'+result[i].contents+'</div>'
									+'</div>';
							$("#replyList").append(str);
							
							var vw = new toastui.Editor({
								el : document.querySelector("#viewer_"+boardNo),
								viewer: true,
								initialValue: result[i].contents
							});
						}			
					}
					$("#title").html(title);
					$("#regDate").html(regDate);
					if(regDate.substr(0, 10) == today){
						$("#title").html(title+"&nbsp;<img src='/resources/img/icons/icon_new_cmt.gif' alt='새글'>");
					}
					$("#userName").html("작성자");
					
					/*************************************************************** viewwer  */
					var viewer = new toastui.Editor({
						el : document.querySelector('#viewer'),
						viewer: true,
						minHeight : '500px',
						initialValue : contents
					});
				},
				error : function(xhr, status, error){
					console.log(error);
				}
			});
		};
		
		/************************************************* 게시글 수정페이지 이동 */
		function fnc_editView(){
			var boardNo = getParameterByName("boardNo");
			location.href='/editView.do?boardNo='+boardNo;
		}
		
		/************************************************** 게시글 삭제 */
		function fnc_delete(){
			var result;
			var replyListCount = $("#reply_1").length;
			if(replyListCount > 0){
				alert("'"+$("#title").text()+"' 에 답글이 있어 삭제할수 없습니다.");
				return;
			}else{
				result = confirm("이 글을 삭제하시겠습니까?");
				
				if(result){
					var boardNo = getParameterByName("boardNo");
					location.href="/deleteBoard.do?boardNo="+boardNo;				
				}
			}
			
		}
		
		/************************************************** 답글작성페이지 이동 */
		function fnc_writeReply(){
			var boardNo = getParameterByName("boardNo");
			location.href="/writeView.do?pBoardNo="+boardNo+"&title="+title;
		}
		
		/************************************************** file download */
		function fnc_fileDown(fileNo){  
			var formObj = $("form[name='readForm']");
			$("#FILE_NO").attr("value", fileNo);
			formObj.attr("action", "/fileDown.do");
			formObj.submit();
		}
		
		/************************************************** function parameter값 받기  */		
		function getParameterByName(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		        results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
		}
		
		/************************************************** 답글달기 클릭*/
		function fnc_replyClick(num){
			location.href='/boardDetail.do?boardNo='+num;
		}
		
	</script>
</body>
</html>