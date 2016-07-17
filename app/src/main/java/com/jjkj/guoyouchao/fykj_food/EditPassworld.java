package com.jjkj.guoyouchao.fykj_food;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjkj.guoyouchao.fykj_food.TOOLS.HtppUrlPath;
import com.jjkj.guoyouchao.fykj_food.TOOLS.Param;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class EditPassworld extends CommonActivity {


    private EditText pwd1;
    private EditText pwd2;
    private Button   okBtn;
    private String   regisId;
    private int ctype = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_passworld);
        getSupportActionBar().setTitle(R.string.edit_password);
        regisId =  getIntent().getExtras().getString("registerphone");
        ctype   = getIntent().getExtras().getInt("ct");

        pwd1  = (EditText)findViewById(R.id.pwd1);
        pwd2  = (EditText)findViewById(R.id.pwd2);
        okBtn = (Button)findViewById(R.id.editpwd);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOk();
            }
        });



    }


    public void clickOk(){
        if(pw1Vaild() == true && pw2Vaild() == true && issame() == true){
            okEditPWD();
        }else if(pw1Vaild() == false){
            Log.d("收到的数据1 = ",String.valueOf(pw1Vaild()));
            Toast.makeText(getApplicationContext(),"密码必须大于6位", Toast.LENGTH_SHORT);
        }else if(pw2Vaild() == false){
            Log.d("收到的数据2 = ",String.valueOf(pw1Vaild()));
            Toast.makeText(getApplicationContext(),"确认密码不一致", Toast.LENGTH_SHORT);
        }else if(issame() == false){
            Log.d("收到的数据3 = ",String.valueOf(pw1Vaild()));
            Toast.makeText(getApplicationContext(),"两次密码不一致", Toast.LENGTH_SHORT);
        }
    }


    public void okEditPWD(){
        // 注册验证码
        final String pwd             =   pwd1.getText().toString();
        // 设备名称
        String dev               =   Param.Device;
        // 网络请求地址
        String url               =   HtppUrlPath.setPwd;

        RequestParams params1    =   new RequestParams();
        final JSONObject obj     =   new JSONObject();
        try{
            obj.put("pwd", pwd);
            obj.put("dev", dev);
            obj.put("uid", regisId);
            obj.put("ct", ctype);
        }catch (JSONException ex){

        }
        Log.d("收到的数据 = ","21212");
        params1.put("pj",obj.toString());
        Param.sendPost(this,url, params1, new JsonDataHandle() {
            @Override
            public void dealJSONData(JSONObject object) {
                try{
                    Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT);
                    gomain();
                }catch (Exception ex){

                }


            }
        });
    }

    public void gomain(){
        Intent intent = new Intent();
        intent.setClass(this ,MainActivity.class);
        intent.putExtra("uid",regisId);
        startActivity(intent);
        finish();
    }

    public boolean pw1Vaild(){
        boolean vai = true;
        if(pwd1.getText().toString().length() < 6){
            vai = false;
        }
        return vai;
    }

    public boolean pw2Vaild(){
        boolean vai = true;
        if(pwd2.getText().toString().length() < 6){
            vai = false;
        }
        return vai;
    }

    public boolean issame(){
        boolean vai = false;
        String v1 = pwd1.getText().toString();
        String v2 = pwd2.getText().toString();
        if(v1.equals(v2)){
            vai = true;
            Log.d("adadasd","dadada");
        }

        return vai;
    }
}
