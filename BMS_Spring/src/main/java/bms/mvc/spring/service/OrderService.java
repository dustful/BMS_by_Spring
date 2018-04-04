package bms.mvc.spring.service;

import org.springframework.ui.Model;

public interface OrderService {
	
	// 주문 목록
	public void viewList(Model model) throws Exception;
	
	// 주문 상세
	public void viewDetail(Model model, int pageCode) throws Exception;
	
	// 주문 등록
	public int insertOrder(Model model) throws Exception;
	
	// 장바구니에 추가
	public int addCart(Model model) throws Exception;
	
	// 장바구니 목록
	public void cartList(Model model) throws Exception;
	
	// 장바구니에서 주문
	public void orderSelected(Model model) throws Exception;
	
	// 장바구니에서 제거
	public void removeToCart(Model model) throws Exception;
	
	// 주문 상태 수정
	public void updateOrderStat(Model model) throws Exception;
	
	// 주문 삭제
	public void deleteOrder(Model model) throws Exception;

}
