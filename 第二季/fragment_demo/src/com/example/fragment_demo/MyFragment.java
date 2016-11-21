package com.example.fragment_demo;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			View view=inflater.inflate(R.layout.fragment, container, false);
			return view;
			
//			View view=inflater.inflate(R.layout.fragment, container, false);
//			TextView tv=(TextView) view.findViewById(R.id.textView);
//			tv.setText("这是Fragment");
//			return view;
		}
}
