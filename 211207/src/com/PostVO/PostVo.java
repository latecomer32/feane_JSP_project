package com.PostVO;

import java.sql.Date;

public class PostVo {
	private String Id;
	private String Title;
	private String Kinds;
	private String Content;
	private String Files;
	private int Hit;
	private int Price;
	private Date WriteDate;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getKinds() {
		return Kinds;
	}
	public void setKinds(String kinds) {
		Kinds = kinds;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getFiles() {
		return Files;
	}
	public void setFiles(String files) {
		Files = files;
	}
	public int getHit() {
		return Hit;
	}
	public void setHit(int hit) {
		Hit = hit;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public Date getWriteDate() {
		return WriteDate;
	}
	public void setWriteDate(Date writeDate) {
		WriteDate = writeDate;
	}
	public PostVo() {
		// TODO Auto-generated constructor stub
	}
	public PostVo(String id, String title, String kinds, String content, String files, int hit, int price,
			Date writeDate) {
		super();
		Id = id;
		Title = title;
		Kinds = kinds;
		Content = content;
		Files = files;
		Hit = hit;
		Price = price;
		WriteDate = writeDate;
	}
	
	public PostVo(String id, String title, String kinds, String content, String files, int price,
			Date writeDate) {
		super();
		Id = id;
		Title = title;
		Kinds = kinds;
		Content = content;
		Files = files;
	
		Price = price;
		WriteDate = writeDate;
	}
	@Override
	public String toString() {
		return "PostVo [Id=" + Id + ", Title=" + Title + ", Kinds=" + Kinds + ", Content=" + Content + ", Files="
				+ Files + ", Hit=" + Hit + ", Price=" + Price + ", WriteDate=" + WriteDate + "]";
	}
	
	
}
