package com.example.demo2;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends Activity{
	private ToggleButton tb;
	private ImageView img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化控件
		tb=(ToggleButton) findViewById(R.id.toggleButton1);
		img=(ImageView) findViewById(R.id.imageView1);
		
		/*
		 * 给当前的tb设置监听器
		 */
		tb.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				// TODO Auto-generated method stub
				img.setBackgroundResource(isChecked?R.drawable.on:R.drawable.off);
			}
			
		});
		
	}
	
}
