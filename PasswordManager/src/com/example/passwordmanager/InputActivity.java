package com.example.passwordmanager;

import java.util.ArrayList;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends Activity {
	//��ʼ���������ݽ���Ŀؼ�
	private EditText ed_title;
	private EditText ed_user;
	private EditText ed_password;
	private EditText ed_note;
	private Button in_confirm;
	private Button in_cancel;
	//������ݵ��н�
	private MyData mdata;
	//���ݿ���
	private MyDataDB iDB;
	//�ж�����Ƿ�ɹ�
	private int i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input);
		ed_title=(EditText) findViewById(R.id.ed_title);
		ed_user=(EditText) findViewById(R.id.ed_user);
		ed_password=(EditText) findViewById(R.id.ed_password);
		ed_note=(EditText) findViewById(R.id.ed_note);
		in_confirm=(Button) findViewById(R.id.in_confirm);
		in_cancel=(Button) findViewById(R.id.in_cancel);
		//Ĭ�ϱ����ȡ����
		ed_title.requestFocus();
		//ȷ�ϰ�ť�ĵ���¼�
		in_confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//����������Ϊ�գ����޷����
				if(TextUtils.isEmpty(ed_title.getText())||TextUtils.isEmpty(ed_user.getText())
						||TextUtils.isEmpty(ed_password.getText())||TextUtils.isEmpty(ed_note.getText())){
					AlertDialog.Builder builder1  = new Builder(InputActivity.this);
					builder1.setTitle("��ʾ��" ) ;
					builder1.setMessage("��ȷ�������������ݲ���Ϊ�գ�" ) ;
					builder1.setPositiveButton("��" ,new DialogInterface.OnClickListener()
					{

						@Override
						public   void  onClick(DialogInterface dialog,  int  which)
						{
							dialog.dismiss();
						}
					});
					builder1.setCancelable(false);
					builder1.show(); 
				}//�������
				else{
					mdata=new MyData();//��ʼ������ȡ���������
					mdata.title=ed_title.getText().toString();
					mdata.user=ed_user.getText().toString();
					mdata.password=ed_password.getText().toString();
					mdata.note=ed_note.getText().toString();

					//��������ʱ�Ȳ�ѯ�ж����ݿ⵱���Ƿ��к͵�ǰ���ݱ���һ�������ݣ����������ʾ���ܲ��룬û�������
					//�����ݿ⣬��ѯ����
					iDB=new MyDataDB();
					iDB.initDB(InputActivity.this);
					List<Map<String,String>> arr_inlist=new ArrayList<Map<String,String>>();
					arr_inlist=iDB.queryDB(mdata.title);
					//����к͵�ǰ����һ����������ܲ���
					if(arr_inlist!=null){
						AlertDialog.Builder builder19  = new Builder(InputActivity.this);
						builder19.setTitle("��ʾ��").setMessage("��ǰ���ݱ������������Ѵ��ڣ����޸ı��⣡");
						builder19.setPositiveButton("ȷ��",new DialogInterface.OnClickListener()
						{

							@Override
							public   void  onClick(DialogInterface dialog,  int  which)
							{
								dialog.dismiss();
							}
						});
						builder19.setCancelable(false);
						builder19.show();
						
					}//û������Բ���
					else{
						//�����ݿ⣬��������
						iDB=new MyDataDB();
						iDB.initDB(InputActivity.this);
						i=iDB.insertDB(InputActivity.this, mdata);
						//���ʧ��ʱ�����Ի���
						if(i==0){
							AlertDialog.Builder builder2  = new Builder(InputActivity.this);
							builder2.setTitle("��ʾ��" ) ;
							builder2.setMessage("���ʧ�ܣ���ȷ�Ͽռ��Ƿ��㹻��" ) ;
							builder2.setPositiveButton("��" , new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									Intent toHome=new Intent(InputActivity.this, MainActivity.class);
									startActivity(toHome);
									finish();  
								}  
							});
							builder2.setCancelable(false);
							builder2.show(); 
						}
						//��ӳɹ�ʱʱ�����Ի���
						else {
							AlertDialog.Builder builder3  = new Builder(InputActivity.this);
							builder3.setTitle("��ӳɹ���").setMessage("������ӳɹ���");
							builder3.setPositiveButton("�������",new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									ed_title.setText("");
									ed_user.setText("");
									ed_password.setText("");
									ed_note.setText("");
									dialog.dismiss();
								}  
							});
							builder3.setNegativeButton("������ҳ��",new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									Intent toHome=new Intent(InputActivity.this, MainActivity.class);
									startActivity(toHome);
									finish();  
								}  
							});
							builder3.setCancelable(false);
							builder3.show();
						}
					}
				}
			}
		});
		//ȡ����ť�ĵ���¼�
		in_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//�����������ȫΪ��ʱ��ֱ�ӷ�����ҳ��
				if(TextUtils.isEmpty(ed_title.getText())&&TextUtils.isEmpty(ed_user.getText())
						&&TextUtils.isEmpty(ed_password.getText())&&TextUtils.isEmpty(ed_note.getText())){
					Intent toHome=new Intent(InputActivity.this, MainActivity.class);
					startActivity(toHome);
					finish();
				}else{
					//����һ�������Ϊ��ʱ��������ʾ��
					AlertDialog.Builder builder4  = new Builder(InputActivity.this);
					builder4.setTitle("��ʾ��").setMessage("����û��������ɣ��Ƿ�ȷ�Ϸ�����ҳ��");
					builder4.setPositiveButton("�������",new DialogInterface.OnClickListener()
					{

						@Override
						public   void  onClick(DialogInterface dialog,  int  which)
						{
							dialog.dismiss();
						}
					});
					builder4.setNegativeButton("������ҳ��",new DialogInterface.OnClickListener() {  
						@Override  
						public void onClick(DialogInterface dialog, int which) {
							Intent toHome=new Intent(InputActivity.this, MainActivity.class);
							startActivity(toHome);
							finish();  
						}  
					});
					builder4.setCancelable(false);
					builder4.show();
				}
			}
		});
	}

	//�Զ��巵�ؼ����ܣ���ȡ����һ��
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//�����������ȫΪ��ʱ��ֱ�ӷ�����ҳ��
			if(TextUtils.isEmpty(ed_title.getText())&&TextUtils.isEmpty(ed_user.getText())
					&&TextUtils.isEmpty(ed_password.getText())&&TextUtils.isEmpty(ed_note.getText())){
				Intent toHome=new Intent(InputActivity.this, MainActivity.class);
				startActivity(toHome);
				finish();
			}else{
				//����һ�������Ϊ��ʱ��������ʾ��
				AlertDialog.Builder builder5  = new Builder(InputActivity.this);
				builder5.setTitle("��ʾ��").setMessage("����û��������ɣ��Ƿ�ȷ�Ϸ�����ҳ��");
				builder5.setPositiveButton("�������",new DialogInterface.OnClickListener()
				{

					@Override
					public   void  onClick(DialogInterface dialog,  int  which)
					{
						dialog.dismiss();
					}
				});
				builder5.setNegativeButton("������ҳ��",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						Intent toHome=new Intent(InputActivity.this, MainActivity.class);
						startActivity(toHome);
						finish();  
					}  
				});
				builder5.setCancelable(false);
				builder5.show();
			}
		}
		return super.onKeyDown(keyCode, event);

	}


}
