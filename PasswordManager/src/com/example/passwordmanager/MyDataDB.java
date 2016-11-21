package com.example.passwordmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MyDataDB {
	private SQLiteDatabase pwdb;
	private MDHelper helper;
	private String DB_Name="pwdb.db";
	private ContentValues cv;
	private MyData mData=new MyData();
	private List<Map<String,String>> arr_list;
	
	//�������ݿ⣬����������
	public void initDB(Context context){
		helper = new MDHelper(context, DB_Name, null, 1);
		pwdb=helper.getWritableDatabase();
	}
	//��������
	public int insertDB(Context context,MyData data){
		cv = new ContentValues();
		cv.put("title", data.title);
		cv.put("user", data.user);
		cv.put("password",data.password);
		cv.put("note", data.note);
		long res=pwdb.insert("pwtb", null, cv);
		cv.clear();
		pwdb.close();
		if (res == -1) {  
			//���ʧ��  
			return 0;
		} else {  
			//��ӳɹ� 
			return 1;
		}
	}
	
	//��ѯ����
	public List<Map<String,String>> queryDB(String st){
		arr_list= new ArrayList<Map<String,String>>();
		//���Ϊ�����ѯȫ������
		if(("".equals(st))||st==null){
			Cursor c;
			c=pwdb.query("pwtb", null, "_id>?", new String[]{"0"}, null, null, null);
			if(c!=null&&c.getCount()!=0){
				while(c.moveToNext()){
					Map<String,String> HM = new HashMap<String,String>();
					String title=c.getString(c.getColumnIndex("title"));
					String user=c.getString(c.getColumnIndex("user"));
					String password=c.getString(c.getColumnIndex("password"));
					String note=c.getString(c.getColumnIndex("note"));
					HM.put("title", title);
					HM.put("user", user);
					HM.put("password", password);
					HM.put("note", note);
					arr_list.add(HM);
				}
				c.close();
			}else if(c.getCount()==0){
				return arr_list=null;
			}
			pwdb.close();
		}//��Ϊ�����ѯ�ض�����
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
					HM.put("password", password);
					HM.put("note", note);
					arr_list.add(HM);
				}
				c.close();
			}
			else if(c.getCount()==0){
				return arr_list=null;
			}
			pwdb.close();
		}
		
		return arr_list;
	}

	//�޸�����
	public int updateDB(MyData udata,String st){
		cv=new ContentValues();
		cv.put("title", udata.title);
		cv.put("user", udata.user);
		cv.put("password", udata.password);
		cv.put("note", udata.note);
		//��������
		int res=pwdb.update("pwtb", cv, "title=?", new String[]{st});
		cv.clear();
		pwdb.close();
		if (res == -1) {  
			//����ʧ��  
			return 0;
		} else {  
			//���³ɹ� 
			return 1;
		}
			
	}
	
	//ɾ������
	public int deleteDB(String st){
		
		int res=pwdb.delete("pwtb", "title=?", new String[]{st});
		pwdb.close();
		if(res==0){
			//ɾ��ʧ��
			return 0;
		}else{
			//ɾ���ɹ�
			return 1;
		}
	}

}
