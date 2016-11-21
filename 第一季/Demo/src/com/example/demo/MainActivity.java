package com.example.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends Activity {
	private AutoCompleteTextView acTextView;
	//3
	private String[] res={"beijing1","beijing2","beijing3","shanghai1","shanghai2"};
	
	private MultiAutoCompleteTextView macTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*
		 * 第一步：初始化控件
		 * 第二步：需要一个适配器
		 * 第三步：初始化数据源---这数据源去匹配文本框中输入的内容
		 * 第四步：将adapter与当前AutoCompleteTextView绑定
		 */
		//1
		acTextView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		//2
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,res);
		//4
		acTextView.setAdapter(adapter);
	
		/*
		 * 第一步：初始化控件
		 * 第二步：需要一个适配器
		 * 第三步：初始化数据源---这数据源去匹配文本框中输入的内容
		 * 第四步：将adapter与当前AutoCompleteTextView绑定
		 * 第五步：设置分隔符
		 */
		//1
		macTextView=(MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
		//4
		macTextView.setAdapter(adapter);
		//5设置以逗号为分隔符为结束的符号
		macTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	
	}

}
