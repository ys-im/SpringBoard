<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<title>게시글 수정</title>

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
					<h1 class="h4 mb-2 text-gray-800">게시글 수정</h1>
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<div class="form-group row">
									<div class="form-inline col-sm-1">제목</div> 
									<div class="col-sm-8">
										<input type="text" id="title" name="title" value="${edit.title}" class="form-control" />
									</div>
									<button class="btn btn-primary mr-3 ml-auto" id="edit"> 
										<i class="fa fa-pen"></i>&nbsp;수정
									</button>
								</div>
								
								<!-- Toast UI grid Start -->
								<div class="contents code-html">
									<div id="editSection"></div>
								</div>								
								<!-- End of Toast UI grid-->
								
							</div>
							
							<div class="upload-btn-wrapper mb-2 mt-2">
								<input type="file" id="input_file" multiple="multiple" class="btn btn-primary" />
							</div>							
							<!-- File Upload Form -->
							<div class="form-inline mb-0">
								<p class="mb-0" style="color:#7bade3;">기존에 첨부된 파일&nbsp;</p> 
								<p class="mb-0">/&nbsp;새로 첨부한 파일</p>
							</div>
							<form name="uploadForm" id="uploadForm" enctype="multipart/form-data" method="post">
								<div id="dropZone"
									style="height: auto; min-height: 100px; border-style: dashed; border-color: gray;">
									<div id="fileDragDesc">파일을 드래그 해주세요.</div>
									
									<div id="fileList">
										<ul style="list-style:none; padding-left:0px"></ul>
									</div>
								</div>
								<input type="hidden" id="boardNo" name="boardNo" value="${edit.boardNo}"/>
								<input type="hidden" id="pBoardNo" name="pBoardNo" value="${edit.pBoardNo}"/>
							</form>
						</div>

					</div>
					
				</div>
			</div>
			<!-- End of Main Content -->

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

	<!-- Page level plugins -->
	<script src="/resources/js/tui-editor-Editor-full.js"></script>
	
	<!-- file drag & drop -->
	<!-- <script src="/resources/js/file-drag-n-drop.js"></script> -->
	
	<!-- Page level custom scripts -->
	<script src="/resources/js/custom-common.js"></script>
	<script type="text/javascript">
		/************************************************** editor 데이터 */	
		var attachedFileList = new Array();
		var editor;
		$(document).ready(function() {
			var boardNo = "${edit.boardNo}";
			var data = {"boardNo":boardNo*1};
			
			$.ajax({
				url : "/ajax/toastBoardDetail.do",
				method : "POST",
				data : data,
				dataType : "json",
				success : function(result) {
					var title = result[0].title;
					var contents = result[0].contents;
					var regDate = result[0].regDate;
							
					editor = new tui.Editor({
						el : document.querySelector('#editSection'),
						previewStyle : 'vertical',
						height : '500px',
						initialValue : contents,
						initialEditType : 'wysiwyg',
					});
				},
				error : function(xhr, status, error){
					console.log(error);
				}
			});
			
			//기존의 첨부파일 불러오기
			(function(){
				$.getJSON("/getAttachedList.do", data, function(arr){
					$(arr).each(function(i, attach){
						var fileSize = attach.FILE_SIZE
						var fileSizeKb = fileSize / 1024; // 파일 사이즈(단위 :kb)
	            		var fileSizeMb = fileSizeKb / 1024;    // 파일 사이즈(단위 :Mb)
	            		attachedFileList[i] = attach;
	            		var aIndex = -(i+1);
	            		addFileList(aIndex, attach.FILE_NAME, getFileSize(fileSize, fileSizeKb, fileSizeMb), "old");
					});	
            		//console.log(attachedFileList);
				});
			})();
		});
		

     	// 파일 리스트 번호
		var fileIndex = 0;
		// 등록할 전체 파일 사이즈
		var totalFileSize = 0;
		// 파일 리스트
		var fileList = new Array();
		// 파일 사이즈 리스트
		var fileSizeList = new Array();
		// 등록 가능한 파일 사이즈 MB
		var uploadSize = 50;
		
		$("#edit").click(function(){	
			var title = $("#title").val();
			var userID = "${userID}";
			var contents = editor.getValue();

	        var uploadFileList = Object.keys(fileList);
	        
            var form = $('#uploadForm');
            var formData = new FormData(form[0]);
            for (var i = 0; i < uploadFileList.length; i++) {
                formData.append('files'+i, fileList[uploadFileList[i]]);
            }
            console.log(fileList);
            
            var attached = new Array();
            for(var i = 0; i < attachedFileList.length; i++){
            	if(typeof(attachedFileList[i]) != "undefined"){
            		attached[i] = attachedFileList[i].FILE_NO;
            	}
            }
            formData.append("attached", attached);
            //boardNo은 uploadForm 안에 있음.            
            formData.append('title', title);
            formData.append('userID', userID);
            formData.append('contents', contents);
			if(title.replace(/\s/gi, "").length == 0){
				alert("제목을 입력해 주세요.");
				$("#title").focus();
				return;
			}
			
			if(contents.replace(/\s/gi, "").length == 0){
				alert("글을 입력해 주세요.");
				editor.focus();
				return;
			}            
            
			$.ajax({
				url:"/edit.do",
				type: "POST",
				data: formData,
				async: false,
                enctype : 'multipart/form-data',
                processData : false,
                contentType : false,
                dataType : 'html',
                cache : false,
				success: function(data){
					location.href = document.referrer;
					history.back();
				},
				error: function(xhr, status, error){
					console.log(error);
				}
			});
		});
		
		$(document).ready(function(){
			$("#input_file").bind('change', function(){
				selectFile(this.files);
			});		
		});

		$(function() {
		    // 파일 드롭 다운
		        fileDropDown();
		    });
			
		 // 파일 드롭 다운
		function fileDropDown() {
		    var dropZone = $("#dropZone");
		    // Drag기능
		    dropZone.on('dragenter', function(e) {
		        e.stopPropagation();
		        e.preventDefault();
		        // 드롭다운 영역 css
		        dropZone.css('background-color', '#E3F2FC');
		    });
		    dropZone.on('dragleave', function(e) {
		        e.stopPropagation();
		        e.preventDefault();
		        // 드롭다운 영역 css
		        dropZone.css('background-color', '#FFFFFF');
		    });
		    dropZone.on('dragover', function(e) {
		        e.stopPropagation();
		        e.preventDefault();
		        // 드롭다운 영역 css
		        dropZone.css('background-color', '#E3F2FC');
		    });
		    dropZone.on('drop', function(e) {
		        e.preventDefault();
		        // 드롭다운 영역 css
		        dropZone.css('background-color', '#FFFFFF');

		        var files = e.originalEvent.dataTransfer.files;
		        if (files != null) {
		            if (files.length < 1) {
		                /* alert("폴더 업로드 불가"); */
		                console.log("폴더 업로드 불가");
		                return;
		            } else {
		                selectFile(files)
		            }
		        } else {
		            alert("ERROR");
		        }
		    });
		}

		// 파일 선택시
		function selectFile(fileObject) {
		    var files = null;

		    if (fileObject != null) {
		        // 파일 Drag 이용하여 등록시
		        files = fileObject;
		    } else {
		        // 직접 파일 등록시
		        files = $('#multipartFileList_' + fileIndex)[0].files;
		    }
		    //console.log(files);
		    
		    var attachedCount = attachedFileList.length;
		    //console.log(attachedCount);
		    
		    // 다중파일 등록
		    if (files != null) {
		        
		        for (var i = 0; i < files.length; i++) {
		            // 파일 이름
		            var fileName = files[i].name;
		            console.log(fileName);
		            var fileNameArr = fileName.split("\.");
		            // 확장자
		            var ext = fileNameArr[fileNameArr.length - 1];
		            
		            var fileSize = files[i].size; // 파일 사이즈(단위 :byte)
		            console.log("fileSize="+fileSize);
		            if (fileSize <= 0) {
		                console.log("0kb file return");
		                return;
		            }		            
		            
		            //파일 사이즈 계산
					var fileSizeKb = fileSize / 1024; // 파일 사이즈(단위 :kb)
            		var fileSizeMb = fileSizeKb / 1024;    // 파일 사이즈(단위 :Mb)
		            var fileSizeStr = getFileSize(fileSize, fileSizeKb, fileSizeMb);

		            /*
					 * if ($.inArray(ext, [ 'exe', 'bat', 'sh', 'java', 'jsp',
					 * 'html', 'js', 'css', 'xml' ]) >= 0) { // 확장자 체크 alert("등록
					 * 불가 확장자"); break;
					 */
		            if ($.inArray(ext, [ 'hwp', 'doc', 'docx', 'xls', 'xlsx', 'ppt', 'pptx', 'txt', 'png', 'pdf', 'jpg', 'jpeg', 'gif', 'zip' ]) < 0) {
		                // 확장자 체크
		                /*
						 * alert("등록이 불가능한 파일 입니다."); break;
						 */
		                alert("등록이 불가능한 파일 입니다.("+fileName+")");
		            } else if (fileSizeMb > uploadSize) {
		                // 파일 사이즈 체크
		                alert("용량 초과\n업로드 가능 용량 : " + uploadSize + " MB");
		                break;
		            } else {
		                // 전체 파일 사이즈
		                totalFileSize += fileSizeMb;

		                // 파일 배열에 넣기 (새로 추가된 파일)
		                fileList[fileIndex] = files[i];
		                
		                // 파일 사이즈 배열에 넣기
		                fileSizeList[fileIndex] = fileSizeMb;

		                // 업로드 파일 목록 생성
		                addFileList(fileIndex, fileName, fileSizeStr, "new");

		                // 파일 번호 증가
		                fileIndex++;
		            }
		        }
		    } else {
		        alert("ERROR");
		    }
		}
		    
		// 업로드 파일 목록 생성
		function addFileList(fIndex, fileName, fileSizeStr, oldNew) {
		    /*
			 * if (fileSize.match("^0")) { alert("start 0"); }
			 */		        
	        if (fileList.length > 0 || attachedFileList.length > 0) {
	            $("#fileDragDesc").hide(); 
	            $("fileListTable").show();
	        } else {
	            $("#fileDragDesc").show(); 
	            $("fileListTable").hide();
	        }
			 
		    var html = "";
		    if(oldNew==="old"){
		    	html += "<li style='color:#7bade3;' id='fileLi_" + fIndex + "'>";
		    }else{
		    	html += "<li id='fileLi_" + fIndex + "'>";
		    }
		   // html += "    <td id='dropZone' class='left' >";
		    html += fileName + " (" + fileSizeStr +") " 
		            // + "<a href='#' onclick='deleteFile(" + fIndex + ");
					// return false;' class='btn small bg_02'> 삭제</a>"
		            
		            + "<input value='삭제' type='button' href='#' onclick='deleteFile(" + fIndex + "); return false;'>"
		   // html += "    </td>"
		    html += "</li>"

		    $('#fileList ul').append(html);
		    
		}

		// 업로드 파일 삭제
		function deleteFile(fIndex) {
		    console.log("deleteFile.fIndex=" + fIndex);
		    // 전체 파일 사이즈 수정
		    totalFileSize -= fileSizeList[fIndex];		 	
		    
		    if(fIndex < 0){
		    	// 기존 첨부파일 리스트에서 삭제
				delete attachedFileList[Math.abs(fIndex+1)]; //attachedFileList.splice(Math.abs(fIndex+1), 1);  //

		    }else{
			    // 파일 배열에서 삭제
			    delete fileList[fIndex]; //fileList.splice(fIndex, 1); //			    

			    // 파일 사이즈 배열 삭제
			    delete fileSizeList[fIndex];
		    }		    

		    // 업로드 파일 테이블 목록에서 삭제
		    $("#fileLi_" + fIndex).remove();
		    
		    console.log("totalFileSize="+totalFileSize);
		    
		    if (totalFileSize > 0 || attachedFileList.length > 0){  //if (totalFileSize > 0) {
		        $("#fileDragDesc").hide(); 
		        $("fileListTable").show();
		    } else {
		        $("#fileDragDesc").show(); 
		        $("fileListTable").hide();
		    }
		}
		
        // 파일 등록
       	/* function uploadFile() {
            // 등록할 파일 리스트
            var uploadFileList = Object.keys(fileList);

         	// 등록 가능한 총 파일 사이즈 MB
         	var maxUploadSize = 500;
         	
            // 파일이 있는지 체크
            if (uploadFileList.length == 0) {
                if(!confirm("파일 없이 게시글을 등록하시겠습니까?")){
                	// 파일등록 경고창
                    alert("파일이 없습니다.");
                    return;
                }    
            } 

            // 용량을 500MB를 넘을 경우 업로드 불가
            if (totalFileSize > maxUploadSize) {
                // 파일 사이즈 초과 경고창
                alert("총 용량 초과\n총 업로드 가능 용량 : " + maxUploadSize + " MB");
                return;
            }

            if (confirm("등록 하시겠습니까?")) {
                // 등록할 파일 리스트를 formData로 데이터 입력
                var form = $('#uploadForm');
                var formData = new FormData(form);
                for (var i = 0; i < uploadFileList.length; i++) {
                    formData.append('files', fileList[uploadFileList[i]]);
                }
            }
        } */  
	    
		//파일 사이즈 계산
		function getFileSize(fileSize, fileSizeKb, fileSizeMb){			
            var fileSizeStr = "";
            if ((1024*1024) <= fileSize) {    // 파일 용량이 1메가 이상인 경우
                console.log("fileSizeMb="+fileSizeMb.toFixed(2));
                fileSizeStr = fileSizeMb.toFixed(2) + " Mb";
            } else if ((1024) <= fileSize) {
                console.log("fileSizeKb="+parseInt(fileSizeKb));
                fileSizeStr = parseInt(fileSizeKb) + " kb";
            } else {
                console.log("fileSize="+parseInt(fileSize));
                fileSizeStr = parseInt(fileSize) + " byte";
            }
            return fileSizeStr;
		}
        
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