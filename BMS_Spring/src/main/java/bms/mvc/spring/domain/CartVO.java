package bms.mvc.spring.domain;

import java.sql.Date;

public class CartVO {
	
	private int ctno; // ��ٱ��� ��ȣ
	private int mbno; // ȸ�� ��ȣ
	private int bkno; // ���� ��ȣ
	private BookVO bkvo; // ���� ����
	private Date ctregdate; // �����
	
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
