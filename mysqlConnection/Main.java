package mysqlConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dto.Car;

public class Main {
	private static CarDB db;
	static Scanner s;
	
	public static void main(String[] args) {
		s = new Scanner(System.in);
		
		try {
			db = new CarDB();
		} catch (ClassNotFoundException|SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		
		//// 숫자로 메뉴 입력받아 해당 기능 실행하며 0번인 종료메뉴 실행될 때까지 무한반복
		for( int menu;;){
			System.out.println("[메뉴] 1) INSERT 2) SELECT 3) SELECT ALL 4) DELETE 5) UPDATE TYPE 0) EXIT");
			switch( menu = s.nextInt() ) {
			case 0: db.close(); break;
			case 1: insert(); break;
			case 2: select(); break;
			case 3: selectAll(); break;
			case 4: delete(); break;
			case 5: updateType(); break;
			default: System.out.println("없는 메뉴입니다.");
			}
			if( menu == 0 ) break;// 종료 메뉴 골랐으면 반복 끝
		}
		s.close();
	}
	
	//// 메뉴별 기능
	private static void insert() {
		System.out.println("차량번호, 종류, 년식? (띄어쓰기로 구분 후 엔터)");
		try {
			Car car = db.insert( s.nextInt(), s.next(), s.nextInt() );// 차량의 요소들을 입력받아 insert
			System.out.println("INSERT 성공 ("+car.getNum()+", "+car.getCtype()+", "+car.getCyear()+")");
		} catch (SQLException e1) {
			System.out.println("INSERT 실패");
			e1.printStackTrace();
		}
	}
	
	private static void select() {
		System.out.println("조회할 차량의 번호?");
		try {
			Car car = db.select( s.nextInt() );// 차량 조회
			System.out.println("["+car.getNum()+"] "+car.getCtype()+", "+car.getCyear()+"년식");// 조회한 차량 표시
		} catch (SQLException e) {
			System.out.println("차량 조회 실패");
			e.printStackTrace();
		}
	}
	
	private static void selectAll() {
		System.out.println("전체조회합니다.");
		try {
			ArrayList<Car> cars = db.selectAll();// 차량 목록 조회하여 받아오기
			for( Car car: cars ) {// 목록의 모든 항목 출력
				System.out.println( "["+car.getNum()+"] "+car.getCtype()+", "+car.getCyear()+"년식" );
			}
		} catch (SQLException e) {
			System.out.println("조회 실패");
			e.printStackTrace();
		}
	}
	
	private static void delete() {
		System.out.println("몇 번 차량을 삭제할까요?");
		try {
			db.delete( s.nextInt() );
			System.out.println("삭제했습니다.");
		} catch (SQLException e) {
			System.out.println("삭제 실패");
			e.printStackTrace();
		}
	}
	
	private static void updateType() {
		System.out.println("몇 번 차량의 차종을 수정할까요?");
		int num = s.nextInt();
		System.out.println("차종을 뭘로 수정할까요?");
		try {
			db.update( num, s.next() );
			System.out.println("수정 완료");
		} catch (SQLException e) {
			System.out.println("수정 실패");
			e.printStackTrace();
		}
	}
}
