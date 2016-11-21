package com.example.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BC1 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String s=intent.getStringExtra("msg");
		System.out.println("receiver1收到消息："+s);
		//bundle方法
		Bundle bundle = new Bundle();
		bundle.putString("test", "广播处理的数据");
		setResultExtras(bundle);
	}
	
}
