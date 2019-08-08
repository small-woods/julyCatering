package cat.bean;

public class FoodDetail {
    String orderno;
    String foodname;
    String foodnum;

    public FoodDetail(){}

    public FoodDetail(String orderno, String foodname, String foodnum) {
        this.orderno = orderno;
        this.foodname = foodname;
        this.foodnum = foodnum;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodnum() {
        return foodnum;
    }

    public void setFoodnum(String foodnum) {
        this.foodnum = foodnum;
    }

    @Override
    public String toString() {
        return "FoodDetail{" +
                "orderno='" + orderno + '\'' +
                ", foodname='" + foodname + '\'' +
                ", foodnum='" + foodnum + '\'' +
                '}';
    }
}

