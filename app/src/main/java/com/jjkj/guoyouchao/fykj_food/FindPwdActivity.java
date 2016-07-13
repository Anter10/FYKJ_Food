package com.jjkj.guoyouchao.fykj_food;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class FindPwdActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pwd);
        getSupportActionBar().setTitle(R.string.find_passwrod_word);
    }

}
