package bms.mvc.spring.service;

import org.springframework.ui.Model;

public interface BookService {
	
	// ���� ���
	public void viewList(Model model) throws Exception;
	
	// ���� ��
	public void viewDetail(Model model) throws Exception;
	
	// ���� ���
	public void insertBook(Model model) throws Exception;
	
	// ���� ����
	public void updateBook(Model model) throws Exception;
	
	// ���� ����
	public void deleteBook(Model model) throws Exception;

}
