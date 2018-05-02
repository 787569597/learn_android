package com.example.learn_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 爱冒险的小鸡仔 on 2018/4/28.
 */

public class FirstActivity extends Activity implements View.OnClickListener{

    private EditText et_first_message;
    private Button btn_first_start1;
    private Button btn_first_start2;

    public FirstActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);


//        初始化视图对象
        et_first_message = (EditText)findViewById(R.id.et_first_message);
        btn_first_start1 = (Button)findViewById(R.id.btn_first_start1);
        btn_first_start2 = (Button)findViewById(R.id.btn_first_start2);

//         设置点击监听
        btn_first_start1.setOnClickListener(this);
        btn_first_start2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){//v就是发生事件的视图对象
        if (v == btn_first_start1){
//            Toast.makeText(this, "一般启动", Toast.LENGTH_SHORT).show();
//            创建Intent对象
            Intent intent = new Intent(this, SecondActivity.class);
//            得到editText中的信息转化为字符串形式
            String message = et_first_message.getText().toString();
            intent.putExtra("MESSAGE", message);
//            启动Activity
           startActivity(intent);
        }else if (v == btn_first_start2){
//           Toast.makeText(this, "带回调的启动", Toast.LENGTH_SHORT).show();
//            Log.d("data","带回调的启动");
            //            创建Intent对象
            Intent intent = new Intent(this, SecondActivity.class);
//            得到editText中的信息转化为字符串形式
            String message = et_first_message.getText().toString();
            intent.putExtra("MESSAGE", message);
//            启动回调Activity
           int requestCode = 2;
            startActivityForResult(intent,requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        判断code
        if (requestCode == 2 && resultCode ==3){
            //        从data中取数据
            String result = data.getStringExtra("RESULT");
//        显示
            et_first_message.setText(result);
        }

    }


}
