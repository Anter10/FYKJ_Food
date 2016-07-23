package com.jjkj.guoyouchao.fykj_food;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjkj.guoyouchao.fykj_food.TOOLS.HtppUrlPath;
import com.jjkj.guoyouchao.fykj_food.TOOLS.Param;
import com.jjkj.guoyouchao.fykj_food.UserDataModel.TableSingModel;
import com.jjkj.guoyouchao.fykj_food.UserDataModel.UserData;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class AddTableActivity extends CommonActivity {

    private EditText tableId;
    private EditText tableType;
    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_table);
        getSupportActionBar().setTitle(R.string.add_table_word);

        tableId      =  (EditText)findViewById(R.id.table_id);
        tableType    =  (EditText)findViewById(R.id.table_type);

        addButton    =  (Button)findViewById(R.id.addtable_ok_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTable(v);
            }
        });
    }

    // 添加
    public void addTable(View v){
        String msg = "餐桌编号不能为空";
        if(typeVailed() == true){
            msg = "餐桌的类型不能为空";
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
        }else  if(idVailed() == true){
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
        }else{
            postTableData();
        }
    }


    // 请求添加
    public void postTableData(){
        // 输入登陆电话
        String tableid           =   tableId.getText().toString();
        String dev               =   Param.Device;
        String userid            =   UserData.getUserdata().getUserid();
        String url               =   HtppUrlPath.addTablePath;
        String tabletype         =   tableType.getText().toString();
        RequestParams params1    =   new RequestParams();
        final JSONObject obj     =   new JSONObject();
        try{
            obj.put("uid", userid);
            obj.put("dev", dev);
            obj.put("tid", tableid);
            obj.put("ttype", tabletype);
        }catch (JSONException ex){

        }
        params1.put("pj",obj.toString());
        Param.sendPost(this,url, params1, new JsonDataHandle() {
            @Override
            public void dealJSONData(JSONObject object) {
                try{
                    JSONObject tbj = (JSONObject) Param.getPSObject(object).get(0);
                    TableSingModel tsm = new TableSingModel(tbj);
                    UserData.getUserdata().addTableSingle(tsm);

                    Toast.makeText(getApplicationContext(),"添加桌号成功",Toast.LENGTH_SHORT).show();
                }catch (Exception ex){

                }
                Log.d("收到的数据 = ",object.toString());

            }
        });
    }

    // 判断输入ID 是否有效
    public boolean idVailed(){
        return (tableId.getText().toString().isEmpty());
    }

    // 判断输入类型是否正确
    public boolean typeVailed(){
        return (tableType.getText().toString().isEmpty());
    }



}
