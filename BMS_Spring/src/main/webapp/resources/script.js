/*
 * ========== 상품 상세 ==========
 */
$(function() {
	// 바로 구입
	$("#buyNowBtn").click(function() {
		$("#bookDetailForm").attr("action", "orderBookForm");
		$("#bookDetailForm").submit();
	});

	// 장바구니에 추가
	$("#addCartBtn").click(function() {
		$("#bookDetailForm").attr("action", "cartAddPro");
		$("#bookDetailForm").submit();
	});
	
	/*$("#addCartBtn").click(function() {
		$("#bookDetailForm").attr("action", "cartAddPro");
		$.ajax({
			url : 'cartAddPro',
			type : 'post',
			data : $('#bookDetailForm').serialize(),
			success : function(data) {
				if(data == "1") {
					$("#resultModal .modal-body").text("상품이 장바구니에 추가되었습니다.");
					$("#resultModal").modal("show");
				} else {
					$("#resultModal .modal-body").text("이미 장바구니에 추가된 상품입니다.");
					$("#resultModal").modal("show");
				}
			}
		});
	});*/
});


/*
 * ========== 체크 박스 전체 선택 ==========
 */
$(function() {
	$("#check_all").change(function() {
		// this = 전체 선택용 체크 박스
		var is_check = $(this).is(":checked");

		// 개별 체크 박스
		$(".check_each").prop("checked", is_check);
	});
});


/*
 * ========== 장바구니 ==========
 */
$(function() {
	// 전체 주문
	$("#orderAllOfThem").click(function() {
		$(".check_each").prop("checked", true);
		$("#cartForm").attr("action", "orderSelected");
		$("#cartForm").submit();
	});
	
	// 선택한 상품을 주문
	$("#orderSelected").click(function() {
		$("#cartForm").attr("action", "orderSelected");
		$("#cartForm").submit();
	});

	// 선택한 상품을 장바구니에서 제거
	$("#removeToCart").click(function() {
		$("#cartForm").attr("action", "removeToCart");
		$("#cartForm").submit();
	});
});


/*
 * ========== 주문 양식 ==========
 * 주문자와 수신자가 동일할 경우, 주문자 정보를 수신자 정보에 반영
 */
$(function() {
	$("#sameOrderInfo").change(function() {
		if($("#sameOrderInfo").is(":checked")) {
			$("#rcname").val($("#odname").val());
			$("#rccontact").val($("#odcontact").val());
			$("#rcname").attr("readonly", true);
			$("#rccontact").attr("readonly", true);
		} else {
			$("#rcname").val("");
			$("#rccontact").val("");
			$("#rcname").attr("readonly", false);
			$("#rccontact").attr("readonly", false);
		}
	});
});


/*
 * ========== 판매자 재고 목록/상세 ==========
 */
$(function() {
	// 선택한 상품을 제거
	$("#bookDeleteBtn").click(function() {
		$("#sellerBookForm").attr("action", "deleteBookPro");
		$("#sellerBookForm").submit();
	});
});


/*
 * ========== 구매자/판매자 주문 상세 ==========
 */
$(function() {
	$("#orderStat").change(function() {
		$.ajax({
			url : "updateOrderStat",
			data : $("#updateOrderStatForm").serialize(),
			success : function(data) {
				$("#orderDetail").append(data);
			}
		});
	});
});