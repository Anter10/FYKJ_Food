package com.jjkj.guoyouchao.fykj_food;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jjkj.guoyouchao.fykj_food.UserDataModel.InOutModel;
import com.jjkj.guoyouchao.fykj_food.UserDataModel.TableSingModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InOutFragmentn extends Fragment {


    private  ListView  listView;
    private  View  rootView;


    public InOutFragmentn() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_in_out_fragmentn, container, false);

//        Log.d("ddsdsdsdsd = ",String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER)));
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        listView =(ListView)rootView.findViewById(R.id.inouts);

        initViews() ;


        return rootView;
    }

    public List<Map<String, InOutModel>> getData(){
        List<Map<String, InOutModel>> list=new ArrayList<Map<String,InOutModel>>();
        for (int i = 0; i < 10; i++) {
            Map<String, InOutModel> map=new HashMap<String, InOutModel>();
            list.add(map);
        }
        return list;
    }
    public void initViews(){
//        if(Sections == 1){
        List<Map<String, InOutModel>> list = getData();
//        listView.addFooterView(new View(this.getContext()));
        listView.setAdapter(new InOutCell(rootView.getContext(), list));
//        }
    }



}
