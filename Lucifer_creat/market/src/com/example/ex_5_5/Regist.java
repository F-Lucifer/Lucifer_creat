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
		//使用findViewById获取对象
		num=(EditText)findViewById(R.id.num);
		phone=(EditText)findViewById(R.id.phone);
		address=(EditText)findViewById(R.id.address);
		password=(EditText)findViewById(R.id.password);
		username=(EditText)findViewById(R.id.username);
		registbtn=(Button)findViewById(R.id.registbtn);
		//绑定单机事件
		registbtn.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//获取数据
		String numstring=num.getText().toString();
		String phonestring=phone.getText().toString();
		String addressstring=address.getText().toString();
		String usernamestring=username.getText().toString();
		String passwordstring=password.getText().toString();
		//生成ContentValues,用于存放像数据库保存的数据
		ContentValues value=new ContentValues();
		value.put("num",numstring);
		value.put("phone",phonestring);
		value.put("address",addressstring);
		value.put("username",usernamestring);
		value.put("password",passwordstring);
		//获取并打开数据库
		dbhelper=new DbHelper(Regist.this,"Db_User",null,1);
		db=dbhelper.getWritableDatabase();
		//插入数据到数据库中，并将返回值传递给变量s。
		long s=db.insert("tb_user", null, value);
		//判断是否保存成功
		if(s!=-1){
			//toast显示保存成功，下同
			Toast.makeText(Regist.this, "保存成功",Toast.LENGTH_LONG).show();
		}
		else{
			Toast.makeText(Regist.this, "保存失败", Toast.LENGTH_LONG).show();
		}
	}
}
