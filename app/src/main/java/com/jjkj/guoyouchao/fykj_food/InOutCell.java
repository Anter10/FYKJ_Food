package com.jjkj.guoyouchao.fykj_food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jjkj.guoyouchao.fykj_food.UserDataModel.InOutModel;
import com.jjkj.guoyouchao.fykj_food.UserDataModel.TableSingModel;

import java.util.List;
import java.util.Map;

/**
 * Created by guoyouchao on 16/7/12.
 */
public class InOutCell extends BaseAdapter {




    private List<Map<String, InOutModel>> tablesData;
    private LayoutInflater layoutInflater;
    private Context context;

    public InOutCell(Context context, List<Map<String, InOutModel>> data){
        this.context = context;
        this.tablesData = data;
        this.layoutInflater = LayoutInflater.from(context);
    }


    public final class InoutModelView{
        private TextView title                   =     null;    // 桌名
        private TextView  subtitle               =     null;    // 餐桌可坐人数
    }


    @Override
    public int getCount() {
        return this.tablesData.size();
    }

    @Override
    public Object getItem(int position) {
        return this.tablesData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InoutModelView tmv = null;
        if(convertView==null){
            tmv = new InoutModelView();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.inoutsinglexml, null);

            convertView.setTag(tmv);
        }else{
            tmv=(InoutModelView)convertView.getTag();
        }
        //绑定数据

        return convertView;
    }


}
