package com.jjkj.guoyouchao.fykj_food;


import java.io.ByteArrayOutputStream;
import java.io.File;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jjkj.guoyouchao.fykj_food.TOOLS.HtppUrlPath;
import com.jjkj.guoyouchao.fykj_food.TOOLS.Param;

import cz.msebera.android.httpclient.client.methods.HttpPost;

public class AddFoodActivity extends CommonActivity {

    private static final int PHOTO_REQUEST_CAREMA = 1;// ≈ƒ’’
    private static final int PHOTO_REQUEST_GALLERY = 2;// ¥”œ‡≤·÷–—°‘Ò
    private static final int PHOTO_REQUEST_CUT = 3;// Ω·π˚
    public Button  firstBtn;
    private ImageView iv_image;
    Bitmap bitmap;
    /* Õ∑œÒ√˚≥∆ */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        getSupportActionBar().setTitle(R.string.add_food_word);

        firstBtn = (Button)findViewById(R.id.first_ok_btn);
        firstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadIcon();
            }
        });
        iv_image = (ImageView)findViewById(R.id.food_image);
        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddFoodActivity.this);
                final String[] cities = {"相机拍照","相册选择"};

                builder.setItems(cities, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(which == 0){
                            seletCamera();
                        }else if(which == 1){
                            gallery();
                        }
                    }
                });
                builder.show();
            }
        });
    }


    private void crop(Uri uri) {
        // ≤√ºÙÕº∆¨“‚Õº
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // ≤√ºÙøÚµƒ±»¿˝£¨1£∫1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // ≤√ºÙ∫Û ‰≥ˆÕº∆¨µƒ≥ﬂ¥Á¥Û–°
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// Õº∆¨∏Ò Ω
        intent.putExtra("noFaceDetection", true);// »°œ˚»À¡≥ ∂±
        intent.putExtra("return-data", true);
        // ø™∆Ù“ª∏ˆ¥¯”–∑µªÿ÷µµƒActivity£¨«Î«Û¬ÎŒ™PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    public void gallery() {
        // º§ªÓœµÕ≥Õºø‚£¨—°‘Ò“ª’≈Õº∆¨
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // ø™∆Ù“ª∏ˆ¥¯”–∑µªÿ÷µµƒActivity£¨«Î«Û¬ÎŒ™PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    // 选择照片
    public void selectImage(){
        Intent imageIntent = new Intent(Intent.ACTION_PICK);
        imageIntent.setType("image/*");
        startActivityForResult(imageIntent,1);
    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    // 选择相机
    public void seletCamera(){

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // ≈–∂œ¥Ê¥¢ø® «∑Òø…“‘”√£¨ø…”√Ω¯––¥Ê¥¢
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    PHOTO_FILE_NAME);
            // ¥”Œƒº˛÷–¥¥Ω®uri
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        // ø™∆Ù“ª∏ˆ¥¯”–∑µªÿ÷µµƒActivity£¨«Î«Û¬ÎŒ™PHOTO_REQUEST_CAREMA
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // ¥”œ‡≤·∑µªÿµƒ ˝æ›
            if (data != null) {
                // µ√µΩÕº∆¨µƒ»´¬∑æ∂
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // ¥”œ‡ª˙∑µªÿµƒ ˝æ›
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(AddFoodActivity.this, "哈哈哈哈", Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // ¥”ºÙ«–Õº∆¨∑µªÿµƒ ˝æ›
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
//                ByteArrayOutputStream ss = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG,100, ss);
//                bitmap.setWidth(160);
//                bitmap.setHeight(160);
                this.iv_image.setImageBitmap(bitmap);
            }
            try {
                // Ω´¡Ÿ ±Œƒº˛…æ≥˝
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    public void uploadIcon(){
//        Param.uploadFile(this, bitmap, HtppUrlPath.dealimage);
    }


}
