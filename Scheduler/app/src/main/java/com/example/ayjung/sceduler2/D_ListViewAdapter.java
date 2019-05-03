package com.example.ayjung.sceduler2;

import android.bluetooth.BluetoothA2dp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 정아연 on 17-06-22.
 */

public class D_ListViewAdapter extends BaseAdapter{
    Context context;
    ArrayList<DInfo> Dinfos;
    LayoutInflater inf;
    ArrayList<Integer>ids=new ArrayList<Integer>();
    public D_ListViewAdapter(Context context, ArrayList<DInfo> Dinfos){
        this.context=context;
        this.Dinfos=Dinfos;
        inf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount(){return Dinfos.size();}

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //    public Objects getItem(int position){return infos.get(position);}
    public View getView(final int position, View convertView, ViewGroup parent){
        final CheckBox checkBox;
        DInfo i=Dinfos.get(position);
        convertView=inf.inflate(R.layout.ddaylistitem,null);
        TextView dday=(TextView) convertView.findViewById(R.id.dday);
        TextView ddayname=(TextView) convertView.findViewById(R.id.ddayname);
        checkBox = (CheckBox) convertView.findViewById(R.id.checkForDelete);
        dday.setText(i.getDday());
        ddayname.setText(i.getDdayname());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=Dinfos.get(position).getId();
                if (checkBox.isChecked()){
                    ids.add(id);
                }else{
                    for(int n=0; n<ids.size(); n++){
                        if(ids.get(n)==id) ids.remove(n);
                    }
                }
            }
        });
        return convertView;
    }
}
