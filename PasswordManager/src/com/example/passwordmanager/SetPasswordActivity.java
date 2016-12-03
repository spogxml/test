package com.example.passwordmanager;

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
import android.widget.TextView;

//设置密码页面
public class SetPasswordActivity extends Activity{
	private EditText et_setpw;
	private EditText et_setpw_confirm;
	private TextView tv_setpw;
	private TextView tv_setpw_confirm;
	private Button set_confirm;
	private Button set_cancel;
	private MyData spwdata;
	private MyDataDB sdb;
	private int i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_pasword);
		et_setpw=(EditText) findViewById(R.id.et_setpw);
		et_setpw_confirm=(EditText) findViewById(R.id.et_setpw_confirm);
		tv_setpw=(TextView) findViewById(R.id.tv_setpw);
		tv_setpw_confirm=(TextView) findViewById(R.id.tv_setpw_confirm);
		set_confirm=(Button) findViewById(R.id.set_confirm);
		set_cancel=(Button) findViewById(R.id.set_cancel);

		//根据主页面点击时传过来的数据确认是登陆密码还是加密密码
		i=this.AcceptIntent();
		//如果是登陆密码
		//修改布局页面信息
		if(i==1){
			tv_setpw.setText("设置登陆密码：");
			tv_setpw_confirm.setText("确认登陆密码：");
		}	
		//如果是加密密码
		//修改布局页面信息
		else {
			tv_setpw.setText("设置加密密码：");
			tv_setpw_confirm.setText("确认加密密码：");
		}
		//确认按钮，设置密码
		set_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//如果输入框为空或过短，则无法设置密码
				if(TextUtils.isEmpty(et_setpw.getText())||et_setpw.getText().toString().length()<6){
					AlertDialog.Builder builder25  = new Builder(SetPasswordActivity.this);
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
					AlertDialog.Builder builder29  = new Builder(SetPasswordActivity.this);
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
					//如果i=1则设置登陆密码
					if(i==1){
						spwdata=new MyData();//初始化并获取输入框内容
						spwdata.lgpassword=et_setpw.getText().toString();
						//打开数据库
						sdb=new MyDataDB();
						sdb.initDB(SetPasswordActivity.this);
						//添加密码
						int i=sdb.insertDB(SetPasswordActivity.this, spwdata);
						//添加失败时弹出对话框
						if(i==0){
							AlertDialog.Builder builder26  = new Builder(SetPasswordActivity.this);
							builder26.setTitle("提示！" ) ;
							builder26.setMessage("设置密码失败！" ) ;
							builder26.setPositiveButton("确认" , new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									Intent toHome=new Intent(SetPasswordActivity.this, MainActivity.class);
									startActivity(toHome);
									finish();  
								}  
							});
							builder26.setCancelable(false);
							builder26.show(); 
						}
						//添加成功时时弹出对话框
						else {
							AlertDialog.Builder builder27  = new Builder(SetPasswordActivity.this);
							builder27.setTitle("设置成功！").setMessage("密码设置成功！下次登陆时将需要输入密码");
							builder27.setPositiveButton("返回主页面",new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									Intent toHome=new Intent(SetPasswordActivity.this, MainActivity.class);
									startActivity(toHome);
									finish();
								}  
							});
							builder27.setCancelable(false);
							builder27.show();
						}
					}//如果i=2则设置加密密码
					else{
						spwdata=new MyData();//初始化并获取输入框内容
						spwdata.itpassword=et_setpw.getText().toString();
						//打开数据库
						sdb=new MyDataDB();
						sdb.initDB(SetPasswordActivity.this);
						//添加密码
						int i=sdb.insertDB(SetPasswordActivity.this, spwdata);
						//添加失败时弹出对话框
						if(i==0){
							AlertDialog.Builder builder43  = new Builder(SetPasswordActivity.this);
							builder43.setTitle("提示！" ) ;
							builder43.setMessage("设置密码失败！" ) ;
							builder43.setPositiveButton("确认" , new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									Intent toHome=new Intent(SetPasswordActivity.this, MainActivity.class);
									startActivity(toHome);
									finish();  
								}  
							});
							builder43.setCancelable(false);
							builder43.show(); 
						}
						//添加成功时时弹出对话框
						else {
							AlertDialog.Builder builder44  = new Builder(SetPasswordActivity.this);
							builder44.setTitle("设置成功！").setMessage("密码设置成功！下次查询数据时将需要输入密码");
							builder44.setPositiveButton("返回主页面",new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									Intent toHome=new Intent(SetPasswordActivity.this, MainActivity.class);
									startActivity(toHome);
									finish();
								}  
							});
							builder44.setCancelable(false);
							builder44.show();
						}
					}
						
				}

			}
		});

		//取消按钮，返回主页面
		set_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder28  = new Builder(SetPasswordActivity.this);
				builder28.setTitle("返回主页面！").setMessage("是否返回主页面！");
				builder28.setPositiveButton("是",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						Intent toHome=new Intent(SetPasswordActivity.this, MainActivity.class);
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
	//接收主页面点击时传来的数据
	public int AcceptIntent() {
		MyData mdata=new MyData();
		Intent intent_accept = getIntent();           //创建一个接收意图
		Bundle bundle = intent_accept.getExtras();    //创建Bundle对象，用于接收主页面的Intent数据
		String s=bundle.getString("type");
		if(s.equals("lgpassword")){
			return 1;
		}else {
			return 2;
		}  
	}
	//自定义返回键功能，和取消键一样
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder builder42  = new Builder(SetPasswordActivity.this);
			builder42.setTitle("返回主页面！").setMessage("是否返回主页面！");
			builder42.setPositiveButton("是",new DialogInterface.OnClickListener() {  
				@Override  
				public void onClick(DialogInterface dialog, int which) {
					Intent toHome=new Intent(SetPasswordActivity.this, MainActivity.class);
					startActivity(toHome);
					finish();
				}  
			});
			builder42.setNegativeButton("否", new DialogInterface.OnClickListener() {  
				@Override  
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}  
			});
			builder42.setCancelable(false);
			builder42.show();
		}
		return super.onKeyDown(keyCode, event);

	}

}
