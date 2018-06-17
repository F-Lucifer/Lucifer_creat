package com.example.ex_5_5;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements View.OnClickListener{
	TabHost tabHost;
	SearchView scview;
	Bundle bundle;
	private List<Map<String,Object>>itemList;
	private Button mybtn,cghdbtn,pbbtn,confirmtn,mypublishbtn,mys;
	private GridView gv;
	private ImageView image_head,pbimage;
	private EditText pbet;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		 tabHost=(TabHost)findViewById(R.id.tabhost);
		//初始化TabHost容器
		tabHost.setup();
		//声明并实例化一个LayoutInflater对象
		LayoutInflater inflater=LayoutInflater.from(this);
		//查找activity嵌入到TabHost容器
        inflater.inflate(R.layout.home_page, tabHost.getTabContentView());  
        inflater.inflate(R.layout.publish, tabHost.getTabContentView());  
        inflater.inflate(R.layout.my, tabHost.getTabContentView());  
        image_head=(ImageView)findViewById(R.id.image_head);
        pbimage=(ImageView)findViewById(R.id.pbimage);
        mybtn=(Button)findViewById(R.id.mybtn);
        mys=(Button)findViewById(R.id.mys);
        confirmtn=(Button)findViewById(R.id.confirmtn);
        mypublishbtn=(Button)findViewById(R.id.mypublishbtn);
        pbbtn=(Button)findViewById(R.id.pbbtn);
        cghdbtn=(Button)findViewById(R.id.cghdbtn);
        scview=(SearchView)findViewById(R.id.scview);
        pbet=(EditText)findViewById(R.id.pbet);
        gv=(GridView)findViewById(R.id.gridview);
        mybtn.setOnClickListener(this);
        cghdbtn.setOnClickListener(this);
        mys.setOnClickListener(this);
        pbbtn.setOnClickListener(this);
        mypublishbtn.setOnClickListener(this);
        confirmtn.setOnClickListener(this);
      //添加第一个标签页  
        tabHost.addTab(tabHost.newTabSpec("tab01")  
                .setIndicator("首页")  
                .setContent(R.id.home));
      //添加第二个标签页  
        tabHost.addTab(tabHost.newTabSpec("tab02")  
                .setIndicator("发布")  
                .setContent(R.id.publish));
      //添加第二个标签页  
        tabHost.addTab(tabHost.newTabSpec("tab03")  
                .setIndicator("我的")  
                .setContent(R.id.my)); 
//------------------------分割线------------------------------------------------------------------------------------------------
        int[]picIDs=new int[]{R.drawable.cfj,R.drawable.cj,R.drawable.jp,R.drawable.qt,R.drawable.s,R.drawable.s2,R.drawable.sb,R.drawable.sh};
        int[]expIDs={R.string.cfj,R.string.cj,R.string.jp,R.string.qt,R.string.s,R.string.s2,R.string.sb,R.string.sh};
        int rowCnt=picIDs.length;
        itemList=new ArrayList<Map<String,Object>>();
        for(int i=0;i<rowCnt;i++)
        {
        	HashMap<String,Object>map=new HashMap<String,Object>();
        	map.put("picCol", picIDs[i]);
        	map.put("expCol", this.getResources().getString(expIDs[i]));
        	itemList.add(map);
        }
         final SimpleAdapter ada=new SimpleAdapter(this,itemList,R.layout.griditem,
        		new String[]{"picCol","expCol"},new int[]{R.id.ItemIgmage,R.id.ItemText});
        gv.setAdapter(ada);
        gv.setTextFilterEnabled(true);
        scview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				if(!TextUtils.isEmpty(newText)){
					Toast.makeText(Login.this, "暂无结果", Toast.LENGTH_LONG).show();
        		}else{
        			gv.setAdapter(ada);
        		}
        		return false;
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.mybtn:
			Intent intent=new Intent();
			intent.setClass(Login.this,Myupdate.class);
			Bundle bundle=this.getIntent().getExtras();
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case R.id.cghdbtn:
			 Intent intent1 = new Intent(Intent.ACTION_PICK,
		     android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		     startActivityForResult(intent1, 1);
		     break;
		case R.id.pbbtn:
			 Intent intent2 = new Intent(Intent.ACTION_PICK,
		     android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		     startActivityForResult(intent2, 2);
		     break;
		case R.id.confirmtn:
			String pbetstring=pbet.getText().toString();
			if(!pbetstring.isEmpty())
			{
				
				Toast.makeText(Login.this, "发布成功", Toast.LENGTH_LONG).show();
			}
			else Toast.makeText(Login.this, "请填写内容", Toast.LENGTH_LONG).show();
			break;
		case R.id.mypublishbtn:
			pbimage.setDrawingCacheEnabled(true);
			Bitmap bm=Bitmap.createBitmap(pbimage.getDrawingCache());
			pbimage.setDrawingCacheEnabled(false);
			String pbetstring1=pbet.getText().toString();
			Intent intent3=new Intent();
			intent3.setClass(Login.this, Mypublish.class);
			Bundle bundle1=new Bundle();
			bundle1.putString("pbet",pbetstring1);
			bundle1.putParcelable("bm", bm);
			intent3.putExtras(bundle1);
			startActivity(intent3);
			break;
		case R.id.mys:
			Uri uri = Uri.parse("tel:17725171416");
			Intent intent4 = new Intent(Intent.ACTION_DIAL, uri);
			startActivity(intent4);
			break;
			
		}
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close(); 
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage2(imagePath);
            c.close(); 
        }
    }

    //加载图片
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        image_head.setImageBitmap(bm);
    }
    private void showImage2(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        pbimage.setImageBitmap(bm);
    }

}
//估计我这是史上最乱的代码..................