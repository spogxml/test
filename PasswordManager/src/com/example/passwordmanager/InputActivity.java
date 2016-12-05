package com.example.passwordmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends Activity {
	//初始化输入数据界面的控件
	private EditText ed_title;
	private EditText ed_user;
	private EditText ed_password;
	private EditText ed_note;
	private Button in_confirm;
	private Button in_cancel;
	//key
	private String ikey=null;
	//存放数据的中介
	private MyData mdata;
	//数据库类
	private MyDataDB iDB;
	//判断添加是否成功
	private int i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input);
		ed_title=(EditText) findViewById(R.id.ed_title);
		ed_user=(EditText) findViewById(R.id.ed_user);
		ed_password=(EditText) findViewById(R.id.ed_password);
		ed_note=(EditText) findViewById(R.id.ed_note);
		in_confirm=(Button) findViewById(R.id.in_confirm);
		in_cancel=(Button) findViewById(R.id.in_cancel);
		//获取key
		ikey=this.AcceptIntent();
		//默认标题获取焦点
		ed_title.requestFocus();
		//确认按钮的点击事件
		in_confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//如果有输入框为空，则无法添加
				if(TextUtils.isEmpty(ed_title.getText())||TextUtils.isEmpty(ed_user.getText())
						||TextUtils.isEmpty(ed_password.getText())||TextUtils.isEmpty(ed_note.getText())){
					AlertDialog.Builder builder1  = new Builder(InputActivity.this);
					builder1.setTitle("提示！" ) ;
					builder1.setMessage("请确认所有输入数据不能为空！" ) ;
					builder1.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
					{

						@Override
						public   void  onClick(DialogInterface dialog,  int  which)
						{
							dialog.dismiss();
						}
					});
					builder1.setCancelable(false);
					builder1.show(); 
				}//添加数据
				else{
					mdata=new MyData();//初始化并获取输入框内容
					mdata.title=ed_title.getText().toString();
					mdata.user=ed_user.getText().toString();
					mdata.password=ed_password.getText().toString();
					mdata.note=ed_note.getText().toString();

					//插入数据时先查询判断数据库当中是否有和当前数据标题一样的数据，如果有则提示不能插入，没有则插入
					//打开数据库，查询数据
					iDB=new MyDataDB();
					iDB.initDB(InputActivity.this);
					List<Map<String,String>> arr_inlist=new ArrayList<Map<String,String>>();
					arr_inlist=iDB.queryDB(mdata.title,ikey);
					//如果有和当前数据一样标题的则不能插入
					if(arr_inlist!=null){
						AlertDialog.Builder builder19  = new Builder(InputActivity.this);
						builder19.setTitle("提示！").setMessage("当前数据标题所存内容已存在，请修改标题！");
						builder19.setPositiveButton("确认",new DialogInterface.OnClickListener()
						{

							@Override
							public   void  onClick(DialogInterface dialog,  int  which)
							{
								dialog.dismiss();
							}
						});
						builder19.setCancelable(false);
						builder19.show();
						
					}//没有则可以插入
					else{
						//打开数据库，插入数据
						iDB=new MyDataDB();
						iDB.initDB(InputActivity.this);
						i=iDB.insertDB(InputActivity.this, mdata,ikey);
						//添加失败时弹出对话框
						if(i==0){
							AlertDialog.Builder builder2  = new Builder(InputActivity.this);
							builder2.setTitle("提示！" ) ;
							builder2.setMessage("添加失败！请确认空间是否足够！" ) ;
							builder2.setPositiveButton("确认" , new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									Intent toHome=new Intent(InputActivity.this, MainActivity.class);
									startActivity(toHome);
									finish();  
								}  
							});
							builder2.setCancelable(false);
							builder2.show(); 
						}
						//添加成功时时弹出对话框
						else {
							AlertDialog.Builder builder3  = new Builder(InputActivity.this);
							builder3.setTitle("添加成功！").setMessage("数据添加成功！");
							builder3.setPositiveButton("继续添加",new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									ed_title.setText("");
									ed_user.setText("");
									ed_password.setText("");
									ed_note.setText("");
									dialog.dismiss();
								}  
							});
							builder3.setNegativeButton("返回主页面",new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									Intent toHome=new Intent(InputActivity.this, MainActivity.class);
									startActivity(toHome);
									finish();  
								}  
							});
							builder3.setCancelable(false);
							builder3.show();
						}
					}
				}
			}
		});
		//取消按钮的点击事件
		in_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//当所有输入框全为空时，直接返回主页面
				if(TextUtils.isEmpty(ed_title.getText())&&TextUtils.isEmpty(ed_user.getText())
						&&TextUtils.isEmpty(ed_password.getText())&&TextUtils.isEmpty(ed_note.getText())){
					Intent toHome=new Intent(InputActivity.this, MainActivity.class);
					startActivity(toHome);
					finish();
				}else{
					//当有一个输入框不为空时，弹出提示框
					AlertDialog.Builder builder4  = new Builder(InputActivity.this);
					builder4.setTitle("提示！").setMessage("数据没有输入完成，是否确认返回主页面");
					builder4.setPositiveButton("继续添加",new DialogInterface.OnClickListener()
					{

						@Override
						public   void  onClick(DialogInterface dialog,  int  which)
						{
							dialog.dismiss();
						}
					});
					builder4.setNegativeButton("返回主页面",new DialogInterface.OnClickListener() {  
						@Override  
						public void onClick(DialogInterface dialog, int which) {
							Intent toHome=new Intent(InputActivity.this, MainActivity.class);
							startActivity(toHome);
							finish();  
						}  
					});
					builder4.setCancelable(false);
					builder4.show();
				}
			}
		});
	}
	//接收输入密码页面点击时传来的数据
		public String AcceptIntent() {
			MyData mdata=new MyData();
			Intent intent_accept = getIntent();           //创建一个接收意图
			Bundle bundle = intent_accept.getExtras();    //创建Bundle对象，用于接收主页面的Intent数据
			String s=bundle.getString("key");
			return s;
		}

	//自定义返回键功能，和取消键一样
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//当所有输入框全为空时，直接返回主页面
			if(TextUtils.isEmpty(ed_title.getText())&&TextUtils.isEmpty(ed_user.getText())
					&&TextUtils.isEmpty(ed_password.getText())&&TextUtils.isEmpty(ed_note.getText())){
				Intent toHome=new Intent(InputActivity.this, MainActivity.class);
				startActivity(toHome);
				finish();
			}else{
				//当有一个输入框不为空时，弹出提示框
				AlertDialog.Builder builder5  = new Builder(InputActivity.this);
				builder5.setTitle("提示！").setMessage("数据没有输入完成，是否确认返回主页面");
				builder5.setPositiveButton("继续添加",new DialogInterface.OnClickListener()
				{

					@Override
					public   void  onClick(DialogInterface dialog,  int  which)
					{
						dialog.dismiss();
					}
				});
				builder5.setNegativeButton("返回主页面",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						Intent toHome=new Intent(InputActivity.this, MainActivity.class);
						startActivity(toHome);
						finish();  
					}  
				});
				builder5.setCancelable(false);
				builder5.show();
			}
		}
		return super.onKeyDown(keyCode, event);

	}


}
