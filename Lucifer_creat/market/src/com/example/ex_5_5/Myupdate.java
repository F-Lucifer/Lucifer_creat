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
		//使用findViewById获取对象
				edtnum=(EditText)findViewById(R.id.edtnum);
				edtphone=(EditText)findViewById(R.id.edtphone);
				edtaddress=(EditText)findViewById(R.id.edtaddress);
				edtpassword=(EditText)findViewById(R.id.edtpassword);
				edtusername=(EditText)findViewById(R.id.edtusername);
				update=(Button)findViewById(R.id.update);
				update.setOnClickListener(this);
		Bundle bundle=this.getIntent().getExtras();
		String numstring=bundle.getString("num");
		//创建打开数据库
		dbhelper=new DbHelper(Myupdate.this,"Db_User",null,1);
		db=dbhelper.getWritableDatabase();
		//sql查询语句查询num
		String sql="select * from tb_user where num=?";
		//定义一个游标指向相符合的一行
		Cursor cursor=db.rawQuery(sql, new String[]
				{numstring});
		if(cursor.moveToFirst()==true)//如果有相符合的一行则返回真，否则返回假
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
			cursor.close();//游标关闭			
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//获取数据
				String numstring=edtnum.getText().toString();
				String phonestring=edtphone.getText().toString();
				String addressstring=edtaddress.getText().toString();
				String usernamestring=edtusername.getText().toString();
				String passwordstring=edtpassword.getText().toString();
				//生成ContentValues,用于存放像数据库保存的数据
				ContentValues value=new ContentValues();
				value.put("num",numstring);
				value.put("phone",phonestring);
				value.put("address",addressstring);
				value.put("username",usernamestring);
				value.put("password",passwordstring);
				//获取并打开数据库
				dbhelper=new DbHelper(Myupdate.this,"Db_User",null,1);
				db=dbhelper.getWritableDatabase();
				//插入数据到数据库中，并将返回值传递给变量s。
				long s=db.update("tb_user", value,"num=?",new String[]{numstring});
				//判断是否保存成功
				if(s!=-1){
					//toast显示保存成功，下同
					Toast.makeText(Myupdate.this, "保存成功",Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(Myupdate.this, "保存失败", Toast.LENGTH_LONG).show();
				}

	}

}
