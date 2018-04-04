package bms.mvc.spring.domain;

import java.sql.Date;

public class MemberVO {
	
	private int mno; // ȸ�� ��ȣ
	private String mid; // ���̵�
	private String mpw; // ��� ��ȣ
	private int mgrd; // ȸ�� ���
	private String mname; // �̸�
	private String mprofimg; // ������ �̹���
	private Date mbirth; // ����
	private String memail; // �̸���
	private String mcp; // �޴� ��ȭ ��ȣ
	private String maddr; // �ּ�
	private int mmileage; // ������
	private int mbalance; // ��ġ��
	private Date mregdate; // ������
	private Date mrecdate; // �ֱ� ������
	private int mstat; // ����
	/*
	 * ===== ȸ�� ��� ���� =====
	 * 0 : �Ǹ���
	 * 1 : ������
	 * 
	 * ===== ȸ�� ���� ���� =====
	 * 0 : �̰���(�������� ����)
	 * 1 : ����(����)
	 * 2 : Ż��
	 */
	
	public int getMno() { return mno; }
	public String getMid() { return mid; }
	public String getMpw() { return mpw; }
	public int getMgrd() { return mgrd; }
	public String getMname() { return mname; }
	public String getMprofimg() { return mprofimg; }
	public Date getMbirth() { return mbirth; }
	public String getMemail() { return memail; }
	public String getMcp() { return mcp; }
	public String getMaddr() { return maddr; }
	public int getMmileage() { return mmileage; }
	public int getMbalance() { return mbalance; }
	public Date getMregdate() { return mregdate; }
	public Date getMrecdate() { return mrecdate; }
	public int getMstat() { return mstat; }
	
	public void setMid(String mid) { this.mid = mid; }
	public void setMno(int mno) { this.mno = mno; }
	public void setMpw(String mpw) { this.mpw = mpw; }
	public void setMgrd(int mgrd) { this.mgrd = mgrd; }
	public void setMname(String mname) { this.mname = mname; }
	public void setMprofimg(String mprofimg) { this.mprofimg = mprofimg; }
	public void setMbirth(Date mbirth) { this.mbirth = mbirth; }
	public void setMemail(String memail) { this.memail = memail; }
	public void setMcp(String mcp) { this.mcp = mcp; }
	public void setMaddr(String maddr) { this.maddr = maddr; }
	public void setMmileage(int mmileage) { this.mmileage = mmileage; }
	public void setMbalance(int mbalance) { this.mbalance = mbalance; }
	public void setMregdate(Date mregdate) { this.mregdate = mregdate; }
	public void setMrecdate(Date mrecdate) { this.mrecdate = mrecdate; }
	public void setMstat(int mstat) { this.mstat = mstat; }
	
}
