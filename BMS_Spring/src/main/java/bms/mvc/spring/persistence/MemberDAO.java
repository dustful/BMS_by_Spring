package bms.mvc.spring.persistence;

import java.util.ArrayList;
import java.util.Map;

import bms.mvc.spring.domain.MemberVO;

public interface MemberDAO {
	
	// 전체 회원수 조회
	public int getMemberTotal();
	
	// 회원 목록 조회
	public ArrayList<MemberVO> getMembers(Map<String, Object> daoMap);
	
	// 회원 조회
	public MemberVO getExistMember(Map<String, Object> daoMap);
	
	// 회원 상세 조회
	public MemberVO getMember(int mno);
	
	// 회원 등록
	public int postMember(MemberVO vo);
	
	// 회원 수정
	public int putMember(MemberVO vo);
	
	// 회원 상태 수정
	public int putMemberStat(MemberVO vo);
	
	// 회원 삭제
	public int deleteMember(int mno);

}
