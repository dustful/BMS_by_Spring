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
	
	// ������ > ��ǰ �ֹ� ���
	@RequestMapping(value = "orderBookForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String orderBookForm(HttpServletRequest req, Model model) throws Exception {
		System.out.println("orderBookForm");
		
		model.addAttribute("req", req);
		service.viewDetail(model, ServiceCode.CUSTOMER_ORDER_FORM);
		
		return "order/customer/orderForm";
	}
	
	// ������ > ��ǰ �ֹ�
	@RequestMapping(value = "orderBookPro", method = {RequestMethod.GET, RequestMethod.POST})
	public String orderBookPro(HttpServletRequest req, Model model, RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("orderBookPro");
		
		model.addAttribute("req", req);
		int result = service.insertOrder(model);
		redirectAttributes.addFlashAttribute("result", result);
		
		return "redirect:orderComplete";
	}
	
	// ������ > �ֹ� �Ϸ� ������
	@RequestMapping(value = "orderComplete")
	public String orderComplete() throws Exception {
		System.out.println("orderComplete");
		
		return "order/customer/complete";
	}
	
	// �Ǹ��� > �ֹ� ���
	// �Ǹ��ڿ� ���� �߰��� ��
	@RequestMapping(value = "sellerOrderList", method = {RequestMethod.GET})
	public String sellerOrderList(HttpServletRequest req, Model model) throws Exception {
		System.out.println("sellerOrderList");
		
		model.addAttribute("req", req);
		service.viewList(model);
		
		return "order/seller/list";
	}
	
	// �Ǹ��� > �ֹ� ��
	// �Ǹ��ڿ� ���� �߰��� ��
	@RequestMapping(value = "sellerOrderDetail", method = {RequestMethod.GET})
	public String sellerOrderDetail(HttpServletRequest req, Model model) throws Exception {
		System.out.println("sellerOrderDetail");
		
		model.addAttribute("req", req);
		service.viewDetail(model, ServiceCode.CUSTOMER_ORDER_DETAIL);
		
		return "order/seller/detail";
	}
	
	// ������ > �ֹ� ���
	@RequestMapping(value = "orderList", method = {RequestMethod.GET})
	public String orderList(HttpServletRequest req, Model model) throws Exception {
		System.out.println("orderList");
		
		model.addAttribute("req", req);
		service.viewList(model);
		
		return "order/customer/list";
	}
	
	// ������ > �ֹ� ��
	@RequestMapping(value = "orderDetail", method = {RequestMethod.GET})
	public String orderDetail(HttpServletRequest req, Model model) throws Exception {
		System.out.println("orderDetail");
		
		model.addAttribute("req", req);
		service.viewDetail(model, ServiceCode.CUSTOMER_ORDER_DETAIL);
		
		return "order/customer/detail";
	}
	
	// �ֹ� ���� ����
	// ��� ������ ��
	@RequestMapping(value = "updateOrderStat", method = {RequestMethod.GET, RequestMethod.POST})
	public String updateOrderStat(HttpServletRequest req, Model model) throws Exception {
		System.out.println("updateOrderStat");
		
		model.addAttribute("req", req);
		service.updateOrderStat(model);
		
		return "order/resultUpdateStat";
	}
	
	// �Ǹ��� > �ֹ� ����
	// ��� ������ ��
	@RequestMapping(value = "deleteOrder", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteOrder(HttpServletRequest req, Model model) throws Exception {
		System.out.println("deleteOrder");
		
		model.addAttribute("req", req);
		service.deleteOrder(model);
		
		return "redirect:orderList";
	}
	
	// ������ > ��ٱ��Ͽ� �߰�
	@RequestMapping(value = "cartAddPro", method = {RequestMethod.GET, RequestMethod.POST})
	public String cartAddPro(HttpServletRequest req, Model model, RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("cartAddPro");
		
		model.addAttribute("req", req);
		
		int result = service.addCart(model);
		
		redirectAttributes.addAttribute("orgNum", req.getParameter("orgNum"));
		redirectAttributes.addFlashAttribute("result", result);
		
		return "redirect:bookDetail";
	}
	
	// ������ > ��ٱ��� ���
	@RequestMapping(value = "cartList", method = RequestMethod.GET)
	public String cartList(HttpServletRequest req, Model model) throws Exception {
		System.out.println("cartList");
		
		model.addAttribute("req", req);
		service.cartList(model);
		
		return "cartList";
	}
	
	// ������ > ��ٱ��Ͽ��� �ֹ�
	@RequestMapping(value = "orderSelected", method = {RequestMethod.GET, RequestMethod.POST})
	public String orderSelected(HttpServletRequest req, Model model) throws Exception {
		System.out.println("orderSelected");

		model.addAttribute("req", req);
		service.orderSelected(model);
		
		return "order/customer/orderForm";
	}

	// ������ > ��ٱ��Ͽ��� ����
	@RequestMapping(value = "removeToCart", method = {RequestMethod.GET, RequestMethod.POST})
	public String removeToCart(HttpServletRequest req, Model model) throws Exception {
		System.out.println("removeToCart");
		
		model.addAttribute("req", req);
		service.removeToCart(model);
		
		return "redirect:cartList";
	}
	
}
