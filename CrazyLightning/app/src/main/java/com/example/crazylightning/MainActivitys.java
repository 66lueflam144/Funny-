package com.example.crazylightning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivitys extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Item> adapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_that_you_want_to_die_with);

        listView = findViewById(R.id.listview);
        itemList = new ArrayList<Item>();

        View headerView = LayoutInflater.from(this).inflate(R.layout.header_tail_girling, null);
        listView.addHeaderView(headerView);
        View footerView = LayoutInflater.from(this).inflate(R.layout.header_tail_girling, null);
        listView.addFooterView(footerView);


        // 添加示例数据
        itemList.add(new Item("Fox 1", "one fox came here"));
        itemList.add(new Item("Fox 2", "two fox came here"));
        itemList.add(new Item("Fox 3", "three fox came here"));
        itemList.add(new Item("Fox 4", "four fox came here"));

        // 使用自定义 ArrayAdapter
        adapter = new ArrayAdapter<Item>(this, 0, itemList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
                }

                TextView textViewItem = convertView.findViewById(R.id.text_view_item);
                TextView textViewDetail = convertView.findViewById(R.id.text_view_detail);
                Item item = getItem(position);

                if (item != null) {
                    textViewItem.setText(item.getName());
                    textViewDetail.setText(item.getDetail());
                }

                return convertView;
            }
        };

        listView.setAdapter(adapter);

        // 绑定按钮并设置功能
        Button addButton = findViewById(R.id.button_add);
        Button insertButton = findViewById(R.id.button_insert);
        Button deleteLastButton = findViewById(R.id.button_delete);
        Button deleteSelectedButton = findViewById(R.id.button_delete_selected);
        Button deleteAllButton = findViewById(R.id.button_delete_all);

        // 添加记录
        addButton.setOnClickListener(v -> addFox());

        // 在第 n 行插入
        insertButton.setOnClickListener(v -> insertAt(2));  // 假设在第 2 行插入

        // 删除最后一行
        deleteLastButton.setOnClickListener(v -> deleteLast());

        // 删除选中行
        deleteSelectedButton.setOnClickListener(v -> deleteSelected());

        // 删除所有记录
        deleteAllButton.setOnClickListener(v -> deleteAll());
    }

    public void addFox(){
        itemList.add(new Item("fox", "A new fox was added!"));
        adapter.notifyDataSetChanged();
    }

    public void insertAt(int n){
        if (n >= 0 && n <= itemList.size()) {
            itemList.add(n, new Item("fox", "A new fox was inserted"));
            adapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(this, "valid position", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteLast() {
        if (!itemList.isEmpty()) {
            itemList.remove(itemList.size() - 1);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No items to remove", Toast.LENGTH_SHORT).show();
        }
    }


    public void deleteSelected() {
        int selectedPosition = listView.getCheckedItemPosition();
        if (selectedPosition != ListView.INVALID_POSITION) {
            itemList.remove(selectedPosition);
            adapter.notifyDataSetChanged();
            listView.clearChoices();  // 清除选中状态
        } else {
            Toast.makeText(this, "No item selected", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAll() {
        itemList.clear();
        adapter.notifyDataSetChanged();
    }





}
