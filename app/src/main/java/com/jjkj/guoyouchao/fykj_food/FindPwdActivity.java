package com.jjkj.guoyouchao.fykj_food;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class FindPwdActivity extends CommonActivity {
    private Button sendregcodeBtn ;
    private EditText phoneNumber    ;

    public  static int Curtime = 60;

    private boolean canclick = true;
    private static String prNumber  = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pwd);
        getSupportActionBar().setTitle(R.string.find_passwrod_word);
        sendregcodeBtn = (Button)findViewById(R.id.send_find_phone);
        phoneNumber    = (EditText)findViewById(R.id.ok_input_id);



        if(FindPwdActivity.Curtime == 60){
            onClickSendCode();
            FindPwdActivity.prNumber = "";
        }else{
            phoneNumber.setText(FindPwdActivity.prNumber);
            startTime();
        }
    }

    public void onClickSendCode(){
        sendregcodeBtn.setText(R.string.send_codeing);
        sendregcodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canclick == true){
                    startTime();
                }

            }
        });
    }

    public void startTime(){
        if(phoneNumberIsVai() == true){
            new CountDownTimer(FindPwdActivity.Curtime * 1000, 1000) {
                public void onTick(long millisUntilFinished) {
                    canclick = false;
                    FindPwdActivity.Curtime = (int)millisUntilFinished / 1000;
                    FindPwdActivity.prNumber = phoneNumber.getText().toString();
                    sendregcodeBtn.setText(FindPwdActivity.Curtime + "秒后可重发");
                }
                public void onFinish() {
                    canclick = true;
                    FindPwdActivity.prNumber = "";
                    FindPwdActivity.Curtime = 60;
                    onClickSendCode();
                    sendregcodeBtn.setText(R.string.send_codeing);
                }
            }.start();
        }else{
            Toast.makeText(this.getApplicationContext(),"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
        }


    }


    // 判断是否输入手机号
    public boolean phoneNumberIsVai(){
        return (phoneNumber.getText().toString().matches("^(13|14|15|18)\\d{9}$"));
    }


}
