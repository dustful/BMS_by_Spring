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

	// ��ü �ֹ��� ��ȸ
	@Override
	public int getOrderTotal() {
		int tot = 0;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		tot = dao.getOrderTotal();
		
		return tot;
	}

	// �ֹ� ��� ��ȸ
	@Override
	public ArrayList<OrderVO> getOrders(Map<String, Object> daoMap) {
		ArrayList<OrderVO> orders = null;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		orders = dao.getOrders(daoMap);
		
		return orders;
	}

	// �ֹ� �� ��ȸ
	@Override
	public ArrayList<Map<Integer, String>> getOrder(int orgnum) {
		ArrayList<Map<Integer, String>> orders = null;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		orders = dao.getOrder(orgnum);
		
		return orders;
	}
	
	// �ֹ� ��ȣ ����
	@Override
	public int getOrderNextval() {
		int nextval = 0;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		nextval = dao.getOrderNextval();
		
		return nextval;
	}

	// �ֹ� ���
	@Override
	public int postOrder(OrderVO vo) {
		int res = 0;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		res = dao.postOrder(vo);
		
		return res;
	}

	// �ֹ� ���� ����
	@Override
	public int putOrderStat(OrderVO vo) {
		int res = 0;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		res = dao.putOrderStat(vo);
		
		return res;
	}

	// �ֹ� ����
	@Override
	public int deleteOrder(int orgnum) {
		int res = 0;
		
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		res = dao.deleteOrder(orgnum);
		
		return res;
	}

}
