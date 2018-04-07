package bms.mvc.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * ========== 프로젝트 개요 ==========
 * 명칭 : BMS Spring(Book Management System for Bookstore)
 * 내용 : Spring Framework & MyBatis 기반 온라인 회원제 서점용 도서 관리 시스템
 * 개발자 : 황성환(dustful@naver.com)
 * 
 * 
 * ========== 작업 내역 ==========
 * 2017.10.6
 * 판매자, 구매자 > 주문 상세 > 주문 상태 변경(AJAX 적용)
 * 메인 페이지 개선(상품 분류별 목록 처리)
 * 상품 후기/문의 게시판 추가
 * 전체 검색 및 검색 결과 처리
 * 
 * 2017.10.5
 * 구매자 > 상품 상세 > 즉시 구입, 장바구니에 추가
 * 구매자 > 장바구니 > 장바구니에서 주문/제거
 * 판매자, 구매자 > 주문 목록/상세
 * 
 * 2017.10.4
 * 판매자 > 상품 등록
 * 판매자, 구매자 > 상품 목록/상세
 * 로그인/아웃, 회원 가입/탈퇴, 개인 정보 열람/수정
 * 판매자 > 회원 등급/상태 변경, 회원 삭제
 * 
 * 
 * ========== 향후 개선 방향 ==========
 * 검색시 쿼리스트링 처리 문제/페이지네이션 AJAX 처리
 * 재고 수정시 파일 경로 문제
 * JQuery 이미지 미리 보기 처리
 * 상품 정보에 적립율, 할인 적용
 * 재고 수량 관련 문제
 * 아이디/비밀번호찾기 처리
 * 결제 방식에 예치금, 쿠폰 적용
 * Oracle 4000 byte 이상 데이터 처리
 * 보안 처리(비밀 번호 암호화, 사용자 세션 미들웨어에 의한 뷰 처리)
 */

@Controller
public class MainController {
	
	// 메인 페이지
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main(HttpServletRequest req, Model model) {
		System.out.println("main");
		
		return "index";
	}
	
}
