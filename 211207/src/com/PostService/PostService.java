package com.PostService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.MemberVO.MemberVo;
import com.PostVO.PostVo;

public class PostService {

public void setPost(String Id, String Title , String Kinds, String Content , String Files, int Price){
	String sql ="INSERT INTO post(Id, Title, Kinds, Content, Files, Price, WriteDate) values(?,?,?,?,?,?,now())";
	
	String dbURL = "jdbc:mysql://localhost:3306/feane";
	String dbID = "root";
	String dbPassword = "1111";
	try {
		Class.forName("com.mysql.jdbc.Driver");
	Connection conn;
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	PreparedStatement st = conn.prepareStatement(sql);
	st.setString(1, Id);
	st.setString(2, Title);
	st.setString(3, Kinds);
	st.setString(4, Content);
	st.setString(5, Files);
	st.setInt(6, Price);
	st.executeUpdate();

        st.close();
        conn.close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

public List<PostVo> getPostList(){

	return getPostList("", "", 1);
}



public List<PostVo> getPostList(String field, String query, int page){
	List<PostVo> list = new ArrayList<>();

	String sql ="select m.UserName, m.Grade, m.JoinDate, p.* from member m LEFT JOIN post p ON m.Id=p.Id  where m.Id=p.Id AND"+field+" LIKE ?  ORDER BY WriteDate desc limit ?, ?";
			
	String dbURL = "jdbc:mysql://localhost:3306/feane";
	String dbID = "root";
	String dbPassword = "1111";
	try {
		Class.forName("com.mysql.jdbc.Driver");
	Connection conn;
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	PreparedStatement st = conn.prepareStatement(sql);
	st.setString(1, "%"+query+"%");
	st.setInt(2, (page-1)*12);
	st.setInt(3, 12);
	
	ResultSet rs=st.executeQuery();
	
	while(rs.next()){
		String id= rs.getString("Id") ;
		String title= rs.getString("Title") ;
		String kinds = rs.getString("Kinds") ;
		String content = rs.getString("Content") ;
		String files = rs.getString("Files") ;
		int price= rs.getInt("Price") ;
		Date writeDate = rs.getDate("WriteDate") ;
		
		//String userName = rs.getString("Hit") ;
		//MemberVo member = new MemberVo(userName, grade, joinDate);
		PostVo post = new PostVo(id, title, kinds, content, files, price, writeDate);
		list.add(post);
		System.out.println("post:"+list);
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

}

