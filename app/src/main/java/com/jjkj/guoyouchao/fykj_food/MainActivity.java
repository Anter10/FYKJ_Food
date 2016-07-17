package com.jjkj.guoyouchao.fykj_food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jjkj.guoyouchao.fykj_food.TOOLS.HtppUrlPath;
import com.jjkj.guoyouchao.fykj_food.TOOLS.Param;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;
import com.jjkj.guoyouchao.fykj_food.JsonDataHandle;

public class MainActivity extends AppCompatActivity {


    private TextView forgetPwd;
    private TextView registerId;
    private Button   firstOkBtn;

    private EditText pwdText;
    private EditText idText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forgetPwd   =  (TextView)findViewById(R.id.forgetPwd)   ;
        registerId  =  (TextView)findViewById(R.id.registerNew) ;
        firstOkBtn  =  (Button)findViewById(R.id.first_ok_btn)  ;

        pwdText     =  (EditText)findViewById(R.id.ok_input_pwd);
        idText      =  (EditText)findViewById(R.id.ok_input_id);

//        String regisId =  getIntent().getExtras().getString("uid");
//        if( regisId != null){
//            idText.setText(regisId);
//        }

        forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               forget();
            }
        });


        registerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        firstOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        if( canLogin() == true){
            try {
                loginServer();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void loginServer() throws JSONException {
        // 输入登陆电话
        String email             =   idText.getText().toString();
        // 设备名称
        String dev               =   Param.Device;
        // 网络请求地址
        String url               =   HtppUrlPath.phoneLoginPath;
        String yzm               =   pwdText.getText().toString();
        RequestParams params1    =   new RequestParams();
        final JSONObject obj     =   new JSONObject();
        try{
            obj.put("uid", email);
            obj.put("dev", dev);
            obj.put("pwd", yzm);
        }catch (JSONException ex){

        }

        params1.put("pj",obj.toString());
        Param.sendPost(this,url, params1, new JsonDataHandle() {
            @Override
            public void dealJSONData(JSONObject object) {
                try{
                    loginSuccess();
                }catch (Exception ex){

                }
                Log.d("收到的数据 = ",object.toString());
            }
        });
    }

    public void loginSuccess(){
        Intent mainIntent = new Intent(this.getApplicationContext(),APPMainActivity.class);
        startActivity(mainIntent);
        this.finish();
    }


    // 点击注册的时候调用
    public void register(){
        Intent intent = new Intent();
        intent.setClass(this ,RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    // 点击注册的时候调用
    public void forget(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        final String[] cities = {"手机找回","邮箱找回"};
//        builder.setItems(cities, new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//
//                findMethod(which);
//            }
//        });
//        builder.show();
        Intent intent = new Intent();
        intent.setClass(this ,FindPwdActivity.class);
        startActivity(intent);
    }

    // 点击确定的时候调用
    public void login(){
        try {
            loginServer();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // 选择找回密码的方式
    public void selectFindMethod(){

    }

    // 选择找回密码的方式
    public void findMethod(int index){

    }

    public boolean canLogin(){
        boolean yes = false;
        if(pwdText.getText().toString().length() > 0  && idText.getText().toString().length() == 11){
            yes = true;
        }

        return yes;
    }

}
