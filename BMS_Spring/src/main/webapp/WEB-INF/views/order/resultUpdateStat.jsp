<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/setting.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${titleBar}</title>
</head>
<body>

	<!-- 결과 알림 출력 시작 -->
	<script type="text/javascript">
		$(function() {
			$("#resultModal .modal-body").text("주문 상태를 반영했습니다.");
			$("#resultModal").modal("show");
		});
	</script>
	
	<div class="modal fade" id="resultModal" tabindex="-1" role="dialog" aria-labelledby="resultModalTitle" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="resultModalTitle">알림</h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<!-- <button type="button" class="btn btn-primary" onClick="location.href = 'orderList'">주문 목록으로 이동</button> -->
				</div>
			</div>
		</div>
	</div>
	<!-- 결과 알림 출력 종료 -->

</body>
</html>