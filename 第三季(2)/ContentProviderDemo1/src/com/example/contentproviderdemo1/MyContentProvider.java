package com.example.contentproviderdemo1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

	@Override//在ContentProvider创建后被调用
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override//根据Uri查询出selection指定的条件所匹配的全部记录，并且可以指定查询哪些列，以什么方式（order）排序
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override//返回当前uri的MIME类型，如果uri对应数据包括多条记录
	//那么MIME类型字符串就是以vnd.android.cursor.dir/开头
	//如果只对应一条记录就是vnd.android.cursor.item/开头
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override//根据Uri插入Values对应的数据
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override//根据Uri删除selection指定的条件所匹配到的全部记录
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override//根据Uri修改selection指定的条件所匹配的全部记录
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
