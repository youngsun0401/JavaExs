package db_imageUpload;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO {
	private static BoardDAO instance = null;// 싱글톤패턴 객체
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	private DataSource dataFactory;

	//// 싱글톤패턴 객체얻개
	public static BoardDAO get() throws ClassNotFoundException, SQLException {
		if( instance == null )
			instance = new BoardDAO();
		return instance;
	}
	
	//// DB 연결
	private BoardDAO() {
		try {
      String url = "jdbc:mysql://localhost:3306/디비이름?useSSL=false";
      String id = "root";
      String pw = "rootroot";
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(url, id, pw);
      System.out.println("DB 연결 성공");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
  
	//// DB 닫기
	public static void close() {
		try {
			if( conn != null ) {
				conn.close();
			}
			if( pstmt != null ) {
				pstmt.close();
			}
			if( rs != null ) {
				rs.close();
			}
		}catch (SQLException e) {}
		System.out.println("db closed");
	}
  
	//// 이미지 업로드
	public static void upload(File file) {
	      String sql = "insert into imageTBL(img) values(?)";
	      try {
	         FileInputStream fis = new FileInputStream(file);
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setBinaryStream(1, fis);
	         pstmt.executeUpdate();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	
}
