package bms.mvc.spring.persistence;

import java.util.ArrayList;
import java.util.Map;

import bms.mvc.spring.domain.MemberVO;

public interface MemberDAO {
	
	// ��ü ȸ���� ��ȸ
	public int getMemberTotal();
	
	// ȸ�� ��� ��ȸ
	public ArrayList<MemberVO> getMembers(Map<String, Object> daoMap);
	
	// ȸ�� ��ȸ
	public MemberVO getExistMember(Map<String, Object> daoMap);
	
	// ȸ�� �� ��ȸ
	public MemberVO getMember(int mno);
	
	// ȸ�� ���
	public int postMember(MemberVO vo);
	
	// ȸ�� ����
	public int putMember(MemberVO vo);
	
	// ȸ�� ���� ����
	public int putMemberStat(MemberVO vo);
	
	// ȸ�� ����
	public int deleteMember(int mno);

}
