package bms.mvc.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import bms.mvc.spring.domain.MemberVO;
import bms.mvc.spring.persistence.MemberDAO;

@Service
public class MemberServiceImp implements MemberService {

	@Inject
	MemberDAO dao;

	// 회원 목록
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
		tot = dao.getMemberTotal();

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
			
			ArrayList<MemberVO> members = dao.getMembers(daoMap);
			model.addAttribute("members", members);
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
	
	// 회원 조회
	@Override
	public int checkMember(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		String mid = req.getParameter("mid");
		String mpw = req.getParameter("mpw");
		Map<String, Object> daoMap = new HashMap<String, Object>();
		
		daoMap.put("mid", mid);
		daoMap.put("mpw", mpw);
		
		MemberVO member = dao.getExistMember(daoMap);
		int result = 0;
		
		if(member == null) {
			model.addAttribute("result", result);
		} else {
			result = 1;
			
			req.getSession().setAttribute("mno", member.getMno());
			req.getSession().setAttribute("mid", member.getMid());
			req.getSession().setAttribute("mname", member.getMname());
			req.getSession().setAttribute("mgrd", member.getMgrd());
			req.getSession().setAttribute("mrecdate", member.getMrecdate());
			// req.getSession().setAttribute("profimg", member.getMprofimg());
		}
		
		return result;
	}

	// 회원 상세
	@Override
	public void viewDetail(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int mno = Integer.parseInt(req.getParameter("mno"));
		MemberVO member = dao.getMember(mno);
		
		model.addAttribute("member", member);
	}

	// 회원 등록
	@Override
	public void insertMember(Model model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// 회원 수정
	@Override
	public void updateMember(Model model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// 회원 삭제
	@Override
	public void deleteMember(Model model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// 회원 접속 해제
	@Override
	public void goodbye(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		req.getSession().invalidate();
	}
	
}
