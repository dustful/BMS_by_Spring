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
	
	// 주문 목록
	@Override
	public void viewList(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int divContent = 20; // 보여줄 레코드 수
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
		tot = dao.getOrderTotal();

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
			
			ArrayList<OrderVO> orders = dao.getOrders(daoMap);
			model.addAttribute("orders", orders);
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

	// 주문 상세
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
				
				// 총 결제 금액이 배송비를 포함하는 최저 주문가보다 작을 경우, 배송비 추가
				totPrice = book.getBkprice();
				
				if(totPrice < ServiceCode.INCLUDED_DELIVERY_CHARGE) model.addAttribute("deliveryCharge", ServiceCode.DELIVERY_CHARGE);
				else model.addAttribute("deliveryCharge", 0);

				// 주문 번호 생성
				int nextv = dao.getOrderNextval();
				
				// 상품 정보 추출
				book.setBkqty(1);
				books.put(orgNum, book);
				
				model.addAttribute("totPrice", totPrice);
				model.addAttribute("nextv", nextv);
				model.addAttribute("books", books);
				break;
		}
	}

	// 주문 등록
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

			// 재고 수량 대 주문 수량 비교
			if(result3 >= 0) {
				// 재고 수량에서 주문 수량만큼 차감
				daoMap = new HashMap<String, Object>();
				daoMap.put("bkqty", result3);
				daoMap.put("bkno", Integer.parseInt(bkno[i]));
				
				int result2 = bDAO.putBookQty(daoMap);
				
				if(result2 == 1) result = dao.postOrder(vo);
			} else {
				// 재고 수량 미달로 주문 도서중 일부 도서의 주문이 취소되었습니다.
			}
		}

		return result;
	}
	
	// 장바구니에 추가
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
			 * 장바구니 보관일수 설정
			 * 30일 = 60 * 60 * 24 * 30
			 * : session 기본 객체를 참조해 보완 예정 : getId(), getCreationTime(), getLastAccessedTime()
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
	
	// 장바구니 목록
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
	
	// 장바구니에서 주문
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
		
		// 총 결제 금액 집계 및 상품 정보 추출
		for(int i = 0; i < chkEach.length; i++) {
			BookVO book = bDAO.getBook(Integer.parseInt(chkEach[i]));
			int bkprice = book.getBkprice();
			totPrice += bkprice * Integer.parseInt(odqty[i]);
			book.setBkqty(Integer.parseInt(odqty[i]));
			books.put(Integer.parseInt(chkEach[i]), book);
		}
		
		// 총 결제 금액이 배송비를 포함하는 최저 주문가보다 작을 경우, 배송비 추가
		if(totPrice < ServiceCode.INCLUDED_DELIVERY_CHARGE) req.setAttribute("deliveryCharge", ServiceCode.DELIVERY_CHARGE);
		else req.setAttribute("deliveryCharge", 0);

		// 주문 번호 생성
		int nextv = dao.getOrderNextval();

		req.setAttribute("nextv", nextv);
		req.setAttribute("totPrice", totPrice);
		req.setAttribute("books", books);
	}
	
	// 장바구니에서 제거
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

	// 주문 상태 수정
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
			// 주문 취소 또는 환불 승인 상태일 경우
			for(int i = 0; i < orders.size(); i++) {
				// 주문 정보에 부속된 상품의 주문 수량을 상품의 재고 수량과 합산
				int bkno = Integer.parseInt(String.valueOf(orders.get(i).get("BKNO")));
				int bkqty = Integer.parseInt(String.valueOf(orders.get(i).get("BKQTY")));
				int odqty = Integer.parseInt(String.valueOf(orders.get(i).get("ODQTY")));
				int result = bkqty + odqty;
				
				daoMap.put("bkno", bkno);
				daoMap.put("bkqty", result);
				
				// 합산 내용을 반영
				bDAO.putBookQty(daoMap);
			}
			
			vo.setOdstat(orderStat);
			vo.setOdref(orderNo);
			
			dao.putOrderStat(vo);
		} else {
			for(int i = 0; i < orders.size(); i++) {
				// 주문 정보에 부속된 상품의 주문 수량을 상품의 재고 수량에서 감산
				int bkno = Integer.parseInt(String.valueOf(orders.get(i).get("BKNO")));
				int bkqty = Integer.parseInt(String.valueOf(orders.get(i).get("BKQTY")));
				int odqty = Integer.parseInt(String.valueOf(orders.get(i).get("ODQTY")));
				int result = bkqty - odqty;
				
				daoMap.put("bkno", bkno);
				daoMap.put("bkqty", result);
				
				// 감산 내용을 반영
				bDAO.putBookQty(daoMap);
			}
			
			vo.setOdstat(orderStat);
			vo.setOdref(orderNo);
			
			dao.putOrderStat(vo);
		}
	}

	// 주문 삭제
	// 확인 요망
	@Override
	public void deleteOrder(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int orgnum = Integer.parseInt(req.getParameter("orgnum"));
		
		dao.deleteOrder(orgnum);
	}

}
