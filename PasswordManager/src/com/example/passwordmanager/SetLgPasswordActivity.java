package com.example.passwordmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SetLgPasswordActivity extends Activity{
	private EditText et_setpw;
	private EditText et_setpw_confirm;
	private Button set_confirm;
	private Button set_cancel;
	private MyData lgdata;
	private MyDataDB lgdb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_pasword);
		et_setpw=(EditText) findViewById(R.id.et_setpw);
		et_setpw_confirm=(EditText) findViewById(R.id.et_setpw_confirm);
		set_confirm=(Button) findViewById(R.id.set_confirm);
		set_cancel=(Button) findViewById(R.id.set_cancel);
		
		//确认按钮，设置密码
		set_confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//如果输入框为空或过短，则无法设置密码
				if(TextUtils.isEmpty(et_setpw.getText())||et_setpw.getText().toString().length()<6){
					AlertDialog.Builder builder25  = new Builder(SetLgPasswordActivity.this);
					builder25.setTitle("提示！" ) ;
					builder25.setMessage("请确认密码不能为空且最少为6位！" ) ;
					builder25.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
					{

						@Override
						public   void  onClick(DialogInterface dialog,  int  which)
						{
							dialog.dismiss();
						}
					});
					builder25.setCancelable(false);
					builder25.show(); 
				}
				//如果2个输入框输入的密码不一致则提醒重新确认
				else if(!(et_setpw.getText().toString().equals(et_setpw_confirm.getText().toString()))){
					AlertDialog.Builder builder29  = new Builder(SetLgPasswordActivity.this);
					builder29.setTitle("提示！" ) ;
					builder29.setMessage("请确认密码一致！" ) ;
					builder29.setPositiveButton("好" ,new DialogInterface.OnClickListener()
					{

						@Override
						public   void  onClick(DialogInterface dialog,  int  which)
						{
							dialog.dismiss();
						}
					});
					builder29.setCancelable(false);
					builder29.show();
				}
				//添加密码
				else{
					lgdata=new MyData();//初始化并获取输入框内容
					lgdata.lgpassword=et_setpw.getText().toString();
					//打开数据库
					lgdb=new MyDataDB();
					lgdb.initDB(SetLgPasswordActivity.this);
					//添加密码
					int i=lgdb.insertDB(SetLgPasswordActivity.this, lgdata);
					//添加失败时弹出对话框
					if(i==0){
						AlertDialog.Builder builder26  = new Builder(SetLgPasswordActivity.this);
						builder26.setTitle("提示！" ) ;
						builder26.setMessage("设置密码失败！" ) ;
						builder26.setPositiveButton("确认" , new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								Intent toHome=new Intent(SetLgPasswordActivity.this, MainActivity.class);
								startActivity(toHome);
								finish();  
							}  
						});
						builder26.setCancelable(false);
						builder26.show(); 
					}
					//添加成功时时弹出对话框
					else {
						AlertDialog.Builder builder27  = new Builder(SetLgPasswordActivity.this);
						builder27.setTitle("设置成功！").setMessage("密码设置成功！");
						builder27.setPositiveButton("返回主页面",new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								Intent toHome=new Intent(SetLgPasswordActivity.this, MainActivity.class);
								startActivity(toHome);
								finish();
							}  
						});
						builder27.setCancelable(false);
						builder27.show();
				}
				}
				
			}
		});
		
		//取消按钮，返回主页面
		set_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder28  = new Builder(SetLgPasswordActivity.this);
				builder28.setTitle("返回主页面！").setMessage("是否返回主页面！");
				builder28.setPositiveButton("是",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						Intent toHome=new Intent(SetLgPasswordActivity.this, MainActivity.class);
						startActivity(toHome);
						finish();
					}  
				});
				builder28.setNegativeButton("否", new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}  
				});
				builder28.setCancelable(false);
				builder28.show();
			}
		});
	}
	
	
}
