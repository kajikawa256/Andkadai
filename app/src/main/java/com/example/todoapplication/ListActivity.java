package com.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // 部品宣言
        ListView listView = findViewById(R.id.ListView);
        Button regibutton = findViewById(R.id.registerbutton);

        // プレファレンスの宣言
        SharedPreferences pref;
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // プリファレンスから値を取得、ArrayListに変換
        String download = pref.getString("todoList","");
        List<String> todoList = new ArrayList<String>(Arrays.asList(download.split(",")));

        if(!(download.equals(""))){
            String[] title = new String[todoList.size() / 3];
            for(int i =0;i < title.length;i++){
                title[i] = todoList.get(i * 3);
            }
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, title);
            listView.setAdapter(arrayAdapter);
        }else{
            // プリファレンスが空の場合
            todoList = new ArrayList<>();
        }

        // デバッグ用
        System.out.println("-----------------");
        System.out.println(todoList);
        System.out.println("-----------------");

        // Todo登録ボタンを押したときの処理
        regibutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ListActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // listviewのクリック検出
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this,DeteilActivity.class);
                intent.putExtra("index",i);
                startActivity(intent);
            }
        });
    }
}