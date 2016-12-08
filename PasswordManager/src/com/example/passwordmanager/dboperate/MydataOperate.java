package com.example.passwordmanager.dboperate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MydataOperate {
	private SQLiteDatabase pwdb;
	private String defkey="123456";
	
	public  List<Map<String,Object>> queryOpe(Context context ,String st,String key,String dbname){
		pwdb=MyDataDB.getSQLiteDatabase(context,dbname);
		List<Map<String,Object>> arr_list = new ArrayList<Map<String,Object>>();
		if(key!=null||!("".equals(key))){
			defkey=key;
		}else{
			defkey="123456";
		}
		//如果为空则查询全部数据
		if(("".equals(st))||st==null){
			arr_list=MyDataDB.queryDB(pwdb, st);
		}
		
		return arr_list;
	}

}
