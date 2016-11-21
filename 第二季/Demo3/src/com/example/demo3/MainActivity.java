package com.example.demo3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{
	
	private GridView gridView;
	private List<Map<String,Object>> dataList;
	private int[] icon={R.drawable.address_book,R.drawable.calendar,R.drawable.camera,
			R.drawable.clock,R.drawable.games_control,R.drawable.messenger,
			R.drawable.ringtone,R.drawable.settings,R.drawable.speech_balloon,
			R.drawable.weather,R.drawable.world,R.drawable.youtube};
	private String[] iconName={"通讯录","日历","照相机","时钟","游戏","短信","铃声","设置","语音",
			"天气","浏览器","视频"};
	private SimpleAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		gridView=(GridView) findViewById(R.id.gridView);
		//1、准备数据源
		//2、新建适配器（SimpleAdapter）
		//3、GridView加载适配器
		//4、GridView配置事件监听器（OnItemClickListener）
		dataList=new ArrayList<Map<String,Object>>();
		adapter=new SimpleAdapter(this, getData(), R.layout.item, new String[]{"image","text"}, new int[]{R.id.image,R.id.text});
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this);
	}
	
	private List<Map<String,Object>> getData() {
		// TODO Auto-generated method stub
		for(int i=0;i<icon.length;i++){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("image", icon[i]);
		map.put("text", iconName[i]);
		dataList.add(map);
		}
		return dataList;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "我是"+iconName[position], Toast.LENGTH_SHORT).show();
	}

}
