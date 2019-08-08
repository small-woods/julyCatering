package cat.bean;

import java.io.InputStream;

public class Food {
    String foodimg;
    String foodname;
    String foodtype;
    String foodprice;
    String foodinfo;

    public Food(){}

    public Food(String foodimg,String foodname, String foodtype, String foodprice, String foodinfo) {
        this.foodimg = foodimg;
        this.foodname = foodname;
        this.foodtype = foodtype;
        this.foodprice = foodprice;
        this.foodinfo = foodinfo;

    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public String getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(String foodprice) {
        this.foodprice = foodprice;
    }

    public String getFoodinfo() {
        return foodinfo;
    }

    public void setFoodinfo(String foodinfo) {
        this.foodinfo = foodinfo;
    }

    public String getFoodimg() {
        return foodimg;
    }

    public void setFoodimg(String foodimg) {
        this.foodimg = foodimg;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodimg=" + foodimg +
                ", foodname='" + foodname + '\'' +
                ", foodtype='" + foodtype + '\'' +
                ", foodprice='" + foodprice + '\'' +
                ", foodinfo='" + foodinfo + '\'' +
                '}';
    }
}
