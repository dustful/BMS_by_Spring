package bms.mvc.spring.service;

import org.springframework.ui.Model;

public interface MemberService {
	
	// 회원 목록
	public void viewList(Model model) throws Exception;
	
	// 회원 조회
	public int checkMember(Model model) throws Exception;
	
	// 회원 상세
	public void viewDetail(Model model) throws Exception;
	
	// 회원 등록
	public void insertMember(Model model) throws Exception;
	
	// 회원 수정
	public void updateMember(Model model) throws Exception;
	
	// 회원 삭제
	public void deleteMember(Model model) throws Exception;
	
	// 회원 접속 해제
	public void goodbye(Model model) throws Exception;

}
