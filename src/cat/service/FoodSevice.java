package cat.service;

import cat.bean.Food;
import cat.bean.ShowFood;
import cat.dao.FoodDao;
import net.sf.json.JSONArray;

import java.util.List;

public class FoodSevice {
//    将String类型解析成Food对象类型
    public Food toFood(String str){
        String[] arr = str.split("&");
        Food food = new Food();
        food.setFoodimg(arr[0]);
        food.setFoodname(arr[1]);
        food.setFoodtype(arr[2]);
        food.setFoodprice(arr[3]);
        food.setFoodinfo(arr[4]);
        return food;
    }

    public boolean delfood(String foodname){
        int rs=0;
        FoodDao staffDao = new FoodDao();
        if(staffDao.delete(foodname)!=-1){
            return true;
        }else{
            return false;
        }
    }


    //每页5条记录
    public JSONArray jsonselectFood(int currentpage){
        FoodDao foodDao = new FoodDao();
        List<Food> foods = foodDao.getFoodForm2(currentpage);
        for (int i = 0; i < foods.size(); i++) {
            System.out.println(foods.get(i));
        }
        JSONArray res = JSONArray.fromObject(foods);
        return res;
    }


    //每页8条记录
    public JSONArray jsonselectFood2(int currentpage){
        FoodDao foodDao = new FoodDao();
        List<Food> foods = foodDao.getFoodForm(currentpage);
        for (int i = 0; i < foods.size(); i++) {
            System.out.println(foods.get(i));
        }
        JSONArray res = JSONArray.fromObject(foods);
        return res;
    }

    //分组每页8条记录
    public JSONArray jsonselectFoodGroup(int currentpage,String foodType){
        FoodDao foodDao = new FoodDao();
        List<Food> foods = foodDao.getFoodGroupbyForm(currentpage,foodType);
        for (int i = 0; i < foods.size(); i++) {
            System.out.println(foods.get(i));
        }
        JSONArray res = JSONArray.fromObject(foods);
        return res;
    }

    //打印记录数然后再处理记录数据
    public String foodTypeNum(String foodType){
        FoodDao foodDao = new FoodDao();
        List<ShowFood> showFoods = foodDao.selectTypeNum();
        for (int i = 0; i < showFoods.size(); i++) {
           if(showFoods.get(i).getFoodtype().equals(foodType)){
               return showFoods.get(i).getNumber();
           }
        }
        return null;
    }


    public static void main(String[] args){
        System.out.println(new FoodSevice().jsonselectFood2(1));

    }
}
