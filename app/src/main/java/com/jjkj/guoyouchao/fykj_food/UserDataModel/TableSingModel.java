package com.jjkj.guoyouchao.fykj_food.UserDataModel;

// 餐桌的数据

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by guoyouchao on 16/7/10.
 */
public class TableSingModel {
    private int       tableId                 =     0;               // 餐桌的编号
    private String    tableName               =     "请设置桌名";    // 桌名
    private String    tableSize               =     "1人";           // 餐桌可坐人数
    private String    tableInfo               =     "餐桌靠近窗户";   // 餐桌简介
    private int       tableStatue             =     0;              // 餐桌的状态 0:有客人 1 无客人
    private int       tableNum                =     0;              // 今日在该餐厅的就餐次数

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public TableSingModel(){

    }



    public TableSingModel(JSONObject obj) throws JSONException {
        if (obj.get("tableId") != null){
            this.tableId = (int)obj.get("tableId");
        }
        if(obj.getString("tableSize") != null){
            this.tableSize = obj.getString("tableSize");
        }

        if(obj.getString("tableName") != null){
            this.tableName = obj.getString("tableName");
        }

        if(obj.getString("tableInfo") != null){
            this.tableInfo = obj.getString("tableInfo");
        }

        if(obj.get("tableStatue") != null){
            this.tableStatue = (int)obj.get("tableStatue");
        }

        if(obj.get("tableNum") != null){
            this.tableNum = (int)obj.get("tableNum");
        }
    }

    public void upadteData(JSONObject obj){
        try {
            if(obj.getInt("tableId") == this.getTableId()){
               if(obj.getString("tableSize") != null){
                   this.tableSize = obj.getString("tableSize");
               }
                if(obj.getString("tableInfo") != null){
                   this.tableInfo = obj.getString("tableInfo");
                }
                if(obj.getString("tableName") != null){
                    this.tableName = obj.getString("tableName");
                }
                if(obj.get("tableStatue") != null){
                    this.tableStatue = (int)obj.get("tableStatue");
                }

                if(obj.get("tableNum") != null){
                    this.tableNum = (int)obj.get("tableNum");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableSize() {
        return tableSize;
    }

    public void setTableSize(String tableSize) {
        this.tableSize = tableSize;
    }

    public String getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(String tableInfo) {
        this.tableInfo = tableInfo;
    }

    public int getTableStatue() {
        return tableStatue;
    }

    public void setTableStatue(int tableStatue) {
        this.tableStatue = tableStatue;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }
}
