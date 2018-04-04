package bms.mvc.spring.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bms.mvc.spring.service.OrderService;
import bms.mvc.spring.service.ServiceCode;

@Controller
public class OrderController {
	
	@Inject
	OrderService service;
	
	// 구매자 > 상품 주문 양식
	@RequestMapping(value = "orderBookForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String orderBookForm(HttpServletRequest req, Model model) throws Exception {
		System.out.println("orderBookForm");
		
		model.addAttribute("req", req);
		service.viewDetail(model, ServiceCode.CUSTOMER_ORDER_FORM);
		
		return "order/customer/orderForm";
	}
	
	// 구매자 > 상품 주문
	@RequestMapping(value = "orderBookPro", method = {RequestMethod.GET, RequestMethod.POST})
	public String orderBookPro(HttpServletRequest req, Model model, RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("orderBookPro");
		
		model.addAttribute("req", req);
		int result = service.insertOrder(model);
		redirectAttributes.addFlashAttribute("result", result);
		
		return "redirect:orderComplete";
	}
	
	// 구매자 > 주문 완료 페이지
	@RequestMapping(value = "orderComplete")
	public String orderComplete() throws Exception {
		System.out.println("orderComplete");
		
		return "order/customer/complete";
	}
	
	// 판매자 > 주문 목록
	// 판매자용 쿼리 추가할 것
	@RequestMapping(value = "sellerOrderList", method = {RequestMethod.GET})
	public String sellerOrderList(HttpServletRequest req, Model model) throws Exception {
		System.out.println("sellerOrderList");
		
		model.addAttribute("req", req);
		service.viewList(model);
		
		return "order/seller/list";
	}
	
	// 판매자 > 주문 상세
	// 판매자용 쿼리 추가할 것
	@RequestMapping(value = "sellerOrderDetail", method = {RequestMethod.GET})
	public String sellerOrderDetail(HttpServletRequest req, Model model) throws Exception {
		System.out.println("sellerOrderDetail");
		
		model.addAttribute("req", req);
		service.viewDetail(model, ServiceCode.CUSTOMER_ORDER_DETAIL);
		
		return "order/seller/detail";
	}
	
	// 구매자 > 주문 목록
	@RequestMapping(value = "orderList", method = {RequestMethod.GET})
	public String orderList(HttpServletRequest req, Model model) throws Exception {
		System.out.println("orderList");
		
		model.addAttribute("req", req);
		service.viewList(model);
		
		return "order/customer/list";
	}
	
	// 구매자 > 주문 상세
	@RequestMapping(value = "orderDetail", method = {RequestMethod.GET})
	public String orderDetail(HttpServletRequest req, Model model) throws Exception {
		System.out.println("orderDetail");
		
		model.addAttribute("req", req);
		service.viewDetail(model, ServiceCode.CUSTOMER_ORDER_DETAIL);
		
		return "order/customer/detail";
	}
	
	// 주문 상태 수정
	// 뷰와 연동할 것
	@RequestMapping(value = "updateOrderStat", method = {RequestMethod.GET, RequestMethod.POST})
	public String updateOrderStat(HttpServletRequest req, Model model) throws Exception {
		System.out.println("updateOrderStat");
		
		model.addAttribute("req", req);
		service.updateOrderStat(model);
		
		return "order/resultUpdateStat";
	}
	
	// 판매자 > 주문 삭제
	// 뷰와 연동할 것
	@RequestMapping(value = "deleteOrder", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteOrder(HttpServletRequest req, Model model) throws Exception {
		System.out.println("deleteOrder");
		
		model.addAttribute("req", req);
		service.deleteOrder(model);
		
		return "redirect:orderList";
	}
	
	// 구매자 > 장바구니에 추가
	@RequestMapping(value = "cartAddPro", method = {RequestMethod.GET, RequestMethod.POST})
	public String cartAddPro(HttpServletRequest req, Model model, RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("cartAddPro");
		
		model.addAttribute("req", req);
		
		int result = service.addCart(model);
		
		redirectAttributes.addAttribute("orgNum", req.getParameter("orgNum"));
		redirectAttributes.addFlashAttribute("result", result);
		
		return "redirect:bookDetail";
	}
	
	// 구매자 > 장바구니 목록
	@RequestMapping(value = "cartList", method = RequestMethod.GET)
	public String cartList(HttpServletRequest req, Model model) throws Exception {
		System.out.println("cartList");
		
		model.addAttribute("req", req);
		service.cartList(model);
		
		return "cartList";
	}
	
	// 구매자 > 장바구니에서 주문
	@RequestMapping(value = "orderSelected", method = {RequestMethod.GET, RequestMethod.POST})
	public String orderSelected(HttpServletRequest req, Model model) throws Exception {
		System.out.println("orderSelected");

		model.addAttribute("req", req);
		service.orderSelected(model);
		
		return "order/customer/orderForm";
	}

	// 구매자 > 장바구니에서 제거
	@RequestMapping(value = "removeToCart", method = {RequestMethod.GET, RequestMethod.POST})
	public String removeToCart(HttpServletRequest req, Model model) throws Exception {
		System.out.println("removeToCart");
		
		model.addAttribute("req", req);
		service.removeToCart(model);
		
		return "redirect:cartList";
	}
	
}
