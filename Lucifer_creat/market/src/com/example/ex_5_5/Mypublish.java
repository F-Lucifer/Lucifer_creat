package com.example.ex_5_5;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Mypublish extends Activity implements View.OnClickListener{
	private TextView mytv;
	private ImageView mypbimage;
	private Button chbtn;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_publish);
		mytv=(TextView)findViewById(R.id.mytv);
		mypbimage=(ImageView)findViewById(R.id.mypbimage);
		chbtn=(Button)findViewById(R.id.chbtn);
		chbtn.setOnClickListener(this);
		Bundle bundle=this.getIntent().getExtras();
		String pbtv=bundle.getString("pbet");
		Bitmap bm=bundle.getParcelable("bm");
		mypbimage.setImageBitmap(bm);
		if(pbtv.isEmpty()){
			mytv.setText("你还没有发布任何物品");
		}
		else
		mytv.setText(pbtv);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(Mypublish.this, "撤回成功", Toast.LENGTH_LONG).show();
	}

}
