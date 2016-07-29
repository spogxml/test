package com.imooc.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 将要完成：
 * 1。通过Collections.sort()方法，对Integer泛型的List进行排序;
 * 2.对String泛型的List进行排序;
 * 3.对其他类型泛型的List进行排序，以Student为例。
 */
public class CollectionsTest {
	/**
	 * 1。通过Collections.sort()方法，对Integer泛型的List进行排序;
	 * 创建一个Integer泛型的List，插入十个100以内的不重复随机整数，
	 * 调用Collections.sort()方法对其进行排序
	 */
	public void testSort1(){
		List<Integer> integerList=new ArrayList<Integer>();
		//插入十个100以内的不重复随机整数
		Random random =new Random();
		Integer k;
		for(int i=0;i<10;i++){
			do{
				k=random.nextInt(100);

			}while(integerList.contains(k));
			integerList.add(k);
			System.out.println("成功添加整数："+k);
		}
		System.out.println("------------排序前-------------");
		for (Integer integer : integerList) {
			System.out.println("元素："+integer);
		}
		Collections.sort(integerList);
		System.out.println("------------排序后-------------");
		for (Integer integer : integerList) {
			System.out.println("元素："+integer);
		}
	}
	/**
	 * 2.对String泛型的List进行排序;
	 * 创建String泛型的List，添加三个乱序的String元素，
	 * 调用sort方法，再次输出排序后的顺序
	 */
	public void testSort2(){
		List<String> stringList=new ArrayList<String>();
		stringList.add("microsoft");
		stringList.add("google");
		stringList.add("lenovo");
		System.out.println("------------排序前-------------");
		for (String string : stringList) {
			System.out.println("元素："+string);
		}
		Collections.sort(stringList);
		System.out.println("------------排序后-------------");
		for (String string : stringList) {
			System.out.println("元素："+string);
		}
	}
	/**
	 * 作业测试
	 */
	public void testSort3(){
		List<String> stringList=new ArrayList<String>();
		//用来截取的总字符串
		String st="abcdefghijklmnopqrstuvwxyz0123456789";

		//随机截取的长度
		int lg=0;
		//定义随机数
		Random random =new Random();
		for(int a=0;a<10;a++){
			//用来存放被截取出来的字符串
			StringBuilder ms=new StringBuilder();
			do{
				//每个stringList的随机长度
				lg=random.nextInt(9)+1;
				//随机获取字符
				for(int b=0;b<lg;b++){
					ms.append(st.charAt(random.nextInt(36)));
				}
			}while(stringList.contains(ms));//如果有重复的，重新随机字符串，否则执行添加
			//把字符串添加进stringList中
			stringList.add(ms.toString());
			System.out.println("将要添加字符串："+ms);
		}
		System.out.println("------------排序前-------------");
		for (String string : stringList) {
			System.out.println("元素："+string);
		}
		Collections.sort(stringList);
		System.out.println("------------排序后-------------");
		for (String string : stringList) {
			System.out.println("元素："+string);
		}
	}
	/**
	 * 3.对其他类型泛型的List进行排序，以Student为例。
	 */
	public void testsort4(){
		List<Student> studentList=new ArrayList<Student>();
		Random random=new Random();
		studentList.add(new Student(random.nextInt(10)+"","Mike"));
		studentList.add(new Student(random.nextInt(10)+"","Angela"));
		studentList.add(new Student(random.nextInt(10)+"","Lucy"));
		studentList.add(new Student(random.nextInt(10)+"","Beyonce"));
		System.out.println("---------------排序前-----------------");
		for (Student student : studentList) {
			System.out.println("学生："+student.id+"："+student.name);
		}
		Collections.sort(studentList);
		System.out.println("---------------排序后-----------------");
		for (Student student : studentList) {
			System.out.println("学生："+student.id+"："+student.name);
		}
		Collections.sort(studentList,new StudentComparator());
		System.out.println("---------------按照id和姓名排序后-----------------");
		for (Student student : studentList) {
			System.out.println("学生："+student.id+"："+student.name);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CollectionsTest ct=new CollectionsTest();
		//		ct.testSort1();
		//		ct.testSort2();
//		ct.testSort3();
		ct.testsort4();
	}

}
