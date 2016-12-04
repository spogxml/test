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

//主页面
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

		//启动时判断是否设置过登陆密码和加密密码
		//打开数据库
		sdb=new MyDataDB();
		sdb.initDB(MainActivity.this);
		//查询数据库
		int r1=sdb.queryLgPW(null);
		int r2=sdb.queryItPW(null);
		//如果设置过登陆密码，则显示登陆页面，且主页面的设置登陆密码按钮变为修改密码按钮
		if(r1==1&&r2==0) {
			set_password.setVisibility(View.GONE);
			amend_password.setVisibility(View.VISIBLE);

		}//如果设置过加密密码，则主页面的设置加密密码按钮变为修改密码按钮
		else if(r1==0&&r2==1){
			set_itpassword.setVisibility(View.GONE);
			amend_itpassword.setVisibility(View.VISIBLE);
		}//如果都设置过，则都显示为修改
		else if(r1==1&&r2==1){
			set_password.setVisibility(View.GONE);
			amend_password.setVisibility(View.VISIBLE);
			set_itpassword.setVisibility(View.GONE);
			amend_itpassword.setVisibility(View.VISIBLE);
		}//如果都没有设置过，则弹出提示框
		else{
			AlertDialog.Builder builder48  = new Builder(MainActivity.this);
			builder48.setTitle("提示！" ) ;
			builder48.setMessage("您还没有设置登陆密码或加密密码，请尽快设置！" ) ;
			builder48.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
			{

				@Override
				public   void  onClick(DialogInterface dialog,  int  which)
				{
					dialog.dismiss();
				}
			});
			builder48.setCancelable(false);
			builder48.show();
		}
		sdb.closeDB();
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
				Intent otpt_intent=new Intent(MainActivity.this, SelectPasswordActivity.class);
				startActivity(otpt_intent);
				finish();
			}
		});
		//设置登陆密码按钮
		set_password.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent slgpw_intent=new Intent(MainActivity.this, SetPasswordActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString("type", "lgpassword");
				slgpw_intent.putExtras(bundle);
				startActivity(slgpw_intent);
				finish();
			}
		});
		//修改登陆密码按钮
		amend_password.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent algpw_intent=new Intent(MainActivity.this, AmendPasswordActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString("type", "lgpassword");
				algpw_intent.putExtras(bundle);
				startActivity(algpw_intent);
				finish();
			}
		});
		//设置加密密码按钮
		set_itpassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent sitpw_intent=new Intent(MainActivity.this, SetPasswordActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString("type", "itpassword");
				sitpw_intent.putExtras(bundle);
				startActivity(sitpw_intent);
				finish();
			}
		});
		//修改加密密码按钮
		amend_itpassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent aitpw_intent=new Intent(MainActivity.this, AmendPasswordActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString("type", "itpassword");
				aitpw_intent.putExtras(bundle);
				startActivity(aitpw_intent);
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
