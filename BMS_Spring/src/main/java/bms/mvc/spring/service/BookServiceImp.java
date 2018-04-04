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
	
	// ���� ���
	@Override
	public void viewList(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int divContent = 16; // ������ ���ڵ� ��
		int divPage = 5; // ������ ������ ��

		int num = 0; // ����� ���ڵ� ��ȣ
		String pageNum = null; // ������ ��ȣ

		int tot = 0; // ��ü ���ڵ� ��
		int beginNum = 0; // ù ��ȣ
		int endNum = 0; // �� ��ȣ

		int totPage = 0; // ������ ��
		int currPage = 0; // ���� ������
		int beginPage = 0; // ù ������
		int endPage = 0; // �� ������

		// ===== ���������̼� ó�� ���� =====
		// ��ü ���ڵ� �� ȣ��
		tot = dao.getBookTotal();

		// ������ ��ȣ ȣ��
		pageNum = req.getParameter("pageNum");

		// ������ ��ȣ�� null�̸� 1�� ����
		if(pageNum == null) pageNum = "1";

		// ������ ��ȣ�� ���� ������ ��ȣ�� �ݿ�
		currPage = Integer.parseInt(pageNum);

		// ������ ��
		totPage = (tot / divContent) + (tot % divContent > 0? 1:0);

		// ù ��ȣ
		beginNum = (currPage - 1) * divContent + 1;

		// �� ��ȣ
		endNum = beginNum + divContent - 1;

		// �� ��ȣ�� ��ü ���ڵ� ������ ũ�� �� ��ȣ�� ��ü ���ڵ� ���� ����
		if(endNum > tot) endNum = tot;

		// ����� ���ڵ� ��ȣ
		num = tot - (currPage - 1) * divContent;
		// ===== ���������̼� ó�� ���� =====

		// ���ڵ� ȣ��
		if(tot > 0) {
			Map<String, Object> daoMap = new HashMap<String, Object>();
			daoMap.put("beginNum", beginNum);
			daoMap.put("endNum", endNum);
			
			ArrayList<BookVO> books = dao.getBooks(daoMap);
			model.addAttribute("books", books);
		}

		// ù ������
		beginPage = (currPage / divPage) * divPage + 1;
		if(currPage % divPage == 0) beginPage -= divPage;

		// �� ������
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

	// ���� ��
	@Override
	public void viewDetail(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int orgNum = Integer.parseInt(req.getParameter("orgNum"));
		BookVO book = dao.getBook(orgNum);
		
		model.addAttribute("book", book);
	}

	// ���� ���
	@Override
	public void insertBook(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		MultipartRequest mr = null;

		// ���ε� ������ �ִ� ������
	    int maxSize = 1024 * 1024 * 10; // 1024 * 1024 * 10 = 10MB

	    // ���ε� ������ ������ ���
	    String saveDir = req.getRealPath("/resources/uploadedFiles/books/");
	    
	    // ���ε� ������ �������� ���
	    String realDir = "C:\\Users\\Ȳ��ȯ\\eclipse-workspace\\BMS_Spring\\src\\main\\webapp\\resources\\uploadedFiles\\books\\";
	    
	    // ���ڵ� Ÿ��
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
			vo.setBkimg(mr.getFilesystemName("bkimg")); // getFilesystemName �޼ҵ�� �޾ƿ�
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

	// ���� ����
	@Override
	public void updateBook(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		MultipartRequest mr = null;

		// ���ε� ������ �ִ� ������
	    int maxSize = 1024 * 1024 * 10; // 1024 * 1024 * 10 = 10MB

	    // ���ε� ������ ������ ���
	    String saveDir = req.getRealPath("/resources/uploadedFiles/books/");
	    
	    // ���ε� ������ �������� ���
	    String realDir = "C:\\Users\\Ȳ��ȯ\\eclipse-workspace\\BMS_Spring\\src\\main\\webapp\\resources\\uploadedFiles\\books\\";
	    
	    // ���ڵ� Ÿ��
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
			vo.setBkimg(mr.getFilesystemName("bkimg")); // getFilesystemName �޼ҵ�� �޾ƿ�
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

	// ���� ����
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
