package com.example.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BC1 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String s=intent.getStringExtra("msg");
		System.out.println("receiver1�յ���Ϣ��"+s);
		//bundle����
		Bundle bundle = new Bundle();
		bundle.putString("test", "�㲥���������");
		setResultExtras(bundle);
	}
	
}
