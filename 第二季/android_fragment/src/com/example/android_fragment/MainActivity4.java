package com.example.android_fragment;

import com.example.android_fragment.MyFragment5.MyListener;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity4 extends Activity implements MyListener {

	private EditText editext;
	private Button	send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main4);
		editext=(EditText) findViewById(R.id.editText);
		send=(Button) findViewById(R.id.button);
		
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String text = editext.getText().toString();
				MyFragment5 fragment5=new MyFragment5();
				Bundle bundle=new Bundle();
				bundle.putString("name", text);
				fragment5.setArguments(bundle);
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
				beginTransaction.replace(R.id.layout, fragment5, "fragment5");
				beginTransaction.commit();
				Toast.makeText(MainActivity4.this, "向fragment发送数据："+text, Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	@Override
	public void thank(String code) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "已成功接收到"+code+"，客气了！", Toast.LENGTH_SHORT).show();
	}
	
}
