package com.jjkj.guoyouchao.fykj_food;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class APPMainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {

    private RadioGroup  tabs;
    private RadioButton shops;
    private RadioButton tabfood;
    private RadioButton inout;
    private RadioButton goesfood;
    private ViewPager   vpager;
    private ViewPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appmain);
        getSupportActionBar().setTitle("放游菜单");
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        findViews();
        shops.setChecked(true);
    }

    public void findViews(){

        tabs = (RadioGroup)findViewById(R.id.bars);
        shops = (RadioButton)findViewById(R.id.shops);

        tabfood = (RadioButton)findViewById(R.id.tab_food);
        inout = (RadioButton)findViewById(R.id.in_out);
        goesfood =  (RadioButton)findViewById(R.id.go_food);
        vpager = (ViewPager) findViewById(R.id.Views);
        tabs.setOnCheckedChangeListener(this);

        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       //noinspection SimplifiableIfStatement
        if (id == R.id.addfood) {

            Intent food = new Intent(this, AddFoodActivity.class);
            startActivity(food);
            return true;
        }else if (id == R.id.addtables) {
            Intent friend = new Intent(this, AddTableActivity.class);
            startActivity(friend);
            return true;
        }else if(id == R.id.addfriends){
            Intent friend = new Intent(this, AddFriendsActivity.class);
            startActivity(friend);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.shops:
                vpager.setCurrentItem(0);
                break;
            case R.id.tab_food:
                vpager.setCurrentItem(1);
                break;
            case R.id.go_food:
                vpager.setCurrentItem(2);
                break;
            case R.id.in_out:
                vpager.setCurrentItem(3);
                break;

        }
    }

    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case 0:
                    shops.setChecked(true);
                    break;
                case 1:
                    tabfood.setChecked(true);
                    break;
                case 2:
                    goesfood.setChecked(true);
                    break;
                case 3:
                    inout.setChecked(true);
                    break;
            }
        }
    }

}
