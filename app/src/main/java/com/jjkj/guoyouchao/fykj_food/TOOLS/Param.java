package com.jjkj.guoyouchao.fykj_food.TOOLS;

/**
 * Created by guoyouchao on 16/6/22.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.jjkj.guoyouchao.fykj_food.JsonDataHandle;
import com.loopj.android.http.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.httpclient.android.BuildConfig;

public class Param {

    public static  final String ptbsname = "tbs";
    public static  final String successkey = "successkey";
    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 5000; //超时时间
    private static final String CHARSET = "utf-8"; //设置编码
    public static final boolean SUCCESS = true;
    public static final boolean FAILURE = false;

    public static String PJ = "pj";
    // 获得设备名称 和 设备型号
    public static String Device = "设备名称:" + Build.MODEL + "设备OS型号:" + Build.DEVICE;
    // 获得当前设备所在的位置

    // 发送JSON数据给服务器端
    public static void sendPost(final Activity activity, String url, RequestParams params1, final JsonDataHandle js) {
        final AsyncHttpClient client = new AsyncHttpClient();
        final ProgressDialog progressDialog;
        progressDialog = ProgressDialog.show(activity, "数据交换中...", "请等待网络返回数据", true, true);
//          progressDialog.setCancelable(false);
        client.post(url, params1, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                //处理数据接口
                int showtype = 0;
                String show     = "";

                try {
                    show     = response.getString("show");
                    showtype = response.getInt("st");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("success showtype = ",String.valueOf(showtype)+show);
                if(showtype == 0){

                }else if (showtype == 1){
                    js.dealJSONData(response);
                }

                try {
                    Param.showError(activity, show);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                progressDialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progressDialog.dismiss();
                client.cancelAllRequests(true);
                client.cancelRequests(activity.getApplicationContext(),true);
            }

        });
    }


    public static JSONArray getPSObject(JSONObject obj) throws JSONException {
        JSONObject alldata = obj.getJSONObject(Param.successkey);
        String  tbsdata = alldata.getString("tbs");
        JSONObject obj1 = new JSONObject(tbsdata);
        JSONArray jsonArray = obj1.getJSONArray("ps");
        return jsonArray;
    }

    public static void showError(Activity activity, String objson) throws JSONException {
        Toast.makeText(activity.getApplicationContext(), objson, Toast.LENGTH_SHORT).show();
    }

    public static void showMessage(Activity activity, String objson) throws JSONException {
        try {
            JSONObject obj = new JSONObject(objson);
            if (obj.getString("show") != null) {
                Toast.makeText(activity.getApplicationContext(), obj.getString("show").toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException ex) {

        }
    }

    public static boolean uploadFile(final Context cont, Bitmap photodata,String url){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流
            photodata.compress(Bitmap.CompressFormat.PNG, 100, baos);
            baos.close();
            byte[] buffer = baos.toByteArray();
            System.out.println("图片的大小："+buffer.length);

            //将图片的字节流数据加密成base64字符输出
            String photo = Base64.encodeToString(buffer, 0, buffer.length,Base64.DEFAULT);

            //photo=URLEncoder.encode(photo,"UTF-8");
            RequestParams params = new RequestParams();
            params.put("photo", photo);
            params.put("name", "woshishishi");//传输的字符数据



            AsyncHttpClient client = new AsyncHttpClient();
//            client.post(url, params, new AsyncHttpResponseHandler() {
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                    Toast.makeText(cont, "头像上传成功!",Toast.LENGTH_SHORT)
//                            .show();
//                }
//
//                @Override
//                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                    Toast.makeText(cont, "头像上传失败!", 0)
//                            .show();
//                }
//
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

