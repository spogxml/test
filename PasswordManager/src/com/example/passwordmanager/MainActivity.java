package com.example.passwordmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	//主界面控件
	private Button input;
	private Button output;
	private Button set_password;
	private Button amend_password;
	private Button set_itpassword;
	private Button amend_itpassword;

	private MyData fdata;
	private MyDataDB sdb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//主界面控件
		input=(Button) findViewById(R.id.input);
		output=(Button) findViewById(R.id.output);
		set_password=(Button) findViewById(R.id.set_password);
		amend_password=(Button) findViewById(R.id.amend_password);
		set_itpassword=(Button) findViewById(R.id.set_itpassword);
		amend_itpassword=(Button) findViewById(R.id.amend_itpassword);

		//启动时判断是否设置过登陆密码
		//打开数据库
		sdb=new MyDataDB();
		sdb.initDB(MainActivity.this);
		//查询数据库
		int r=sdb.queryPW(null);
		//如果设置过登陆密码，则默认显示登陆页面，且主页面的设置登陆密码按钮变为修改密码按钮
		if(r==1) {
			set_password.setVisibility(View.GONE);
			Log.i("info", "隐藏设置按钮");
			amend_password.setVisibility(View.VISIBLE);
			Log.i("info", "显示修改按钮");

		}

		//主界面
		//输入数据按钮
		input.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent inpt_intent=new Intent(MainActivity.this, InputActivity.class);
				startActivity(inpt_intent);
				finish();
			}
		});
		//查询数据按钮
		output.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent otpt_intent=new Intent(MainActivity.this, OutputSelectActivity.class);
				startActivity(otpt_intent);
				finish();
			}
		});
		//设置登陆密码按钮
		set_password.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent slgpw_intent=new Intent(MainActivity.this, SetLgPasswordActivity.class);
				startActivity(slgpw_intent);
				finish();
			}
		});
		//修改登陆密码按钮
		amend_password.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent algpw_intent=new Intent(MainActivity.this, InputActivity.class);
				startActivity(algpw_intent);
				finish();
			}
		});
		//设置加密密码按钮
		set_itpassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent inpt_intent=new Intent(MainActivity.this, InputActivity.class);
				startActivity(inpt_intent);
				finish();
			}
		});
		//修改登陆密码按钮
		amend_itpassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent inpt_intent=new Intent(MainActivity.this, InputActivity.class);
				startActivity(inpt_intent);
				finish();
			}
		});

	}
	
	//自定义返回键功能
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder builder16  = new Builder(MainActivity.this);
			builder16.setTitle("提示！").setMessage("是否退出程序!");
			builder16.setPositiveButton("是",new DialogInterface.OnClickListener()
			{
				@Override
				public   void  onClick(DialogInterface dialog,  int  which)
				{
					finish();  
				}
			});
			builder16.setNegativeButton("否",new DialogInterface.OnClickListener() {  
				@Override  
				public void onClick(DialogInterface dialog, int which) {

					dialog.dismiss();
				}  
			});
			builder16.setCancelable(false);
			builder16.show();
		}
		return super.onKeyDown(keyCode, event);
	}
}
