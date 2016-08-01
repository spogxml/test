package com.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 一整副扑克牌的List，包含了创建扑克牌和洗牌方法
 * @author Administrator
 *
 */
public class Poker{
	public List<Card> cardList=new ArrayList<Card>();//创建一副空扑克牌List
	
	//添加扑克牌的值
	public void createPoker(){
		System.out.println("----------------创建扑克牌------------------");
		String[] st1=new String[]{"黑桃","红心","梅花","方块"};
		String[] st2=new String[]{"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		for(int i=0;i<4;i++){
			for(int j=0;j<13;j++){
				this.cardList.add(new Card(st1[i],st2[j],j,i));
			}
		}
		System.out.println("----------------扑克牌创建成功------------------");
		System.out.print("为：[");
		for (Card card : cardList) {
			System.out.print(card.getHuase()+card.getData()+",");
		}
		System.out.println("]");
	}
	
	//洗牌
	public void shufflePoker(){
		Collections.shuffle(cardList);
	}

}
