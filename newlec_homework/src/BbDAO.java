package bb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbDAO {
	private Connection conn;
	private ResultSet rs;
	
	public BbDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS";
			String dbID = "root";
			String dbPassword = "1111";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";//데이터베이스 오류
	}
	
	public int getNext() {
		String SQL = "SELECT bbsID FROM bbs ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1)+1;
			}
			return 1; //첫번째 게시물인 경우
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;//데이터베이스 오류
	}
	
	public int write(String bbsTitle, String userID, String bbsContent){
		String SQL = "INSERT INTO BBS VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  getNext());
			pstmt.setString(2,  bbsTitle);
			pstmt.setString(3,  userID);
			pstmt.setString(4,  getDate());
			pstmt.setString(5,  bbsContent);
			pstmt.setInt(6,  1);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;//데이터베이스 오류
	}
	public ArrayList<Bb> getList(int pageNumber){
		//String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID LIMIT 10 OFFSET?";
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID LIMIT 10";
		ArrayList<Bb> list = new ArrayList<Bb>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			//pstmt.setInt(1,  (pageNumber - 1) * 10);
			pstmt.setInt(1,  getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bb bb = new Bb();
				bb.setBbsID(rs.getInt(1));
				bb.setBbsTitle(rs.getString(2));
				bb.setUserID(rs.getString(3));
				bb.setBbsDate(rs.getString(4));
				bb.setBbsContent(rs.getString(5));
				bb.setBbsAvailable(rs.getInt(6));
				list.add(bb);
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;//데이터베이스 오류
	} 
	public boolean nextPage(int pageNumber) {
		//String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID LIMIT 10 OFFSET?";
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			//pstmt.setInt(1, (pageNumber - 1) * 10);
			pstmt.setInt(1,  getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
		return false;
	}
	public Bb getBbs(int bbsID) {
		String SQL = "SELECT * FROM BBS WHERE bbsID < ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,  bbsID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
					Bb bb = new Bb();
					bb.setBbsID(rs.getInt(1));
					bb.setBbsTitle(rs.getString(2));
					bb.setUserID(rs.getString(3));
					bb.setBbsDate(rs.getString(4));
					bb.setBbsContent(rs.getString(5));
					bb.setBbsAvailable(rs.getInt(6));
					return bb;
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
}
