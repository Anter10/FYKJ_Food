package com.jjkj.guoyouchao.fykj_food;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by guoyouchao on 16/7/10.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final int pagecount = 4;

    private ShopsFragment shops = null;
    private InOutFragmentn inout = null;
    private GoesFoodFragment goesfood = null;
    private FoodFragment  foods = null;
    public ViewPagerAdapter(FragmentManager framan){
        super(framan);
        shops = new ShopsFragment();
        foods = new FoodFragment();
        inout = new InOutFragmentn();
        goesfood = new GoesFoodFragment();
    }

    @Override
    public int getCount(){
        return pagecount;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = shops;
                break;
            case 1:
                fragment = foods;
                break;
            case 2:
                fragment = goesfood;
                break;
            case 3:
                fragment = inout;
                break;
        }
        return fragment;
    }

}
