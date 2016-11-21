package com.example.sqlitedemo3;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DBOpenHelper helper=new DBOpenHelper(MainActivity.this, "stu.db");
//		helper.getReadableDatabase();//��ȡһ��ֻ�������ݿ⣬ֻ�ܲ�ѯ������д�룬���ܸ���
		SQLiteDatabase db=helper.getWritableDatabase();//��ȡһ���ɶ���д�����ݿ�
//		db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
		Cursor c=db.rawQuery("select * from stutb", null);
		if(c!=null){
			String []cols=c.getColumnNames();
			while(c.moveToNext()){
				for (String ColumnName : cols) {
					Log.i("info", ColumnName+":"+c.getString(c.getColumnIndex(ColumnName)));
				}
				
			}
			c.close();
		}
		db.close();
	}

}
