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

	// ȸ�� ���
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
		tot = dao.getMemberTotal();

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
			
			ArrayList<MemberVO> members = dao.getMembers(daoMap);
			model.addAttribute("members", members);
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
	
	// ȸ�� ��ȸ
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

	// ȸ�� ��
	@Override
	public void viewDetail(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int mno = Integer.parseInt(req.getParameter("mno"));
		MemberVO member = dao.getMember(mno);
		
		model.addAttribute("member", member);
	}

	// ȸ�� ���
	@Override
	public void insertMember(Model model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// ȸ�� ����
	@Override
	public void updateMember(Model model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// ȸ�� ����
	@Override
	public void deleteMember(Model model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// ȸ�� ���� ����
	@Override
	public void goodbye(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		req.getSession().invalidate();
	}
	
}
