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
//			Toast.makeText(this, "�ļ��Ѿ�����", Toast.LENGTH_SHORT).show();
//		}
//		file.delete();
//		File file = this.getFilesDir();//���Ŀ¼�ǵ�ǰӦ�ó���Ĭ�ϵ����ݴ洢Ŀ¼
//		Log.i("info", file.toString());
//		File file = this.getCacheDir();//���Ŀ¼�ǵ�ǰӦ�ó���Ĭ�ϵĻ����ļ��Ĵ洢Ŀ¼
//		//��һЩ���Ƿǳ���Ҫ���ļ��ڴ˴�������ʹ��
//		//����ֻ����ڴ治���ʱ��ϵͳ���Զ�ȥɾ��APP��cacheĿ¼������
//		Log.i("info", file.toString());
//		// /data/data/<����>/app_imooc
//		File file=this.getDir("imooc", MODE_PRIVATE);
//		Log.i("info", file.toString());
//		this.getExternalFilesDir(type);
		//���Եõ��ⲿ�Ĵ洢λ�ã���λ�õ����ݸ����õ�ʹ����һ����
		//���appж���ˣ������������Ҳ���Զ������
		File file = this.getExternalCacheDir();
		Log.i("info", file.toString());
		//��������߲����ذ����ݷ���data/data/<����>����/mnt/sdcard/Android/data/<����>��ж�ز������
	}
}
