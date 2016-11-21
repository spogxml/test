package com.example.helloworld;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity{

	//	private Button loginButton;
	private Button bt1;
	private Button bt2;
	//	private ImageButton imgBt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//将布局xml文件引入到activity当中
		setContentView(R.layout.activity_main);

		//		
		//		/*
		//		 * 1.初始化当前所需要的控件,如何初始化一个控件？
		//		 * findViewById返回的是---返回的是一个View的对象，
		//		 * findViewById是如何查找到对应view的id
		//		 * 
		//		 * 2.设置Button的监听器，通过监听器实现我们点击Button要操作的事情
		//		 */
		//		loginButton=(Button)findViewById(R.id.button1);
		//		/*
		//		 * 1.监听事件通过第一种方式实现（匿名内部类）
		//		 */
		//				loginButton.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						//在当前onClick方法中监听点击Button的动作
		//						System.out.println("我的Button被点击了");
		//					}
		//				});

		bt1=(Button)findViewById(R.id.button1);
		bt2=(Button)findViewById(R.id.button2);

		/*
		 * 点击事件外部类的写法和作用
		 */
		bt1.setOnClickListener(new MyOnClickListener());
		//			@Override
		//			public void onClick(View v) {
		//				//调用父类的onClick
		//				super.onClick(v);
		//				Toast.makeText(MainActivity.this, "bt1要执行的逻辑", 1).show();
		//			}
		//		});
		bt2.setOnClickListener(new MyOnClickListener());
		//			@Override
		//			public void onClick(View v) {
		//				//调用父类的onClick
		//				super.onClick(v);
		//				Toast.makeText(MainActivity.this, "bt2要执行的逻辑", 1).show();
		//			}
		//		});

		/*
		 * 3.通过实现一个接口的方式实现监听事件
		 */
		//		imgBt=(ImageButton) findViewById(R.id.imageButton1);
		//		imgBt.setOnClickListener(this);

	}
	//@Override
	//public void onClick(View v) {
	//	// TODO Auto-generated method stub
	//	Log.i("tag", "第三种方式实现");
	//}

}
/*
 * OnClickListener是一个接口
 */
class MyOnClickListener implements OnClickListener{



	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.button1:
			Log.i("tag", "父类的onClick事件");
			//让所有使用当前外部类的点击事件的按钮都要做出一个动作，改变button本身的透明度
			v.setAlpha(0.5f);
			break;

		case R.id.button2: 
			Log.i("tag", "父类的onClick事件");
			//让所有使用当前外部类的点击事件的按钮都要做出一个动作，改变button本身的透明度
			v.setAlpha(0.5f);
			break;

		}

	}

}