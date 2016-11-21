package com.example.fragment_demo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button add;
	private Button reduce;
	private FragmentManager manager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		add=(Button) findViewById(R.id.button);
		reduce=(Button) findViewById(R.id.button2);
		add.setOnClickListener(this);
		reduce.setOnClickListener(this);
		manager=this.getFragmentManager();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		MyFragment frag;
		FragmentTransaction transaction;
		switch (v.getId()) {
		case R.id.button:
			frag=new MyFragment();
			transaction=manager.beginTransaction();
			transaction.add(R.id.fram, frag);
			transaction.commit();
			break;
		case R.id.button2:
			MyFragment frag2=(MyFragment) manager.findFragmentById(R.id.fram);
			transaction=manager.beginTransaction();
			transaction.remove(frag2);
			transaction.commit();
			break;

		}
		
	}

}
