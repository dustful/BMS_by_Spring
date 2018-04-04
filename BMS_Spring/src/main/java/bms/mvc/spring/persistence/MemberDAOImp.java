package bms.mvc.spring.persistence;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import bms.mvc.spring.domain.MemberVO;

@Repository
public class MemberDAOImp implements MemberDAO {

	@Inject
	SqlSession sqlSession;
	
	// 전체 회원수 조회
	@Override
	public int getMemberTotal() {
		int tot = 0;
		
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		tot = dao.getMemberTotal();
		
		return tot;
	}

	// 회원 목록 조회
	@Override
	public ArrayList<MemberVO> getMembers(Map<String, Object> daoMap) {
		ArrayList<MemberVO> members = null;
		
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		members = dao.getMembers(daoMap);
		
		return members;
	}
	
	// 회원 조회
	@Override
	public MemberVO getExistMember(Map<String, Object> daoMap) {
		MemberVO member = null;
		
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		member = dao.getExistMember(daoMap);
		
		return member;
	}

	// 회원 상세 조회
	@Override
	public MemberVO getMember(int mno) {
		MemberVO member = null;
		
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		member = dao.getMember(mno);
		
		return member;
	}

	// 회원 등록
	@Override
	public int postMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 회원 수정
	@Override
	public int putMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 회원 상태 수정
	@Override
	public int putMemberStat(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 회원 삭제
	@Override
	public int deleteMember(int mno) {
		// TODO Auto-generated method stub
		return 0;
	}

}
