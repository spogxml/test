package com.imooc.android_gallery;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter extends BaseAdapter{

	private int[]res;
	private Context context;
	public ImageAdapter(int []res,Context context)
	{
		this.res=res;
		this.context=context;
	}
	
	//返回数据源的数量
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return res[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.i("Main", "position="+position+"res的角标="+position%res.length);
		ImageView image=new ImageView(context);
		image.setBackgroundResource(res[position%res.length]);
        image.setLayoutParams(new Gallery.LayoutParams(200, 150));
        image.setScaleType(ScaleType.FIT_XY);
		return image;
	}



}
