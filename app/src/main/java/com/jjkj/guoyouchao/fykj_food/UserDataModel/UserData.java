package com.jjkj.guoyouchao.fykj_food.UserDataModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by guoyouchao on 16/7/10.
 */
public class UserData {

    private static UserData userdata;


    List<Map<String, FoodDataModel>> FDM =new ArrayList<Map<String,FoodDataModel>>();
    List<Map<String, TableSingModel>> TSM =new ArrayList<Map<String,TableSingModel>>();


    public static UserData getUserdata(){
        if(userdata == null){
           userdata = new UserData(new JSONObject());
        }
        return userdata;
    }


    public UserData(JSONObject data){

    }

    // 添加桌子
    public void addTableSingle(TableSingModel tsm){
        if(tsm != null){
            Map<String, TableSingModel> map = new HashMap<String, TableSingModel>();
            map.put(String.valueOf(tsm.getTableId()),tsm);
            TSM.add(TSM.size(),map);
        }
    }

    // 删除桌子
    public void removeTableSingle(TableSingModel tsm){
        if(tsm != null){
            TSM.remove(tsm);
        }
    }

    // 添加菜单
    public void addFoodData(FoodDataModel fdm){
        if(fdm != null){
            Map<String, FoodDataModel> map = new HashMap<String, FoodDataModel>();
            map.put(String.valueOf(fdm.getFoodId()),fdm);
            FDM.add(map);
        }
    }

    // 删除菜单
    public void removeFoodData(FoodDataModel fdm){
        if(fdm != null){
            FDM.remove(fdm);
        }
    }

    // 获得餐桌数据
    public TableSingModel getTableSingModelById(String tableId){
        for(int i = 0; i < TSM.size(); i ++){
            if(TSM.get(i).get(tableId) != null ){
                return TSM.get(i).get(tableId);
            }
        }
        return null;
    }

    // 获得菜单数据
    public FoodDataModel getFoodDataModelById(String tableId){
        for(int i = 0; i < FDM.size(); i ++){
            if(FDM.get(i).get(tableId) != null ){
                return FDM.get(i).get(tableId);
            }
        }
        return null;
    }

    // 更新餐桌数据
    public void updateTableSingleModel(JSONObject obj){

    }


    // 餐厅餐桌数据



}
