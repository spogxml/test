package com.lendbook.demo;

import java.util.*;
public class Lend {
	private static LendBook[] lb={new LendBook("西游记",1),new LendBook("红楼梦",2),
		new LendBook("水浒传",3),new LendBook("三国演义",4)};//输入图书数据
	public static void main(String[] args) {
		while(true){
			try{
				System.out.println("输入命令选择：1，按照书名查找图书；2，按照序号查找图书");
				Scanner s=new Scanner(System.in);//定义输入命令
				int a=s.nextInt();
				if(a==1){
					try{
						searchName();
					}catch(InputMismatchException e){
						System.out.println("命令输入错误，请根据提示重新输入命令！");
						continue;
					}catch(Exception e){
						System.out.println(e.getMessage());
						continue;
					}
				}
				else if(a==2){
					try{
						searchId();
					}catch(InputMismatchException e){
						System.out.println("命令输入错误，请根据提示重新输入命令！");
						continue;
					}catch(Exception e){
						System.out.println(e.getMessage());
						continue;
					}
				}
			
				break;
			}catch(InputMismatchException e){
				System.out.println("命令输入错误，请根据提示重新输入命令！");
				continue;
			}
		}
	}

	public static void searchName() throws Exception{//定义按照书名查找书的方法，并设置抛出书不存在异常
		Scanner s2=new Scanner(System.in);//定义输入数据
		System.out.println("输入图书名称：");
		String b=s2.nextLine();
		for(int i=0;i<lb.length;i++){
			if(lb[i].getBookname().equals(b)){
				System.out.println("book:"+lb[i].getBookname()+"\tid:"+lb[i].getBookid());
				System.exit(0);
			}
		}
			throw new Exception("书不存在");
		}

	public static void searchId() throws Exception{//定义按照书序号查找书的方法，并设置抛出书不存在异常
		Scanner s3=new Scanner(System.in);//定义输入数据
		System.out.println("输入图书序号：");
		int c=s3.nextInt();
		for(int j=0;j<lb.length;j++){
			if(lb[j].getBookid()==c){
				System.out.println("book:"+lb[j].getBookname()+"\tid:"+lb[j].getBookid());
				System.exit(0);
			}
		}
			throw new Exception("书不存在");
		}

}