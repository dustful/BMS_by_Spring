package bms.mvc.spring.persistence;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import bms.mvc.spring.domain.BookVO;

@Repository
public class BookDAOImp implements BookDAO {
	
	@Inject
	SqlSession sqlSession;

	// 전체 도서수 조회
	@Override
	public int getBookTotal() {
		int tot = 0;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		tot = dao.getBookTotal();
		
		return tot;
	}

	// 도서 목록 조회
	@Override
	public ArrayList<BookVO> getBooks(Map<String, Object> daoMap) {
		ArrayList<BookVO> books = null;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		books = dao.getBooks(daoMap);
		
		return books;
	}

	// 도서 상세 조회
	@Override
	public BookVO getBook(int orgnum) {
		BookVO book = null;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		book = dao.getBook(orgnum);
		
		return book;
	}

	// 도서 등록
	@Override
	public int postBook(BookVO vo) {
		int res = 0;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		res = dao.postBook(vo);
		
		return res;
	}

	// 도서 수정
	@Override
	public int putBook(BookVO vo) {
		int res = 0;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		res = dao.putBook(vo);
		
		return res;
	}
	
	// 도서 수량 수정
	@Override
	public int putBookQty(Map<String, Object> daoMap) {
		int res = 0;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		res = dao.putBookQty(daoMap);
		
		return res;
	}

	// 도서 삭제
	@Override
	public int deleteBook(int orgnum) {
		int res = 0;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		res = dao.deleteBook(orgnum);
		
		return res;
	}

}
