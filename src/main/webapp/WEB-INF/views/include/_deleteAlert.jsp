<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">'게시 글 제목' 글의 <br/>하위 답글도 모두 삭제 됩니다. <br/> 삭제하시겠습니까?</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<i class="fa fa-times"></i>
				</button>
			</div>
			<div class="modal-body">삭제하시려면 아래의 삭제 버튼을 클릭하세요.</div>
			<div class="modal-footer">
				<button class="btn btn-secondary" type="button"
					data-dismiss="modal">취소</button>
				<a class="btn btn-primary" href="/"><i class="fa fa-trash-alt"></i>&nbsp;삭제</a>
			</div>
		</div>
	</div>
</div>