package com.example.demo4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {
	private RadioGroup rg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		rg=(RadioGroup)findViewById(R.id.radioGroup1);
		/*
		 * 实现RadioGroup的监听事件
		 */
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.radio0:
					Log.i("tag", "你当前是一个男孩");
					break;
				case R.id.radio1:
					Log.i("tag", "你当前是一个女孩");
					break;
				default:
					break;
				}
			}
		});
	}

}
