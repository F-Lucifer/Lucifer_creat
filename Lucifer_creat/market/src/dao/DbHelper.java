package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{
	//���ø�����super
	public DbHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	//�������ݿ��еı�
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//������tb_user������numΪ���������δ�����Ҫ����
		db.execSQL("create table tb_user(num varchar(13) primary key, phone varchar(12),"
				+ "address varchar(50),username varchar(20),password varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}

