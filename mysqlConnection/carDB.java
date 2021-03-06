package mysqlConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Car;

/* car db 만든 쿼리문은 이렇다
DROP database if exists cardb;
CREATE database cardb;
use cardb;

DROP table if exists carTBL;
CREATE table carTBL(
	num int primary key not null,
	ctype varchar(100) not null,
	cyear int not null
);
*/

public class CarDB {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//// DB 연결 (cardb라는 이름의 db에)
	public CarDB() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/cardb?useSSL=false";// 참고로 localhost는 127.0.0.1과 같다.
		String id = "root";
		String pw = "rootroot";
		Class.forName("com.mysql.cj.jdbc.Driver");// 반환값도 안 받는 게 왜 있는가 했는데…. 클래스 정보를 가져온다고 하는데…. 나도 잘 모르겠는데 이클립스에서 빌드패스로 가져오면 이거 없어도 잘 되는데 딴데서 불러오면 이거 없으면 안 되네.
		conn = DriverManager.getConnection(url, id, pw);
		System.out.println("DB 연결 성공");
	}
	
	//// CRUD 기능들
	public Car insert( Car car ) throws SQLException {//
		String sql = "INSERT into carTBL ( num, ctype, cyear) values (?, ?, ?);";// sql문
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, car.getNum());
		pstmt.setString(2, car.getCtype());
		pstmt.setInt(3, car.getCyear());
		pstmt.executeUpdate();
		return car;
	}
	public Car insert( int num, String ctype, int cyear ) throws SQLException {
		return insert( new Car(num, ctype, cyear) );
	}
	
	public Car select( int num ) throws SQLException {
		Car car;
		String sql = "SELECT * from carTBL where num = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		if( rs.next() ) {// 
			car = new Car( rs.getInt("num"), rs.getString("ctype"), rs.getInt("cyear") );// select 결과를 dto에 담음
			return car;
		}else {
			throw new SQLException("no car found");
		}
	}
	public ArrayList<Car> selectAll() throws SQLException {
		ArrayList<Car> cars = new ArrayList<Car>();
		String sql = "SELECT * from carTBL";
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt.getClass());
		rs = pstmt.executeQuery();
		while( rs.next() ) {// 
			cars.add( new Car( rs.getInt("num"), rs.getString("ctype"), rs.getInt("cyear") ) );// select 결과를 하나씩 배열에 담음
		}
		return cars;
	}
	
	public void delete( int num ) throws SQLException {
		String sql = "DELETE from carTBL where num = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.executeUpdate();
	}
	
	public void update( int num, Car car ) throws SQLException {
		String sql = "UPDATE set ctype = ?, cyear = ? where num = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, car.getCtype());
		pstmt.setInt(2, car.getCyear());
		pstmt.setInt(3, num);
		pstmt.executeUpdate();
	}
	public void update( int num, String ctype ) throws SQLException {
		String sql = "UPDATE carTBL set ctype = ? where num = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ctype);
		pstmt.setInt(2, num);
		pstmt.executeUpdate();
	}
	
	//// DB 닫기
	public void close() {
		if( conn != null ) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if( pstmt != null ) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if( rs != null ) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("rs close 실패");
				e.printStackTrace();
			}
		}
		System.out.println("db closed");
	}
}
