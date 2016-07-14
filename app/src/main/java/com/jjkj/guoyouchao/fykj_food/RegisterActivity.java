package com.jjkj.guoyouchao.fykj_food;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends CommonActivity {

    private Button   sendregcodeBtn ;
    private EditText phoneNumber    ;
    private CheckBox xyCheckbox    ;

    public  static int Curtime = 60;

    private boolean canclick = true;
    private static String prNumber  = "";
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle(R.string.register_new_user_word);

        sendregcodeBtn = (Button)findViewById(R.id.send_register_code);
        phoneNumber    = (EditText)findViewById(R.id.ok_input_phone);
        xyCheckbox    = (CheckBox)findViewById(R.id.support_xy);




        if(RegisterActivity.Curtime == 60){
            onClickSendCode();
        }else{
            phoneNumber.setText(RegisterActivity.prNumber);
            startTime();
        }

//        CountDownTimer
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
            new CountDownTimer(RegisterActivity.Curtime * 1000, 1000) {
                public void onTick(long millisUntilFinished) {
                    canclick = false;
                    RegisterActivity.Curtime = (int)millisUntilFinished / 1000;
                    RegisterActivity.prNumber = phoneNumber.getText().toString();
                    sendregcodeBtn.setText(RegisterActivity.Curtime + "秒后可重发");
                }
                public void onFinish() {
                    canclick = true;
                    RegisterActivity.prNumber = "";
                    RegisterActivity.Curtime = 60;
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
       return (phoneNumber.getText().toString().matches("^(13|15|18)\\d{9}$"));
    }

    // 判断是否选择协议
    public boolean supportXy(){
        return xyCheckbox.isChecked();
    }

}
