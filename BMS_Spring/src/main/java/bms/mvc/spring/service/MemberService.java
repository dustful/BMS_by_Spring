package bms.mvc.spring.service;

import org.springframework.ui.Model;

public interface MemberService {
	
	// ȸ�� ���
	public void viewList(Model model) throws Exception;
	
	// ȸ�� ��ȸ
	public int checkMember(Model model) throws Exception;
	
	// ȸ�� ��
	public void viewDetail(Model model) throws Exception;
	
	// ȸ�� ���
	public void insertMember(Model model) throws Exception;
	
	// ȸ�� ����
	public void updateMember(Model model) throws Exception;
	
	// ȸ�� ����
	public void deleteMember(Model model) throws Exception;
	
	// ȸ�� ���� ����
	public void goodbye(Model model) throws Exception;

}
