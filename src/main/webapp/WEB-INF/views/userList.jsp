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

<title>사용자 관리</title>

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
					<h1 class="h4 mb-2 text-gray-800">사용자 리스트</h1>
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<div class="form-inline mr-auto w-100 navbar-search mb-2"
									id="div">
									<select name="searchOption" id="searchOption"
										class="form-control bg-light small mr-1"
										onchange="checkActive(this.value);">
										<option value="userID">아이디</option>
										<option value="name">이름</option>
										<option value="active">활동상태</option>
									</select> 
									<select name="option1" id="option1"
										class="form-control bg-light small mr-1"
										onchange="activeSearch(this.value);" style="display: none;">
										<option value="">전체</option>
										<option value="Y">활성화</option>
										<option value="N">비활성화</option>
									</select> <input type="search" id="search"
										class="form-control bg-light small" placeholder="검색어를 입력하세요."
										aria-label="Search" onkeyup="enterKey();">&nbsp;
									<div class="input-group-append">
										<button type="button" class="btn btn-primary"
											id="searchButton">
											<i class="fas fa-search fa-sm"></i>
										</button>
									</div>

									<a class="btn btn-primary mr-0 ml-auto" href="/registView.do">
										<i class="fa fa-plus"></i>&nbsp;사용자 등록
									</a>
								</div>

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
	<script src="/resources/js/custom-common.js"></script>
	<script>
		/************************************************** 사용할 변수 선언 */
		var dataSource;		
		var pagiantion;
		
		var startPage = "${pageMaker.startPage}"*1;
		var perPageNum = "${pageMaker.perPageNum}"*1;
		var totalCount = "${pageMaker.totalCount}"*1;
		
		var searchTypeInfo;
		var searchKeyword;	
	
		/************************************************** renderer class */
		//제목 컬럼에 a tag 추가
		class ShowDetail {
			constructor(props){
				this.props = props;
				const aTag = document.createElement('a');
				let userID = props.value;	
				
				aTag.innerHTML = userID;
				aTag.style = "margin-left: 10px";
				aTag.href = "/userDetail.do?userID="+userID;
				
				this.aTag = aTag;
				this.render(props);
			}
			
			getElement(){
				return this.aTag;
			}
			
			render(props){
				this.aTag.value = String(props.value);
			}
		}
		//사용자 삭제 버튼
		class UserDelete{
			constructor(props){
				this.props = props;				
				const el = document.createElement("button");
				let delFlag = props.value;
				let userID = props.grid.getValue(props.rowKey, "userID");
			
				el.style="font-size : 0.8rem;";		
				el.className = "btn btn-primary mt-1 mb-1";
				el.innerHTML = "삭제";	
				
				el.addEventListener('click', function(){
					if(confirm("사용자 '"+userID+"' 를 삭제하시겠습니까?")){							
						location.href = "/userDelete.do?userID="+userID;
					}
				});
				
				
				this.el = el;
				this.render(props);
			}
			
			getElement(){
				return this.el;
			}
			
			render(props){
				this.el.value = String(props.value);
			}
		}
		
		//사용자 중지 버튼
		class ShowActiveCheck {
			constructor(props) {
				this.props = props;
				const el = document.createElement("button");
				let active = props.value;
				console.log("active :"+active);
				let userID = props.grid.getValue(props.rowKey,"userID");
				el.style="font-size : 0.8rem;";
				el.className = "btn btn-primary mt-1 mb-1";

				
 				if(active == 'N'){
					el.innerHTML = "N";
					
					el.addEventListener('click',function(){
						if(confirm("사용자'"+userID+"'를 활성화시키겠습니까?")){
							console.log("여기 active : "+active);
							location.href="/userActive.do?active="+active+"&userID="+userID;
						}
					});

				}else{
					el.innerHTML = "Y";
					
					el.addEventListener('click',function(){
						if(confirm("사용자'"+userID+"'를 비활성화시키겠습니까?")){
							console.log("여기 active : "+active);
							location.href="/userActive.do?active="+active+"&userID="+userID;
						}
					});

				}
				
				
				
				this.el = el;
				this.render(props);
			}
			getElement(){
				return this.el;
			}
			render(props){
				this.el.value = String(props.value);
			}
			
		}
		
		
		/************************************************** grid 디자인 */
		tui.Grid.applyTheme('default', {
			outline : {
				border : '#C0C0C0',
				showVerticalBorder : true
			},
			area : {
				header : {
					background : "#C0C0C0"
				}
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
			el : document.getElementById('grid'), //그리드가 생성될 컨테이너 HTML
			scrollX : false,
			scrollY : false,
			columns : [ {		//컬럼 정보 배열		
				header : '사용자 이름', //컬럼 헤더
				name : 'name', //필수속성 row 데이터의 key
				align : 'center', 			
				sortable : true
			}, {				
				header : '사용자 ID',
				name : 'userID',
				align : 'center',
				sortable : true,
				renderer : { //
					type: ShowDetail
				}
			},  {				
				header : '활동 상태',
				name : 'active',
				align : 'center',
				sortable : true,
				renderer : {
					type : ShowActiveCheck
				}
			}, {
				header : '등록일',
				name : 'regDate',
				align : 'center',
				sortable : true
			}, {
				header : '사용자 삭제',
				name : 'delFlag',
				align : 'center',
				width : '100',
				renderer : {
					type: UserDelete //생성자 함수
				}
			}],
			
			rowHeaders: [
				//{type :'checkbox'},
				{type :'rowNum'}
			]
		});
		
		$(document).ready(function(){
			fnc_readGridData(startPage, perPageNum);
		});
		
		$("#searchButton").click(function(){
			searchTypeInfo = $("select option:selected").val();
			searchKeyword = $("#search").val();
			//console.log(searchTypeInfo+", "+searchKeyword);
			fnc_readGridData(startPage, perPageNum);
		});
		
		function fnc_readGridData(startPage, perPageNum){
			$.ajax({
				url : "/ajax/userListCount.do",
				method : "GET",
				async : false,
				data : {page:startPage, perPage:perPageNum, searchType:searchTypeInfo, keyword:searchKeyword},
				dataType : "json",
				success : function(result) {
					totalCount = result.totalCount;
					$('#tnt').append(totalCount);
				},
				error : function(xhr, status, error){
					console.log("code: "+xhr.status);
					console.log("message: "+xhr.responseText);
					console.log("error: "+error);
				}
			});
			
			$.ajax({				
				url : "/ajax/userList.do",
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
		
		
		
	//활동상태 선택 시,
	function checkActive(){
		var chk = $('#searchOption option:selected').val();
		console.log("chk : "+chk);
		var div1 ="";
		
		if(chk != 'active'){
			$('#search').show();
			$('#searchButton').show();
			$('#option1').hide();

		}else{
 			$('#search').hide();
 			$('#searchButton').hide();
			$('#option1').show();
		}
	}
	//활동여부 선택 시,	
	function activeSearch(){
		searchTypeInfo = $("select option:selected").val();
		searchKeyword = $('#option1 option:selected').val();
		console.log("activeSearch : "+searchTypeInfo+", "+searchKeyword);
		fnc_readGridData(startPage, perPageNum);

		
	}

	
	</script>
</body>
</html>