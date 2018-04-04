package bms.mvc.spring.persistence;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import bms.mvc.spring.domain.OrderVO;

@Repository
public class OrderDAOImp implements OrderDAO {
	
	@Inject
	SqlSession sqlSession;

	// 전체 주문수 조회
	@Override
	public int getOrderTotal() {
		int tot = 0;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		tot = dao.getOrderTotal();
		
		return tot;
	}

	// 주문 목록 조회
	@Override
	public ArrayList<OrderVO> getOrders(Map<String, Object> daoMap) {
		ArrayList<OrderVO> orders = null;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		orders = dao.getOrders(daoMap);
		
		return orders;
	}

	// 주문 상세 조회
	@Override
	public ArrayList<Map<Integer, String>> getOrder(int orgnum) {
		ArrayList<Map<Integer, String>> orders = null;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		orders = dao.getOrder(orgnum);
		
		return orders;
	}
	
	// 주문 번호 생성
	@Override
	public int getOrderNextval() {
		int nextval = 0;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		nextval = dao.getOrderNextval();
		
		return nextval;
	}

	// 주문 등록
	@Override
	public int postOrder(OrderVO vo) {
		int res = 0;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		res = dao.postOrder(vo);
		
		return res;
	}

	// 주문 상태 수정
	@Override
	public int putOrderStat(OrderVO vo) {
		int res = 0;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		res = dao.putOrderStat(vo);
		
		return res;
	}

	// 주문 삭제
	@Override
	public int deleteOrder(int orgnum) {
		int res = 0;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		res = dao.deleteOrder(orgnum);
		
		return res;
	}

}
