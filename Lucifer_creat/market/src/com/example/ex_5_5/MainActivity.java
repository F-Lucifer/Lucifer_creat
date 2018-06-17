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
//Activity作为事件监听器方式
public class MainActivity extends Activity implements View.OnClickListener{
	//申明变量
	Button regist,loginbtn;
	EditText num,password;
	private DbHelper dbhelper;
	private SQLiteDatabase db;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//使用findViewById获取对象
		regist=(Button)findViewById(R.id.registbtn);
		loginbtn=(Button)findViewById(R.id.loginbtn);
		num=(EditText)findViewById(R.id.num);
		password=(EditText)findViewById(R.id.password);
		//绑定单机事件
		regist.setOnClickListener(this);
		loginbtn.setOnClickListener(this);
	}
	//实现OnClickListener接口的onClick方法
	public void onClick(View v){
		//通过ID确定单击事件
		switch(v.getId())
		{
		//点击注册按钮 通过intent跳转到注册页面
		case R.id.registbtn:
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, Regist.class);
			startActivity(intent);
			break;
		case R.id.loginbtn:
			//获取输入框内容
			String numstring=num.getText().toString();
			String passwordstring=password.getText().toString();
			//判断学号和密码是否为空
			if(numstring.equals("") || passwordstring.equals(""))
			{
				Toast.makeText(MainActivity.this, "学号或密码不能为空", Toast.LENGTH_LONG).show();
			}else if(check(numstring,passwordstring)){//交给check判断,正确则跳转到成功界面，否则显示学号或密码错误
				Intent intent1=new Intent();
				intent1.setClass(MainActivity.this, Login.class);
				Bundle bundle=new Bundle();
				bundle.putString("num",numstring);
				intent1.putExtras(bundle);
				startActivity(intent1);
			}
			else{
				Toast.makeText(MainActivity.this, "学号或密码错误", Toast.LENGTH_LONG).show();
			}
		}
	}
	public Boolean check(String num,String password){
		//创建打开数据库
		dbhelper=new DbHelper(MainActivity.this,"Db_User",null,1);
		db=dbhelper.getReadableDatabase();
		//sql查询语句查询num和password
		String sql="select * from tb_user where num=? and password=?";
		//定义一个游标指向相符合的一行
		Cursor cursor=db.rawQuery(sql, new String[]
				{num,password});
		if(cursor.moveToFirst()==true)//如果有相符合的一行则返回真，否则返回假
		{
			cursor.close();//游标关闭
			return true;
		}else 
		return false;
		
		}

	
}
