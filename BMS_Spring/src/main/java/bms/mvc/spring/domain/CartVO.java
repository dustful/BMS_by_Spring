package bms.mvc.spring.domain;

import java.sql.Date;

public class CartVO {
	
	private int ctno; // 장바구니 번호
	private int mbno; // 회원 번호
	private int bkno; // 도서 번호
	private BookVO bkvo; // 도서 정보
	private Date ctregdate; // 등록일
	
	public int getCtno() { return ctno; }
	public int getMbno() { return mbno; }
	public int getBkno() { return bkno; }
	public BookVO getBkvo() { return bkvo; }
	public Date getCtregdate() { return ctregdate; }
	
	public void setCtno(int ctno) { this.ctno = ctno; }
	public void setMbno(int mbno) { this.mbno = mbno; }
	public void setBkno(int bkno) { this.bkno = bkno; }
	public void setBkvo(BookVO bkvo) { this.bkvo = bkvo; }
	public void setCtregdate(Date ctregdate) { this.ctregdate = ctregdate; }
	
}
