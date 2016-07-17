package com.jjkj.guoyouchao.fykj_food;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProEditActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_edit);
        getSupportActionBar().setTitle(R.string.shop_proedit_name);
    }
}
