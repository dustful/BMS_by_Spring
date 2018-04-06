package bms.mvc.spring.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bms.mvc.spring.service.BookService;

@Controller
public class BookController {
	
	@Inject
	BookService service;
	
	// 판매자 > 상품 입고 양식
	@RequestMapping(value = "bookRegisterForm")
	public String bookRegisterForm() throws Exception {
		System.out.println("bookRegisterForm");
		
		return "book/seller/registerForm";
	}
	
	// 판매자 > 상품 입고
	@RequestMapping(value = "bookRegisterPro", method = {RequestMethod.GET, RequestMethod.POST})
	public String bookRegisterPro(HttpServletRequest req, Model model) throws Exception {
		System.out.println("bookRegisterPro");
		
		model.addAttribute("req", req);
		service.insertBook(model);
		
		return "redirect:sellerBookList";
	}
	
	// 판매자 > 재고 목록
	@RequestMapping(value = "sellerBookList", method = {RequestMethod.GET})
	public String sellerBookList(HttpServletRequest req, Model model) throws Exception {
		System.out.println("sellerBookList");
		
		model.addAttribute("req", req);
		service.viewList(model);
		
		return "book/seller/list";
	}
	
	// 판매자 > 재고 상세
	@RequestMapping(value = "sellerBookDetail", method = {RequestMethod.GET})
	public String sellerBookDetail(HttpServletRequest req, Model model) throws Exception {
		System.out.println("sellerBookDetail");
		
		model.addAttribute("req", req);
		service.viewDetail(model);
		
		return "book/seller/detail";
	}
	
	// 판매자 > 재고 정보 수정 양식
	@RequestMapping(value = "bookModifyForm", method = {RequestMethod.GET})
	public String bookModifyForm(HttpServletRequest req, Model model) throws Exception {
		System.out.println("bookModifyForm");
		
		model.addAttribute("req", req);
		service.viewDetail(model);
		
		return "book/seller/modifyForm";
	}
	
	// 판매자 > 재고 정보 수정
	@RequestMapping(value = "bookModifyPro", method = {RequestMethod.GET, RequestMethod.POST})
	public String bookModifyPro(HttpServletRequest req, Model model) throws Exception {
		System.out.println("bookModifyPro");
		
		model.addAttribute("req", req);
		service.updateBook(model);
		
		return "redirect:sellerBookList";
	}
	
	// 판매자 > 재고 삭제
	@RequestMapping(value = "deleteBookPro", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteBookPro(HttpServletRequest req, Model model) throws Exception {
		System.out.println("deleteBookPro");
		
		model.addAttribute("req", req);
		service.deleteBook(model);
		
		return "redirect:sellerBookList";
	}
	
	// 구매자 > 상품 목록
	@RequestMapping(value = "bookList", method = {RequestMethod.GET})
	public String bookList(HttpServletRequest req, Model model) throws Exception {
		System.out.println("bookList");
		
		model.addAttribute("req", req);
		service.viewList(model);
		
		return "book/customer/list";
	}
	
	// 구매자 > 상품 상세
	@RequestMapping(value = "bookDetail", method = {RequestMethod.GET})
	public String bookDetail(HttpServletRequest req, Model model) throws Exception {
		System.out.println("bookDetail");
		
		model.addAttribute("req", req);
		service.viewDetail(model);
		
		return "book/customer/detail";
	}

}