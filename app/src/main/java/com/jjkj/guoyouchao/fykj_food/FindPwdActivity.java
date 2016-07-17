package com.jjkj.guoyouchao.fykj_food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.jjkj.guoyouchao.fykj_food.TOOLS.HtppUrlPath;
import com.jjkj.guoyouchao.fykj_food.TOOLS.Param;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class FindPwdActivity extends CommonActivity {
    private Button sendregcodeBtn ;
    private EditText phoneNumber    ;
    private EditText codeText;

    private Button FOkBtn;
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
        codeText     = (EditText)findViewById(R.id.input_findcodings);
        FOkBtn =    (Button)findViewById(R.id.findpwd_ok_btn);

        FOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(codeVail() == true && phoneNumberIsVai() == true){
                   OKYzm();
                }else{
                    Toast.makeText(getApplicationContext(),"请检查手机号或验证码是否正确",Toast.LENGTH_SHORT).show();
                }
            }
        });

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
                    sendYZM();
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


    // 发送验证码
    public void sendYZM(){
      if(phoneNumberIsVai() == false){
          Toast.makeText(this.getApplicationContext(),"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
      }else if(phoneNumberIsVai() == true){
          // 注册验证码
          final String uid             =   phoneNumber.getText().toString();
          // 设备名称
          String dev               =   Param.Device;
          // 网络请求地址
          String url               =   HtppUrlPath.setFindPwdCode;
          RequestParams params1    =   new RequestParams();
          final JSONObject obj     =   new JSONObject();
          try{
              obj.put("uid", uid);
              obj.put("dev", dev);
          }catch (JSONException ex){

          }
          params1.put("pj",obj.toString());
          Param.sendPost(this,url, params1, new JsonDataHandle() {
              @Override
              public void dealJSONData(JSONObject object) {
                  try{

                  }catch (Exception ex){

                  }
              }
          });
      }
    }

    // 确定验证码
    public void OKYzm(){
        if(phoneNumberIsVai() == false){
            Toast.makeText(this.getApplicationContext(),"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
        }else if(codeIsVail() == false){
            Toast.makeText(this.getApplicationContext(),"验证码格式不正确",Toast.LENGTH_SHORT).show();
        }else {
            // 注册验证码
            final String uid             =   phoneNumber.getText().toString();
            final String code            =   codeText.getText().toString();
            // 设备名称
            String dev               =   Param.Device;
            // 网络请求地址
            String url               =   HtppUrlPath.okfwcIsOk;
            RequestParams params1    =   new RequestParams();
            final JSONObject obj     =   new JSONObject();
            try{
                obj.put("uid", uid);
                obj.put("yzm", code);
                obj.put("dev", dev);

            }catch (JSONException ex){

            }
            params1.put("pj",obj.toString());
            Param.sendPost(this,url, params1, new JsonDataHandle() {
                @Override
                public void dealJSONData(JSONObject object) {
                    try{
                        hasVialCode(uid);
                    }catch (Exception ex){

                    }
                }
            });
        }

    }

    public boolean codeVail(){
        return (codeText.getText().toString().matches("\\d{6}$"));
    }

    public void hasVialCode(String phone ){
        Intent intent = new Intent();
        intent.setClass(this, EditPassworld.class);
        intent.putExtra("registerphone",phone);
        intent.putExtra("ct",1);
        Log.d("收到的数据123 = ",phone);
        startActivity(intent);
        finish();
    }


    // 判断是否输入手机号
    public boolean phoneNumberIsVai(){
        return (phoneNumber.getText().toString().matches("^(13|14|15|18)\\d{9}$"));
    }

    // 验证码有效性
    public boolean codeIsVail(){
       return  (codeText.getText().toString().matches("\\d{6}$"));
    }

}
