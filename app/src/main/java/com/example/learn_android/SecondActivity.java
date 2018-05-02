package com.example.learn_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 爱冒险的小鸡仔 on 2018/5/1.
 */

public class SecondActivity extends Activity{
    private EditText et_second_message;
    private Button btn_second_back1;
    private Button btn_second_back2;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        et_second_message = (EditText)findViewById(R.id.et_second_message);

//        4）得到intent对象
        Intent intent = getIntent();
//        5）通过intent读取额外数据
        String message = intent.getStringExtra("MESSAGE");
//        6）显示到edittext
        et_second_message.setText(message);
    }

    public void back1(View v){
//        关闭当前界面
        finish();
    }
    public void back2(View v){

        //保存结果
        int resultCode = 3;
//        数据载体 准备一个带额外数据的intent的对象
        Intent data = new Intent();
        String result = et_second_message.getText().toString();
        data.putExtra("RESULT", result);
//        设置结果
        setResult(resultCode, data);
//        关闭当前界面
        finish();
    }
}


