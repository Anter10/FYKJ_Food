package com.jjkj.guoyouchao.fykj_food;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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




    private InOutModel[] tablesData;
    private LayoutInflater layoutInflater;
    private Context context;

    public InOutCell(Context context, InOutModel[] data){
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
        return this.tablesData.length + 3;
    }

    @Override
    public Object getItem(int position) {
        return this.tablesData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        InoutModelView tmv = null;
        if(position == 0 && convertView==null){
            convertView=layoutInflater.inflate(R.layout.pmsingle, null);
        }else if(position == 3){
            convertView=layoutInflater.inflate(R.layout.graycolor, null);
        }else if(position == 6){
            convertView=layoutInflater.inflate(R.layout.graycolor, null);
        }else{
            if(convertView==null){
                tmv = new InoutModelView();
                //获得组件，实例化组件

                convertView=layoutInflater.inflate(R.layout.inoutsinglexml, null);
                tmv.title = (TextView)convertView.findViewById(R.id.title);
                tmv.subtitle = (TextView)convertView.findViewById(R.id.subtitle);
                convertView.setTag(tmv);
            }else{
                tmv=(InoutModelView)convertView.getTag();
            }
            int tp = position-1;
            if (position > 0 && position < 3){
                tp = position-1;
            }else if(position > 3 && position < 6){
                tp = position-2;
            }else if(position > 6){
                tp = position-3;
            }

            if(position != 7){
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickPos(position);
                    }
                });
            }

            Log.d("pos = ",String.valueOf(tp));
            tmv.title.setText(tablesData[tp].getTitle());
            tmv.subtitle.setText(tablesData[tp].getSubTitle());
        }

        //绑定事件



        return convertView;
    }

    public void clickPos(int position){
        Intent intens = null;
        if(position == 1){
            intens = new Intent();
            intens.setClass(this.context,ShopInActivity.class);
        }else if(position == 2){
            intens = new Intent();
            intens.setClass(this.context,ShopOutActivity.class);
        }else if(position == 3){

        }else if(position == 4){
            intens = new Intent();
            intens.setClass(this.context,MembersActivity.class);
        }else if(position == 5){
            intens = new Intent();
            intens.setClass(this.context,ProEditActivity.class);
        }else if(position == 6){

        }else if(position == 7){

        }
        if(intens != null){
            this.context.startActivity(intens);
        }
    }

}
