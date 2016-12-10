package com.example.passwordmanager.dboperate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.passwordmanager.util.EncryptUitl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MydataOperate {

	//插入
	public void insertAll(Context context,MyData mdata,String key){
		SQLiteDatabase pwdb=MyDataDB.getSQLiteDatabase(context);//打开数据库
		String st=null;
		String[] values;
		//判断key的值
		String defkey=null;
		if(key!=null||!("".equals(key))){
			defkey=key;
		}else{
			defkey="123456";
		}
		//如果数据不为空则插入数据
		if(mdata.title!=null){
			st="insert into pwtb (title,user,password,note) values (?,?,?,?)";
			values=new String[]{mdata.title,mdata.user,mdata.password,mdata.note};
		}//否则插入密码
		else {
			//如果登陆密码不为空，则设置登陆密码
			if(mdata.lgpassword!=null){
				st="insert into ustb lgpassword value (?)";
				values=new String[]{EncryptUitl.encode(mdata.lgpassword,defkey)};
			}//否则设置加密密码
			else{
				st="insert into ustb itpassword value (?)";
				values=new String[]{EncryptUitl.encode(mdata.itpassword,defkey)};
			}
		}

		MyDataDB.insertDB(pwdb,st,values);//插入数据
	}

	//查询数据
	public  List<Map<String,Object>> queryData(Context context ,String title,String key){
		SQLiteDatabase pwdb=MyDataDB.getSQLiteDatabase(context);//打开数据库
		List<Map<String,Object>> arr_list = new ArrayList<Map<String,Object>>();//接收查询结果集合
		List<Map<String,Object>> out_list = new ArrayList<Map<String,Object>>();//输出查询结果集合
		//判断key的值
		String defkey=null;
		if(key!=null||!("".equals(key))){
			defkey=key;
		}else{
			defkey="123456";
		}
		//查询结果
		//如果不为空则查询特定数据
		if(title!=null){
			String st="select * from pwtb where title=?";
			String[] vaules=new String[]{title};
			arr_list=MyDataDB.queryDB(pwdb,st,vaules);
			//如果查询结果为空
			if(arr_list==null){
				out_list=null;
			}//如果查询结果不为空
			else{
				int size=arr_list.size();
				Map<String, Object> map =new HashMap<String, Object>();
				//对查询结果当中的密码进行解码后再放入到集合当中返回
				for(int i=0;i<size;i++){
					map=arr_list.get(i);
					map.put("password",EncryptUitl.decode(map.get("password").toString(), defkey));
					out_list.add(map);
				}
			}
		}//如果为空则查询全部数据
		else{
			String st="select * from pwtb";
			String[] vaules=null;
			arr_list=MyDataDB.queryDB(pwdb,st,vaules);
			//如果查询结果为空
			if(arr_list==null){
				out_list=null;
			}//如果查询结果不为空
			else{
				int size=arr_list.size();
				Map<String, Object> map =new HashMap<String, Object>();
				//对查询结果当中的密码进行解码后再放入到集合当中返回
				for(int i=0;i<size;i++){
					map=arr_list.get(i);
					map.put("password",EncryptUitl.decode(map.get("password").toString(), defkey));
					out_list.add(map);
				}
			}
		}
		return out_list;
	}
	//查询登陆密码
	public  List<Map<String,Object>> queryLg(Context context ,String lgpassword,String key){
		SQLiteDatabase pwdb=MyDataDB.getSQLiteDatabase(context);//打开数据库
		List<Map<String,Object>> arr_list = new ArrayList<Map<String,Object>>();//接收查询结果集合
		List<Map<String,Object>> out_list = new ArrayList<Map<String,Object>>();//输出查询结果集合
		//判断key的值
		String defkey=null;
		if(key!=null||!("".equals(key))){
			defkey=key;
		}else{
			defkey="123456";
		}
		//查询结果
		String st="select * from ustb where lgpassword=?";
		String[] vaules=new String[]{EncryptUitl.encode(lgpassword,defkey)};
		arr_list=MyDataDB.queryDB(pwdb,st,vaules);
		//如果查询结果为空
		if(arr_list==null){
			out_list=null;
		}//如果查询结果不为空
		else{
			int size=arr_list.size();
			Map<String, Object> map =new HashMap<String, Object>();
			//对查询结果当中的密码进行解码后再放入到集合当中返回
			for(int i=0;i<size;i++){
				map=arr_list.get(i);
				map.put("lgpassword",EncryptUitl.decode(map.get("lgpassword").toString(), defkey));
				out_list.add(map);
			}
		}
		return out_list;
	}
	//查询加密密码
	public  List<Map<String,Object>> queryIt(Context context ,String itpassword,String key){
		SQLiteDatabase pwdb=MyDataDB.getSQLiteDatabase(context);//打开数据库
		List<Map<String,Object>> arr_list = new ArrayList<Map<String,Object>>();//接收查询结果集合
		List<Map<String,Object>> out_list = new ArrayList<Map<String,Object>>();//输出查询结果集合
		//判断key的值
		String defkey=null;
		if(key!=null||!("".equals(key))){
			defkey=key;
		}else{
			defkey="123456";
		}
		//查询结果
		String st="select * from ustb where itpassword=?";
		String[] vaules=new String[]{EncryptUitl.encode(itpassword,defkey)};
		arr_list=MyDataDB.queryDB(pwdb,st,vaules);
		//如果查询结果为空
		if(arr_list==null){
			out_list=null;
		}//如果查询结果不为空
		else{
			int size=arr_list.size();
			Map<String, Object> map =new HashMap<String, Object>();
			//对查询结果当中的密码进行解码后再放入到集合当中返回
			for(int i=0;i<size;i++){
				map=arr_list.get(i);
				map.put("itpassword",EncryptUitl.decode(map.get("itpassword").toString(), defkey));
				out_list.add(map);
			}
		}
		return out_list;
	}
	//更新
	public void updateOpe(Context context,MyData mdata,String title,String key){
		SQLiteDatabase pwdb=MyDataDB.getSQLiteDatabase(context);//打开数据库
		String st=null;
		String[] values=null;
		//判断key的值
		String defkey=null;
		if(key!=null||!("".equals(key))){
			defkey=key;
		}else{
			defkey="123456";
		}
		//如果数据不为空则更新数据
		if(mdata.title!=null){
			st="update pwtb set title=?,user=?,password=?,note=? where title=?";
			values=new String[]{mdata.title,mdata.user,EncryptUitl.encode(mdata.password,defkey),mdata.note,title};
		}//否则更新密码
		else {
			//如果登陆密码不为空，则更新登陆密码
			if(mdata.lgpassword!=null){
				st="update ustb set lgpassword=? where lgpassword=?";
				values=new String[]{EncryptUitl.encode(mdata.lgpassword,defkey)};
			}//否则更新加密密码
			else if(mdata.itpassword!=null){
				st="update ustb set itpassword=? where itpassword=?";
				values=new String[]{EncryptUitl.encode(mdata.itpassword,defkey)};
			}
		}
		MyDataDB.updateDB(pwdb, st, values);
	}

	//删除数据
	public void deleteOpe(Context context ,String title,String key){
		SQLiteDatabase pwdb=MyDataDB.getSQLiteDatabase(context);//打开数据库
		String st=null;
		String[] values=null;
		//如果title不为空，则删除特定数据
		if(title!=null){
			st="delete from pwtb where title=?";
			values=new String[]{title};
			MyDataDB.deleteDB(pwdb, st, values);
		}//如果为空则删除全部数据
		else{
			st="delete * from pwtb";
		}
		MyDataDB.deleteDB(pwdb, st, values);
	}
	//删除登陆密码
	//删除加密密码
}
