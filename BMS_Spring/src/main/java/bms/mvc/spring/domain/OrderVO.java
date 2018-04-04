package bms.mvc.spring.domain;

import java.sql.Date;

public class OrderVO {
	
	private int odno; // �ֹ� ��ȣ
	private int mbno; // ȸ�� ��ȣ
	private String mid; // ȸ�� ���̵�
	private String odname; // �ֹ��ڸ�
	private String odcontact; // �ֹ��� ����ó
	private String rcname; // �����ڸ�
	private String rccontact; // ������ ����ó
	private String rcaddr; // ������ �ּ�
	private int pymd; // ���� ���
	private int bkno; // ���� ��ȣ
	private int odqty; // �ֹ� ����
	private Date odregdate; // �ֹ���
	private int cntref; // �ֹ��Ǽ�
	private int sumprice; // �ֹ� �ݾ�
	private int odref; // ���� �ֹ� ��ȣ
	private BookVO bookvo; // BookVO ���� ����
	private int odstat; // �ֹ� ����
	/*
	 * ===== �ֹ� ���� ���� =====
	 * 0 : �ֹ� �Ϸ�
	 * 1 : ��ҵ� �ֹ�
	 * 2 : ���� Ȯ��
	 * 3 : �߼� ó��
	 * 4 : ��� �Ϸ�
	 * 5 : ȯ�� ���
	 * 6 : ȯ�� �Ϸ�
	 */
	
	public int getOdno() { return odno; }
	public int getMbno() { return mbno; }
	public String getMid() { return mid; }
	public String getOdname() { return odname; }
	public String getOdcontact() { return odcontact; }
	public String getRcname() { return rcname; }
	public String getRccontact() { return rccontact; }
	public String getRcaddr() { return rcaddr; }
	public int getPymd() { return pymd; }
	public int getBkno() { return bkno; }
	public int getOdqty() { return odqty; }
	public Date getOdregdate() { return odregdate; }
	public int getCntref() { return cntref; }
	public int getSumprice() { return sumprice; }
	public int getOdref() { return odref; }
	public BookVO getBookVO() { return bookvo; }
	public int getOdstat() { return odstat; }

	public void setOdno(int odno) { this.odno = odno; }
	public void setMbno(int mbno) { this.mbno = mbno; }
	public void setMid(String mid) { this.mid= mid; }
	public void setOdname(String odname) { this.odname = odname; }
	public void setOdcontact(String odcontact) { this.odcontact = odcontact; }
	public void setRcname(String rcname) { this.rcname = rcname; }
	public void setRccontact(String rccontact) { this.rccontact = rccontact; }
	public void setRcaddr(String rcaddr) { this.rcaddr = rcaddr; }
	public void setPymd(int pymd) { this.pymd = pymd; }
	public void setBkno(int bkno) { this.bkno = bkno; }
	public void setOdqty(int odqty) { this.odqty = odqty; }
	public void setOdregdate(Date odregdate) { this.odregdate = odregdate; }
	public void setCntref(int cntref) { this.cntref = cntref; }
	public void setSumprice(int sumprice) { this.sumprice = sumprice; }
	public void setOdref(int odref) { this.odref = odref; }
	public void setBookVO(BookVO bookvo) { this.bookvo = bookvo; }
	public void setOdstat(int odstat) { this.odstat = odstat; }
	
}
