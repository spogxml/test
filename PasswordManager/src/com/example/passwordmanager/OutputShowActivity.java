package com.example.passwordmanager;

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

public class OutputShowActivity extends Activity {
	//��ʼ���ؼ�
	private EditText et_ot_title;
	private EditText et_ot_user;
	private EditText et_ot_password;
	private EditText et_ot_note;
	private Button update;
	private Button delete;
	private Button ot_sh_cancel;

	//������ݵ��н�
	private MyData ot_shdata;
	//���ݿ���
	private MyDataDB ot_shDB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.output_show);
		et_ot_title=(EditText) findViewById(R.id.et_ot_title);
		et_ot_user=(EditText) findViewById(R.id.et_ot_user);
		et_ot_password=(EditText) findViewById(R.id.et_ot_password);
		et_ot_note=(EditText) findViewById(R.id.et_ot_note);
		update=(Button) findViewById(R.id.update);
		delete=(Button) findViewById(R.id.delete);
		ot_sh_cancel=(Button) findViewById(R.id.ot_sh_cancel);

		//��������
		ot_shdata=new MyData();
		ot_shdata=this.AcceptIntent();

		//����������ʾ
		et_ot_title.setText(ot_shdata.title);
		et_ot_user.setText(ot_shdata.user);
		et_ot_password.setText(ot_shdata.password);
		et_ot_note.setText(ot_shdata.note);

		//�޸�����
		update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//����������Ϊ�գ������ʧ��
				if(TextUtils.isEmpty(et_ot_title.getText())||TextUtils.isEmpty(et_ot_user.getText())
						||TextUtils.isEmpty(et_ot_password.getText())||TextUtils.isEmpty(et_ot_note.getText())){
					AlertDialog.Builder builder6  = new Builder(OutputShowActivity.this);
					builder6.setTitle("��ʾ��" ) ;
					builder6.setMessage("��ȷ�������������ݲ���Ϊ�գ�" ) ;
					builder6.setPositiveButton("ȷ��" ,new DialogInterface.OnClickListener()
					{

						@Override
						public   void  onClick(DialogInterface dialog,  int  which)
						{
							dialog.dismiss();
						}
					});
					builder6.setCancelable(false);
					builder6.show(); 
				}//���������������и���
				else{
					//��ȡԭ���ı�����Ϣ����ƥ���޸�
					String title=ot_shdata.title;
					//���EditText�޸ĺ������
					MyData tdata=new MyData();
					tdata.title=et_ot_title.getText().toString();
					tdata.user=et_ot_user.getText().toString();
					tdata.password=et_ot_password.getText().toString();
					tdata.note=et_ot_note.getText().toString();
					//�����ݿ⣬�޸�����
					ot_shDB=new MyDataDB();
					ot_shDB.initDB(OutputShowActivity.this);
					int j=ot_shDB.updateDB(tdata, title);
					if(j==0){
						//����ʧ��
						AlertDialog.Builder builder7  = new Builder(OutputShowActivity.this);
						builder7.setTitle("��ʾ��" ) ;
						builder7.setMessage("����ʧ�ܣ�" ) ;
						builder7.setPositiveButton("ȷ��" , new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								Intent toHome=new Intent(OutputShowActivity.this, MainActivity.class);
								startActivity(toHome);
								finish();  
							}  
						});
						builder7.setCancelable(false);
						builder7.show();
					}else{
						//���³ɹ�
						AlertDialog.Builder builder8  = new Builder(OutputShowActivity.this);
						builder8.setTitle("�޸ĳɹ���").setMessage("�����޸ĳɹ���");
						builder8.setPositiveButton("�����޸�",new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}  
						});
						builder8.setNegativeButton("����",new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								AlertDialog.Builder builder24  = new Builder(OutputShowActivity.this);
								builder24.setTitle("���أ�").setMessage("���ز�ѯҳ��/������ҳ��");
								builder24.setPositiveButton("���ز�ѯҳ��",new DialogInterface.OnClickListener() {  
									@Override  
									public void onClick(DialogInterface dialog, int which) {
										Intent toSelect=new Intent(OutputShowActivity.this, OutputSelectActivity.class);
										startActivity(toSelect);
										finish();
									}  
								});
								builder24.setNegativeButton("������ҳ��",new DialogInterface.OnClickListener() {  
									@Override  
									public void onClick(DialogInterface dialog, int which) {
										Intent toHome=new Intent(OutputShowActivity.this, MainActivity.class);
										startActivity(toHome);
										finish();  
									}  
								});
								builder24.setCancelable(false);
								builder24.show();   
							}  
						});
						builder8.setCancelable(false);
						builder8.show();
					}
				}
			}

		});

		//ɾ������
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder13  = new Builder(OutputShowActivity.this);
				builder13.setTitle("���棡").setMessage("�Ƿ�ȷ��ɾ���������ݣ�ɾ���󲻿ɻָ���");
				builder13.setPositiveButton("ȷ��ɾ��",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						//��ȡԭ���ı������ݣ�����ƥ��ɾ��
						String title=ot_shdata.title;
						//�����ݿ⣬ɾ������
						ot_shDB=new MyDataDB();
						ot_shDB.initDB(OutputShowActivity.this);
						int k=ot_shDB.deleteDB(title);
						//ɾ��ʧ��
						if(k==0){
							//����ɾ����ʾ��
							AlertDialog.Builder builder14  = new Builder(OutputShowActivity.this);
							builder14.setTitle("��ʾ��").setMessage("ɾ��ʧ�ܣ�");
							builder14.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									Intent toHome=new Intent(OutputShowActivity.this, MainActivity.class);
									startActivity(toHome);
									finish();
								}
							});
							builder14.setCancelable(false);
							builder14.show();
						}//ɾ���ɹ�
						else{
							//����ɾ����ʾ��
							AlertDialog.Builder builder15  = new Builder(OutputShowActivity.this);
							builder15.setTitle("��ʾ��").setMessage("ɾ���ɹ���");
							builder15.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {  
								@Override  
								public void onClick(DialogInterface dialog, int which) {
									Intent toHome=new Intent(OutputShowActivity.this, MainActivity.class);
									startActivity(toHome);
									finish();
								}
							});
							builder15.setCancelable(false);
							builder15.show();
						}
					}
				});
				builder13.setNegativeButton("��ɾ��",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}  
				});
				builder13.setCancelable(false);
				builder13.show();
				
			}
		});
		
		
		//ȡ��
		ot_sh_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder11  = new Builder(OutputShowActivity.this);
				builder11.setTitle("��ʾ��").setMessage("�Ƿ�ȷ�Ϸ���!");
				builder11.setPositiveButton("��",new DialogInterface.OnClickListener()
				{

					@Override
					public   void  onClick(DialogInterface dialog,  int  which)
					{
						AlertDialog.Builder builder22  = new Builder(OutputShowActivity.this);
						builder22.setTitle("���أ�").setMessage("���ز�ѯҳ��/������ҳ��");
						builder22.setPositiveButton("���ز�ѯҳ��",new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								Intent toSelect=new Intent(OutputShowActivity.this, OutputSelectActivity.class);
								startActivity(toSelect);
								finish();
							}  
						});
						builder22.setNegativeButton("������ҳ��",new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								Intent toHome=new Intent(OutputShowActivity.this, MainActivity.class);
								startActivity(toHome);
								finish();  
							}  
						});
						builder22.setCancelable(false);
						builder22.show(); 
					}
				});
				builder11.setNegativeButton("��",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.dismiss();
					}  
				});
				builder11.setCancelable(false);
				builder11.show();
			}
		});
		
	}
	
	//����ListView����item���ʱ����������
	public MyData AcceptIntent() {
		MyData mdata=new MyData();
		Intent intent_accept = getIntent();           //����һ��������ͼ
		Bundle bundle = intent_accept.getExtras();    //����Bundle�������ڽ���OutputSelect��Intent����
		mdata.title = bundle.getString("title");       //��ȡIntent������
		mdata.user = bundle.getString("user");       //��ȡIntent������
		mdata.password = bundle.getString("password");       //��ȡIntent������
		mdata.note = bundle.getString("note");       //��ȡIntent������
		return mdata;          
	}

	//�Զ��巵�ؼ����ܣ���ȡ����һ��
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				AlertDialog.Builder builder12  = new Builder(OutputShowActivity.this);
				builder12.setTitle("��ʾ��").setMessage("�Ƿ�ȷ�Ϸ���!");
				builder12.setPositiveButton("��",new DialogInterface.OnClickListener()
				{

					@Override
					public   void  onClick(DialogInterface dialog,  int  which)
					{
						AlertDialog.Builder builder23  = new Builder(OutputShowActivity.this);
						builder23.setTitle("���أ�").setMessage("���ز�ѯҳ��/������ҳ��");
						builder23.setPositiveButton("���ز�ѯҳ��",new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								Intent toSelect=new Intent(OutputShowActivity.this, OutputSelectActivity.class);
								startActivity(toSelect);
								finish();
							}  
						});
						builder23.setNegativeButton("������ҳ��",new DialogInterface.OnClickListener() {  
							@Override  
							public void onClick(DialogInterface dialog, int which) {
								Intent toHome=new Intent(OutputShowActivity.this, MainActivity.class);
								startActivity(toHome);
								finish();  
							}  
						});
						builder23.setCancelable(false);
						builder23.show();   
					}
				});
				builder12.setNegativeButton("��",new DialogInterface.OnClickListener() {  
					@Override  
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.dismiss();
					}  
				});
				builder12.setCancelable(false);
				builder12.show();
			}
			return super.onKeyDown(keyCode, event);
		}
}
