package com.jjkj.guoyouchao.fykj_food;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddTableActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_table);
        getSupportActionBar().setTitle(R.string.add_table_word);
    }
}
