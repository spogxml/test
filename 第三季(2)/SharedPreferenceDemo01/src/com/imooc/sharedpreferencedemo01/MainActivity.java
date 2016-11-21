package com.imooc.sharedpreferencedemo01;

import com.example.sharedpreferencedemo01.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.style.LineHeightSpan.WithDensity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText etusername, etuserpass;
	CheckBox chk;
	SharedPreferences pref;
	Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// // SharedPreferences
		// pref=PreferenceManager.getDefaultSharedPreferences(this);
		// SharedPreferences pref=getSharedPreferences("myPref", MODE_PRIVATE);
		// Editor editor=pref.edit();
		// editor.putString("name", "张三");
		// editor.putInt("age", 30);
		// editor.putLong("time", System.currentTimeMillis());
		// editor.putBoolean("default", true);
		// editor.commit();
		// editor.remove("default");
		// editor.commit();
		// System.out.println(pref.getString("name", ""));
		// System.out.println(pref.getInt("age", 0));

		etusername = (EditText) findViewById(R.id.etusername);
		etuserpass = (EditText) findViewById(R.id.etuserpass);
		chk = (CheckBox) findViewById(R.id.checksavename);
		pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
		editor = pref.edit();
		String name=pref.getString("username", "");
		if(name.equals("")){
			chk.setChecked(false);
		}else{
			chk.setChecked(true);
			etusername.setText(name);
		}
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnlogin:
			String name=etusername.getText().toString().trim();
			String pass=etuserpass.getText().toString().trim();
			if("admin".equals(name)&&"123456".equals(pass)){
				if(chk.isChecked()){
					editor.putString("username", name);
					editor.commit();
					
				}else{
					editor.remove("username");
					editor.commit();
				}
				Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(MainActivity.this, "禁止登陆", Toast.LENGTH_SHORT).show();
			}
			break;
			
		default:
			break;

		}
		
	}

}
