package bms.mvc.spring.service;

public interface ServiceCode {
	
	/*
	 * �Ǹ��� ���� �ڵ�
	 */
	public static final int INCLUDED_DELIVERY_CHARGE = 50000; // ��ۺ� �����ϴ� ���� �ֹ���
	public static final int DELIVERY_CHARGE = 3000; // ��ۺ�
	public static final int SELLER_BOOK_LIST = 10; // ��ǰ ���
	public static final int SELLER_BOOK_DETAIL = 11; // ��ǰ ��
	public static final int SELLER_BOOK_MODIFY_FORM = 12; // ��ǰ ���� ���
	public static final int SELLER_ORDER_LIST = 13; // �ֹ� ���
	public static final int SELLER_ORDER_DETAIL = 14; // �ֹ� ��
	
	/*
	 * ������ ���� �ڵ�
	 */
	public static final int CUSTOMER_BOOK_LIST = 20; // ��ǰ ���
	public static final int CUSTOMER_BOOK_DETAIL = 21; // ��ǰ ��
	public static final int CUSTOMER_ORDER_FORM = 22; // �ֹ� ���
	public static final int CUSTOMER_ORDER_LIST = 23; // �ֹ� ���
	public static final int CUSTOMER_ORDER_DETAIL = 24; // �ֹ� ��
	
	/*
	 * ���� ���� �ڵ�
	 */
	public static final int SELECT_BOOK_TOTAL = 100; // ��ü ������ ��ȸ
	public static final int SELECT_BOOK_LIST = 101; // ���� ��� ��ȸ
	public static final int SELECT_BOOK_DETAIL = 102; // ���� �� ��ȸ
	public static final int INSERT_BOOK = 103; // ���� ���
	public static final int UPDATE_BOOK = 104; // ���� ����
	public static final int UPDATE_BOOK_QTY = 105; // ���� ���� ����
	public static final int DELETE_BOOK = 106; // ���� ����
	
	/*
	 * �ֹ� ���� �ڵ�
	 */
	public static final int SELECT_ORDER_TOTAL = 200; // ��ü �ֹ� �Ǽ� ��ȸ
	public static final int SELECT_ORDER_LIST = 201; // �ֹ� ��� ��ȸ
	public static final int SELECT_ORDER_DETAIL = 202; // �ֹ� �� ��ȸ
	public static final int SELECT_ORDER_NEXTVAL = 203; // �ֹ� ��ȣ ����
	public static final int INSERT_ORDER = 204; // �ֹ� ���
	public static final int UPDATE_ORDER = 205; // �ֹ� ����
	public static final int DELETE_ORDER = 206; // �ֹ� ����
	
	/*
	 * ȸ�� ���� �ڵ�
	 */
	public static final int SELECT_MEMBER_TOTAL = 300; // ��ü ȸ���� ��ȸ
	public static final int SELECT_MEMBER_LIST = 301; // ȸ�� ��� ��ȸ
	public static final int SELECT_MEMBER = 302; // ȸ�� ��ȸ
	public static final int SELECT_MEMBER_DETAIL = 303; // ȸ�� �� ��ȸ
	public static final int INSERT_MEMBER = 304; // ȸ�� ���
	public static final int UPDATE_MEMBER = 305; // ȸ�� ����
	public static final int UPDATE_MEMBER_STAT = 306; // ȸ�� ���� ����
	public static final int DELETE_MEMBER = 307; // ȸ�� ����
	
	/*
	 * �Խ��� ���� �ڵ�
	 */
	public static final int SELECT_BOARD_TOTAL = 400; // ��ü �Խù��� ��ȸ
	public static final int SELECT_BOARD_LIST = 401; // �Խù� ��� ��ȸ
	public static final int SELECT_BOARD_DETAIL = 402; // �Խù� �� ��ȸ
	public static final int INSERT_BOARD = 403; // �Խù� ���
	public static final int UPDATE_BOARD = 404; // �Խù� ����
	public static final int DELETE_BOARD = 405; // �Խù� ����

}
