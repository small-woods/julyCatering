package cat.dao;

import cat.bean.Food;
import cat.bean.ShowFood;
import cat.utils.JdbcTeamplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {
    /**
     * 一页最多多少条数据
     */
    public final int ONE_PAGE_INFO_COUNT = 8;
    public final int TWO_PAGE_INFO_COUNT =5;
    public final int THREE_PAGE_INFO_COUNT =3;

    /*
     *插入食物数据
     */
    public int addFood(Food food){
        String sql = "insert into food (foodimg,foodname,foodtype,foodprice,foodinfo) values(?,?,?,?,?)";
        int rs;
        try {
            rs = JdbcTeamplate.insertOrUpdateOrDelete(
                    sql,
                    food.getFoodimg(),
                    food.getFoodname(),
                    food.getFoodtype(),
                    food.getFoodprice(),
                    food.getFoodinfo()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return rs;
    }

    //查询美食记录数
    public int getFoodCount(){
        return JdbcTeamplate.getTableRowCount("food");
    }
    //查询各个类型的美食总数
   public List<ShowFood> selectTypeNum(){
       List<ShowFood> showFoods = new ArrayList<>();
        String sql ="SELECT foodtype,COUNT(*) AS Number FROM food GROUP BY foodtype";
       try {
           showFoods = JdbcTeamplate.queryData(sql,ShowFood.class);
       } catch (SQLException e) {
           e.printStackTrace();
       } catch (InstantiationException e) {
           e.printStackTrace();
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       }
       System.out.println("FoodDao-->selectTypeNum查询各个类型的美食总数: "+showFoods);
       return showFoods;
   }
    /**
     * 根据美食名称查询
     * @param foodname
     * @return
     */
    public List<Food> select(String foodname){
        String sql="select * from food where foodname =?";
        List<Food> foods = null;
        try {
            foods = JdbcTeamplate.queryData(sql, Food.class, foodname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foods;
    }

    /**
     * 根据美食名称删除单条美食信息
     * @param foodname
     * @return -1 代表失败
     */
    public int delete(String foodname){
        String sql="delete from food where foodname=?";
        try {
            int count=JdbcTeamplate.insertOrUpdateOrDelete(sql,foodname);
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    /*
     *分页查询美食记录，每条记录为8
     */
    public List<Food> getFoodForm(int currentPage){
        String sql = "select * from food limit "+(currentPage-1)*ONE_PAGE_INFO_COUNT+","+ONE_PAGE_INFO_COUNT;
        List<Food> foodForm = new ArrayList<>();
        try {
            foodForm = JdbcTeamplate.queryData(sql,Food.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("数据库代码-->foodForm: "+foodForm);
        return foodForm;
    }

    /*
     *分页查询美食记录，每条记录为5
     */
    public List<Food> getFoodForm2(int currentPage){
        String sql = "select * from food limit "+(currentPage-1)*TWO_PAGE_INFO_COUNT+","+TWO_PAGE_INFO_COUNT;
        List<Food> foodForm = new ArrayList<>();
        try {
            foodForm = JdbcTeamplate.queryData(sql,Food.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("数据库代码-->foodForm: "+foodForm);
        return foodForm;
    }


    /*
     *分组分页查询美食记录，每条记录为8
     */
    public List<Food> getFoodGroupbyForm(int currentPage,String foodtype){
        String sql = "select * from food where foodtype = '"+foodtype+"' limit "+(currentPage-1)*ONE_PAGE_INFO_COUNT+","+ONE_PAGE_INFO_COUNT;
        List<Food> foodForm = new ArrayList<>();
        try {
            foodForm = JdbcTeamplate.queryData(sql,Food.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("数据库代码-->foodForm: "+foodForm);
        return foodForm;
    }


}
