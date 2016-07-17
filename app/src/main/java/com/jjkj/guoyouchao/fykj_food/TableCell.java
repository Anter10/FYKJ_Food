package com.jjkj.guoyouchao.fykj_food;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jjkj.guoyouchao.fykj_food.UserDataModel.TableSingModel;

import java.util.List;
import java.util.Map;

/**
 * Created by guoyouchao on 16/7/10.
 */
public class TableCell extends BaseAdapter {

    private List<Map<String, TableSingModel>> tablesData;
    private LayoutInflater layoutInflater;
    private Context context;

    public TableCell(Context context, List<Map<String, TableSingModel>> data){
        this.context = context;
        this.tablesData = data;
        this.layoutInflater = LayoutInflater.from(context);
    }


    public final class TableModelView{
        private TextView  tableName               =     null;    // 桌名
        private TextView  tableSize               =     null;    // 餐桌可坐人数
        private TextView  tableInfo               =     null;    // 餐桌简介
        private TextView  tableStatue             =     null;    // 餐桌的状态 0:有客人 1 无客人
        private TextView  tableNum                =     null;    // 今日在该餐厅的就餐次数
        private TextView  endThis                 =     null;    // 结账
        private TextView  tapFood                 =     null;    // 点菜
        private TextView  editPerson              =     null;    // 设置餐桌结账的人
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
        TableModelView tmv = null;
        if(convertView==null){
            tmv = new TableModelView();

            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.tablesinglexml, null);

            convertView.setTag(tmv);
        }else{
            tmv=(TableModelView)convertView.getTag();
        }
        //绑定数据


        return convertView;
    }
}
