package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MemberVO;

public class MemberDAO {
	private static MemberDAO instance = null;// 싱글톤패턴 객체
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	//// 싱글톤패턴 객체얻개
	public static MemberDAO get() throws ClassNotFoundException, SQLException {
		if( instance == null )
			instance = new MemberDAO();
		return instance;
	}
	
	//// DB 연결 (cardb라는 이름의 db에)
	private MemberDAO() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/jspTestDB?useSSL=false";// 참고로 localhost는 127.0.0.1과 같다.
		String id = "root";
		String pw = "rootroot";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, id, pw);
		System.out.println("DB 연결 성공");
	}

	public static MemberVO select( String id ) throws SQLException {
		MemberVO member;
		String sql = "SELECT * from t_member where id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if( rs.next() ) {// 
			member = new MemberVO( rs.getString("id"), rs.getString("pwd"), rs.getString("name"), rs.getString("email"), rs.getDate("joinDate") );
			return member;
		}else {
			throw new SQLException("no member found");
		}
	}
	public static List<MemberVO> selectAll() throws SQLException {
		List<MemberVO> members = new ArrayList<MemberVO>();
		String sql = "SELECT * from t_member";
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt.getClass());
		rs = pstmt.executeQuery();
		while( rs.next() ) {// 
			members.add( new MemberVO( rs.getString("id"), rs.getString("pwd"), rs.getString("name"), rs.getString("email"), rs.getDate("joinDate") ) );
		}
		return members;
	}
}
