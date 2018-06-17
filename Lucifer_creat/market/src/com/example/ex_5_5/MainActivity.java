package com.example.ex_5_5;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import dao.DbHelper;
import android.view.Menu;
import android.view.MenuItem;
//Activity��Ϊ�¼���������ʽ
public class MainActivity extends Activity implements View.OnClickListener{
	//��������
	Button regist,loginbtn;
	EditText num,password;
	private DbHelper dbhelper;
	private SQLiteDatabase db;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//ʹ��findViewById��ȡ����
		regist=(Button)findViewById(R.id.registbtn);
		loginbtn=(Button)findViewById(R.id.loginbtn);
		num=(EditText)findViewById(R.id.num);
		password=(EditText)findViewById(R.id.password);
		//�󶨵����¼�
		regist.setOnClickListener(this);
		loginbtn.setOnClickListener(this);
	}
	//ʵ��OnClickListener�ӿڵ�onClick����
	public void onClick(View v){
		//ͨ��IDȷ�������¼�
		switch(v.getId())
		{
		//���ע�ᰴť ͨ��intent��ת��ע��ҳ��
		case R.id.registbtn:
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, Regist.class);
			startActivity(intent);
			break;
		case R.id.loginbtn:
			//��ȡ���������
			String numstring=num.getText().toString();
			String passwordstring=password.getText().toString();
			//�ж�ѧ�ź������Ƿ�Ϊ��
			if(numstring.equals("") || passwordstring.equals(""))
			{
				Toast.makeText(MainActivity.this, "ѧ�Ż����벻��Ϊ��", Toast.LENGTH_LONG).show();
			}else if(check(numstring,passwordstring)){//����check�ж�,��ȷ����ת���ɹ����棬������ʾѧ�Ż��������
				Intent intent1=new Intent();
				intent1.setClass(MainActivity.this, Login.class);
				Bundle bundle=new Bundle();
				bundle.putString("num",numstring);
				intent1.putExtras(bundle);
				startActivity(intent1);
			}
			else{
				Toast.makeText(MainActivity.this, "ѧ�Ż��������", Toast.LENGTH_LONG).show();
			}
		}
	}
	public Boolean check(String num,String password){
		//���������ݿ�
		dbhelper=new DbHelper(MainActivity.this,"Db_User",null,1);
		db=dbhelper.getReadableDatabase();
		//sql��ѯ����ѯnum��password
		String sql="select * from tb_user where num=? and password=?";
		//����һ���α�ָ������ϵ�һ��
		Cursor cursor=db.rawQuery(sql, new String[]
				{num,password});
		if(cursor.moveToFirst()==true)//���������ϵ�һ���򷵻��棬���򷵻ؼ�
		{
			cursor.close();//�α�ر�
			return true;
		}else 
		return false;
		
		}

	
}
