package com.example.filedemo4;

import java.io.File;
import java.io.IOException;

import com.example.sqlitedemo4.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		File file=new File("/mnt/sdcard/test");
//		if(!file.exists()){
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else{
//			Toast.makeText(this, "文件已经存在", Toast.LENGTH_SHORT).show();
//		}
//		file.delete();
//		File file = this.getFilesDir();//这个目录是当前应用程序默认的数据存储目录
//		Log.i("info", file.toString());
//		File file = this.getCacheDir();//这个目录是当前应用程序默认的缓存文件的存储目录
//		//把一些不是非常重要的文件在此处创建，使用
//		//如果手机的内存不足的时候，系统会自动去删除APP的cache目录的数据
//		Log.i("info", file.toString());
//		// /data/data/<包名>/app_imooc
//		File file=this.getDir("imooc", MODE_PRIVATE);
//		Log.i("info", file.toString());
//		this.getExternalFilesDir(type);
		//可以得到外部的存储位置，该位置的数据跟内置的使用是一样的
		//如果app卸载了，这里面的数据也会自动清除掉
		File file = this.getExternalCacheDir();
		Log.i("info", file.toString());
		//如果开发者不遵守把数据放入data/data/<包名>或者/mnt/sdcard/Android/data/<包名>，卸载不会清除
	}
}
