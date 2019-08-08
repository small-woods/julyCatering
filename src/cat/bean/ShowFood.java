package cat.bean;

public class ShowFood {
    String foodtype;
    String number;
    public ShowFood(){};

    public ShowFood(String foodtype, String number) {
        this.foodtype = foodtype;
        this.number = number;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ShowFood{" +
                "foodtype='" + foodtype + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
