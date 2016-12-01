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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	//登陆界面控件
	private EditText et_inlgpw;
	private Button lg_confirm;
	private Button lg_cancel;
	private MyDataDB fdb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		//登陆界面控件
		et_inlgpw=(EditText) findViewById(R.id.et_inlgpw);
		lg_confirm=(Button) findViewById(R.id.lg_confirm);
		lg_cancel=(Button) findViewById(R.id.lg_cancel);

		//启动时判断是否设置过登陆密码
		//打开数据库
		fdb=new MyDataDB();
		fdb.initDB(LoginActivity.this);
		//查询数据库
		int r=fdb.queryPW(null);
		//如果没有设置过密码则直接显示主页面
		if(r==0) {
			Intent toHome=new Intent(LoginActivity.this, MainActivity.class);
			startActivity(toHome);
			finish();
		}
		//登陆界面
		//确认登陆按钮
		lg_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//如果密码输入为空
				if(TextUtils.isEmpty(et_inlgpw.getText())){
					AlertDialog.Builder builder30  = new Builder(LoginActivity.this);
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
						AlertDialog.Builder builder31  = new Builder(LoginActivity.this);
						builder31.setTitle("提示！" ) ;
						builder31.setMessage("登陆成功！" ) ;
						builder31.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
						{

							@Override
							public   void  onClick(DialogInterface dialog,  int  which)
							{
								Intent toHome=new Intent(LoginActivity.this, MainActivity.class);
								startActivity(toHome);
								finish();
							}
						});
						builder31.setCancelable(false);
						builder31.show();

					}
					//查询失败则提示错误
					else{
						AlertDialog.Builder builder31  = new Builder(LoginActivity.this);
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
		//取消按钮
		lg_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder32  = new Builder(LoginActivity.this);
				builder32.setTitle("提示！").setMessage("是否退出程序!");
				builder32.setPositiveButton("是",new DialogInterface.OnClickListener()
				{
					@Override
					public   void  onClick(DialogInterface dialog,  int  which)
					{
						finish();  
					}
				});
				builder32.setNegativeButton("否",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();
					}  
				});
				builder32.setCancelable(false);
				builder32.show();
			}
		});
	}
	//自定义返回键功能
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				AlertDialog.Builder builder33  = new Builder(LoginActivity.this);
				builder33.setTitle("提示！").setMessage("是否退出程序!");
				builder33.setPositiveButton("是",new DialogInterface.OnClickListener()
				{
					@Override
					public   void  onClick(DialogInterface dialog,  int  which)
					{
						finish();  
					}
				});
				builder33.setNegativeButton("否",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();
					}  
				});
				builder33.setCancelable(false);
				builder33.show();
			}
			return super.onKeyDown(keyCode, event);
		}
}
