package bms.mvc.spring.domain;

import java.sql.Date;

public class BookVO {
	
	private int bkno; // ���� ��ȣ
	private String bkname; // ������
	private String bkimg; // ���� �̹���
	private String bkauthor; // ����
	private String bkpublisher; // ���ǻ�
	private int bkprice; // ����
	private int bkqty; // ��� ����
	private String bkcontent; // ���� �Ұ�
	private Date bkregdate; // �԰���
	
	public int getBkno() { return bkno; }
	public String getBkname() { return bkname; }
	public String getBkimg() { return bkimg; }
	public String getBkauthor() { return bkauthor; }
	public String getBkpublisher() { return bkpublisher; }
	public int getBkprice() { return bkprice; }
	public int getBkqty() { return bkqty; }
	public String getBkcontent() { return bkcontent; }
	public Date getBkregdate() { return bkregdate; }
	
	public void setBkno(int bkno) { this.bkno = bkno; }
	public void setBkname(String bkname) { this.bkname = bkname; }
	public void setBkimg(String bkimg) { this.bkimg = bkimg; }
	public void setBkauthor(String bkauthor) { this.bkauthor = bkauthor; }
	public void setBkpublisher(String bkpublisher) { this.bkpublisher = bkpublisher; }
	public void setBkprice(int bkprice) { this.bkprice = bkprice; }
	public void setBkqty(int bkqty) { this.bkqty = bkqty; }
	public void setBkcontent(String bkcontent) { this.bkcontent = bkcontent; }
	public void setBkregdate(Date bkregdate) { this.bkregdate = bkregdate; }
	
}
