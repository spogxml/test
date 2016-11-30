package com.example.passwordmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
	private RelativeLayout MainRelativeLayout;
	private Button input;
	private Button output;
	private Button set_password;
	private Button amend_password;
	private Button set_itpassword;
	private Button amend_itpassword;
	//登陆界面控件
	private RelativeLayout LgRelativeLayout;
	private EditText et_inlgpw;
	private Button lg_confirm;
	private Button lg_cancel;
	
	private MyData fdata;
	private MyDataDB fdb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//主界面控件
		MainRelativeLayout=(RelativeLayout) findViewById(R.id.MainRelativeLayout);
		input=(Button) findViewById(R.id.input);
		output=(Button) findViewById(R.id.output);
		set_password=(Button) findViewById(R.id.set_password);
		amend_password=(Button) findViewById(R.id.amend_password);
		set_itpassword=(Button) findViewById(R.id.set_itpassword);
		amend_itpassword=(Button) findViewById(R.id.amend_itpassword);
		//登陆界面控件
		LgRelativeLayout=(RelativeLayout) findViewById(R.id.LgRelativeLayout);
		et_inlgpw=(EditText) findViewById(R.id.et_inlgpw);
		lg_confirm=(Button) findViewById(R.id.lg_confirm);
		lg_cancel=(Button) findViewById(R.id.lg_cancel);
		
		//启动时判断是否设置过登陆密码，如果设置过，则首先显示出输入密码界面
		//打开数据库
		fdb=new MyDataDB();
		fdb.initDB(MainActivity.this);
		//查询数据库
		int r=fdb.queryPW(null);
		//如果没有设置过登陆密码，则直接显示主页面
		if(r==0){
			//VISIBLE:0  意思是可见的
			//INVISIBILITY:4 意思是不可见的，但还占着原来的空间
			//GONE:8  意思是不可见的，不占用原来的布局空间
			LgRelativeLayout.setVisibility(8);
			MainRelativeLayout.setVisibility(0);
		}//如果设置过登陆密码，则默认显示登陆页面，且主页面的设置登陆密码按钮变为修改密码按钮
		else if(r==1){
			set_password.setVisibility(8);
			amend_password.setVisibility(0);
		}
		//登陆界面
		//确认登陆按钮
		lg_confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//如果密码输入为空
				if(TextUtils.isEmpty(et_inlgpw.getText())){
					AlertDialog.Builder builder30  = new Builder(MainActivity.this);
					builder30.setTitle("提示！" ) ;
					builder30.setMessage("请确认密码不能为空" ) ;
					builder30.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
					{

						@Override
						public   void  onClick(DialogInterface dialog,  int  which)
						{
							dialog.dismiss();
						}
					});
					builder30.setCancelable(false);
					builder30.show();
				}//如果不为空则进行匹配查询
				else{
					String rs=et_inlgpw.getText().toString();
					int i=fdb.queryPW(rs);
					//查询成功，进入主页面
					if(i==1){
						AlertDialog.Builder builder31  = new Builder(MainActivity.this);
						builder31.setTitle("提示！" ) ;
						builder31.setMessage("登陆成功！" ) ;
						builder31.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
						{

							@Override
							public   void  onClick(DialogInterface dialog,  int  which)
							{
								LgRelativeLayout.setVisibility(8);
								MainRelativeLayout.setVisibility(0);
								dialog.dismiss();
							}
						});
						builder31.setCancelable(false);
						builder31.show();
						
					}
					//查询失败则提示错误
					else{
						AlertDialog.Builder builder31  = new Builder(MainActivity.this);
						builder31.setTitle("提示！" ) ;
						builder31.setMessage("密码错误，请重新输入" ) ;
						builder31.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
						{

							@Override
							public   void  onClick(DialogInterface dialog,  int  which)
							{
								dialog.dismiss();
							}
						});
						builder31.setCancelable(false);
						builder31.show();
					}
				}
			}
		});
		
		
		
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
