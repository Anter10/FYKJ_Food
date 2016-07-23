package com.jjkj.guoyouchao.fykj_food.UserDataModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by guoyouchao on 16/7/10.
 */
public class UserData {

    private static UserData userdata;

    private String userid = "暂无帐号";

    private List<FoodDataModel>  FDM = new LinkedList<FoodDataModel>();
    private List<TableSingModel> TSM = new LinkedList<TableSingModel>();


    public static void clearData(){
        userdata = new UserData();
    }

    private InOutModel[] iomodels = {null, null, null, null, null};

    public static UserData getUserdata() {
        if (userdata == null) {
            userdata = new UserData();
            userdata.initio();
        }
        return userdata;
    }

    public void initio() {
        String[] titles = {"店里收入", "店里支出", "相关店员", "权限设置", "退出帐号"};
        String[] subtitles = {" ", " ", " ", " ", " "};
        for (int i = 0; i < titles.length; i++) {
            InOutModel iom = new InOutModel();
            iom.setTitle(titles[i]);
            iom.setSubTitle(subtitles[i]);
            iomodels[i] = iom;
        }

    }

    public InOutModel[] getIomodels() {
        return iomodels;
    }

    public UserData() {

    }

    public UserData(JSONObject data) {

    }

    // 添加桌子
    public void addTableSingle(TableSingModel tsm) {
        if (tsm != null) {
            TSM.add(tsm);
        }
    }

    // 删除桌子
    public void removeTableSingle(TableSingModel tsm) {
        if (tsm != null) {
            TSM.remove(tsm);
        }
    }

    public LinkedList<TableSingModel> getTSM(){
        return  (LinkedList)TSM;
    }
    // 添加菜单
    public void addFoodData(FoodDataModel fdm) {
        if (fdm != null) {
            FDM.add(fdm);
        }
    }

    // 删除菜单
    public void removeFoodData(FoodDataModel fdm) {
        if (fdm != null) {
            FDM.remove(fdm);
        }
    }

    // 获得餐桌数据
    public TableSingModel getTableSingModelById(String tableId) {
        for (int i = 0 ; i < TSM.size(); i ++){
            if (TSM.get(i).getTableId() == tableId){
                return  TSM.get(i);
            }
        }
        return null;
    }

    // 获得菜单数据
    public FoodDataModel getFoodDataModelById(String tableId) {
        for (int i = 0 ; i < FDM.size(); i ++){
            if (TSM.get(i).getTableId() == tableId){
                return  FDM.get(i);
            }
        }
        return null;
    }

    // 更新餐桌数据
    public void updateTableSingleModel(JSONObject obj) {

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}