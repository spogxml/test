package com.lendbook.demo;

public class LendBook {
	private String bookname;//定义书名属性
	private int bookid;//定义书序号属性
	
	
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	
	public LendBook(String name, int id) {
		this.setBookname(name);
		this.setBookid(id);
	}

}
