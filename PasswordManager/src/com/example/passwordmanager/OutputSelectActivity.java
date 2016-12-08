package com.example.passwordmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.passwordmanager.dboperate.MyData;
import com.example.passwordmanager.dboperate.MyDataDB;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class OutputSelectActivity extends Activity {
	//初始化控件
	private EditText et_select;
	private ListView lv_result;
	private Button ot_confirm;
	private Button ot_cancel;
	//key
	private String okey=null;
	//存放数据的中介
	private MyData ot_data;
	//数据库类
	private MyDataDB otDB;
	//创建一个简单适配器
	private SimpleAdapter simp_adapter;
	//创建一个集合列表
	private List<Map<String,String>> arr_outlist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.output_select);
		et_select=(EditText) findViewById(R.id.et_select);
		lv_result=(ListView) findViewById(R.id.lv_result);
		ot_confirm=(Button) findViewById(R.id.ot_confirm);
		ot_cancel=(Button) findViewById(R.id.ot_cancel);
		//获取key
		okey=this.AcceptIntent();
		//确认按钮
		ot_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				arr_outlist= new ArrayList<Map<String,String>>();
				//如果查询框为空，则显示所有数据
				if(TextUtils.isEmpty(et_select.getText())){
					//打开数据库，查询出数据放到集合列表当中
					otDB=new MyDataDB();
					otDB.initDB(OutputSelectActivity.this);
					arr_outlist=otDB.queryDB(et_select.getText().toString(),okey);
					//如果没有存储过数据
					if(arr_outlist==null){
						AlertDialog.Builder builder17  = new Builder(OutputSelectActivity.this);
						builder17.setTitle("提示！").setMessage("您还没有存入过数据");
						builder17.setPositiveButton("确认",new DialogInterface.OnClickListener()
						{

							@Override
							public   void  onClick(DialogInterface dialog,  int  which)
							{
								dialog.dismiss();
							}
						});
						builder17.setCancelable(false);
						builder17.show();
					}else {
						//适配器加载数据源
						simp_adapter=new SimpleAdapter(OutputSelectActivity.this, arr_outlist, R.layout.item,
								new String[]{"title","note"}, new int[]{R.id.it_title,R.id.it_note});
						//视图加载适配器
						lv_result.setAdapter(simp_adapter);
						lv_result.setVisibility(View.VISIBLE);
						//查询出结果时弹出确认框
						AlertDialog.Builder builder20  = new Builder(OutputSelectActivity.this);
						builder20.setTitle("提示！").setMessage("查询成功！");
						builder20.setPositiveButton("确认",new DialogInterface.OnClickListener()
						{

							@Override
							public   void  onClick(DialogInterface dialog,  int  which)
							{
								dialog.dismiss();
							}
						});
						builder20.setCancelable(false);
						builder20.show();
						//实现点击视图单项事件
						lv_result.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								Map<String, String> map =new HashMap<String, String>();
								map= arr_outlist.get(position);
								Intent intent=new Intent(OutputSelectActivity.this,OutputShowActivity.class);
								Bundle bundle=new Bundle();
								bundle.putString("title", map.get("title"));
								bundle.putString("user", map.get("user"));
								bundle.putString("password", map.get("password"));
								bundle.putString("note", map.get("note"));
								bundle.putString("key", okey);
								intent.putExtras(bundle);
								startActivity(intent);
								finish();
							}
						});
					}
				}//如果查询框不为空，则显示匹配到的数据
				else {

					//打开数据库，查询出数据放到集合列表当中
					otDB=new MyDataDB();
					otDB.initDB(OutputSelectActivity.this);
					arr_outlist=otDB.queryDB(et_select.getText().toString(),okey);
					//如果没有查询到数据
					if(arr_outlist==null){
						AlertDialog.Builder builder18  = new Builder(OutputSelectActivity.this);
						builder18.setTitle("提示！").setMessage("没有查询到当前数据，请确认输入信息");
						builder18.setPositiveButton("确认",new DialogInterface.OnClickListener()
						{

							@Override
							public   void  onClick(DialogInterface dialog,  int  which)
							{
								dialog.dismiss();
							}
						});
						builder18.setCancelable(false);
						builder18.show();
					}else {
						//适配器加载数据源
						simp_adapter=new SimpleAdapter(OutputSelectActivity.this, arr_outlist, R.layout.item,
								new String[]{"title","note"}, new int[]{R.id.it_title,R.id.it_note});
						//视图加载适配器
						lv_result.setAdapter(simp_adapter);
						lv_result.setVisibility(View.VISIBLE);
						//查询出结果时弹出确认框
						AlertDialog.Builder builder21  = new Builder(OutputSelectActivity.this);
						builder21.setTitle("提示！").setMessage("查询成功！");
						builder21.setPositiveButton("确认",new DialogInterface.OnClickListener()
						{

							@Override
							public   void  onClick(DialogInterface dialog,  int  which)
							{
								dialog.dismiss();
							}
						});
						builder21.setCancelable(false);
						builder21.show();
						//实现点击视图单项事件
						lv_result.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								Map<String, String> map =new HashMap<String, String>();
								map= arr_outlist.get(position);
								Intent intent=new Intent(OutputSelectActivity.this,OutputShowActivity.class);
								Bundle bundle=new Bundle();
								bundle.putString("title", map.get("title"));
								bundle.putString("user", map.get("user"));
								bundle.putString("password", map.get("password"));
								bundle.putString("note", map.get("note"));
								bundle.putString("key", okey);
								intent.putExtras(bundle);
								startActivity(intent);
								finish();
							}
						});
					}
				}

			}
		});
		//取消按钮
		ot_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder9  = new Builder(OutputSelectActivity.this);
				builder9.setTitle("提示！").setMessage("是否确认返回主页面!");
				builder9.setPositiveButton("是",new DialogInterface.OnClickListener()
				{

					@Override
					public   void  onClick(DialogInterface dialog,  int  which)
					{
						Intent toHome=new Intent(OutputSelectActivity.this, MainActivity.class);
						startActivity(toHome);
						finish();  
					}
				});
				builder9.setNegativeButton("否",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();
					}  
				});
				builder9.setCancelable(false);
				builder9.show();
			}
		});
	}

	//接收输入密码页面点击时传来的数据
	public String AcceptIntent() {
		MyData mdata=new MyData();
		Intent intent_accept = getIntent();           //创建一个接收意图
		Bundle bundle = intent_accept.getExtras();    //创建Bundle对象，用于接收主页面的Intent数据
		String s=bundle.getString("key");
		return s;
	}
	//自定义返回键功能，和取消键一样
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder builder10  = new Builder(OutputSelectActivity.this);
			builder10.setTitle("提示！").setMessage("是否确认返回主页面!");
			builder10.setPositiveButton("是",new DialogInterface.OnClickListener()
			{

				@Override
				public   void  onClick(DialogInterface dialog,  int  which)
				{
					Intent toHome=new Intent(OutputSelectActivity.this, MainActivity.class);
					startActivity(toHome);
					finish();  
				}
			});
			builder10.setNegativeButton("否",new DialogInterface.OnClickListener() {  
				@Override  
				public void onClick(DialogInterface dialog, int which) {

					dialog.dismiss();
				}  
			});
			builder10.setCancelable(false);
			builder10.show();
		}
		return super.onKeyDown(keyCode, event);
	}
}
