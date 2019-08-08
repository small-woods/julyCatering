package cat.service;

import cat.bean.FoodDetail;
import cat.bean.ShowFoodDetail;
import cat.dao.FoodDetailDao;
import net.sf.json.JSONArray;

import java.util.List;

public class ToCarService {
    /*
     *如果 type =1 表示先往订单详情表中添加订单号，美食名称，美食数量（美食数量第一次默认为1）
     * 如果type =2 询相关美食信息，显示到订单表里面
     * 如果type =3 为在购物车表里面为加减数量添加事件，修改数据库内容信息
     * 如果type =4 表示删除购物车该行的内容
     */

    //往订单详情表中添加订单号，美食名称，美食数量（美食数量第一次默认为1）
    public boolean insertCarInfo(String orderno, String foodname, String foodnum) {
        FoodDetailDao foodDetailDao = new FoodDetailDao();
        if (foodDetailDao.addFoodDetail(new FoodDetail(orderno, foodname, foodnum)) != -1) {
            return true;
        } else {
            return false;
        }
    }

    //查询相关美食信息
    public JSONArray jsonSelctCar(String orderno){
        FoodDetailDao foodDetailDao = new FoodDetailDao();
        List<ShowFoodDetail> showFoodDetails = foodDetailDao.selectfoodcar(orderno);
        for(int i=0;i < showFoodDetails.size();i++){
            System.out.println("jsonSelctCar: "+showFoodDetails.get(i));
        }
        JSONArray res = JSONArray.fromObject(showFoodDetails);
        return res;
    }

    //修改数据库中数据的内容信息
    public boolean updatacarnumdel(String orderno,String foodname,String foodnum){
        FoodDetailDao foodDetailDao = new FoodDetailDao();
        if(foodDetailDao.updatadel(new FoodDetail(orderno,foodname,foodnum)) != -1){
            return true;
        }else {
            return false;
        }

    }

    public boolean updatacarnumadd(String orderno,String foodname,String foodnum){
        FoodDetailDao foodDetailDao = new FoodDetailDao();
        if(foodDetailDao.updataadd(new FoodDetail(orderno,foodname,foodnum)) != -1){
            return true;
        }else {
            return false;
        }

    }


    //根据美食名称删除数据库信息
    public boolean delCarrow(String foodname){
        FoodDetailDao foodDetailDao = new FoodDetailDao();
        if(foodDetailDao.delete(foodname) != -1){
            return true;
        }else {
            return false;
        }
    }

    //购物车的数据插入数据库的条件
    public boolean isinsert(String orderno,String foodname){
        if(new FoodDetailDao().selectsame(orderno,foodname).size()==0){
            return true;
        }else {
            return false;
        }
    }
    public static void main(String[] args){
        System.out.println(new ToCarService().jsonSelctCar("20190701130700192"));
//        System.out.println(new ToCarService().insertCarInfo("20190701141658392", "food1", "7"));
    }

}
