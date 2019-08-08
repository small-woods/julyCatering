package cat.dao;

import cat.bean.Category;
import cat.utils.JdbcTeamplate;

import java.sql.SQLException;

public class CategoryDao {
    /*
     *插入美食类型
     */
    public int addFoodType(Category category){
        String sql = "insert into category (foodtype) values(?)";
        int rs;
        try {
            rs = JdbcTeamplate.insertOrUpdateOrDelete(
                    sql,
                    category.getFoodtype()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return rs;
    }
}
