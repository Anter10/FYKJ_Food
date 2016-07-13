package com.jjkj.guoyouchao.fykj_food;

import android.os.Bundle;
import android.app.Activity;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class CommonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.return_function);

//        ac.setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeAsUpIndicator(R.drawable.buttonshape);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

}
