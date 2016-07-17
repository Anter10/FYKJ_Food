package com.jjkj.guoyouchao.fykj_food;

import android.app.Activity;
import android.content.Intent;
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

import com.jjkj.guoyouchao.fykj_food.TOOLS.HtppUrlPath;
import com.jjkj.guoyouchao.fykj_food.TOOLS.Param;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends CommonActivity {

    private Button   sendregcodeBtn ;
    private EditText phoneNumber    ;
    private EditText codeNumber;
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
        codeNumber     = (EditText)findViewById(R.id.input_codings);
        xyCheckbox    = (CheckBox)findViewById(R.id.support_xy);
        Button registerByn = (Button)findViewById(R.id.ok_register_user);

        registerByn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(supportXy() == false){
                    Toast.makeText(getApplicationContext(),"请同意放游菜单注册协议",Toast.LENGTH_SHORT).show();
                }else if(phoneNumberIsVai() == false){
                    Toast.makeText(getApplicationContext(),"手机号不对,请确定",Toast.LENGTH_SHORT).show();
                }else if(phoneNumberIsVai() == false){
                    Toast.makeText(getApplicationContext(),"验证码格式不对,请确定",Toast.LENGTH_SHORT).show();
                }else{
                    registerUser();
                }
            }
        });



        if(RegisterActivity.Curtime == 60){
            onClickSendCode();
        }else{
            phoneNumber.setText(RegisterActivity.prNumber);
            startTime();
        }

//        CountDownTimer
    }


    // 发送注册信息



    public void onClickSendCode(){
        sendregcodeBtn.setText(R.string.send_codeing);
        sendregcodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canclick == true){
                    startTime();
                    sendCode();
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
                }
            }.start();
        }else{
            Toast.makeText(this.getApplicationContext(),"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
        }


    }

   // 发送注册服务器请求
    public void sendCode(){
        // 注册验证码
        final String phone             =   phoneNumber.getText().toString();
        // 设备名称
        String dev               =   Param.Device;
        // 网络请求地址
        String url               =   HtppUrlPath.sendYZMByPhone;

        RequestParams params1    =   new RequestParams();
        final JSONObject obj     =   new JSONObject();
        try{
            obj.put("uid", phone);
            obj.put("dev", dev);

        }catch (JSONException ex){

        }
        params1.put("pj",obj.toString());
        Param.sendPost(this,url, params1, new JsonDataHandle() {
            @Override
            public void dealJSONData(JSONObject object) {
                try{
                   if(object.getString("error") != null){
                       canclick = true;
                       RegisterActivity.prNumber = "";
                       RegisterActivity.Curtime = 60;
                       onClickSendCode();
                   }else{

                   }
                }catch (Exception ex){

                }

                Log.d("收到的数据 = ",object.toString());
            }
        });
    }

    // 注册按钮调用
    public void registerUser(){
        // 注册验证码
       final String phone             =   phoneNumber.getText().toString();
        // 设备名称
        String dev               =   Param.Device;
        String code              =   codeNumber.getText().toString();
        // 网络请求地址
        String url               =   HtppUrlPath.registerByPhonePath;

        RequestParams params1    =   new RequestParams();
        final JSONObject obj     =   new JSONObject();
        try{
            obj.put("uid", phone);
            obj.put("dev", dev);
            obj.put("yzm",code);

        }catch (JSONException ex){

        }
        params1.put("pj",obj.toString());
        Param.sendPost(this,url, params1, new JsonDataHandle() {
            @Override
            public void dealJSONData(JSONObject object) {
                try{
                  editPwd(phone);
                }catch (Exception ex){

                }
            }
        });
    }

    public void   editPwd(String phone){
        Intent intent = new Intent();
        intent.setClass(this, EditPassworld.class);
        intent.putExtra("registerphone",phone);
        intent.putExtra("ct",0);
        Log.d("收到的数据123 = ",phone);
        startActivity(intent);
        finish();
    }
    // 判断是否输入手机号
    public boolean phoneNumberIsVai(){
       return (phoneNumber.getText().toString().matches("^(13|15|18)\\d{9}$"));
    }

    // 判断是否输入手机号
    public boolean codeNumberIsVai(){
        return (codeNumber.getText().toString().matches("\\d{6}$"));
    }
    // 判断是否选择协议
    public boolean supportXy(){
        return xyCheckbox.isChecked();
    }

}
