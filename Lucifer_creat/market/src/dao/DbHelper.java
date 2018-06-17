package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{
	//调用父函数super
	public DbHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	//创建数据库中的表
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//创建表tb_user，设置num为主键，依次创建需要的列
		db.execSQL("create table tb_user(num varchar(13) primary key, phone varchar(12),"
				+ "address varchar(50),username varchar(20),password varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}

