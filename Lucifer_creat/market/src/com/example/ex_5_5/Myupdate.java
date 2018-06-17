package com.example.ex_5_5;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import dao.DbHelper;

public class Myupdate extends Activity implements View.OnClickListener{
	private EditText edtnum,edtphone,edtaddress,edtusername,edtpassword;
	private DbHelper dbhelper;
	private SQLiteDatabase db;
	private Button update;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		//ʹ��findViewById��ȡ����
				edtnum=(EditText)findViewById(R.id.edtnum);
				edtphone=(EditText)findViewById(R.id.edtphone);
				edtaddress=(EditText)findViewById(R.id.edtaddress);
				edtpassword=(EditText)findViewById(R.id.edtpassword);
				edtusername=(EditText)findViewById(R.id.edtusername);
				update=(Button)findViewById(R.id.update);
				update.setOnClickListener(this);
		Bundle bundle=this.getIntent().getExtras();
		String numstring=bundle.getString("num");
		//���������ݿ�
		dbhelper=new DbHelper(Myupdate.this,"Db_User",null,1);
		db=dbhelper.getWritableDatabase();
		//sql��ѯ����ѯnum
		String sql="select * from tb_user where num=?";
		//����һ���α�ָ������ϵ�һ��
		Cursor cursor=db.rawQuery(sql, new String[]
				{numstring});
		if(cursor.moveToFirst()==true)//���������ϵ�һ���򷵻��棬���򷵻ؼ�
		{ 	String num=cursor.getString(0);
			String phone=cursor.getString(1);
			String address=cursor.getString(2);
			String username=cursor.getString(3);
			String password=cursor.getString(4);
			edtnum.setText(num);
			edtphone.setText(phone);
			edtaddress.setText(address);
			edtusername.setText(username);
			edtpassword.setText(password);
			cursor.close();//�α�ر�			
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//��ȡ����
				String numstring=edtnum.getText().toString();
				String phonestring=edtphone.getText().toString();
				String addressstring=edtaddress.getText().toString();
				String usernamestring=edtusername.getText().toString();
				String passwordstring=edtpassword.getText().toString();
				//����ContentValues,���ڴ�������ݿⱣ�������
				ContentValues value=new ContentValues();
				value.put("num",numstring);
				value.put("phone",phonestring);
				value.put("address",addressstring);
				value.put("username",usernamestring);
				value.put("password",passwordstring);
				//��ȡ�������ݿ�
				dbhelper=new DbHelper(Myupdate.this,"Db_User",null,1);
				db=dbhelper.getWritableDatabase();
				//�������ݵ����ݿ��У���������ֵ���ݸ�����s��
				long s=db.update("tb_user", value,"num=?",new String[]{numstring});
				//�ж��Ƿ񱣴�ɹ�
				if(s!=-1){
					//toast��ʾ����ɹ�����ͬ
					Toast.makeText(Myupdate.this, "����ɹ�",Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(Myupdate.this, "����ʧ��", Toast.LENGTH_LONG).show();
				}

	}

}
