package com.example.huangli.mydemos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by huangli on 16/3/1.
 */
public abstract class BaseListActivity extends ListActivity {
    protected ArrayList<HashMap<String, Object>> listItems = new ArrayList<>();    //存放文字、图片信息
    protected SimpleAdapter listItemAdapter;           //适配器
    protected ArrayList<ItemClickListener> itemClickListeners = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadListView();
        initItems();
    }

    public abstract void initItems();

    protected interface ItemClickListener{
        void itemClicked();
    }

    protected void intentActivity(Class activity){
        Intent i = new Intent(this, activity);
        startActivity(i);
    }

    protected void addListviewItem(String name,ItemClickListener itemClickListener){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);    //文字
        listItems.add(map);
        itemClickListeners.add(itemClickListener);
        listItemAdapter.notifyDataSetChanged();
    }

    protected void loadListView(){
        listItemAdapter = new SimpleAdapter(this,listItems,R.layout.main_item_list,new String[] {"name"},new int[ ] {R.id.name});
        setListAdapter(listItemAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        itemClickListeners.get(position).itemClicked();
    }
}
