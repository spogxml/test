package com.example.broadcastreceiverdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	BC3 bc3 = new BC3();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// IntentFilter intentFilter = new IntentFilter("BC_One");
		// BC2 bc2=new BC2();
		// registerReceiver(bc2, intentFilter);
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.send1:// 发送一条普通广播
			Intent intent = new Intent();
			intent.putExtra("msg", "这是一条普通广播");
			intent.setAction("BC_One");
			sendBroadcast(intent);
			break;

		case R.id.send2:// 发送一条有序广播
			Intent intent2 = new Intent();
			intent2.putExtra("msg", "这是一条有序广播");
			intent2.setAction("BC_One");
			sendOrderedBroadcast(intent2, null);
			break;

		case R.id.send3:// 发送一条异步广播
			Intent intent3 = new Intent();
			intent3.putExtra("msg", "这是一条异步广播");
			intent3.setAction("BC_Three");
			sendStickyBroadcast(intent3);
			IntentFilter intentFilter = new IntentFilter("BC_Three");
			registerReceiver(bc3, intentFilter);
			break;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(bc3);
	}
}
