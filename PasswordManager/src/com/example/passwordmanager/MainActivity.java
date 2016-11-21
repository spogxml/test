package com.example.passwordmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button input;
	private Button output;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		input=(Button) findViewById(R.id.input);
		output=(Button) findViewById(R.id.output);

		input.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent inpt_intent=new Intent(MainActivity.this, InputActivity.class);
				startActivity(inpt_intent);
				finish();
			}
		});
		output.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent otpt_intent=new Intent(MainActivity.this, OutputSelectActivity.class);
				startActivity(otpt_intent);
				finish();
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
