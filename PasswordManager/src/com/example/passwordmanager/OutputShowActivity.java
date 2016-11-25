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

public class OutputShowActivity extends Activity {
	//初始化控件
	private EditText et_ot_title;
	private EditText et_ot_user;
	private EditText et_ot_password;
	private EditText et_ot_note;
	private Button update;
	private Button delete;
	private Button ot_sh_cancel;

	//存放数据的中介
	private MyData ot_shdata;
	//数据库类
	private MyDataDB ot_shDB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.output_show);
		et_ot_title=(EditText) findViewById(R.id.et_ot_title);
		et_ot_user=(EditText) findViewById(R.id.et_ot_user);
		et_ot_password=(EditText) findViewById(R.id.et_ot_password);
		et_ot_note=(EditText) findViewById(R.id.et_ot_note);
		update=(Button) findViewById(R.id.update);
		delete=(Button) findViewById(R.id.delete);
		ot_sh_cancel=(Button) findViewById(R.id.ot_sh_cancel);

		//接收数据
		ot_shdata=new MyData();
		ot_shdata=this.AcceptIntent();

		//设置数据显示
		et_ot_title.setText(ot_shdata.title);
		et_ot_user.setText(ot_shdata.user);
		et_ot_password.setText(ot_shdata.password);
		et_ot_note.setText(ot_shdata.note);

		//修改数据
		update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//如果有输入框为空，则更新失败
				if(TextUtils.isEmpty(et_ot_title.getText())||TextUtils.isEmpty(et_ot_user.getText())
						||TextUtils.isEmpty(et_ot_password.getText())||TextUtils.isEmpty(et_ot_note.getText())){
					AlertDialog.Builder builder6  = new Builder(OutputShowActivity.this);
					builder6.setTitle("提示！" ) ;
					builder6.setMessage("请确认所有输入数据不能为空！" ) ;
					builder6.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
					{

						@Override
						public   void  onClick(DialogInterface dialog,  int  which)
						{
							dialog.dismiss();
						}
					});
					builder6.setCancelable(false);
					builder6.show(); 
				}//输入框都有数据则进行更新
				else{
					//获取原来的标题信息用来匹配修改
					String title=ot_shdata.title;
					//存放EditText修改后的内容
					MyData tdata=new MyData();
					tdata.title=et_ot_title.getText().toString();
					tdata.user=et_ot_user.getText().toString();
					tdata.password=et_ot_password.getText().toString();
					tdata.note=et_ot_note.getText().toString();
					//打开数据库，修改数据
					ot_shDB=new MyDataDB();
					ot_shDB.initDB(OutputShowActivity.this);
					int j=ot_shDB.updateDB(tdata, title);
					if(j==0){
						//更新失败
						AlertDialog.Builder builder7  = new Builder(OutputShowActivity.this);
						builder7.setTitle("提示！" ) ;
						builder7.setMessage("更新失败！" ) ;
						builder7.setPositiveButton("确认" , new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								Intent toHome=new Intent(OutputShowActivity.this, MainActivity.class);
								startActivity(toHome);
								finish();  
							}  
						});
						builder7.setCancelable(false);
						builder7.show();
					}else{
						//更新成功
						AlertDialog.Builder builder8  = new Builder(OutputShowActivity.this);
						builder8.setTitle("修改成功！").setMessage("数据修改成功！");
						builder8.setPositiveButton("重新修改",new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}  
						});
						builder8.setNegativeButton("返回",new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								AlertDialog.Builder builder24  = new Builder(OutputShowActivity.this);
								builder24.setTitle("返回！").setMessage("返回查询页面/返回主页面");
								builder24.setPositiveButton("返回查询页面",new DialogInterface.OnClickListener() {  
									@Override  
									public void onClick(DialogInterface dialog, int which) {
										Intent toSelect=new Intent(OutputShowActivity.this, OutputSelectActivity.class);
										startActivity(toSelect);
										finish();
									}  
								});
								builder24.setNegativeButton("返回主页面",new DialogInterface.OnClickListener() {  
									@Override  
									public void onClick(DialogInterface dialog, int which) {
										Intent toHome=new Intent(OutputShowActivity.this, MainActivity.class);
										startActivity(toHome);
										finish();  
									}  
								});
								builder24.setCancelable(false);
								builder24.show();   
							}  
						});
						builder8.setCancelable(false);
						builder8.show();
					}
				}
			}

		});

		//删除数据
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder13  = new Builder(OutputShowActivity.this);
				builder13.setTitle("警告！").setMessage("是否确认删除这条数据，删除后不可恢复！");
				builder13.setPositiveButton("确认删除",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						//获取原来的标题内容，用来匹配删除
						String title=ot_shdata.title;
						//打开数据库，删除数据
						ot_shDB=new MyDataDB();
						ot_shDB.initDB(OutputShowActivity.this);
						int k=ot_shDB.deleteDB(title);
						//删除失败
						if(k==0){
							//弹出删除提示框
							AlertDialog.Builder builder14  = new Builder(OutputShowActivity.this);
							builder14.setTitle("提示！").setMessage("删除失败！");
							builder14.setPositiveButton("确认",new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									Intent toHome=new Intent(OutputShowActivity.this, MainActivity.class);
									startActivity(toHome);
									finish();
								}
							});
							builder14.setCancelable(false);
							builder14.show();
						}//删除成功
						else{
							//弹出删除提示框
							AlertDialog.Builder builder15  = new Builder(OutputShowActivity.this);
							builder15.setTitle("提示！").setMessage("删除成功！");
							builder15.setPositiveButton("确认",new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									Intent toHome=new Intent(OutputShowActivity.this, MainActivity.class);
									startActivity(toHome);
									finish();
								}
							});
							builder15.setCancelable(false);
							builder15.show();
						}
					}
				});
				builder13.setNegativeButton("不删除",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}  
				});
				builder13.setCancelable(false);
				builder13.show();
				
			}
		});
		
		
		//取消
		ot_sh_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder11  = new Builder(OutputShowActivity.this);
				builder11.setTitle("提示！").setMessage("是否确认返回!");
				builder11.setPositiveButton("是",new DialogInterface.OnClickListener()
				{

					@Override
					public   void  onClick(DialogInterface dialog,  int  which)
					{
						AlertDialog.Builder builder22  = new Builder(OutputShowActivity.this);
						builder22.setTitle("返回！").setMessage("返回查询页面/返回主页面");
						builder22.setPositiveButton("返回查询页面",new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								Intent toSelect=new Intent(OutputShowActivity.this, OutputSelectActivity.class);
								startActivity(toSelect);
								finish();
							}  
						});
						builder22.setNegativeButton("返回主页面",new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								Intent toHome=new Intent(OutputShowActivity.this, MainActivity.class);
								startActivity(toHome);
								finish();  
							}  
						});
						builder22.setCancelable(false);
						builder22.show(); 
					}
				});
				builder11.setNegativeButton("否",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.dismiss();
					}  
				});
				builder11.setCancelable(false);
				builder11.show();
			}
		});
		
	}
	
	//接收ListView单项item点击时传来的数据
	public MyData AcceptIntent() {
		MyData mdata=new MyData();
		Intent intent_accept = getIntent();           //创建一个接收意图
		Bundle bundle = intent_accept.getExtras();    //创建Bundle对象，用于接收OutputSelect的Intent数据
		mdata.title = bundle.getString("title");       //获取Intent的内容
		mdata.user = bundle.getString("user");       //获取Intent的内容
		mdata.password = bundle.getString("password");       //获取Intent的内容
		mdata.note = bundle.getString("note");       //获取Intent的内容
		return mdata;          
	}

	//自定义返回键功能，和取消键一样
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				AlertDialog.Builder builder12  = new Builder(OutputShowActivity.this);
				builder12.setTitle("提示！").setMessage("是否确认返回!");
				builder12.setPositiveButton("是",new DialogInterface.OnClickListener()
				{

					@Override
					public   void  onClick(DialogInterface dialog,  int  which)
					{
						AlertDialog.Builder builder23  = new Builder(OutputShowActivity.this);
						builder23.setTitle("返回！").setMessage("返回查询页面/返回主页面");
						builder23.setPositiveButton("返回查询页面",new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								Intent toSelect=new Intent(OutputShowActivity.this, OutputSelectActivity.class);
								startActivity(toSelect);
								finish();
							}  
						});
						builder23.setNegativeButton("返回主页面",new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								Intent toHome=new Intent(OutputShowActivity.this, MainActivity.class);
								startActivity(toHome);
								finish();  
							}  
						});
						builder23.setCancelable(false);
						builder23.show();   
					}
				});
				builder12.setNegativeButton("否",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.dismiss();
					}  
				});
				builder12.setCancelable(false);
				builder12.show();
			}
			return super.onKeyDown(keyCode, event);
		}
}
