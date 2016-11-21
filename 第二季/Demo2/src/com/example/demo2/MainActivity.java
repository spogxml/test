package com.example.demo2;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class MainActivity extends Activity {
	
	private TimePicker timePicker;
	private DatePicker datePicker;
	private Calendar cal;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//获取日历的一个对象
		cal=Calendar.getInstance();
		//获取年月日时分秒信息
		year=cal.get(Calendar.YEAR);
		month=cal.get(Calendar.MONTH)+1;
		day=cal.get(Calendar.DAY_OF_MONTH);
		hour=cal.get(Calendar.HOUR_OF_DAY);
		minute=cal.get(Calendar.MINUTE);
		setTitle(year+"-"+month+"-"+day+"-"+hour+":"+minute);
		datePicker=(DatePicker) findViewById(R.id.datePicker);
		timePicker=(TimePicker) findViewById(R.id.timePicker);
		
		//datePicker初始化
		datePicker.init(year, cal.get(Calendar.MONTH), day,new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
			}
		});
		//timePicker默认就初始化为当前时间
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				setTitle(hourOfDay+":"+minute);
			}
		});
		
		//DatePickerDialog
		new DatePickerDialog(this, new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
			}
		}, year, cal.get(Calendar.MONTH), day).show();
		
		//TimePickerDialog
//		new TimePickerDialog(this,new OnTimeSetListener() {
//			
//			@Override
//			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//				// TODO Auto-generated method stub
//				setTitle(hourOfDay+":"+minute);
//			}
//		}, hour, minute, true).show();
	}

}
