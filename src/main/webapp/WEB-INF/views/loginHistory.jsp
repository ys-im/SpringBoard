<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>로그인/로그아웃 내역</title>

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

.modal-addUser {
	max-width: 1000px;
	margin: 1.75rem auto;
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
					<h1 class="h4 mb-2 text-gray-800">로그인 / 로그아웃 내역</h1>
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<div class="form-inline mr-auto w-100 navbar-search mb-2">
									<!-- <select name="searchOption" id="searchOption" class="form-control bg-light small mr-1">
										<option value="userID">아이디</option>
										<option value="userName">이름</option>
									</select> -->
									<div class="mr-3">아이디 : </div>
									<input type="search" id="search" class="form-control bg-light small"
										placeholder="검색어를 입력하세요." aria-label="Search"  onkeyup="enterKey();">
									<div class="input-group-append">
										<button type="button" class="btn btn-primary" id="searchButton">
											<i class="fas fa-search fa-sm"></i>
										</button>
									</div>
								</div>
								<input type="hidden" id="profile" name="profile" value="${profile}">
								
								<!-- Toast UI grid Start -->
								<div class="code-html contents">
									<div id="grid"></div>
									<div id="pagination" class="tui-pagination"></div>
								</div>
								<!-- End of Toast UI grid-->		
							</div>
						</div>
					</div>				
				</div>		
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
	
	<!-- Page level custom scripts -->
	<script src="/resources/js/custom-common.js"></script>
	<script>
		/************************************************** 사용할 변수 선언 */
		var dataSource;		
		var pagiantion;
		
		var startPage = "${pageMaker.startPage}"*1;
		var perPageNum = "${pageMaker.perPageNum}"*1;
		var totalCount = "${pageMaker.totalCount}"*1;
		
		var searchTypeInfo = "userID";
		var searchKeyword = $("#search").val();	
	
		/************************************************** class showDetail -> 제목 컬럼에 a tag 추가 */
		class ShowDetail {
			constructor(props){
				this.props = props;
				const aTag = document.createElement('a');
				let userID = props.value;
								
				aTag.innerHTML = userID;
				aTag.href = "javascript:fnc_user_click('"+userID+"');";
				aTag.className = "uID";
				aTag.style = "margin-left: 10px";
				
				this.aTag = aTag;
				this.render(props);
			}
			
			getElement(){
				return this.aTag;
			}
			
			render(props){
				this.aTag.uID = String(props.value);
			}
		}
		
		/************************************************** grid 디자인 */
		tui.Grid.applyTheme('default', {
			outline : {
				border : '#C0C0C0',
				showVerticalBorder : true
			},
			cell : {
				normal : {
					background : '#FFF',
					border : '#C0C0C0',
					showVerticalBorder : true,
					showHorizontalBorder : true,
					text : '#000'
				},
				header : {
					background : '#E0E0E0',
					border : '#A0A0A0',
					showVerticalBorder : true,
					showHorizontalBorder : true
				},
				rowHeader : {
					background : '#E0E0E0',
					border : '#A0A0A0',
					showVerticalBorder : true,
					showHorizontalBorder : true
				}
			}
		});
		
		/************************************************** grid 양식 */	
		
		if(perPageNum <= 0 || perPageNum > 100){
			perPageNum = 20;
		}
		
		var grid = new tui.Grid({
			el : document.getElementById('grid'),
			scrollX : false,
			scrollY : false,
			columns : [{
				header : 'No.',
				name : 'rowNo',
				width : '50',
				minWidth : '30',
				align : 'center'
			}, {				
				header : '사용자 ID',
				name : 'userID',
				align : 'center',			
				sortable : true,
				renderer : {
					type : ShowDetail
				}
			}, {				
				header : '로그인 시간',
				name : 'loginTime',
				align : 'center',			
				sortable : true
			}, {				
				header : '로그아웃 시간',
				align : 'center',
				name : 'logoutTime',			
				sortable : true
			}, {
				header : '접속 IP',
				name : 'ipAddress',
				align : 'center'
			}]
		});
		
		$(document).ready(function(){			
			if($("#profile").val() == "true"){
				var userID = $("#userDropdown span").text().trim();
				fnc_user_click(userID);
			} else{
				fnc_readGridData(startPage, perPageNum);
			}
		});
		
		$("#searchButton").click(function(){
			searchTypeInfo = "userID";
			searchKeyword = $("#search").val();
			
			fnc_readGridData(startPage, perPageNum);
		});
		
		function fnc_user_click(userID){
			$("#search").val(userID);
			$("#searchButton").click();
		}
		
		function fnc_readGridData(startPage, perPageNum){
			$.ajax({
				url : "/ajax/loginHistoryListCount.do",
				method : "GET",
				async : false,
				data : {page:startPage, perPage:perPageNum, searchType:searchTypeInfo, keyword:searchKeyword},
				dataType : "json",
				success : function(result) {
					totalCount = result.totalCount;
				},
				error : function(xhr, status, error){
					console.log("code: "+xhr.status);
					console.log("message: "+xhr.responseText);
					console.log("error: "+error);
				}
			});
			
			$.ajax({				
				url : "/ajax/loginHistoryList.do",
				method : "GET",
				async : false,
				data : {page:startPage, perPage:perPageNum, searchType:searchTypeInfo, keyword:searchKeyword},
				dataType : "json",
				success : function(result) {
					dataSource = result;
				},
				error : function(xhr, status, error){
					console.log("code: "+xhr.status);
					console.log("message: "+xhr.responseText);
					console.log("error: "+error);
				}
			});	
						
			grid.resetData(dataSource);
			
			
			pagination = new tui.Pagination('pagination', {
				totalItems : totalCount,
				itemsPerPage : 20,
				visiblePages : 10,
				firstItemClassName: 'tui-first-child',
			    lastItemClassName: 'tui-last-child',
			    template: {
			    	page: '<a href="javascript;" class="tui-page-btn" onclick="fnc_readGridData({{page}}, '+perPageNum+')">{{page}}</a>',
			        currentPage: '<strong class="tui-page-btn tui-is-selected">{{page}}</strong>',
			        moveButton:
			             '<a href="javascript;" class="tui-page-btn tui-{{type}}" onclick="fnc_getType(this, '+perPageNum+')">' +
			                 '<span class="tui-ico-{{type}}">{{type}}</span>' +
			             '</a>',
			        disabledMoveButton:
			             '<span class="tui-page-btn tui-is-disabled tui-{{type}}">' +
			                 '<span class="tui-ico-{{type}}">{{type}}</span>' +
			             '</span>'
			     }
			});
		}
		
		function fnc_getType(obj, perPageNum){
			var className = obj.className;
			var movePage = pagination.getCurrentPage();
			if(className === "tui-page-btn tui-next"){
				fnc_readGridData(movePage+1, perPageNum);
			}else if(className === "tui-page-btn tui-prev"){
				fnc_readGridData(movePage-1, perPageNum);
			}else if(className === "tui-page-btn tui-first"){
				fnc_readGridData(1, perPageNum);
			}else if(className === "tui-page-btn tui-last"){
				var lastPage = Math.ceil(totalCount/perPageNum);
				fnc_readGridData(lastPage, perPageNum);
			}
		}
		
		//enterKey 검색
		function enterKey(){
			if(window.event.keyCode == 13){
				$('#searchButton').click();
			}
		}	

	</script>
</body>
</html>