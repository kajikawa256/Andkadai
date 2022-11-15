package com.example.todoapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RegisterActivity extends AppCompatActivity {
    Uri imageUri;
    final int CAMERA_RESULT = 100;

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_RESULT && resultCode == RESULT_OK){
            ImageView cameraImage = findViewById(R.id.imageView);
            cameraImage.setImageURI(imageUri);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 部品宣言
        EditText titlefont = findViewById(R.id.titleEnter);
        EditText contentfont = findViewById(R.id.contentEnter);
        Button backButton = findViewById(R.id.backButton);
        Button regiButton = findViewById(R.id.registerButton);
        ImageView imageView = findViewById(R.id.imageView);
        FloatingActionButton imgButton = findViewById(R.id.imageButton);

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



        // 画像ボタンクリック時の処理
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 画像の処理
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = "Traning_" + timestamp + "_.jpg";
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, fileName);
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                imageUri =
                        getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent,CAMERA_RESULT);

            }
        });

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
                    if(imageUri != null){
                        todoList.add(imageUri.toString());
                    }else{
                        todoList.add("null");
                    }
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