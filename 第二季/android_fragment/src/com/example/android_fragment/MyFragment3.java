package com.example.android_fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyFragment3 extends Fragment {
	
	private TextView tv;
	/**
	 * 每次创建都会绘制Fragment的View组件时调用该方法,相当于fragment绑定一个布局，布局文件转换成view返回回来；
	 * onCreate()->onCreateView()->onActivityCreated()
	 */
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
		View view=inflater.inflate(R.layout.fragment2, container, false);
		TextView tv=(TextView) view.findViewById(R.id.text);
		tv.setText("第一个Fragment");
		Log.i("Main", "Fragment1--onCreateView()");
		return view;
	}
	
	/**
	 * 当Fragment被添加到Activity时候会回调这个方法，并且只调用一次
	 */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.i("Main", "Fragment1--onAttach()");
	}
	
	/**
	 * 在fragment创建的时候回调这个方法；只会调用一次；
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("Main", "Fragment1--onCreate()");
	}
	
	/**
	 * fragment所在的activity启动完成后调用；
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i("Main", "Fragment1--onActivityCreated()");
	}
	
	/**
	 * 启动Fragment时会被回调
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("Main", "Fragment1--onStart()");
	}
	
	/**
	 * 调用onStart()方法后面一定会调用这个方法，恢复fragment时回调
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("Main", "Fragment1--onResume()");
	}
	
	/**
	 * 暂停Fragment
	 */
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("Main", "Fragment1--onPause()");
	}
	
	/**
	 * 停止Fragment
	 */
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("Main", "Fragment1--onStop()");
	}
	
	/**
	 * 销毁Fragment所包含的View组件，与onCreateView相对应
	 */
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.i("Main", "Fragment1--onDestroyView()");
	}
	
	/**
	 * 销毁Fragment时会被回调
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("Main", "Fragment1--onDestroy()");
	}
	
	/**
	 * Fragment从Activity中删除时回调这个方法，并且只调用一次
	 */
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		Log.i("Main", "Fragment1--onDetach()");
	}
}
