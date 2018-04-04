package bms.mvc.spring.domain;

import java.sql.Timestamp;

public class BoardVO {
	
	/*
	 * 멤버 재구성 예정
	 */
	int bno; // 게시물 번호
	int mno; // 작성 회원 번호
	String subj; // 제목
	String writer; // 작성자
	String bcontent; // 내용
	int bref; // 참조 게시물 번호
	int hit; // 조회수
	int recomm; // 추천수
	Timestamp bregdate; // 작성일
	Timestamp upddate; // 수정일
	int bstat; // 게시물 상태
	
	public int getBno() { return bno; }
	public int getMno() { return mno; }
	public String getBsubj() { return subj; }
	public String getBwriter() { return writer; }
	public String getBcontent() { return bcontent; }
	public int getBref() { return bref; }
	public int getBhit() { return hit; }
	public int getBrecomm() { return recomm; }
	public Timestamp getBreg_date() { return bregdate; }
	public Timestamp getBupd_date() { return upddate; }
	public int getBstat() { return bstat; }
	
	public void setBno(int bno) { this.bno = bno; }
	public void setMno(int mno) { this.mno = mno; }
	public void setBsubj(String subj) { this.subj = subj; }
	public void setBwriter(String writer) { this.writer = writer; }
	public void setBcontent(String bcontent) { this.bcontent = bcontent; }
	public void setBref(int bref) { this.bref = bref; }
	public void setBhit(int hit) { this.hit = hit; }
	public void setBrecomm(int recomm) { this.recomm = recomm; }
	public void setBreg_date(Timestamp bregdate) { this.bregdate = bregdate; }
	public void setBupd_date(Timestamp upddate) { this.upddate = upddate; }
	public void setBstat(int bstat) { this.bstat = bstat; }

}
