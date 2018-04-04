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

	// ��ü ������ ��ȸ
	@Override
	public int getBookTotal() {
		int tot = 0;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		tot = dao.getBookTotal();
		
		return tot;
	}

	// ���� ��� ��ȸ
	@Override
	public ArrayList<BookVO> getBooks(Map<String, Object> daoMap) {
		ArrayList<BookVO> books = null;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		books = dao.getBooks(daoMap);
		
		return books;
	}

	// ���� �� ��ȸ
	@Override
	public BookVO getBook(int orgnum) {
		BookVO book = null;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		book = dao.getBook(orgnum);
		
		return book;
	}

	// ���� ���
	@Override
	public int postBook(BookVO vo) {
		int res = 0;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		res = dao.postBook(vo);
		
		return res;
	}

	// ���� ����
	@Override
	public int putBook(BookVO vo) {
		int res = 0;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		res = dao.putBook(vo);
		
		return res;
	}
	
	// ���� ���� ����
	@Override
	public int putBookQty(Map<String, Object> daoMap) {
		int res = 0;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		res = dao.putBookQty(daoMap);
		
		return res;
	}

	// ���� ����
	@Override
	public int deleteBook(int orgnum) {
		int res = 0;
		
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		res = dao.deleteBook(orgnum);
		
		return res;
	}

}
