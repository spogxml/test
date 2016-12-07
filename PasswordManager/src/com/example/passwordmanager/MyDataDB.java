package com.example.passwordmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.example.passwordmanager.util.EncryptUitl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyDataDB {
	private SQLiteDatabase pwdb;
	private MDHelper helper;
	private static final  String DB_NAME="pwdb.db";
	private ContentValues cv;
//	private List<Map<String,String>> arr_list;
	private String defkey="123456";
	
	//创建数据库，如果存在则打开
	private static SQLiteDatabase getSQLiteDatabase(Context context){
		MDHelper helper = new MDHelper(context, DB_NAME, null, 1);
		return helper.getWritableDatabase();
	}
	//插入数据
	public int insertDB(Context context,MyData data,String key){
		long res=1;
		long res1=1;
		long res2=1;
		long res3=1;
		if(key!=null||!("".equals(key))){
			defkey=key;
		}else{
			defkey="123456";
		}
		cv = new ContentValues();
		if(data.title!=null){
			cv.put("title", data.title);
			cv.put("user", data.user);
			//将密码进行编码后存储，默认使用defkey,如果设置过加密密码，则修改defkey的值。
			String s=data.password;
			cv.put("password",EncryptUitl.encode(s,defkey));
			Log.i("info", "插入数据时defkey为："+defkey);
			cv.put("note", data.note);
			res1=pwdb.insert("pwtb", null, cv);
			cv.clear();
		}//设置登陆密码
		else if(data.lgpassword!=null){
			//将登陆密码进行编码后存储
			String s1=data.lgpassword;
			cv.put("lgpassword", EncryptUitl.encode(s1,"123456"));
			res2=pwdb.insert("ustb", "lgpassword", cv);
			cv.clear();
		}//设置加密密码
		else if(data.itpassword!=null){
			//将加密密码进行编码后存储
			String s2=data.itpassword;
			cv.put("itpassword", EncryptUitl.encode(s2,"123456"));
			res3=pwdb.insert("ustb", "itpassword", cv);
			cv.clear();
		}else{
			res=-1;
		}
		pwdb.close();
		if (res1 == -1||res2==-1||res3==-1||res==-1) {  
			//添加失败  
			return 0;
		} else {  
			//添加成功 
			return 1;
		}
	}

	//查询数据
	public List<Map<String,Object>> queryDB(SQLiteDatabase pwdb ,String st,String key){
		
		List<Map<String,Object>> arr_list = new ArrayList<Map<String,Object>>();
		if(key!=null||!("".equals(key))){
			defkey=key;
		}else{
			defkey="123456";
		}
		//如果为空则查询全部数据
		if(("".equals(st))||st==null){
			Cursor c;
//			c=pwdb.query("pwtb", null, "_id>?", new String[]{"0"}, null, null, null);
			
			c=pwdb.rawQuery(st,null);
			if(c!=null&&c.getCount()!=0){
				while(c.moveToNext()){
					Map<String,Object> HM = new HashMap<String,Object>();
					for(int i=0;i<c.getColumnCount();i++){
						Object o=null ;
						switch(c.getType(i)){
						case Cursor.FIELD_TYPE_INTEGER:
							o=c.getInt(i);
							break;
						case Cursor.FIELD_TYPE_BLOB:
							o=c.getBlob(i);
							break;
						case Cursor.FIELD_TYPE_FLOAT:
							o=c.getFloat(i);
							break;
						case Cursor.FIELD_TYPE_STRING:
							o=c.getString(i);
							break;
						case Cursor.FIELD_TYPE_NULL:
							o=null;
							break;
						
						}
					
						HM.put(c.getColumnName(i), o);
					}
					
//					String title=c.getString(c.getColumnIndex(c.getColumnName(columnIndex)));
//					String user=c.getString(c.getColumnIndex("user"));
//					String password=c.getString(c.getColumnIndex("password"));
//					String note=c.getString(c.getColumnIndex("note"));
//					HM.put("title", title);
//					HM.put("user", user);
//					HM.put("password", EncryptUitl.decode(password,defkey));
//					Log.i("info", "查询全部数据时defkey为："+defkey);
//					HM.put("note", note);
					arr_list.add(HM);
				}
				c.close();
			}else if(c.getCount()==0){
				arr_list=null;
				c.close();
			}
			pwdb.close();
		}//不为空则查询特定数据
		else {
			Cursor c;
			c=pwdb.query("pwtb", null, "title Like ?", new String[]{"%"+st+"%"}, null, null, null);
			if(c!=null&&c.getCount()!=0){
				while(c.moveToNext()){
					HashMap<String,String> HM = new HashMap<String,String>();
					String title=c.getString(c.getColumnIndex("title"));
					String user=c.getString(c.getColumnIndex("user"));
					String password=c.getString(c.getColumnIndex("password"));
					String note=c.getString(c.getColumnIndex("note"));
					HM.put("title", title);
					HM.put("user", user);
					HM.put("password", EncryptUitl.decode(password,defkey));
					Log.i("info", "查询特定数据时defkey为："+defkey);
					HM.put("note", note);
					arr_list.add(HM);
				}
				c.close();
			}
			else if(c.getCount()==0){
				arr_list=null;
				c.close();
			}
			pwdb.close();
		}

		pwdb.close();
		return arr_list;
	}

	//修改数据
	public int updateDB(MyData udata,String st,String key){
		long res1;
		long res2;
		long res3;
		if(key!=null||!("".equals(key))){
			defkey=key;
		}else{
			defkey="123456";
		}
		cv=new ContentValues();
		if(udata.title!=null){
			cv.put("title", udata.title);
			cv.put("user", udata.user);
			//将修改后的密码编码后更新
			String s=udata.password;
			cv.put("password", EncryptUitl.encode(s,defkey));
			cv.put("note", udata.note);
			//更新数据
			res1=pwdb.update("pwtb", cv, "title=?", new String[]{st});
			cv.clear();
		}else
			res1=1;
		//修改登陆密码
		if(udata.lgpassword!=null){
			//将登陆密码进行编码后更新
			String s1=udata.lgpassword;
			cv.put("lgpassword", EncryptUitl.encode(s1,"123456"));
			//更新密码
			res2=pwdb.update("ustb", cv, "lgpassword=?", new String[]{EncryptUitl.encode(st,"123456")});
			cv.clear();
		}else 
			res2=1;
		//修改加密密码
		if(udata.itpassword!=null){
			//将加密密码进行编码后更新
			String s2=udata.itpassword;
			cv.put("itpassword", EncryptUitl.encode(s2,"123456"));
			//更新密码
			res3=pwdb.update("ustb", cv, "itpassword=?", new String[]{EncryptUitl.encode(st,"123456")});
			cv.clear();
		}else
			res3=1;
		pwdb.close();
		if (res1 == -1||res2==-1||res3==-1) {  
			//更新失败  
			return 0;
		} else {  
			//更新成功 
			return 1;
		}

	}

	//删除数据
	public int deleteDB(String st){
		long res=0;
		if(st.equals("deleteall")){
			res=pwdb.delete("pwtb", null, null);
		}else{
			res=pwdb.delete("pwtb", "title=?", new String[]{st});
		}
		if(res==0){
			//删除失败
			return 0;
		}else{
			//删除成功
			return 1;
		}
	}
	//删除密码
	public void deletePW(){
		pwdb.delete("ustb", null, null);
		pwdb.close();
	}
	//查询登陆密码
	public int queryLgPW(String st){
		int res=0;
		//如果为空
		if("".equals(st)||st==null){
			//查询所有数据
			Cursor c;
			c=pwdb.query("ustb",null, "lgpassword>?", new String[]{"0"}, null, null, null);
			if(c!=null&&c.getCount()!=0){
				//存在数据则返回1
				res=1;
				c.close();
			}else{
				//不存在数据则返回0
				res=0;
				c.close();
			}
		}//如果不为空
		else{
			//将密码进行编码后匹配数据库查询
			//查询
			Cursor c;
			c=pwdb.query("ustb", null, "lgpassword Like ?", new String[]{EncryptUitl.encode(st,"123456")}, null, null, null);
			if(c!=null&&c.getCount()!=0){
				//查询成功
				c.close();
				res=1;
			}
			else{
				//查询失败
				c.close();
				res=0;
			}
		}
		
		return res;
	}
	//查询加密密码
	public int queryItPW(String st){
		int res=0;
		//如果为空
		if("".equals(st)||st==null){
			//查询所有数据
			Cursor c;
			c=pwdb.query("ustb", null, "itpassword>?", new String[]{"0"}, null, null, null);
			if(c!=null&&c.getCount()!=0){
				//存在数据则返回1
				c.close();
				res=1;
			}else{
				//不存在数据则返回0
				c.close();
				res=0;
			}
		}//如果不为空
		else{
			//将密码进行编码后匹配数据库查询
			//查询
			Cursor c;
			c=pwdb.query("ustb", null, "itpassword Like ?", new String[]{EncryptUitl.encode(st,"123456")}, null, null, null);
			if(c!=null&&c.getCount()!=0){
				//查询成功
				c.close();
				res=1;
			}
			else{
				//查询失败
				c.close();
				res=0;
			}
		}
		return res;
	}

}
