package bms.mvc.spring.service;

import org.springframework.ui.Model;

public interface OrderService {
	
	// �ֹ� ���
	public void viewList(Model model) throws Exception;
	
	// �ֹ� ��
	public void viewDetail(Model model, int pageCode) throws Exception;
	
	// �ֹ� ���
	public int insertOrder(Model model) throws Exception;
	
	// ��ٱ��Ͽ� �߰�
	public int addCart(Model model) throws Exception;
	
	// ��ٱ��� ���
	public void cartList(Model model) throws Exception;
	
	// ��ٱ��Ͽ��� �ֹ�
	public void orderSelected(Model model) throws Exception;
	
	// ��ٱ��Ͽ��� ����
	public void removeToCart(Model model) throws Exception;
	
	// �ֹ� ���� ����
	public void updateOrderStat(Model model) throws Exception;
	
	// �ֹ� ����
	public void deleteOrder(Model model) throws Exception;

}
