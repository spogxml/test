package com.example.passwordmanager;

import com.example.passwordmanager.dboperate.MyData;
import com.example.passwordmanager.dboperate.MyDataDB;

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
import android.widget.TextView;

public class SelectPasswordActivity extends Activity {
	//加密界面控件
	private TextView tv_initpw;
	private EditText et_initpw;
	private Button it_confirm;
	private Button it_cancel;
	//用来判断是输入页面还是查询页面
	private int sp;

	private MyDataDB tdb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//和登陆界面使用同样的布局界面
		setContentView(R.layout.inputpassword);
		tv_initpw=(TextView) findViewById(R.id.tv_inlgpw);
		et_initpw=(EditText) findViewById(R.id.et_inlgpw);
		it_confirm=(Button) findViewById(R.id.lg_confirm);
		it_cancel=(Button) findViewById(R.id.lg_cancel);

		tv_initpw.setText("输入加密密码：");

		//启动时判断是否设置过加密密码，并获取intent传递的数据进行判断
		sp=this.AcceptIntent();
		//打开数据库
		tdb=new MyDataDB();
		tdb.initDB(SelectPasswordActivity.this);
		//查询数据库
		int r=tdb.queryItPW(null);
		Log.i("info", "r="+r);
		//如果没有设置过密码则直接显示查询页面
		if(r==0) {
			//如果是输入页面则跳转到输入页面
			if(sp==1){
				Intent toInput=new Intent(SelectPasswordActivity.this,InputActivity.class);
				//传递密码过去
				Bundle bundle=new Bundle();
				bundle.putString("key",null);
				toInput.putExtras(bundle);
				startActivity(toInput);
				finish();
			}else{
				//否则跳转到查询页面
				Intent toSelect=new Intent(SelectPasswordActivity.this,OutputSelectActivity.class);
				//传递密码过去
				Bundle bundle=new Bundle();
				bundle.putString("key",null);
				toSelect.putExtras(bundle);
				startActivity(toSelect);
				finish();
			}
		}
		//加密密码输入界面
		//确认按钮
		it_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//如果密码输入为空
				if(TextUtils.isEmpty(et_initpw.getText())){
					AlertDialog.Builder builder48  = new Builder(SelectPasswordActivity.this);
					builder48.setTitle("提示！" ) ;
					builder48.setMessage("请确认密码不能为空" ) ;
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
				}//如果不为空则进行匹配查询
				else{
					int i=0;
					//打开数据库进行查询
					String rs=et_initpw.getText().toString();
					i=tdb.queryItPW(rs);
					//查询成功，进入查询页面
					if(i==1){
						AlertDialog.Builder builder49  = new Builder(SelectPasswordActivity.this);
						builder49.setTitle("提示！" ) ;
						builder49.setMessage("密码正确！" ) ;
						builder49.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
						{

							@Override
							public   void  onClick(DialogInterface dialog,  int  which)
							{
								//如果是输入页面则跳转到输入页面
								if(sp==1){
									Intent toInput=new Intent(SelectPasswordActivity.this,InputActivity.class);
									//传递密码过去
									Bundle bundle=new Bundle();
									bundle.putString("key",et_initpw.getText().toString());
									toInput.putExtras(bundle);
									startActivity(toInput);
									finish();
								}else{
									//否则跳转到查询页面
									Intent toSelect=new Intent(SelectPasswordActivity.this,OutputSelectActivity.class);
									//传递密码过去
									Bundle bundle=new Bundle();
									bundle.putString("key",et_initpw.getText().toString());
									toSelect.putExtras(bundle);
									startActivity(toSelect);
									finish();
								}
							}
						});
						builder49.setCancelable(false);
						builder49.show();

					}
					//查询失败则提示错误
					else{
						AlertDialog.Builder builder50  = new Builder(SelectPasswordActivity.this);
						builder50.setTitle("提示！" ) ;
						builder50.setMessage("密码错误，请重新输入" ) ;
						builder50.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
						{

							@Override
							public   void  onClick(DialogInterface dialog,  int  which)
							{
								dialog.dismiss();
							}
						});
						builder50.setCancelable(false);
						builder50.show();
					}
				}
			}
		});
		//取消按钮
		it_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder51  = new Builder(SelectPasswordActivity.this);
				builder51.setTitle("提示！").setMessage("是否返回主页面!");
				builder51.setPositiveButton("是",new DialogInterface.OnClickListener()
				{
					@Override
					public   void  onClick(DialogInterface dialog,  int  which)
					{
						Intent toHome=new Intent(SelectPasswordActivity.this, MainActivity.class);
						startActivity(toHome); 
						finish();  
					}
				});
				builder51.setNegativeButton("否",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();
					}  
				});
				builder51.setCancelable(false);
				builder51.show();
			}
		});
	}

	//接收主页面点击时传来的数据
	public int AcceptIntent() {
		MyData mdata=new MyData();
		Intent intent_accept = getIntent();           //创建一个接收意图
		Bundle bundle = intent_accept.getExtras();    //创建Bundle对象，用于接收主页面的Intent数据
		String s=bundle.getString("type");
		if(s.equals("input")){
			return 1;
		}else {
			return 2;
		}  
	}
	//自定义返回键功能
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder builder52  = new Builder(SelectPasswordActivity.this);
			builder52.setTitle("提示！").setMessage("是否返回主页面!");
			builder52.setPositiveButton("是",new DialogInterface.OnClickListener()
			{
				@Override
				public   void  onClick(DialogInterface dialog,  int  which)
				{
					Intent toHome=new Intent(SelectPasswordActivity.this, MainActivity.class);
					startActivity(toHome); 
					finish();
				}
			});
			builder52.setNegativeButton("否",new DialogInterface.OnClickListener() {  
				@Override  
				public void onClick(DialogInterface dialog, int which) {

					dialog.dismiss();
				}  
			});
			builder52.setCancelable(false);
			builder52.show();
		}
		return super.onKeyDown(keyCode, event);
	}
}