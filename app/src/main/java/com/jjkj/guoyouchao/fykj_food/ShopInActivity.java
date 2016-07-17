package com.jjkj.guoyouchao.fykj_food;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShopInActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_in);
        getSupportActionBar().setTitle(R.string.shop_in_name);
    }
}
