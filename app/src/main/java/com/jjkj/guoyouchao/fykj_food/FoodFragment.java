package com.jjkj.guoyouchao.fykj_food;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jjkj.guoyouchao.fykj_food.UserDataModel.FoodDataModel;
import com.jjkj.guoyouchao.fykj_food.UserDataModel.TableSingModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FoodFragment extends Fragment {
    private ListView listView;
    private  View  rootView;

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_food, container, false);

//        Log.d("ddsdsdsdsd = ",String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER)));
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        listView =(ListView)rootView.findViewById(R.id.foods);

        initViews() ;


        return rootView;
    }
    // 初始化餐桌数据
    public List<Map<String, FoodDataModel>> getData(){
        List<Map<String, FoodDataModel>> list=new ArrayList<Map<String,FoodDataModel>>();
        for (int i = 0; i < 10; i++) {
            Map<String, FoodDataModel> map=new HashMap<String, FoodDataModel>();
            list.add(map);
        }
        return list;
    }

    //
    public void initViews(){
//        if(Sections == 1){
        List<Map<String, FoodDataModel>> list = getData();
        listView.addFooterView(new View(this.getContext()));
        listView.setAdapter(new FoodsCell(rootView.getContext(), list));
//        }
    }


}
