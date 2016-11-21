package com.example.contentproviderdemo2;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ContentResolver cr = getContentResolver();
		//向联系人中插入一行数据
		ContentValues values=new ContentValues();
		Uri uri = cr.insert(RawContacts.CONTENT_URI, values);
		long raw_content_id = ContentUris.parseId(uri);
		values.clear();
		//插入人名
		values.put(StructuredName.RAW_CONTACT_ID, raw_content_id);
		values.put(StructuredName.DISPLAY_NAME, "张三三");
		values.put(StructuredName.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
		uri=cr.insert(Data.CONTENT_URI, values);
		//插入电话信息
		values.clear();
		values.put(Phone.RAW_CONTACT_ID, raw_content_id);
		values.put(Phone.NUMBER, "1333333333");
		values.put(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
		uri=cr.insert(Data.CONTENT_URI, values);
		
	}

}
