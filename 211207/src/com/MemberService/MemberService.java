package com.MemberService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.MemberVO.MemberVo;


public class MemberService {

public List<MemberVo> getMemberList(){
		
		return getMemberList("userName","", 1, 15, "JoinDate", "DESC" );
	}
	
public List<MemberVo> getMemberList(int page){
		
		return getMemberList("userName","", page, 15, "JoinDate", "DESC" );
	}
	
public List<MemberVo> getMemberList(String field, String query, int page, int rowNum, String order, String desc){
	List<MemberVo> list = new ArrayList<>();

	String sql ="SELECT * FROM member WHERE "+field+" LIKE ? ORDER BY "+order+" "+ desc+" limit ?, ?";

	String dbURL = "jdbc:mysql://localhost:3306/feane";
	String dbID = "root";
	String dbPassword = "1111";
	try {
		Class.forName("com.mysql.jdbc.Driver");
	Connection conn;
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	PreparedStatement st = conn.prepareStatement(sql);
	st.setString(1, "%"+query+"%");
	st.setInt(2, (page-1)*rowNum);
	st.setInt(3, rowNum);
	ResultSet rs=st.executeQuery();
	
	//0,10,20,->(page-1)*10
	
	
	while(rs.next()){
		int no= rs.getInt("No") ;
		String id= rs.getString("ID") ;
		String password= rs.getString("Password") ;
		String userName = rs.getString("UserName") ;
		int grade = rs.getInt("Grade") ;
		Date joinDate = rs.getDate("JoinDate");
		
		MemberVo member = new MemberVo(no, id, userName, grade, joinDate);
		list.add(member);
		System.out.println("member:"+list);
	}
		rs.close();
        st.close();
        conn.close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return list;
	}

public int getDelMember(String field){
		List<MemberVo> list = new ArrayList<>();
		int rs=0;
		String sql ="DELETE FROM member WHERE Id="+field;

		String dbURL = "jdbc:mysql://localhost:3306/feane";
		String dbID = "root";
		String dbPassword = "1111";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		Connection conn;
		conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		Statement st = conn.createStatement();
		rs=st.executeUpdate(sql);

	        st.close();
	        conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		}

public int getMemberCount(){
	
	return getMemberCount("userName", "");
}

public int getMemberCount(String field, String query){
	
	int count = 0;
	
	String sql = "SELECT COUNT(Id) COUNT FROM (SELECT * FROM member WHERE "+field+" LIKE ? ORDER BY JoinDate DESC)N";
	List<MemberVo> list= new ArrayList<MemberVo>();

	String dbURL = "jdbc:mysql://localhost:3306/feane";
	String dbID = "root";
	String dbPassword = "1111";
	try {
		Class.forName("com.mysql.jdbc.Driver");
	Connection conn;
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	PreparedStatement st = conn.prepareStatement(sql);
	st.setString(1, "%"+query+"%");
	ResultSet rs=st.executeQuery();
while(rs.next()) {
	count = rs.getInt("count");
}
		rs.close();
        st.close();
        conn.close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return count;
}

public MemberVo getNotice(String id) {
	MemberVo member = null;
	
	String sql = "SELECT * FROM member WHERE Id=?";
	
	String dbURL = "jdbc:mysql://localhost:3306/feane";
	String dbID = "root";
	String dbPassword = "1111";
	try {
		Class.forName("com.mysql.jdbc.Driver");
	Connection conn;
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	PreparedStatement st = conn.prepareStatement(sql);
	st.setString(1, id);
	
	ResultSet rs=st.executeQuery();

	if(rs.next()){
		int no= rs.getInt("No") ;
		String id_= rs.getString("ID") ;
		String password= rs.getString("Password") ;
		String userName = rs.getString("UserName") ;
		int grade = rs.getInt("Grade") ;
		Date joinDate = rs.getDate("JoinDate");
		
		member = new MemberVo(no, id_, userName, grade, joinDate);
	} 
		rs.close();
        st.close();
        conn.close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return member;
}

public MemberVo getNextNotice(String id) {
	MemberVo member = null;
	
	String sql = "SELECT * FROM member " +
			"WHERE ID = ( " +
			"	SELECT ID FROM member " +
			"	WHERE JoinDate > (SELECT JoinDate FROM member WHERE ID=? limit 1)) ";
	
	String dbURL = "jdbc:mysql://localhost:3306/feane";
	String dbID = "root";
	String dbPassword = "1111";
	try {
		Class.forName("com.mysql.jdbc.Driver");
	Connection conn;
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	PreparedStatement st = conn.prepareStatement(sql);
	st.setString(1, id);
	
	ResultSet rs=st.executeQuery();

	if(rs.next()){
		int no= rs.getInt("No") ;
		String id_= rs.getString("ID") ;
		String password= rs.getString("Password") ;
		String userName = rs.getString("UserName") ;
		int grade = rs.getInt("Grade") ;
		Date joinDate = rs.getDate("JoinDate");
		
		member = new MemberVo(no, id_, userName, grade, joinDate);
	
	} 

		rs.close();
        st.close();
        conn.close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return member;
}

public MemberVo getPrevNotice(String id) {
	MemberVo member = null;
	
	String sql = "SELECT ID FROM (SELECT * FROM member ORDER BY JoinDate DESC) " +
			"WHERE ID = ( " +
			"	SELECT ID FROM member " +
			"	WHERE JoinDate < (SELECT JoinDate FROM member WHERE ID=? limit 1))";
	
	String dbURL = "jdbc:mysql://localhost:3306/feane";
	String dbID = "root";
	String dbPassword = "1111";
	try {
		Class.forName("com.mysql.jdbc.Driver");
	Connection conn;
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	PreparedStatement st = conn.prepareStatement(sql);
	st.setString(1, id);
	
	ResultSet rs=st.executeQuery();

	if(rs.next()){
		int no= rs.getInt("No") ;
		String id_= rs.getString("ID") ;
		String password= rs.getString("Password") ;
		String userName = rs.getString("UserName") ;
		int grade = rs.getInt("Grade") ;
		Date joinDate = rs.getDate("JoinDate");
		
		member = new MemberVo(no, id_, userName, grade, joinDate);
	
	} 

		rs.close();
        st.close();
        conn.close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return member;
}

	
}

