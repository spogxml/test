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

//修改密码页面
public class AmendPasswordActivity extends Activity {
	private EditText et_firstpw;
	private EditText et_amendpw;
	private EditText et_confirmpw;
	private TextView tv_firstpw;
	private TextView tv_amendpw;
	private TextView tv_cofirmpw;
	private Button amend_confirm;
	private Button amend_cancel;
	private int j;

	private MyData adata;
	private MyDataDB adb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.amend_password);
		et_firstpw=(EditText) findViewById(R.id.et_firstpw);
		et_amendpw=(EditText) findViewById(R.id.et_amendpw);
		et_confirmpw=(EditText) findViewById(R.id.et_confirmpw);
		tv_firstpw=(TextView) findViewById(R.id.tv_firstpw);
		tv_amendpw=(TextView) findViewById(R.id.tv_amendpw);
		tv_cofirmpw=(TextView) findViewById(R.id.tv_cofirmpw);
		amend_confirm=(Button) findViewById(R.id.amend_confirm);
		amend_cancel=(Button) findViewById(R.id.amend_cancel);

		//根据主页面点击时传过来的数据确认是登陆密码还是加密密码
		j=this.AcceptIntent();
		//如果是登陆密码
		//修改布局页面信息
		if(j==1){
			tv_firstpw.setText("原登陆密码：");
			tv_amendpw.setText("新登陆密码：");
			tv_cofirmpw.setText("确认新登陆密码：");
		}	
		//如果是加密密码
		//修改布局页面信息
		else {
			tv_firstpw.setText("原加密密码：");
			tv_amendpw.setText("新加密密码：");
			tv_cofirmpw.setText("确认新加密密码：");
		}

		//确认按钮
		amend_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//判断输入框为空和修改密码不一致时的情况
				if(TextUtils.isEmpty(et_firstpw.getText())||TextUtils.isEmpty(et_amendpw.getText())||
						TextUtils.isEmpty(et_confirmpw.getText())){
					AlertDialog.Builder builder35  = new Builder(AmendPasswordActivity.this);
					builder35.setTitle("提示！" ) ;
					builder35.setMessage("请确认密码不能为空！" ) ;
					builder35.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
					{

						@Override
						public   void  onClick(DialogInterface dialog,  int  which)
						{
							dialog.dismiss();
						}
					});
					builder35.setCancelable(false);
					builder35.show(); 
				}else if(!et_amendpw.getText().toString().equals(et_confirmpw.getText().toString())){
					AlertDialog.Builder builder36  = new Builder(AmendPasswordActivity.this);
					builder36.setTitle("提示！" ) ;
					builder36.setMessage("请确认修改密码是否一致！" ) ;
					builder36.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
					{

						@Override
						public   void  onClick(DialogInterface dialog,  int  which)
						{
							dialog.dismiss();
						}
					});
					builder36.setCancelable(false);
					builder36.show();
				}else{
					//如果j==1则修改登陆密码
					if(j==1){
						//获取输入框的原密码
						String os=et_firstpw.getText().toString();
						//获取修改后的密码
						adata=new MyData();
						adata.lgpassword=et_amendpw.getText().toString();
						//查询原密码是否准确
						//打开数据库
						adb=new MyDataDB();
						adb.initDB(AmendPasswordActivity.this);
						//查询原密码
						int a=adb.queryLgPW(os);
						//如果查询成功，则进行修改
						if(a==1){
							int b=adb.updateDB(adata, os);
							//更新成功
							if(b==1){
								AlertDialog.Builder builder37  = new Builder(AmendPasswordActivity.this);
								builder37.setTitle("提示！" ) ;
								builder37.setMessage("修改密码成功！" ) ;
								builder37.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
								{

									@Override
									public   void  onClick(DialogInterface dialog,  int  which)
									{
										Intent toHome=new Intent(AmendPasswordActivity.this, MainActivity.class);
										startActivity(toHome);
										finish();  
									}
								});
								builder37.setCancelable(false);
								builder37.show();
							}//更新失败
							else{
								AlertDialog.Builder builder38  = new Builder(AmendPasswordActivity.this);
								builder38.setTitle("提示！" ) ;
								builder38.setMessage("修改密码失败！" ) ;
								builder38.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
								{

									@Override
									public   void  onClick(DialogInterface dialog,  int  which)
									{
										Intent toHome=new Intent(AmendPasswordActivity.this, MainActivity.class);
										startActivity(toHome);
										finish();  
									}
								});
								builder38.setCancelable(false);
								builder38.show();
							}
						}
						//如果查询失败
						else{
							AlertDialog.Builder builder39  = new Builder(AmendPasswordActivity.this);
							builder39.setTitle("提示！" ) ;
							builder39.setMessage("请确认原密码是否正确！" ) ;
							builder39.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
							{

								@Override
								public   void  onClick(DialogInterface dialog,  int  which)
								{
									dialog.dismiss();
								}
							});
							builder39.setCancelable(false);
							builder39.show();
						}
					}//如果j==2则修改加密密码
					else{
						//获取输入框的原密码
						String os=et_firstpw.getText().toString();
						//获取修改后的密码
						adata=new MyData();
						adata.itpassword=et_amendpw.getText().toString();
						//查询原密码是否准确
						//打开数据库
						adb=new MyDataDB();
						adb.initDB(AmendPasswordActivity.this);
						//查询原密码
						int c=adb.queryItPW(os);
						//如果查询成功，则进行修改
						if(c==1){
							int d=adb.updateDB(adata, os);
							//更新成功
							if(d==1){
								AlertDialog.Builder builder45  = new Builder(AmendPasswordActivity.this);
								builder45.setTitle("提示！" ) ;
								builder45.setMessage("修改密码成功！" ) ;
								builder45.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
								{

									@Override
									public   void  onClick(DialogInterface dialog,  int  which)
									{
										Intent toHome=new Intent(AmendPasswordActivity.this, MainActivity.class);
										startActivity(toHome);
										finish();  
									}
								});
								builder45.setCancelable(false);
								builder45.show();
							}//更新失败
							else{
								AlertDialog.Builder builder46  = new Builder(AmendPasswordActivity.this);
								builder46.setTitle("提示！" ) ;
								builder46.setMessage("修改密码失败！" ) ;
								builder46.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
								{

									@Override
									public   void  onClick(DialogInterface dialog,  int  which)
									{
										Intent toHome=new Intent(AmendPasswordActivity.this, MainActivity.class);
										startActivity(toHome);
										finish();  
									}
								});
								builder46.setCancelable(false);
								builder46.show();
							}
						}
						//如果查询失败
						else {
							AlertDialog.Builder builder47  = new Builder(AmendPasswordActivity.this);
							builder47.setTitle("提示！" ) ;
							builder47.setMessage("请确认原密码是否正确！" ) ;
							builder47.setPositiveButton("确认" ,new DialogInterface.OnClickListener()
							{

								@Override
								public   void  onClick(DialogInterface dialog,  int  which)
								{
									dialog.dismiss();
								}
							});
							builder47.setCancelable(false);
							builder47.show();
						}
					}
				}
			}
		});
		//取消按钮
		amend_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder40  = new Builder(AmendPasswordActivity.this);
				builder40.setTitle("返回主页面！").setMessage("是否返回主页面！");
				builder40.setPositiveButton("是",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						Intent toHome=new Intent(AmendPasswordActivity.this, MainActivity.class);
						adb.closeDB();
						startActivity(toHome);
						finish();
					}  
				});
				builder40.setNegativeButton("否", new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}  
				});
				builder40.setCancelable(false);
				builder40.show();
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
			AlertDialog.Builder builder41  = new Builder(AmendPasswordActivity.this);
			builder41.setTitle("返回主页面！").setMessage("是否返回主页面！");
			builder41.setPositiveButton("是",new DialogInterface.OnClickListener() {  
				@Override  
				public void onClick(DialogInterface dialog, int which) {
					Intent toHome=new Intent(AmendPasswordActivity.this, MainActivity.class);
					adb.closeDB();
					startActivity(toHome);
					finish();
				}  
			});
			builder41.setNegativeButton("否", new DialogInterface.OnClickListener() {  
				@Override  
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}  
			});
			builder41.setCancelable(false);
			builder41.show();
		}
		return super.onKeyDown(keyCode, event);

	}

}
