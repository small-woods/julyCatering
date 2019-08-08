package cat.service;

import cat.bean.Category;
import cat.dao.CategoryDao;

public class CategoryService {
    /*
     *将美食类型信息插入数据库
     */
    public boolean addFoodType(String foodType){
        CategoryDao categoryDao = new CategoryDao();
        if(categoryDao.addFoodType(new Category(foodType)) != -1){
            return true;
        }
        else {
            return false;
        }
    }
}
