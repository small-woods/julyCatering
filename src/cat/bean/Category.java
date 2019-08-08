package cat.bean;

public class Category {
    String foodtype;

    public Category(){};

    public Category(String foodtype) {
        this.foodtype = foodtype;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    @Override
    public String toString() {
        return "Category{" +
                "foodtype='" + foodtype + '\'' +
                '}';
    }
}
