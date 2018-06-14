package com.example.fxl5r.providertest;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button queryData=(Button)findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                //查询所有信息
                Uri uri=Uri.parse("content://com.example.fxl5r.provider/contacts");
                Cursor cursor=getContentResolver().query(uri,null,null,null,null,null);
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String number=cursor.getString(cursor.getColumnIndex("number"));
                        Log.e("MainActivity","name is "+name);
                        Log.e("MainActivity","number is"+number);
                    }
                    cursor.close();
                }
            }
        });
        Button queryItem=(Button)findViewById(R.id.query_item);
        final EditText edit_id=(EditText) findViewById(R.id.edit_id);
        queryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查询单条信息
                String id=edit_id.getText().toString();
                Uri uri=Uri.parse("content://com.example.fxl5r.provider/contacts/"+id);
                Cursor cursor=getContentResolver().query(uri,null,"id=?",new String[] {id},null,null);
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String number=cursor.getString(cursor.getColumnIndex("number"));
                        Log.e("MainActivity","name is "+name);
                        Log.e("MainActivity","number is"+number);
                    }
                    cursor.close();
                }
            }
        });

        }
    }

