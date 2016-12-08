package com.example.passwordmanager;

import com.example.passwordmanager.dboperate.MyData;
import com.example.passwordmanager.dboperate.MyDataDB;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//主页面
public class MainActivity extends Activity {
	//主界面控件
	private Button input;
	private Button output;
	private Button set_password;
	private Button amend_password;
	private Button set_itpassword;
	private Button amend_itpassword;
	private Button bt_deleteall;
	
	private MyData fdata;
	private MyDataDB sdb;
	
	private int r1=0;
	private int r2=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//主界面控件
		input=(Button)findViewById(R.id.input);
		output=(Button)findViewById(R.id.output);
		set_password=(Button)findViewById(R.id.set_password);
		amend_password=(Button)findViewById(R.id.amend_password);
		set_itpassword=(Button)findViewById(R.id.set_itpassword);
		amend_itpassword=(Button)findViewById(R.id.amend_itpassword);
		bt_deleteall=(Button)findViewById(R.id.bt_deleteall);
		//启动时判断是否设置过登陆密码和加密密码
		//打开数据库
		sdb=new MyDataDB();
		sdb.initDB(MainActivity.this);
		//查询数据库
		r1=sdb.queryLgPW(null);
		r2=sdb.queryItPW(null);
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
		//主界面
		//输入数据按钮
		input.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent inpt_intent=new Intent(MainActivity.this, SelectPasswordActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString("type", "input");
				inpt_intent.putExtras(bundle);
				startActivity(inpt_intent);
				finish();
			}
		});
		//查询数据按钮
		output.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent otpt_intent=new Intent(MainActivity.this, SelectPasswordActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString("type", "output");
				otpt_intent.putExtras(bundle);
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
		
		
		//清空所有数据按钮
		bt_deleteall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				AlertDialog.Builder builder53  = new Builder(MainActivity.this);
				builder53.setTitle("提示！").setMessage("是否确认清除所有数据，还原为初始状态？");
				builder53.setPositiveButton("是",new DialogInterface.OnClickListener()
				{
					@Override
					public   void  onClick(DialogInterface dialog,  int  which)
					{
						//如果设置过登陆密码，没有设置过加密密码，删除前需要输入登陆密码
						if(r1==1&&r2==0) {
							//自定义弹出的输入框
							final View view1 = getLayoutInflater().inflate(R.layout.deleteall, null); 
							final EditText et_depassword1=(EditText) view1.findViewById(R.id.et_deleteall);
							AlertDialog.Builder builder54  = new Builder(MainActivity.this);
							builder54.setTitle("请输入登陆密码：");
							builder54.setView(view1);
							builder54.setPositiveButton("确认",new DialogInterface.OnClickListener()
							{
								@Override
								public   void  onClick(DialogInterface dialog,  int  which)
								{
									//根据输入框内容查询登陆密码是否正确
									String p1=et_depassword1.getText().toString();
									int r=sdb.queryLgPW(p1);
									//错误
									if(r==0){
										AlertDialog.Builder builder57  = new Builder(MainActivity.this);
										builder57.setTitle("提示！" ) ;
										builder57.setMessage("密码输入错误！" ) ;
										builder57.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
										{

											@Override
											public   void  onClick(DialogInterface dialog,  int  which)
											{
												dialog.dismiss();
											}
										});
										builder57.setCancelable(false);
										builder57.show();
									}//正确
									else{
										sdb.deleteDB("deleteall");
										sdb.deletePW();
										AlertDialog.Builder builder58  = new Builder(MainActivity.this);
										builder58.setTitle("提示！" ) ;
										builder58.setMessage("已删除所有数据，将退出程序！" ) ;
										builder58.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
										{

											@Override
											public   void  onClick(DialogInterface dialog,  int  which)
											{
												finish();
											}
										});
										builder58.setCancelable(false);
										builder58.show();
									}

								}
							});
							builder54.setNegativeButton("取消",new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {

									dialog.dismiss();
								}  
							});
							builder54.setCancelable(false);
							builder54.show();
						}//如果设置过加密密码，没有设置过登陆密码，删除前需要输入加密密码
						else if(r1==0&&r2==1){
							//自定义弹出的输入框
							final View view2 = getLayoutInflater().inflate(R.layout.deleteall, null); 
							final EditText et_depassword2=(EditText) view2.findViewById(R.id.et_deleteall);
							AlertDialog.Builder builder55  = new Builder(MainActivity.this);
							builder55.setTitle("请输入加密密码：");
							builder55.setView(view2);
							builder55.setPositiveButton("确认",new DialogInterface.OnClickListener()
							{
								@Override
								public   void  onClick(DialogInterface dialog,  int  which)
								{

									//根据输入框内容查询加密密码是否正确
									String p1=et_depassword2.getText().toString();
									int r=sdb.queryItPW(p1);
									//错误
									if(r==0){
										AlertDialog.Builder builder59  = new Builder(MainActivity.this);
										builder59.setTitle("提示！" ) ;
										builder59.setMessage("密码输入错误！" ) ;
										builder59.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
										{

											@Override
											public   void  onClick(DialogInterface dialog,  int  which)
											{
												dialog.dismiss();
											}
										});
										builder59.setCancelable(false);
										builder59.show();
									}//正确
									else{
										sdb.deleteDB("deleteall");
										sdb.deletePW();
										AlertDialog.Builder builder60  = new Builder(MainActivity.this);
										builder60.setTitle("提示！" ) ;
										builder60.setMessage("已删除所有数据，将退出程序！" ) ;
										builder60.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
										{

											@Override
											public   void  onClick(DialogInterface dialog,  int  which)
											{
												finish();
											}
										});
										builder60.setCancelable(false);
										builder60.show();
									}
								}
							});
							builder55.setNegativeButton("取消",new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {

									dialog.dismiss();
								}  
							});
							builder55.setCancelable(false);
							builder55.show();
						}//如果都设置过，则2个密码都需要输入
						else if(r1==1&&r2==1){
							//自定义弹出的输入框
							final View view3 = getLayoutInflater().inflate(R.layout.deleteall, null); 
							final EditText et_depassword3=(EditText) view3.findViewById(R.id.et_deleteall);
							AlertDialog.Builder builder54  = new Builder(MainActivity.this);
							builder54.setTitle("请输入登录密码：");
							builder54.setView(view3);
							builder54.setPositiveButton("确认",new DialogInterface.OnClickListener()
							{
								@Override
								public   void  onClick(DialogInterface dialog,  int  which)
								{
									//根据输入框内容查询登陆密码是否正确
									String p1=et_depassword3.getText().toString();
									int r=sdb.queryLgPW(p1);
									//错误
									if(r==0){
										AlertDialog.Builder builder57  = new Builder(MainActivity.this);
										builder57.setTitle("提示！" ) ;
										builder57.setMessage("密码输入错误！" ) ;
										builder57.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
										{

											@Override
											public   void  onClick(DialogInterface dialog,  int  which)
											{
												dialog.dismiss();
											}
										});
										builder57.setCancelable(false);
										builder57.show();
									}//正确
									else{
										final View view4 = getLayoutInflater().inflate(R.layout.deleteall, null); 
										final EditText et_depassword4=(EditText) view4.findViewById(R.id.et_deleteall);
										AlertDialog.Builder builder55  = new Builder(MainActivity.this);
										builder55.setTitle("请输入加密密码：");
										builder55.setView(view4);
										builder55.setPositiveButton("确认",new DialogInterface.OnClickListener()
										{
											@Override
											public   void  onClick(DialogInterface dialog,  int  which)
											{

												//根据输入框内容查询加密密码是否正确
												String p1=et_depassword4.getText().toString();
												int r=sdb.queryItPW(p1);
												//错误
												if(r==0){
													AlertDialog.Builder builder59  = new Builder(MainActivity.this);
													builder59.setTitle("提示！" ) ;
													builder59.setMessage("密码输入错误！" ) ;
													builder59.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
													{

														@Override
														public   void  onClick(DialogInterface dialog,  int  which)
														{
															dialog.dismiss();
														}
													});
													builder59.setCancelable(false);
													builder59.show();
												}//正确
												else{
													int i=sdb.deleteDB("deleteall");
													Log.i("info", "删除数据结果r="+r);
													sdb.deletePW();
													AlertDialog.Builder builder60  = new Builder(MainActivity.this);
													builder60.setTitle("提示！" ) ;
													builder60.setMessage("已删除所有数据，将退出程序！" ) ;
													builder60.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
													{

														@Override
														public   void  onClick(DialogInterface dialog,  int  which)
														{
															finish();
														}
													});
													builder60.setCancelable(false);
													builder60.show();
												}
											}
										});
										builder55.setNegativeButton("取消",new DialogInterface.OnClickListener() {  
											@Override  
											public void onClick(DialogInterface dialog, int which) {

												dialog.dismiss();
											}  
										});
										builder55.setCancelable(false);
										builder55.show();
									}
								}
							});
							builder54.setNegativeButton("取消",new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {

									dialog.dismiss();
								}  
							});
							builder54.setCancelable(false);
							builder54.show();
						}//如果没有设置过密码
						else{
							sdb.deleteDB("deleteall");
							sdb.deletePW();
							AlertDialog.Builder builder61  = new Builder(MainActivity.this);
							builder61.setTitle("提示！" ) ;
							builder61.setMessage("已删除所有数据，将退出程序！" ) ;
							builder61.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
							{

								@Override
								public   void  onClick(DialogInterface dialog,  int  which)
								{
									finish();
								}
							});
							builder61.setCancelable(false);
							builder61.show();
						}
						dialog.dismiss();
					}
				});
				builder53.setNegativeButton("否",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();
					}  
				});
				builder53.setCancelable(false);
				builder53.show();
				
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
