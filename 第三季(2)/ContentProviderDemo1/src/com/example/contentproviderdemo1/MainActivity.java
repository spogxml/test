package com.example.contentproviderdemo1;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ContentResolver cr=getContentResolver();
		Cursor c=cr.query(Contacts.CONTENT_URI,new String[]{Contacts._ID,Contacts.DISPLAY_NAME}, null, null, null);
		
		if(c!=null){
			while(c.moveToNext()){
				int id=c.getInt(c.getColumnIndex(Contacts._ID));
				Log.i("info", "_id:"+id);
				Log.i("info","name:"+c.getString(c.getColumnIndex(Contacts.DISPLAY_NAME)));
				Cursor c1=cr.query(Phone.CONTENT_URI, new String[]{Phone.NUMBER,Phone.TYPE}, Phone.CONTACT_ID+"="+id, null, null);
				//������ϵ��ID��ѯ����ϵ�˵ĵ绰����
				if(c1!=null){
					while(c1.moveToNext()){
						int type=c1.getInt(c1.getColumnIndex(Phone.TYPE));
						if(type==Phone.TYPE_HOME){
							Log.i("info", "��ͥ�绰:"+c1.getString(c1.getColumnIndex(Phone.NUMBER)));
						}else if(type==Phone.TYPE_MOBILE){
							Log.i("info", "�ֻ���:"+c1.getString(c1.getColumnIndex(Phone.NUMBER)));
						}
					}
					c1.close();
				}
				//������ϵ��ID��ѯ����ϵ�˵������ַ
				Cursor c2=cr.query(Email.CONTENT_URI, new String[]{Email.DATA,Email.TYPE}, Email.CONTACT_ID+"="+id, null, null);
				if(c2!=null){
					while(c2.moveToNext()){
						int type = c2.getInt(c2.getColumnIndex(Email.TYPE));
						if(type==Email.TYPE_WORK){
							Log.i("info", "��������:"+c2.getString(c2.getColumnIndex(Email.TYPE)));
						}else if(type==Email.TYPE_OTHER){
							Log.i("info", "��������:"+c2.getString(c2.getColumnIndex(Email.TYPE)));
						}
						
					}
					c2.close();
				}
			}
			c.close();
		}
	}

}
