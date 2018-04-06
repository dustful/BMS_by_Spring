package bms.mvc.spring.persistence;

import java.util.ArrayList;
import java.util.Map;

import bms.mvc.spring.domain.OrderVO;

public interface OrderDAO {
	
	// 전체 주문수 조회
	public int getOrderTotal();
	
	// 주문 목록 조회
	public ArrayList<OrderVO> getOrders(Map<String, Object> daoMap);
	
	// 주문 상세 조회
	public ArrayList<Map<Integer, String>> getOrder(int orgnum);
	
	// 주문 번호 생성
	public int getOrderNextval();
	
	// 주문 등록
	public int postOrder(OrderVO vo);
	
	// 주문 상태 수정
	public int putOrderStat(OrderVO vo);
	
	// 주문 삭제
	public int deleteOrder(int orgnum);
	
	// 판매 총액 조회
	public int getTotOrder();
	
	// 정산 총액 조회
	public int getSumOrder();

}
