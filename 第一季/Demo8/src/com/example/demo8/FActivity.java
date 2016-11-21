package com.example.demo8;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FActivity extends Activity {
	private Button bt1;
	private Button bt2;
	private Context mContext;
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.factivity);
		/*
		 * 通过点击bt1实现页面之间的跳转
		 * 1.startActivity的方式来实现跳转
		 * 1>初始化Intent
		 */
		mContext=this;
		tv=(TextView) findViewById(R.id.textView1);
		bt1=(Button) findViewById(R.id.button1_first);
		bt2=(Button) findViewById(R.id.button2_second);
		
		//注册点击事件
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*
				 * 第一个参数：上下文对象this
				 * 第二个参数：目标文件
				 */
				Intent intent=new Intent(mContext, SActivity.class);
				startActivity(intent);
			}
		});
		/*
		 * 通过startActivityForResult
		 */
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(mContext, SActivity.class);
				/*
				 * 第一个参数是Intent对象
				 * 第二个参数是请求的一个标识
				 */
				startActivityForResult(intent, 1);
				
			}
		});		
	}
	
	/*
	 *  通过startActivityForResult跳转，接收返回数据的方法
	 *  requestCode:请求的标识
	 *  resultCode：第二个页面的返回的标识
	 *  data:第二个页面回传的数据
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1&&resultCode==2){
			String content=data.getStringExtra("data");
			tv.setText(content);
		}
	}
}
