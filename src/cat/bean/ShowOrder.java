package cat.bean;

public class ShowOrder {
    String ordertype;
    String number;
    public ShowOrder(){};

    public ShowOrder(String ordertype, String number) {
        this.ordertype = ordertype;
        this.number = number;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ShowOrder{" +
                "ordertype='" + ordertype + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
