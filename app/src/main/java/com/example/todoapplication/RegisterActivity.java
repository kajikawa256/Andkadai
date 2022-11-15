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
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
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
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // リスト宣言
        List<String> todoList;

        // プリファレンスから値を取得、ArrayListに変換
        String download = pref.getString("todoList","");

        if(!(download.equals(""))){
            todoList = new ArrayList<String>(Arrays.asList(download.split(",")));
        }else{
            todoList = new ArrayList<String>();
        }

        // 戻るボタンクリック時の処理
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               Intent intent = new Intent(RegisterActivity.this,ListActivity.class);
               startActivity(intent);
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
                    // ArrayListをコンマで区切ってString型に格納、プレファレンスへ登録;
                    String title = titlefont.getText().toString().replace(",","、");
                    String content = contentfont.getText().toString().replace(",","、");
                    todoList.add(title);
                    todoList.add(content);
                    todoList.add("URI");
                    String upload = String.join(",",todoList);
                    pref.edit().putString("todoList",upload).apply();

                    // リストアクティビティを起動
                    Intent intent = new Intent(RegisterActivity.this,ListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}