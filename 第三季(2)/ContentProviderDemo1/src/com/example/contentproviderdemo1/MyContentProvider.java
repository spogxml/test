package com.example.contentproviderdemo1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

	@Override//��ContentProvider�����󱻵���
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override//����Uri��ѯ��selectionָ����������ƥ���ȫ����¼�����ҿ���ָ����ѯ��Щ�У���ʲô��ʽ��order������
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override//���ص�ǰuri��MIME���ͣ����uri��Ӧ���ݰ���������¼
	//��ôMIME�����ַ���������vnd.android.cursor.dir/��ͷ
	//���ֻ��Ӧһ����¼����vnd.android.cursor.item/��ͷ
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override//����Uri����Values��Ӧ������
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override//����Uriɾ��selectionָ����������ƥ�䵽��ȫ����¼
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override//����Uri�޸�selectionָ����������ƥ���ȫ����¼
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
