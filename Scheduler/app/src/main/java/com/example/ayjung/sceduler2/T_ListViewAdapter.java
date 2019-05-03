package com.example.ayjung.sceduler2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 정아연 on 17-06-21.
 */

public class T_ListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Info> infos;
    LayoutInflater inf;
    ArrayList<Integer>ids=new ArrayList<Integer>();
    public T_ListViewAdapter(Context context,ArrayList<Info> infos){
        this.context=context;
        this.infos=infos;
        inf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount(){return infos.size();}

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
        Info i=infos.get(position);
        convertView=inf.inflate(R.layout.listview_item,null);
        TextView todo=(TextView) convertView.findViewById(R.id.todo);
        checkBox = (CheckBox) convertView.findViewById(R.id.checkForDelete);
        todo.setText(i.getTodo());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=infos.get(position).getId();
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
