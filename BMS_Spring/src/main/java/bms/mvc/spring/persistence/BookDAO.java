package bms.mvc.spring.persistence;

import java.util.ArrayList;
import java.util.Map;

import bms.mvc.spring.domain.BookVO;

public interface BookDAO {
	
	// 전체 도서수 조회
	public int getBookTotal();
	
	// 도서 목록 조회
	public ArrayList<BookVO> getBooks(Map<String, Object> daoMap);
	
	// 도서 상세 조회
	public BookVO getBook(int orgnum);
	
	// 도서 등록
	public int postBook(BookVO vo);
	
	// 도서 수정
	public int putBook(BookVO vo);
	
	// 도서 수량 수정
	public int putBookQty(Map<String, Object> daoMap);
	
	// 도서 삭제
	public int deleteBook(int orgnum);

}
