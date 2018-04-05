package bms.mvc.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import bms.mvc.spring.domain.BookVO;
import bms.mvc.spring.domain.OrderVO;
import bms.mvc.spring.persistence.BookDAO;
import bms.mvc.spring.persistence.OrderDAO;

@Service
public class OrderServiceImp implements OrderService {

	@Inject
	OrderDAO dao;
	
	@Inject
	BookDAO bDAO;
	
	// �ֹ� ���
	@Override
	public void viewList(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int divContent = 20; // ������ ���ڵ� ��
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
		tot = dao.getOrderTotal();

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
			
			ArrayList<OrderVO> orders = dao.getOrders(daoMap);
			model.addAttribute("orders", orders);
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

	// �ֹ� ��
	@Override
	public void viewDetail(Model model, int pageCode) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int orgNum = Integer.parseInt(req.getParameter("orgNum"));
		
		switch(pageCode) {
			case ServiceCode.CUSTOMER_ORDER_DETAIL:
				ArrayList<Map<Integer, String>> orders = dao.getOrder(orgNum);
				
				model.addAttribute("includedDeliveryCharge", ServiceCode.INCLUDED_DELIVERY_CHARGE);
				model.addAttribute("deliveryCharge", ServiceCode.DELIVERY_CHARGE);
				model.addAttribute("orders", orders);
				break;
			case ServiceCode.CUSTOMER_ORDER_FORM:
				HashMap<Integer, BookVO> books = new HashMap<Integer, BookVO>();
				int totPrice = 0;
				BookVO book = bDAO.getBook(orgNum);
				
				// �� ���� �ݾ��� ��ۺ� �����ϴ� ���� �ֹ������� ���� ���, ��ۺ� �߰�
				totPrice = book.getBkprice();
				
				if(totPrice < ServiceCode.INCLUDED_DELIVERY_CHARGE) model.addAttribute("deliveryCharge", ServiceCode.DELIVERY_CHARGE);
				else model.addAttribute("deliveryCharge", 0);

				// �ֹ� ��ȣ ����
				int nextv = dao.getOrderNextval();
				
				// ��ǰ ���� ����
				book.setBkqty(1);
				books.put(orgNum, book);
				
				model.addAttribute("totPrice", totPrice);
				model.addAttribute("nextv", nextv);
				model.addAttribute("books", books);
				break;
		}
	}

	// �ֹ� ���
	@Override
	public int insertOrder(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		String[] bkno = req.getParameterValues("bkno");
		String[] odqty = req.getParameterValues("odqty");
		OrderVO vo = new OrderVO();
		Map<String, Object> daoMap = null;
		int result = 0;

		vo.setOdref(Integer.parseInt(req.getParameter("nextv")));
		vo.setOdname(req.getParameter("odname"));
		vo.setOdcontact(req.getParameter("odcontact"));
		vo.setRcname(req.getParameter("rcname"));
		vo.setRccontact(req.getParameter("rccontact"));
		vo.setRcaddr(req.getParameter("rcaddr"));
		vo.setPymd(Integer.parseInt(req.getParameter("pymd")));
		vo.setMbno(Integer.parseInt(req.getParameter("mbno")));
		
		for(int i = 0; i < bkno.length; i++) {
			vo.setBkno(Integer.parseInt(bkno[i]));
			vo.setOdqty(Integer.parseInt(odqty[i]));

			BookVO bkvo = bDAO.getBook(Integer.parseInt(bkno[i]));
			int result3 = bkvo.getBkqty() - Integer.parseInt(odqty[i]);

			// ��� ���� �� �ֹ� ���� ��
			if(result3 >= 0) {
				// ��� �������� �ֹ� ������ŭ ����
				daoMap = new HashMap<String, Object>();
				daoMap.put("bkqty", result3);
				daoMap.put("bkno", Integer.parseInt(bkno[i]));
				
				int result2 = bDAO.putBookQty(daoMap);
				
				if(result2 == 1) result = dao.postOrder(vo);
			} else {
				// ��� ���� �̴޷� �ֹ� ������ �Ϻ� ������ �ֹ��� ��ҵǾ����ϴ�.
			}
		}

		return result;
	}
	
	// ��ٱ��Ͽ� �߰�
	@Override
	public int addCart(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		String cartNum = "cart" + req.getSession().getAttribute("mno");
		HashMap<Integer, BookVO> carts = (HashMap<Integer, BookVO>) req.getSession().getAttribute(cartNum);
		int orgNum = Integer.parseInt(req.getParameter("orgNum"));
		BookVO vo = bDAO.getBook(orgNum);
		int result = 0;
		
		if(carts == null) {
			HashMap<Integer, BookVO> newCarts = new HashMap<Integer, BookVO>();
			
			newCarts.put(orgNum, vo);
			req.getSession().setAttribute(cartNum, newCarts);
			
			result = 1;
			
			/*
			 * ��ٱ��� �����ϼ� ����
			 * 30�� = 60 * 60 * 24 * 30
			 * : session �⺻ ��ü�� ������ ���� ���� : getId(), getCreationTime(), getLastAccessedTime()
			 */
			req.getSession().setMaxInactiveInterval(60 * 60 * 24 * 30);
		} else {
			if(!carts.containsKey(orgNum)) {
				carts.put(orgNum, vo);
				req.getSession().setAttribute(cartNum, carts);
				
				result = 1;
			} else {
				result = 2;
			}
		}
		
		return result;
	}
	
	// ��ٱ��� ���
	@Override
	public void cartList(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		String cartNum = "cart" + req.getSession().getAttribute("mno");
		HashMap<Integer, BookVO> carts = (HashMap<Integer, BookVO>) req.getSession().getAttribute(cartNum);
		int num = (carts == null? 0 : carts.size());
		
		model.addAttribute("num", num);
		model.addAttribute("carts", carts);
	}
	
	// ��ٱ��Ͽ��� �ֹ�
	@Override
	public void orderSelected(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		String cartNum = "cart" + req.getSession().getAttribute("mno");
		HashMap<Integer, BookVO> carts = (HashMap<Integer, BookVO>) req.getSession().getAttribute(cartNum);
		String[] chkEach = req.getParameterValues("check_each");
		String[] odqty = req.getParameterValues("odqty");
		HashMap<Integer, BookVO> books = new HashMap<Integer, BookVO>();
		int totPrice = 0;
		
		// �� ���� �ݾ� ���� �� ��ǰ ���� ����
		for(int i = 0; i < chkEach.length; i++) {
			BookVO book = bDAO.getBook(Integer.parseInt(chkEach[i]));
			int bkprice = book.getBkprice();
			totPrice += bkprice * Integer.parseInt(odqty[i]);
			book.setBkqty(Integer.parseInt(odqty[i]));
			books.put(Integer.parseInt(chkEach[i]), book);
		}
		
		// �� ���� �ݾ��� ��ۺ� �����ϴ� ���� �ֹ������� ���� ���, ��ۺ� �߰�
		if(totPrice < ServiceCode.INCLUDED_DELIVERY_CHARGE) req.setAttribute("deliveryCharge", ServiceCode.DELIVERY_CHARGE);
		else req.setAttribute("deliveryCharge", 0);

		// �ֹ� ��ȣ ����
		int nextv = dao.getOrderNextval();

		req.setAttribute("nextv", nextv);
		req.setAttribute("totPrice", totPrice);
		req.setAttribute("books", books);
	}
	
	// ��ٱ��Ͽ��� ����
	@Override
	public void removeToCart(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		String cartNum = "cart" + req.getSession().getAttribute("mno");
		HashMap<Integer, BookVO> carts = (HashMap<Integer, BookVO>) req.getSession().getAttribute(cartNum);
		String[] chkEach = req.getParameterValues("check_each");
		
		for(int i = 0; i < chkEach.length; i++) {
			if(carts.containsKey(Integer.parseInt(chkEach[i]))) {
				carts.remove(Integer.parseInt(chkEach[i]));
			}
		}		
	}

	// �ֹ� ���� ����
	@Override
	public void updateOrderStat(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int orderNo = Integer.parseInt(req.getParameter("orderNo"));
		int orderStat = Integer.parseInt(req.getParameter("orderStat"));
		OrderVO vo = new OrderVO();
		Map<String, Object> daoMap = null;
		daoMap = new HashMap<String, Object>();
		ArrayList<Map<Integer, String>> orders = dao.getOrder(orderNo);
		
		if(orderStat == 1 || orderStat == 6) {
			// �ֹ� ��� �Ǵ� ȯ�� ���� ������ ���
			for(int i = 0; i < orders.size(); i++) {
				// �ֹ� ������ �μӵ� ��ǰ�� �ֹ� ������ ��ǰ�� ��� ������ �ջ�
				int bkno = Integer.parseInt(String.valueOf(orders.get(i).get("BKNO")));
				int bkqty = Integer.parseInt(String.valueOf(orders.get(i).get("BKQTY")));
				int odqty = Integer.parseInt(String.valueOf(orders.get(i).get("ODQTY")));
				int result = bkqty + odqty;
				
				daoMap.put("bkno", bkno);
				daoMap.put("bkqty", result);
				
				// �ջ� ������ �ݿ�
				bDAO.putBookQty(daoMap);
			}
			
			vo.setOdstat(orderStat);
			vo.setOdref(orderNo);
			
			dao.putOrderStat(vo);
		} else {
			for(int i = 0; i < orders.size(); i++) {
				// �ֹ� ������ �μӵ� ��ǰ�� �ֹ� ������ ��ǰ�� ��� �������� ����
				int bkno = Integer.parseInt(String.valueOf(orders.get(i).get("BKNO")));
				int bkqty = Integer.parseInt(String.valueOf(orders.get(i).get("BKQTY")));
				int odqty = Integer.parseInt(String.valueOf(orders.get(i).get("ODQTY")));
				int result = bkqty - odqty;
				
				daoMap.put("bkno", bkno);
				daoMap.put("bkqty", result);
				
				// ���� ������ �ݿ�
				bDAO.putBookQty(daoMap);
			}
			
			vo.setOdstat(orderStat);
			vo.setOdref(orderNo);
			
			dao.putOrderStat(vo);
		}
	}

	// �ֹ� ����
	// Ȯ�� ���
	@Override
	public void deleteOrder(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int orgnum = Integer.parseInt(req.getParameter("orgnum"));
		
		dao.deleteOrder(orgnum);
	}

}
