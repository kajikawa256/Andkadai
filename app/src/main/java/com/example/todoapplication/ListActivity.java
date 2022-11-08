package com.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
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
        ArrayList<String> titleList;

        // リストを読み込む
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Set<String> sArray = pref.getStringSet("ToDoTitle",null);

        if(sArray!=null){
            titleList = new ArrayList<>(sArray);
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titleList);
            listView.setAdapter(arrayAdapter);
        }else{
            // プリファレンスが空の場合
            titleList = new ArrayList<>();
        }

        // Todo登録ボタンを押したときの処理
        regibutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ListActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}