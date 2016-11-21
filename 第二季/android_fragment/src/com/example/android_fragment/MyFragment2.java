package com.example.android_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyFragment2 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//layout布局文件转换成view
		/**
		 * resource:Fragment需要加载的布局文件
		 * root:加载layout的父ViewGroup
		 * attactToRoot:false,不返回ViewGroup
		 */
		View view=inflater.inflate(R.layout.fragment, container, false);
		TextView text=(TextView) view.findViewById(R.id.text);
		Button button=(Button) view.findViewById(R.id.button);
		text.setText("动态加载Fragment");
		return view;
	}
}
