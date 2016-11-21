package com.example.demo8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SActivity extends Activity {
	private Button bt;
	private String content="你好";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sactivity);
		/*
		 * 第二个页面什么时候给第一个页面回传数据
		 * 回传到第一个页面的实际上是一个Intent对象
		 */
		bt=(Button) findViewById(R.id.button1);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent data=new Intent();
				data.putExtra("data", content);
				setResult(2, data);
				//结束当前页面
				finish();
			}
		});
	}
	
	
}
