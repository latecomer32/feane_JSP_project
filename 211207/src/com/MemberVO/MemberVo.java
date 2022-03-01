package com.MemberVO;

import java.sql.Date;

public class MemberVo {
	private int No;
	private String Id;
	private String Password;
	private String UserName;
	private int Grade;
	private Date JoinDate;
	
	public MemberVo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MemberVo [No=" + No + ", Id=" + Id + ", Password=" + Password + ", UserName=" + UserName + ", Grade="
				+ Grade + ", JoinDate=" + JoinDate + "]";
	}

	public int getNo() {
		return No;
	}

	public void setNo(int no) {
		No = no;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getGrade() {
		return Grade;
	}

	public void setGrade(int grade) {
		Grade = grade;
	}

	public Date getJoinDate() {
		return JoinDate;
	}

	public void setJoinDate(Date joinDate) {
		JoinDate = joinDate;
	}

	public MemberVo(int no, String id, String password, String userName, int grade, Date joinDate) {
	
		No = no;
		Id = id;
		Password = password;
		UserName = userName;
		Grade = grade;
		JoinDate = joinDate;
	}
	
	public MemberVo(int no, String id, String userName, int grade, Date joinDate) {
		
		No = no;
		Id = id;
		
		UserName = userName;
		Grade = grade;
		JoinDate = joinDate;
	}
}

