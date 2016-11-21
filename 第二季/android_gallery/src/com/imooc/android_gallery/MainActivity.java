package com.imooc.android_gallery;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ViewSwitcher.ViewFactory;
import android.os.Build;

public class MainActivity extends Activity implements OnItemSelectedListener,ViewFactory{

	// 准备数据源
	private int[] res = { R.drawable.item1, R.drawable.item2, R.drawable.item3,
			R.drawable.item4, R.drawable.item5, R.drawable.item6,
			R.drawable.item7, R.drawable.item8, R.drawable.item9,
			R.drawable.item10, R.drawable.item11, R.drawable.item12 };

	private ImageAdapter adapter;
	private Gallery gallery;
	private ImageSwitcher is;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gallery = (Gallery) findViewById(R.id.gallery);
		is=(ImageSwitcher) findViewById(R.id.is);
		// gallery加载适配器
		adapter = new ImageAdapter(res, this);
		gallery.setAdapter(adapter);
        gallery.setOnItemSelectedListener(this);
        is.setFactory(this);
        is.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
	    is.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
	    
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		//image.setBackgroundResource(res[position%res.length]);
		is.setBackgroundResource(res[position%res.length]);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		ImageView image=new ImageView(this);
		image.setScaleType(ScaleType.FIT_CENTER);
		
		return image;
	}

}
