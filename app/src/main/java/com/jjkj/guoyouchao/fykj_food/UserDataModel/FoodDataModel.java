package com.jjkj.guoyouchao.fykj_food.UserDataModel;

// 菜的数据模型

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by guoyouchao on 16/7/10.
 */
public class FoodDataModel {
    private int    foodId           =           0;                       // 菜的ID
    private String foodType         =           "请设置菜品的类型";        // 菜品的类型
    private String foodName         =           "请设置菜品的名称";        // 菜品的名称
    private String foodInfo         =           "请设置菜品的简介";        // 菜品的简介
    private String foodImage        =           "请上传菜品图片";         //  菜品的图片
    private int    foodPrice        =           0;                      //  菜品的价格
    private int    daySellNum      =            0;                      //  菜品单日销量
    private int    monthSellNum      =          0;                      //  菜品单月销量


    public FoodDataModel(){

    }

    public FoodDataModel(JSONObject obj) throws JSONException {
        if(obj.get("foodId") != null){
            this.foodId = (int)obj.get("foodId");
        }
        if(obj.getString("foodType") != null){
            this.foodType = obj.getString("foodType");
        }
        if(obj.getString("foodName") != null){
            this.foodName = obj.getString("foodName");
        }
        if(obj.getString("foodInfo") != null){
            this.foodInfo = obj.getString("foodInfo");
        }
        if(obj.getString("foodImage") != null){
            this.foodImage = obj.getString("foodImage");
        }


        if(obj.get("foodPrice") != null){
            this.foodPrice = (int)obj.get("foodPrice");
        }

        if(obj.get("daySellNum") != null){
            this.daySellNum = (int)obj.get("daySellNum");
        }

        if(obj.get("monthSellNum") != null){
            this.monthSellNum = (int)obj.get("monthSellNum");
        }
    }


    public void upadteData(JSONObject obj){
        try {
            if(obj.getInt("foodId") == this.getFoodId()){
                if(obj.getString("foodType") != null){
                    this.foodType = obj.getString("foodType");
                }
                if(obj.getString("foodName") != null){
                    this.foodName = obj.getString("foodName");
                }
                if(obj.getString("foodInfo") != null){
                    this.foodInfo = obj.getString("foodInfo");
                }
                if(obj.getString("foodImage") != null){
                    this.foodImage = obj.getString("foodImage");
                }


                if(obj.get("foodPrice") != null){
                    this.foodPrice = (int)obj.get("foodPrice");
                }

                if(obj.get("daySellNum") != null){
                    this.daySellNum = (int)obj.get("daySellNum");
                }

                if(obj.get("monthSellNum") != null){
                    this.monthSellNum = (int)obj.get("monthSellNum");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(String foodInfo) {
        this.foodInfo = foodInfo;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getDaySellNum() {
        return daySellNum;
    }

    public void setDaySellNum(int daySellNum) {
        this.daySellNum = daySellNum;
    }

    public int getMonthSellNum() {
        return monthSellNum;
    }

    public void setMonthSellNum(int monthSellNum) {
        this.monthSellNum = monthSellNum;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }




}
