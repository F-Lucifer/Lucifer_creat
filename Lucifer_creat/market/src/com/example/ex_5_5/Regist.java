package com.example.ex_5_5;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import dao.DbHelper;
public class Regist extends Activity implements View.OnClickListener{
	private EditText num,phone,address,username,password;
	private Button registbtn;
	private DbHelper dbhelper;
	private SQLiteDatabase db;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		//ʹ��findViewById��ȡ����
		num=(EditText)findViewById(R.id.num);
		phone=(EditText)findViewById(R.id.phone);
		address=(EditText)findViewById(R.id.address);
		password=(EditText)findViewById(R.id.password);
		username=(EditText)findViewById(R.id.username);
		registbtn=(Button)findViewById(R.id.registbtn);
		//�󶨵����¼�
		registbtn.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//��ȡ����
		String numstring=num.getText().toString();
		String phonestring=phone.getText().toString();
		String addressstring=address.getText().toString();
		String usernamestring=username.getText().toString();
		String passwordstring=password.getText().toString();
		//����ContentValues,���ڴ�������ݿⱣ�������
		ContentValues value=new ContentValues();
		value.put("num",numstring);
		value.put("phone",phonestring);
		value.put("address",addressstring);
		value.put("username",usernamestring);
		value.put("password",passwordstring);
		//��ȡ�������ݿ�
		dbhelper=new DbHelper(Regist.this,"Db_User",null,1);
		db=dbhelper.getWritableDatabase();
		//�������ݵ����ݿ��У���������ֵ���ݸ�����s��
		long s=db.insert("tb_user", null, value);
		//�ж��Ƿ񱣴�ɹ�
		if(s!=-1){
			//toast��ʾ����ɹ�����ͬ
			Toast.makeText(Regist.this, "����ɹ�",Toast.LENGTH_LONG).show();
		}
		else{
			Toast.makeText(Regist.this, "����ʧ��", Toast.LENGTH_LONG).show();
		}
	}
}
