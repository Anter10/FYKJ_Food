package com.jjkj.guoyouchao.fykj_food.TOOLS;

/**
 * Created by guoyouchao on 16/6/22.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.jjkj.guoyouchao.fykj_food.JsonDataHandle;
import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.httpclient.android.BuildConfig;

public class Param {

      public static String PJ = "pj";
      // 获得设备名称 和 设备型号
      public static String Device = "设备名称:"+Build.MODEL+"设备OS型号:"+Build.DEVICE;
      // 获得当前设备所在的位置

      // 发送JSON数据给服务器端
      public static void sendPost(final Activity activity,String url, RequestParams params1, final JsonDataHandle js){
          AsyncHttpClient client = new AsyncHttpClient();
          final ProgressDialog progressDialog;
          progressDialog = ProgressDialog.show(activity, "数据交换中...", "请等待网络返回数据", true,true);
//          progressDialog.setCancelable(false);
          client.post(url, params1, new JsonHttpResponseHandler(){

              @Override
              public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                  //处理数据接口
                  boolean success = true;
                  try {

                      if (response.getString("error") != null){
                          success = false;
                          Param.showError(activity,response.toString());
                      }
                  }catch (JSONException ex){

                  }
                  Log.d("服务器发送的数据 = ",response.toString()+success);
                  if(success){
                      js.dealJSONData(response);
                  }
                  progressDialog.dismiss();

              }

              @Override
              public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                  progressDialog.dismiss();
              }

          });
      }


     public static  void showError(Activity activity, String objson) throws JSONException {
        try {
            JSONObject obj = new JSONObject(objson);
            if(obj.getString("error") != null){
                Toast.makeText(activity.getApplicationContext(),obj.getString("error").toString(),Toast.LENGTH_SHORT).show();
            }
        }catch (JSONException ex){

        }
     }

    public static  void showMessage(Activity activity, String objson) throws JSONException {
        try {
            JSONObject obj = new JSONObject(objson);
            if(obj.getString("show") != null){
                Toast.makeText(activity.getApplicationContext(),obj.getString("show").toString(),Toast.LENGTH_SHORT).show();
            }
        }catch (JSONException ex){

        }
    }


}

