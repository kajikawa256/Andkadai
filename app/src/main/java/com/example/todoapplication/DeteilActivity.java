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
import java.util.HashSet;
import java.util.LinkedHashSet;
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

        // ListViewで選択したタイトルの詳細表示
        title.setText(titleList.get(index));
        content.setText(memoList.get(index));

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
                // ToDoの削除
                titleList.remove(index);
                memoList.remove(index);
                // 削除したArrayListをプリファレンスへ登録
                Set<String> cArray = new LinkedHashSet<String>(titleList);
                Set<String> dArray = new LinkedHashSet<String>(memoList);
                pref.edit().putStringSet("ToDoTitle",cArray).apply();
                pref.edit().putStringSet("ToDoMemo",dArray).apply();
                // ListActivityへ遷移
                Intent intent = new Intent(DeteilActivity.this,ListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}