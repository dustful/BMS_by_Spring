package bms.mvc.spring.persistence;

import java.util.ArrayList;
import java.util.Map;

import bms.mvc.spring.domain.OrderVO;

public interface OrderDAO {
	
	// ��ü �ֹ��� ��ȸ
	public int getOrderTotal();
	
	// ���͸��� ��ü �ֹ��� ��ȸ
	public int getWhereOrderTotal(int odstat);
	
	// �ֹ� ��� ��ȸ
	public ArrayList<OrderVO> getOrders(Map<String, Object> daoMap);
	
	// ���͸��� �ֹ� ��� ��ȸ
	public ArrayList<OrderVO> getWhereOrders(Map<String, Object> daoMap);
	
	// �ֹ� �� ��ȸ
	public ArrayList<Map<Integer, String>> getOrder(int orgnum);
	
	// �ֹ� ��ȣ ����
	public int getOrderNextval();
	
	// �ֹ� ���
	public int postOrder(OrderVO vo);
	
	// �ֹ� ���� ����
	public int putOrderStat(OrderVO vo);
	
	// �ֹ� ����
	public int deleteOrder(int orgnum);
	
	// �Ǹ� �Ѿ� ��ȸ
	public int getTotOrder();
	
	// ���� �Ѿ� ��ȸ
	public int getSumOrder();

}
