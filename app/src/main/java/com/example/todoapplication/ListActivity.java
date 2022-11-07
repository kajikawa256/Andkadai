package com.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

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
        ArrayList<String> memoList;

        // リストを読み込む

        // 登録ボタンを押したときの処理
        regibutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ListActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}