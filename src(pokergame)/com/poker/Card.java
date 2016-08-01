package com.poker;

/**
 * 单张扑克牌
 * @author Administrator
 *
 */
public class Card implements Comparable<Card>{

	//花色
	private String huase;

	//点数
	private String data;

	//用来比较卡牌大小的2条属性
	//根据点数
	private int num;
	//根据花色
	private int col;
	
	public String getHuase() {
		return huase;
	}
	public void setHuase(String huase) {
		this.huase = huase;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	//构造函数
	public Card(String huase,String data,int num,int col){
		this.huase=huase;
		this.data=data;
		this.num=num;
		this.col=col;
	}

	public Card(){

	}
	@Override
	public int compareTo(Card o) {
		if(this.num>o.num)
			return 1;
		else if(this.num==o.num&&this.col>o.col)
			return 1;
		else 
			return -1;
	}
	


}
