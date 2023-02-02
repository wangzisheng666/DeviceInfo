package com.mobiles.devices.fragment.openEnv;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiles.devices.R;

import java.util.ArrayList;

public class MainListViewAdapter extends BaseAdapter {

	
	public Context congb;
	public ArrayList<ListItem> mList;
	public MainListViewAdapter(Context _congb,ArrayList<ListItem> _mList)
	{
		congb = _congb;
		mList = _mList;		 
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ListItemView listItemView;  
				
        // ��ʼ��item view  
        if (convertView == null) {  
            // ͨ��LayoutInflater��xml�ж������ͼʵ������һ��View��  
            convertView = LayoutInflater.from(congb).inflate(  
                    R.layout.main_tab_setting_list_item, null);

            // ʵ����һ����װ��ListItemView����ʵ��������������  
            listItemView = new ListItemView();  
            listItemView.imageView = (ImageView) convertView  
                    .findViewById(R.id.iv_system_left);  
            listItemView.textView = (TextView) convertView  
                    .findViewById(R.id.tv_system_title);
            listItemView.textView1 = (TextView) convertView
                    .findViewById(R.id.iv_system_right);
            // ��ListItemView���󴫵ݸ�convertView  
            convertView.setTag(listItemView);  
        } else {  
            // ��converView�л�ȡListItemView����  
            listItemView = (ListItemView) convertView.getTag();  
        }  
        // ��ȡ��mList��ָ������λ�õ���Դ  
        Drawable img = mList.get(position).getImage();  
        String title = mList.get(position).getTitle();
        String title1 = mList.get(position).getTitle1();
        // ����Դ���ݸ�ListItemView�����������  
        listItemView.imageView.setImageDrawable(img);  
        listItemView.textView.setText(title);
        listItemView.textView1.setText(title1);
       
        // ����convertView����  
        return convertView;  
	}

	
}


/** 
 * ��װ������ͼ������� 
 */  
class ListItemView {  
    ImageView imageView;  
    TextView textView;
    TextView textView1;
}

/** 
 * ��װ��������Դ���� 
 */  
class ListItem {  
    private Drawable image;  
    private String title;
    private String title1;

    public Drawable getImage() {  
        return image;  
    }  

    public void setImage(Drawable image) {  
        this.image = image;  
    }  

    public String getTitle() {  
        return title;  
    }  

    public void setTitle(String title) {  
        this.title = title;  
    }


    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }


}  
