package com.example.passwordmanager.dboperate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MDHelper extends SQLiteOpenHelper {

	public MDHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists pwtb(_id integer primary key autoincrement,title text not null,user text not null,password text not null,note text not null)");
		db.execSQL("create table if not exists ustb(_id integer primary key autoincrement,lgpassword text,itpassword text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
