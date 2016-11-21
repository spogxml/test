package com.example.demo3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {
	private CheckBox checkBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化checkBox
		checkBox=(CheckBox) findViewById(R.id.checkBox1);
		
		//通过设置checkbox的监听事件来对checkbox是不是被选中监听
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				//通过onCheckedChanged方法来监听当前的checkbox是否被选中
				Log.i("tag", isChecked+"");
				if(isChecked){
					//获得checkbox的文本内容
					String text=checkBox.getText().toString();
					Log.i("tag", text);
				}
				
			}
		});
	}
	
}
