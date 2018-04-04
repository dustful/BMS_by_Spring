package bms.mvc.spring.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bms.mvc.spring.service.MemberService;

@Controller
public class MemberController {
	
	@Inject
	MemberService service;
	
	// 로그인 양식
	@RequestMapping(value = "signinForm", method = RequestMethod.GET)
	public String signinForm() throws Exception {
		System.out.println("signinForm");
		
		return "member/signinForm";
	}
	
	// 로그인
	@RequestMapping(value = "signinPro", method = {RequestMethod.GET, RequestMethod.POST})
	public String signinPro(HttpServletRequest req, Model model) throws Exception {
		System.out.println("signinPro");
		
		model.addAttribute("req", req);
		int result = service.checkMember(model);
		String returnPath = "member/signinForm";
		
		if(result > 0) {
			returnPath = "redirect:main";
		}
		
		return returnPath;
	}
	
	// 아이디/비밀 번호 찾기
	
	// 마이 페이지
	@RequestMapping(value = "myPage", method = RequestMethod.GET)
	public String myPage() throws Exception {
		System.out.println("myPage");
		
		return "myPage";
	}
	
	// 로그아웃
	@RequestMapping(value = "signout", method = RequestMethod.GET)
	public String signout(HttpServletRequest req, Model model) throws Exception {
		System.out.println("signout");
		
		model.addAttribute("req", req);
		service.goodbye(model);
		
		return "redirect:main";
	}
	
	// 회원 가입 양식
	@RequestMapping(value = "signupForm", method = RequestMethod.GET)
	public String signupForm() throws Exception {
		System.out.println("signupForm");
		
		return "member/signupForm";
	}
	
	// 회원 가입
	
	// 회원 정보 수정
	
	// 회원 탈퇴 확인
	
	// 회원 탈퇴
	
	// 회원 목록
	@RequestMapping(value = "memberList", method = {RequestMethod.GET})
	public String memberList(HttpServletRequest req, Model model) throws Exception {
		System.out.println("memberList");
		
		model.addAttribute("req", req);
		service.viewList(model);
		
		return "member/list";
	}
	
	// 회원 상세
	@RequestMapping(value = "memberDetail", method = {RequestMethod.GET})
	public String memberDetail(HttpServletRequest req, Model model) throws Exception {
		System.out.println("memberDetail");
		
		model.addAttribute("req", req);
		service.viewDetail(model);
		
		return "member/detail";
	}

}
