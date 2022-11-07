package com.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

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

        // リスト取得
        Intent getintent = getIntent();
        ArrayList<String> titleList = getintent.getStringArrayListExtra("titleList");
        ArrayList<String> contentList = getintent.getStringArrayListExtra("titleList");

        // 戻るボタンクリック時の処理
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(RegisterActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });

        // 登録ボタンクリック時の処理
        regiButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String title = titlefont.getText().toString();
                String content = contentfont.getText().toString();
                titleList.add(title);
                contentList.add(content);
                Intent intent = new Intent(RegisterActivity.this,ListActivity.class);
                intent.putStringArrayListExtra("titleList",titleList);
                intent.putStringArrayListExtra("contentList",contentList);
                startActivity(intent);
            }
        });
    }
}