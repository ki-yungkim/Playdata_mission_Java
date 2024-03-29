package com.work.model.service;

import com.work.model.dto.AdminMember;
import com.work.model.dto.GeneralMember;
import com.work.model.dto.Member;
import com.work.model.dto.SpecialMember;

/**
 * 회원을 관리하기 위한 서비스 클래스 (추상클래스 설계 변경) 
 * -- 추상클래스 구성요소
 * 	>> 클래스 구성요소 + [추상메서드] //추상메서드 없어도 추상 클래스일 수 있다
 * 
 * 
 * 
 * 
 * -- 회원들을 관리하기 위한 자료 저장구조 : 배열 다형성 반영(부모타입으로 진행) 
 * -- 메서드 아규먼트 다형성 반영 (부모타입), 반환타입?? 
 * 
 * -- 메서드명(매개변수) : 반환타입
 * 
 * -- C (등록) 
 * -- R (조회)
 * => 단일조회 (상세조회) => 아이디
 * => 다중조회 (전체조회, 그룹조회) => 전체조회(), 등급별회원전체조회(등급)
 * 
 * -- U (변경)
 * => 전체변경()
 * => 부분변경(비밀번호 변경 )
 * 
 * -- D	(삭제)
 * => 전체삭제
 * => 특정삭제 : 아이디 (아이디 입력하면 해당 회원 삭제)
 *  
 *  
 * @author 김기영
 *
 */
public abstract class MemberServiceAbstract {
	
	/**
	 * 회원등록
	 * -- 메서드명 : addMember()
	 * -- 매개변수 : Member 
	 * -- 반환 타입 : void
	 */
	public abstract void addMember(Member dto); 
	
	/**
	 * CRUD 메서드에서 사용하기 위한 회원 존재유무 및 저장 위치 조회 메서드
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param memberEntryDate 가입일
	 * @param memberMobile 전화번호
	 * @param memberEmail 이메일
	 * @param memberGrade 등급
	 * @return 존재시에 저장위치 번호, 미존재시 -1
	 */
	
	public abstract int exist(String memberId); 
	
	public abstract int existPw(String memberPw);
	
	public abstract int existEntryDate(String entryDate);
	
	public abstract int existMobile(String mobile);
	
	public abstract int existEmail(String email);
	
	public abstract int existGrade(String grade);
	
	public abstract int[] existGradeArray(String grade);

	/**
	 * 전체조회
	 * -- 메서드명 : getMember()
	 * -- 매개변수 : no argument
	 * -- 반환 타입 : Member[]
	 */
	
	public abstract Member[] getMember();
	
	
	/**
	 * 상세조회
	 * -- 메서드명 : getMember() 
	 * -- 매개변수 : memberId
	 * -- 반환 타입 : Member (반환타입다형성 : 부모타입) 추상메서드 선언!
	 */
	
	public abstract Member getMember(String memberId);
	
	/**
	 * 전체변경
	 * 
	 * -- 메서드명 : setMember()
	 * -- 매개변수 : Member
	 * -- 반환 타입 : void (일단 void) / 오류 발생시 메세지 처리
	 */
	
	public abstract void setMember(Member dto);
	
	/**
	 * 비밀번호변경
	 * 아이디 비밀번호 변경암호 
	 * 아이디 위치 - 저장되어 있는 비밀번호와 전달받은 비밀번호가 같은지 비교 equals - 같으면 변경 암호로 변경 - setMemberPw, 아이디부터 없으면 존재X, 아이디는 존재하는데 비밀번호 안 맞으면 안 맞다고 출력 (오류사항 2개)
	 * -- 메서드명 : setMemberPw()
	 * -- 매개변수 : 아이디, 비밀번호, 변경비밀번호
	 * -- 반환 타입 : boolean 암호변경성공 true, 실패하면 false 
	 */
	
	public abstract boolean setMemberPw(String memberId, String memberPw, String newPw);

	/**
	 * 회원탈퇴 
	 * 
	 * -- 메서드명 : removeMember()
	 * -- 매개변수 : 아이디, 비밀번호
	 * -- 반환 타입 : void
	 */
	
	public abstract void removeMember(String memberId, String memberPw);
	
	/**
	 * 회원전체탈퇴 (데이터초기화) 
	 * -- 메서드명 : removeMember()
	 * -- 매개변수 : no argument
	 * -- 반환 타입 : void 
	 */
	
	public abstract void removeMember();
	
	/**
	 * 로그인
	 * -- 메서드명 : login()
	 * -- 매개변수 : memberId, memberPw
	 * -- 반환 타입 : void 
	 */
	public abstract void login(String memberId, String memberPW);
	
	
	/**
	 * 가입일 조회해서 아이디 출력
	 * 
	 */
	public abstract void entryDateId(String entryDate);
	
	/**
	 * 전화번호, 이메일  조회해서 아이디 출력
	 */
	public abstract void searchId(String mobile, String email);
	
	/**
	 * 아이디, 전화번호, 이메일 조회해서 비밀번호 출력
	 */
	public abstract void searchPw(String memberId, String mobile, String email);
	
	/**
	 * 등급별 회원 아이디 조회
	 */
	public abstract void gradeMember(String grade);
	
	
	/**
	 * 초기회원 등록 메서드
	 */
	public void initMember() {
		Member dto1 = new GeneralMember("user01", "password01", "홍길동", "01012341000", "user01@work.com", "2020-12-15", "G", 50000);
		Member dto2 = new GeneralMember("user02", "password02", "강감찬", "01012342000", "user02@work.com", "2021-01-05", "G", 950000);
		Member dto3 = new SpecialMember("user03", "password03", "이순신", "01012343000", "user03@work.com", "2020-11-15", "S", "강동원");
		Member dto4 = new SpecialMember("user04", "password04", "김유신", "01012344000", "user04@work.com", "2021-01-05", "S", "김하린");
		Member dto5 = new AdminMember("administrator", "admin1004", "유관순", "01012345000", "administrator@work.com", "2020-04-01", "A", "선임");
		
	};
	

}
