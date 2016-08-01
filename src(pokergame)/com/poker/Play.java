package com.poker;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 游戏开始
 * @author Administrator
 *
 */
public class Play {

	public static void main(String[] args) {
		Poker poker=new Poker();//创建扑克对象
		Player[] pl=new Player[2];
		//创建扑克牌
		poker.createPoker();
		//洗牌
		System.out.println("----------------开始洗牌------------------");
		poker.shufflePoker();
		System.out.println("----------------洗牌结束------------------");
		for (Card card : poker.cardList) {
			System.out.print(card.getHuase()+card.getData()+",");
		}
		System.out.println("----------------创建玩家------------------");


		while(true){

			try{
				Scanner s=new Scanner(System.in);
				for(int j=0;j<2;j++){
					System.out.println("请输入第"+(j+1)+"位玩家的ID和姓名：");
					System.out.println("输入ID：");
					pl[j]=new Player();
					int id=s.nextInt();
					pl[j].setId(id);
					System.out.println("输入姓名：");
					String name=s.next();
					pl[j].setName(name);

				}
				break;
			}catch(InputMismatchException e){
				System.out.println("输入格式错误，请根据提示重新输入！");
			}

		}
		System.out.println("-----欢迎玩家:"+pl[0].getName());
		System.out.println("-----欢迎玩家:"+pl[1].getName());
		//发牌
		System.out.println("----------------开始发牌------------------");
		
		pl[0].handList.add(poker.cardList.get(0));
		System.out.println("-----玩家："+pl[0].getName()+"-拿牌");
		
		pl[1].handList.add(poker.cardList.get(1));
		System.out.println("-----玩家："+pl[1].getName()+"-拿牌");
		
		pl[0].handList.add(poker.cardList.get(2));
		System.out.println("-----玩家："+pl[0].getName()+"-拿牌");
		
		pl[1].handList.add(poker.cardList.get(3));
		System.out.println("-----玩家："+pl[1].getName()+"-拿牌");
		
		System.out.println("----------------发牌结束------------------");
		System.out.println("----------------开始游戏------------------");
		Collections.sort(pl[0].handList);
		Collections.sort(pl[1].handList);
		//玩家1最大的手牌
		System.out.println("玩家："+pl[0].getName()+"最大的手牌为："+pl[0].handList.get(1).getHuase()+pl[0].handList.get(1).getData());
		//玩家2最大的手牌
		System.out.println("玩家："+pl[1].getName()+"最大的手牌为："+pl[1].handList.get(1).getHuase()+pl[1].handList.get(1).getData());

		if(pl[0].handList.get(1).compareTo(pl[1].handList.get(1))==1){
			System.out.println("----------------玩家："+pl[0].getName()+"获胜------------------");
		}else
			System.out.println("----------------玩家："+pl[1].getName()+"获胜------------------");

		System.out.println("玩家各自的手牌为：");
		System.out.println(pl[0].getName()+"："+pl[0].handList.get(0).getHuase()+pl[0].handList.get(0).getData()+","
				+pl[0].handList.get(1).getHuase()+pl[0].handList.get(1).getData());
		System.out.println(pl[1].getName()+"："+pl[1].handList.get(0).getHuase()+pl[1].handList.get(0).getData()+","
				+pl[1].handList.get(1).getHuase()+pl[1].handList.get(1).getData());
	}

}
