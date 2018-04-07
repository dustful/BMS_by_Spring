package bms.mvc.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * ========== ������Ʈ ���� ==========
 * ��Ī : BMS Spring(Book Management System for Bookstore)
 * ���� : Spring Framework & MyBatis ��� �¶��� ȸ���� ������ ���� ���� �ý���
 * ������ : Ȳ��ȯ(dustful@naver.com)
 * 
 * 
 * ========== �۾� ���� ==========
 * 2017.10.6
 * �Ǹ���, ������ > �ֹ� �� > �ֹ� ���� ����(AJAX ����)
 * ���� ������ ����(��ǰ �з��� ��� ó��)
 * ��ǰ �ı�/���� �Խ��� �߰�
 * ��ü �˻� �� �˻� ��� ó��
 * 
 * 2017.10.5
 * ������ > ��ǰ �� > ��� ����, ��ٱ��Ͽ� �߰�
 * ������ > ��ٱ��� > ��ٱ��Ͽ��� �ֹ�/����
 * �Ǹ���, ������ > �ֹ� ���/��
 * 
 * 2017.10.4
 * �Ǹ��� > ��ǰ ���
 * �Ǹ���, ������ > ��ǰ ���/��
 * �α���/�ƿ�, ȸ�� ����/Ż��, ���� ���� ����/����
 * �Ǹ��� > ȸ�� ���/���� ����, ȸ�� ����
 * 
 * 
 * ========== ���� ���� ���� ==========
 * �˻��� ������Ʈ�� ó�� ����/���������̼� AJAX ó��
 * ��� ������ ���� ��� ����
 * JQuery �̹��� �̸� ���� ó��
 * ��ǰ ������ ������, ���� ����
 * ��� ���� ���� ����
 * ���̵�/��й�ȣã�� ó��
 * ���� ��Ŀ� ��ġ��, ���� ����
 * Oracle 4000 byte �̻� ������ ó��
 * ���� ó��(��� ��ȣ ��ȣȭ, ����� ���� �̵��� ���� �� ó��)
 */

@Controller
public class MainController {
	
	// ���� ������
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main(HttpServletRequest req, Model model) {
		System.out.println("main");
		
		return "index";
	}
	
}
