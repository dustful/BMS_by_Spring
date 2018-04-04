package bms.mvc.spring.domain;

import java.sql.Timestamp;

public class BoardVO {
	
	/*
	 * ��� �籸�� ����
	 */
	int bno; // �Խù� ��ȣ
	int mno; // �ۼ� ȸ�� ��ȣ
	String subj; // ����
	String writer; // �ۼ���
	String bcontent; // ����
	int bref; // ���� �Խù� ��ȣ
	int hit; // ��ȸ��
	int recomm; // ��õ��
	Timestamp bregdate; // �ۼ���
	Timestamp upddate; // ������
	int bstat; // �Խù� ����
	
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
