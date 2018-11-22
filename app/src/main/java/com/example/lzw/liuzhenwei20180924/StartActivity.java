package com.example.lzw.liuzhenwei20180924;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_time;
    private SharedPreferences sp;
    private  int second=3;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            second--;
            tv_time.setText(second+"s");
            if (second==0){
               sp.edit().putBoolean("isfirst",true).commit();
               handler.removeCallbacksAndMessages(null);
                startActivity(new Intent(StartActivity.this,MainActivity.class));
                finish();
            }else {
                handler.sendEmptyMessageDelayed(101,1000);
            }
        }
    };
    private Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
          tv_time=(TextView)findViewById(R.id.tv_time);
          click=(Button)findViewById(R.id.click);
          click.setOnClickListener(this);
          sp=getSharedPreferences("config",MODE_PRIVATE);
        boolean b = sp.getBoolean("isfirst", false);
        if (b){
            startActivity(new Intent(StartActivity.this,MainActivity.class));
            finish();

          }else {
            handler.sendEmptyMessageDelayed(101,1000);

        }
    }

    @Override
    public void onClick(View view) {
        sp.edit().putBoolean("isfirst",true).commit();
        handler.removeCallbacksAndMessages(null);
        startActivity(new Intent(StartActivity.this,MainActivity.class));
        finish();
    }
}
