package cat.bean;

public class ShowFoodDetail {
    String orderno;
    String foodname;
    String foodprice;
    String foodnum;

    public ShowFoodDetail(){}

    public ShowFoodDetail(String orderno, String foodname, String foodprice, String foodnum) {
        this.orderno = orderno;
        this.foodname = foodname;
        this.foodprice = foodprice;
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

    public String getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(String foodprice) {
        this.foodprice = foodprice;
    }

    public String getFoodnum() {
        return foodnum;
    }

    public void setFoodnum(String foodnum) {
        this.foodnum = foodnum;
    }

    @Override
    public String toString() {
        return "ShowFoodDetail{" +
                "orderno='" + orderno + '\'' +
                ", foodname='" + foodname + '\'' +
                ", foodprice='" + foodprice + '\'' +
                ", foodnum='" + foodnum + '\'' +
                '}';
    }
}
