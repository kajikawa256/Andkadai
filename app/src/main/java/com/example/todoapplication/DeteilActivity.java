package com.example.todoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DeteilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deteil);

        // 部品宣言
        TextView title = findViewById(R.id.textView3);
        TextView content = findViewById(R.id.textView4);
        Button finishbtn = findViewById(R.id.button4);
        Button backbtn = findViewById(R.id.button5);
        ImageView imageView = findViewById(R.id.imageView2);

        // プレファレンスのindex番号をListViewから受け取る
        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);

        // プレファレンスの宣言
        SharedPreferences pref;
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // リスト宣言
        List<String> todoList;

        // プリファレンスから値を取得、ArrayListに変換
        String download = pref.getString("todoList","");
        todoList = new ArrayList<String>(Arrays.asList(download.split(",")));

        // ListViewで選択したタイトルの詳細表示
        if(index == 0){
            title.setText(todoList.get(0));
            content.setText(todoList.get(1));
        }else{
            title.setText(todoList.get(index * 3 + 1));
            content.setText(todoList.get(index * 3 + 2));
        }

        // 戻るボタンクリック時の処理
        backbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DeteilActivity.this,ListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 完了ボタンクリック時の処理
        finishbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // title,memo,画像の削除
                int start;
                if(index == 0){
                    start = index;
                }else{
                    start = index * 3;
                }
                for(int i = 0;i < 3;i++){
                    todoList.remove(start);
                }

                // 削除したArrayListをプリファレンスへ登録
                String upload = String.join(",",todoList);
                pref.edit().putString("todoList",upload).apply();
                // ListActivityへ遷移
                Intent intent = new Intent(DeteilActivity.this,ListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}