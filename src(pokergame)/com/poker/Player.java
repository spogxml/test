package com.poker;

import java.util.ArrayList;
import java.util.List;
/**
 * 玩家
 * @author Administrator
 *
 */
public class Player{
	private int id;//玩家ID
	private String name;//玩家姓名
	public List<Card> handList=new ArrayList<Card>();;//玩家手牌
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public Player(int id,String name){
		this.id=id;
		this.name=name;
	}
	public Player(){
	}

}
