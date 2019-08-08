package cat.dao;

import cat.bean.FoodDetail;
import cat.bean.ShowFoodDetail;
import cat.utils.JdbcTeamplate;

import java.sql.SQLException;
import java.util.List;

public class FoodDetailDao {
    /*
     *往订单详情表中添加订单号，美食名称，美食数量（美食数量第一次默认为1）
     */
    public int addFoodDetail(FoodDetail foodDetail){
        String sql = "insert into fooddetail (orderno,foodname,foodnum) values(?,?,?)";
        int rs;
        try {
            rs = JdbcTeamplate.insertOrUpdateOrDelete(
                    sql,
                    foodDetail.getOrderno(),
                    foodDetail.getFoodname(),
                    foodDetail.getFoodnum()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return rs;
    }




    /*
     *查询相关美食信息SELECT fooddetail.orderno,food.foodname,foodprice,foodnum FROM fooddetail,food WHERE fooddetail.foodname =food.foodname AND fooddetail.orderno='20190701130700192'
     */
    public List<ShowFoodDetail> selectfoodcar(String orderno){
        String sql="SELECT fooddetail.orderno,food.foodname,foodprice,foodnum FROM fooddetail,food WHERE fooddetail.foodname =food.foodname AND fooddetail.orderno=?";
        List<ShowFoodDetail> showFoodDetails =null;
        try {
            showFoodDetails =JdbcTeamplate.queryData(sql,ShowFoodDetail.class,orderno);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return showFoodDetails;
    }



    /*
     *修改美食数量UPDATE fooddetail  SET foodnum=foodnum+1 WHERE foodname = '牛排1'
     */

    public int updatadel(FoodDetail foodDetail){
        String sql="update fooddetail set foodnum=foodnum-1 where foodname=?";
        int count = 0;
        try {
            count = JdbcTeamplate.insertOrUpdateOrDelete(
                    sql,
                    foodDetail.getFoodname()
            );
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int updataadd(FoodDetail foodDetail){
        String sql="update fooddetail set foodnum=foodnum+1 where foodname=?";
        int count = 0;
        try {
            count = JdbcTeamplate.insertOrUpdateOrDelete(
                    sql,
                    foodDetail.getFoodname()
            );
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
     *根据美食名称删除美食信息delete from fooddetail where foodname='牛排1'
     *return -1 代表失败
     */

    public int delete(String foodname){
        String sql="delete from fooddetail where foodname=?";
        try {
            int count= JdbcTeamplate.insertOrUpdateOrDelete(sql,foodname);
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 根据美食名称已经订单编号查询
     * 如果为空 说明没有找到 可以插入数据
     */
    public List<FoodDetail> selectsame(String orderno, String foodname){
        String sql="select * from fooddetail where orderno = ? AND foodname =?";
        List<FoodDetail> foodDetails = null;
        try {
            foodDetails = JdbcTeamplate.queryData(sql, FoodDetail.class, orderno,foodname);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return foodDetails;
    }
    public static void main(String[] args){
        System.out.println(new FoodDetailDao().selectsame("20190701130700142","牛排2"));
    }
}

