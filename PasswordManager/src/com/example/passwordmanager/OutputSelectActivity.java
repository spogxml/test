package com.example.passwordmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class OutputSelectActivity extends Activity {
	//��ʼ���ؼ�
	private EditText et_select;
	private ListView lv_result;
	private Button ot_confirm;
	private Button ot_cancel;

	//������ݵ��н�
	private MyData ot_data;
	//���ݿ���
	private MyDataDB otDB;
	//����һ����������
	private SimpleAdapter simp_adapter;
	//����һ�������б�
	private List<Map<String,String>> arr_outlist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.output_select);
		et_select=(EditText) findViewById(R.id.et_select);
		lv_result=(ListView) findViewById(R.id.lv_result);
		ot_confirm=(Button) findViewById(R.id.ot_confirm);
		ot_cancel=(Button) findViewById(R.id.ot_cancel);

		//ȷ�ϰ�ť
		ot_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				arr_outlist= new ArrayList<Map<String,String>>();
				//�����ѯ��Ϊ�գ�����ʾ��������
				if(TextUtils.isEmpty(et_select.getText())){
					//�����ݿ⣬��ѯ�����ݷŵ������б���
					otDB=new MyDataDB();
					otDB.initDB(OutputSelectActivity.this);
					arr_outlist=otDB.queryDB(et_select.getText().toString());
					//���û�д洢������
					if(arr_outlist==null){
						AlertDialog.Builder builder17  = new Builder(OutputSelectActivity.this);
						builder17.setTitle("��ʾ��").setMessage("����û�д��������");
						builder17.setPositiveButton("ȷ��",new DialogInterface.OnClickListener()
						{

							@Override
							public   void  onClick(DialogInterface dialog,  int  which)
							{
								dialog.dismiss();
							}
						});
						builder17.setCancelable(false);
						builder17.show();
					}else {
						//��������������Դ
						simp_adapter=new SimpleAdapter(OutputSelectActivity.this, arr_outlist, R.layout.item,
								new String[]{"title","note"}, new int[]{R.id.it_title,R.id.it_note});
						//��ͼ����������
						lv_result.setAdapter(simp_adapter);
						lv_result.setVisibility(View.VISIBLE);
						//ʵ�ֵ����ͼ�����¼�
						lv_result.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								Map<String, String> map =new HashMap<String, String>();
								map= arr_outlist.get(position);
								Intent intent=new Intent(OutputSelectActivity.this,OutputShowActivity.class);
								Bundle bundle=new Bundle();
								bundle.putString("title", map.get("title"));
								bundle.putString("user", map.get("user"));
								bundle.putString("password", map.get("password"));
								bundle.putString("note", map.get("note"));
								intent.putExtras(bundle);
								startActivity(intent);
								finish();
							}
						});
					}
				}//�����ѯ��Ϊ�գ�����ʾƥ�䵽������
				else {

					//�����ݿ⣬��ѯ�����ݷŵ������б���
					otDB=new MyDataDB();
					otDB.initDB(OutputSelectActivity.this);
					arr_outlist=otDB.queryDB(et_select.getText().toString());
					//���û�в�ѯ������
					if(arr_outlist==null){
						AlertDialog.Builder builder18  = new Builder(OutputSelectActivity.this);
						builder18.setTitle("��ʾ��").setMessage("û�в�ѯ����ǰ���ݣ���ȷ��������Ϣ");
						builder18.setPositiveButton("ȷ��",new DialogInterface.OnClickListener()
						{

							@Override
							public   void  onClick(DialogInterface dialog,  int  which)
							{
								dialog.dismiss();
							}
						});
						builder18.setCancelable(false);
						builder18.show();
					}else {
						//��������������Դ
						simp_adapter=new SimpleAdapter(OutputSelectActivity.this, arr_outlist, R.layout.item,
								new String[]{"title","note"}, new int[]{R.id.it_title,R.id.it_note});
						//��ͼ����������
						lv_result.setAdapter(simp_adapter);
						lv_result.setVisibility(View.VISIBLE);
						//ʵ�ֵ����ͼ�����¼�
						lv_result.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								Map<String, String> map =new HashMap<String, String>();
								map= arr_outlist.get(position);
								Intent intent=new Intent(OutputSelectActivity.this,OutputShowActivity.class);
								Bundle bundle=new Bundle();
								bundle.putString("title", map.get("title"));
								bundle.putString("user", map.get("user"));
								bundle.putString("password", map.get("password"));
								bundle.putString("note", map.get("note"));
								intent.putExtras(bundle);
								startActivity(intent);
								finish();
							}
						});
					}
				}

			}
		});
		//ȡ����ť
		ot_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder9  = new Builder(OutputSelectActivity.this);
				builder9.setTitle("��ʾ��").setMessage("�Ƿ�ȷ�Ϸ�����ҳ��!");
				builder9.setPositiveButton("��",new DialogInterface.OnClickListener()
				{

					@Override
					public   void  onClick(DialogInterface dialog,  int  which)
					{
						Intent toHome=new Intent(OutputSelectActivity.this, MainActivity.class);
						startActivity(toHome);
						finish();  
					}
				});
				builder9.setNegativeButton("��",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();
					}  
				});
				builder9.setCancelable(false);
				builder9.show();
			}
		});
	}
	//�Զ��巵�ؼ����ܣ���ȡ����һ��
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder builder10  = new Builder(OutputSelectActivity.this);
			builder10.setTitle("��ʾ��").setMessage("�Ƿ�ȷ�Ϸ�����ҳ��!");
			builder10.setPositiveButton("��",new DialogInterface.OnClickListener()
			{

				@Override
				public   void  onClick(DialogInterface dialog,  int  which)
				{
					Intent toHome=new Intent(OutputSelectActivity.this, MainActivity.class);
					startActivity(toHome);
					finish();  
				}
			});
			builder10.setNegativeButton("��",new DialogInterface.OnClickListener() {  
				@Override  
				public void onClick(DialogInterface dialog, int which) {

					dialog.dismiss();
				}  
			});
			builder10.setCancelable(false);
			builder10.show();
		}
		return super.onKeyDown(keyCode, event);
	}
}
