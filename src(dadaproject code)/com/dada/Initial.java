package com.dada;

import java.util.Scanner;

public class Initial {
	public static void main(String[] args){
		PeopleCar[] pc = {new PeopleCar("奥迪A4",500,0,4),new PeopleCar("马自达6",400,0,4),
				new PeopleCar("金龙",500,0,20)};
		TruckCar[] tc={new TruckCar("松花江",400,4,0),new TruckCar("依维柯",1000,20,0)};
		PickUp[] pu={new PickUp("皮卡雪6",450,2,4)};//输入车辆数据
		int totalPeople=0;//定义总载人数
		int totalWeight=0;//定义总载重量
		int totalMoney=0;//定义总单价
		String[] style=new String[(pc.length+tc.length+pu.length)];//定义字符串数组用来存放选取的车的车型
		
		Scanner se = new Scanner(System.in);//键盘输入
		
		int a=0;
		System.out.println("欢迎使用答答租车系统"+"\n"+"请选择是否想租车：1、是	2.否");
		int i=se.nextInt();//输入选项
		if(i==1){
			System.out.println("您可租车的类型及其价目表");//是则进入下一步
			System.out.println("序号\t汽车名称\t租金\t容量");
			
			for(a=0;a<(pc.length+tc.length+pu.length);a++){
				if(a<pc.length){
					System.out.println((a+1)+".\t"+pc[a].getCarStyle()+"\t"+
			pc[a].getMoney()+"元/天\t"+"载人："+pc[a].getPeople()+"人");//显示载人车辆
				}
				else if(a<(tc.length+pc.length)&&a>=pc.length){
					System.out.println((a+1)+".\t"+tc[a-pc.length].getCarStyle()+"\t"+
			tc[a-pc.length].getMoney()+"元/天\t"+"载货："+tc[a-pc.length].getWeight()+"吨");//显示载货车辆
				}
				else{
					System.out.println((a+1)+".\t"+pu[a-(pc.length+tc.length)].getCarStyle()+"\t"+
					pu[a-(pc.length+tc.length)].getMoney()+"元/天\t"+"载人："+pu[a-(pc.length+tc.length)].getPeople()+"人"+
					"载重"+pu[a-(pc.length+tc.length)].getWeight()+"吨");//显示皮卡车辆
				}
				
			}
			System.out.println("请输入您要租车的数量：(数量上限为：1-"+(pc.length+tc.length+pu.length)+")");
			int j=se.nextInt();//选择租车数量
			if(j>(pc.length+tc.length+pu.length)&&j<1){
				System.out.println("输入有误，请重新输入！");
				j=se.nextInt();
			}else{
				for(int b=1;b<=j;b++){
					System.out.println("清输入第"+b+"辆车的序号");
					int k=se.nextInt();//选择车辆
						switch(k){
							case 1:
								style[0]=new String(pc[0].getCarStyle());
								totalPeople+=pc[0].getPeople();
								totalMoney+=pc[0].getMoney();
								break;
							case 2:
								style[1]=new String(pc[1].getCarStyle());
								totalPeople+=pc[1].getPeople();
								totalMoney+=pc[1].getMoney();
								break;
							case 3:
								style[2]=new String(pc[2].getCarStyle());
								totalPeople+=pc[2].getPeople();
								totalMoney+=pc[2].getMoney();
								break;
							case 4:
								style[3]=new String(tc[0].getCarStyle());
								totalWeight+=tc[0].getWeight();
								totalMoney+=tc[0].getMoney();
								break;
							case 5:
								style[4]=new String(tc[1].getCarStyle());
								totalWeight+=tc[1].getWeight();
								totalMoney+=tc[1].getMoney();
								break;
							case 6:
								style[5]=new String(pu[0].getCarStyle());
								totalPeople+=pu[0].getPeople();
								totalWeight+=pu[0].getWeight();
								totalMoney+=pu[0].getMoney();
								break;
						}
				}
				System.out.println("请输入租车的天数：");
				int h=se.nextInt();
				System.out.println("您的账单：");
				if(style[0]!=null||style[1]!=null||style[2]!=null||style[5]!=null){
					System.out.println("***可载人的车 有：");
					for(int c=0;c<3;c++){
						if(style[c]!=null)
						System.out.print("\t"+style[c]);
					}
					if(style[5]!=null)
						System.out.print("\t"+style[5]);
				}
				else System.out.println("没有选择租取载人车辆！");
				
				if(totalPeople!=0){
					
				System.out.println("\t共载人："+totalPeople+"人");
				
				}
				if(style[3]!=null||style[4]!=null||style[5]!=null){
					System.out.println("***可载货的车 有：");
					for(int d=3;d<5;d++){
						if(style[d]!=null)
						System.out.print("\t"+style[d]);
					}
					if(style[5]!=null)
						System.out.print("\t"+style[5]);
				}
				else System.out.println("没有选择租取载货车辆！");
				
				if(totalWeight!=0){
					
					System.out.println("\t共载货："+totalWeight+"吨");
					
				}
				System.out.println("***租车总价格："+totalMoney*h);
				
			}
		}else{ 
			System.out.println("谢谢使用！");
			System.exit(0);//否则退出程序
	
		}

	}
}