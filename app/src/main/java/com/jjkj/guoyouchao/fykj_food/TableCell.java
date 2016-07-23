package com.jjkj.guoyouchao.fykj_food;

import android.content.Context;
import android.opengl.EGLDisplay;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jjkj.guoyouchao.fykj_food.UserDataModel.TableSingModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by guoyouchao on 16/7/10.
 */
public class TableCell extends BaseAdapter {

    private LinkedList<TableSingModel> tablesData;
    private LayoutInflater layoutInflater;
    private Context context;

    public TableCell(Context context, LinkedList<TableSingModel>  data){
        this.context = context;
        this.tablesData = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public TableCell(){

    }

    public final class TableModelView{
        private TextView  tablename               =     null;    // 桌名
        private TextView  tabletype               =     null;    // 餐桌可坐人数
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
        TableSingModel tb = tablesData.get(position);
        if(convertView==null){
            tmv = new TableModelView();

            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.tablesinglexml, null);
            tmv.tablename = (TextView) convertView.findViewById(R.id.table_ms_id);
            tmv.tabletype = (TextView) convertView.findViewById(R.id.table_ms_size);
            convertView.setTag(tmv);
        }else{
            tmv=(TableModelView)convertView.getTag();
        }
        //绑定数据
        tmv.tablename.setText(tb.getTableName());
        tmv.tabletype.setText(tb.getTableType());
        return convertView;
    }
}
