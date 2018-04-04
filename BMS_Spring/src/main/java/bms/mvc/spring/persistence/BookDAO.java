package bms.mvc.spring.persistence;

import java.util.ArrayList;
import java.util.Map;

import bms.mvc.spring.domain.BookVO;

public interface BookDAO {
	
	// ��ü ������ ��ȸ
	public int getBookTotal();
	
	// ���� ��� ��ȸ
	public ArrayList<BookVO> getBooks(Map<String, Object> daoMap);
	
	// ���� �� ��ȸ
	public BookVO getBook(int orgnum);
	
	// ���� ���
	public int postBook(BookVO vo);
	
	// ���� ����
	public int putBook(BookVO vo);
	
	// ���� ���� ����
	public int putBookQty(Map<String, Object> daoMap);
	
	// ���� ����
	public int deleteBook(int orgnum);

}
