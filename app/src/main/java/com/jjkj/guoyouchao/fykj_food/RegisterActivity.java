package com.jjkj.guoyouchao.fykj_food;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class RegisterActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle(R.string.register_new_user_word);
    }



}
