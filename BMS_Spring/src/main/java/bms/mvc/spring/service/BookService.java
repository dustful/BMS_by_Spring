package bms.mvc.spring.service;

import org.springframework.ui.Model;

public interface BookService {
	
	// 도서 목록
	public void viewList(Model model) throws Exception;
	
	// 도서 상세
	public void viewDetail(Model model) throws Exception;
	
	// 도서 등록
	public void insertBook(Model model) throws Exception;
	
	// 도서 수정
	public void updateBook(Model model) throws Exception;
	
	// 도서 삭제
	public void deleteBook(Model model) throws Exception;

}
