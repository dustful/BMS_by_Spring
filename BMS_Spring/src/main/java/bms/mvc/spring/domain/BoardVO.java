package bms.mvc.spring.domain;

import java.sql.Timestamp;

public class BoardVO {
	
	int bno; // 게시물 번호
	int mno; // 작성 회원 번호
	String subject; // 제목
	String author; // 작성자
	String content; // 내용
	int bref; // 게시물 분류
	int baref; // 참조 게시물 번호
	int hit; // 조회수
	int recomm; // 추천수
	Timestamp created_at; // 작성일
	Timestamp updated_at; // 수정일
	int bstat; // 게시물 상태
	
	public int getBno() { return bno; }
	public int getMno() { return mno; }
	public String getSubject() { return subject; }
	public String getAuthor() { return author; }
	public String getContent() { return content; }
	public int getBref() { return bref; }
	public int getBaref() { return baref; }
	public int getHit() { return hit; }
	public int getRecomm() { return recomm; }
	public Timestamp getCreated_at() { return created_at; }
	public Timestamp getUpdated_at() { return updated_at; }
	public int getBstat() { return bstat; }
	
	public void setBno(int bno) { this.bno = bno; }
	public void setMno(int mno) { this.mno = mno; }
	public void setSubject(String subject) { this.subject = subject; }
	public void setAuthor(String author) { this.author = author; }
	public void setContent(String content) { this.content = content; }
	public void setBref(int bref) { this.bref = bref; }
	public void setBaref(int baref) { this.baref = baref; }
	public void setHit(int hit) { this.hit = hit; }
	public void setRecomm(int recomm) { this.recomm = recomm; }
	public void setCreated_at(Timestamp created_at) { this.created_at = created_at; }
	public void setUpdated_at(Timestamp updated_at) { this.updated_at = updated_at; }
	public void setBstat(int bstat) { this.bstat = bstat; }

}
