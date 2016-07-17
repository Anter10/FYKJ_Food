package com.jjkj.guoyouchao.fykj_food;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShopOutActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_out);
        getSupportActionBar().setTitle(R.string.shop_out_name);
    }
}
