package bms.mvc.spring.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bms.mvc.spring.domain.BookVO;
import bms.mvc.spring.persistence.BookDAO;

@Service
public class BookServiceImp implements BookService {

	@Inject
	BookDAO dao;
	
	// 도서 목록
	@Override
	public void viewList(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int divContent = 16; // 보여줄 레코드 수
		int divPage = 5; // 보여줄 페이지 수

		int num = 0; // 출력할 레코드 번호
		String pageNum = null; // 페이지 번호

		int tot = 0; // 전체 레코드 수
		int beginNum = 0; // 첫 번호
		int endNum = 0; // 끝 번호

		int totPage = 0; // 페이지 수
		int currPage = 0; // 현재 페이지
		int beginPage = 0; // 첫 페이지
		int endPage = 0; // 끝 페이지

		// ===== 페이지네이션 처리 시작 =====
		// 전체 레코드 수 호출
		tot = dao.getBookTotal();

		// 페이지 번호 호출
		pageNum = req.getParameter("pageNum");

		// 페이지 번호가 null이면 1로 대입
		if(pageNum == null) pageNum = "1";

		// 페이지 번호를 현재 페이지 번호로 반영
		currPage = Integer.parseInt(pageNum);

		// 페이지 수
		totPage = (tot / divContent) + (tot % divContent > 0? 1:0);

		// 첫 번호
		beginNum = (currPage - 1) * divContent + 1;

		// 끝 번호
		endNum = beginNum + divContent - 1;

		// 끝 번호가 전체 레코드 수보다 크면 끝 번호에 전체 레코드 수를 대입
		if(endNum > tot) endNum = tot;

		// 출력할 레코드 번호
		num = tot - (currPage - 1) * divContent;
		// ===== 페이지네이션 처리 종료 =====

		// 레코드 호출
		if(tot > 0) {
			Map<String, Object> daoMap = new HashMap<String, Object>();
			daoMap.put("beginNum", beginNum);
			daoMap.put("endNum", endNum);
			
			ArrayList<BookVO> books = dao.getBooks(daoMap);
			model.addAttribute("books", books);
		}

		// 첫 페이지
		beginPage = (currPage / divPage) * divPage + 1;
		if(currPage % divPage == 0) beginPage -= divPage;

		// 끝 페이지
		endPage = beginPage + divPage - 1;
		if(endPage > totPage) endPage = totPage;

		model.addAttribute("tot", tot);
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);

		if(tot > 0) {
			model.addAttribute("tot", tot);
			model.addAttribute("beginPage", beginPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("divPage", divPage);
			model.addAttribute("totPage", totPage);
			model.addAttribute("currPage", currPage);
		}
	}

	// 도서 상세
	@Override
	public void viewDetail(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int orgNum = Integer.parseInt(req.getParameter("orgNum"));
		BookVO book = dao.getBook(orgNum);
		
		model.addAttribute("book", book);
	}

	// 도서 등록
	@Override
	public void insertBook(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		MultipartRequest mr = null;

		// 업로드 파일의 최대 사이즈
	    int maxSize = 1024 * 1024 * 10; // 1024 * 1024 * 10 = 10MB

	    // 업로드 파일의 논리적인 경로
	    String saveDir = req.getRealPath("/resources/uploadedFiles/books/");
	    
	    // 업로드 파일의 물리적인 경로
	    String realDir = "C:\\Users\\황성환\\eclipse-workspace\\BMS_Spring\\src\\main\\webapp\\resources\\uploadedFiles\\books\\";
	    
	    // 인코딩 타입
	    String encType = "UTF-8";
	    
	    try {
	    	mr = new MultipartRequest(req, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
	    	
	    	FileInputStream fis = new FileInputStream(saveDir + mr.getFilesystemName("bkimg"));
	    	FileOutputStream fos = new FileOutputStream(realDir + mr.getFilesystemName("bkimg"));
	    	
	    	int data = 0;
	    	
	    	while((data = fis.read()) != -1) {
	    	    fos.write(data);
	    	}
	    	
	    	fis.close();
	    	fos.close();
	    	
	    	BookVO vo = new BookVO();
			
			vo.setBkname(mr.getParameter("bkname"));
			vo.setBkimg(mr.getFilesystemName("bkimg")); // getFilesystemName 메소드로 받아옴
			vo.setBkauthor(mr.getParameter("bkauthor"));
			vo.setBkpublisher(mr.getParameter("bkpublisher"));
			vo.setBkprice(Integer.parseInt(mr.getParameter("bkprice")));
			vo.setBkqty(Integer.parseInt(mr.getParameter("bkqty")));
			vo.setBkcontent(mr.getParameter("bkcontent"));
			// vo.setBkcontent(req.getParameter("bkcontent").replace("\r\n", "<br/>"));
			
			int cnt = dao.postBook(vo);
			
			model.addAttribute("cnt", cnt);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	// 도서 수정
	@Override
	public void updateBook(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		MultipartRequest mr = null;

		// 업로드 파일의 최대 사이즈
	    int maxSize = 1024 * 1024 * 10; // 1024 * 1024 * 10 = 10MB

	    // 업로드 파일의 논리적인 경로
	    String saveDir = req.getRealPath("/resources/uploadedFiles/books/");
	    
	    // 업로드 파일의 물리적인 경로
	    String realDir = "C:\\Users\\황성환\\eclipse-workspace\\BMS_Spring\\src\\main\\webapp\\resources\\uploadedFiles\\books\\";
	    
	    // 인코딩 타입
	    String encType = "UTF-8";
	    
	    try {
	    	mr = new MultipartRequest(req, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
	    	
	    	FileInputStream fis = new FileInputStream(saveDir + mr.getFilesystemName("bkimg"));
	    	FileOutputStream fos = new FileOutputStream(realDir + mr.getFilesystemName("bkimg"));
	    	
	    	int data = 0;
	    	
	    	while((data = fis.read()) != -1) {
	    	    fos.write(data);
	    	}
	    	
	    	fis.close();
	    	fos.close();
	    	
	    	BookVO vo = new BookVO();
			
	    	vo.setBkno(Integer.parseInt(mr.getParameter("bkno")));
			vo.setBkname(mr.getParameter("bkname"));
			vo.setBkimg(mr.getFilesystemName("bkimg")); // getFilesystemName 메소드로 받아옴
			vo.setBkauthor(mr.getParameter("bkauthor"));
			vo.setBkpublisher(mr.getParameter("bkpublisher"));
			vo.setBkprice(Integer.parseInt(mr.getParameter("bkprice")));
			vo.setBkqty(Integer.parseInt(mr.getParameter("bkqty")));
			vo.setBkcontent(mr.getParameter("bkcontent"));
			// vo.setBkcontent(req.getParameter("bkcontent").replace("\r\n", "<br/>"));
			
			int cnt = dao.putBook(vo);
			
			model.addAttribute("cnt", cnt);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }		
	}

	// 도서 삭제
	@Override
	public void deleteBook(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		String[] chkEach = req.getParameterValues("check_each");
		String orgNum = req.getParameter("orgNum");
		
		if(orgNum != null) {
			dao.deleteBook(Integer.parseInt(orgNum));
		} else {
			for(int i = 0; i < chkEach.length; i++) {
				dao.deleteBook(Integer.parseInt(chkEach[i]));
			}	
		}
	}

}
