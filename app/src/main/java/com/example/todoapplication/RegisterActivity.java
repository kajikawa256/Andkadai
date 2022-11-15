package com.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 部品宣言
        EditText titlefont = findViewById(R.id.titleEnter);
        EditText contentfont = findViewById(R.id.contentEnter);
        Button backButton = findViewById(R.id.backButton);
        Button regiButton = findViewById(R.id.registerButton);

        // プレファレンスの宣言
        SharedPreferences pref;
        ArrayList<String> titleList;
        ArrayList<String> memoList;

        // プリファレンスから値を取得し、リストを初期化する
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Set<String> sArray = pref.getStringSet("ToDoTitle",null);
        Set<String> s2Array = pref.getStringSet("ToDoMemo",null);
        if(sArray != null && s2Array != null){
            titleList = new ArrayList<>(sArray);
            memoList = new ArrayList<>(s2Array);
        }else{
            titleList = new ArrayList<>();
            memoList = new ArrayList<>();
        }

        // 戻るボタンクリック時の処理
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               finish();
            }
        });

        // 登録ボタンクリック時の処理
        regiButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(titlefont.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"タイトルを入力してください",Toast.LENGTH_SHORT).show();
                }else if(contentfont.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"内容を入力してください",Toast.LENGTH_SHORT).show();
                }else{
                    // タイトルと内容取得、追加
                    titleList.add(titlefont.getText().toString());
                    memoList.add(contentfont.getText().toString());
                    // キャストしてプリファレンスへ登録
                    Set<String> cArray = new LinkedHashSet<String>(titleList);
                    Set<String> dArray = new LinkedHashSet<String>(memoList);

                    // デバッグ用
                    System.out.println("-----------------");
                    System.out.println(cArray);
                    System.out.println(dArray);
                    System.out.println("-----------------");

                    pref.edit().putStringSet("ToDoTitle",cArray).apply();
                    pref.edit().putStringSet("ToDoMemo",dArray).apply();
                    // リストアクティビティを起動
                    Intent intent = new Intent(RegisterActivity.this,ListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}