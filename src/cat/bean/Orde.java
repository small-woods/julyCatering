package cat.bean;

public class Orde {
    String orderno;
    String tableno;
    String orderpeople;
    String ordertime;
    String orderallmoney;
    String ordertype;

    public Orde(){}

    public Orde(String orderno, String tableno, String orderpeople, String ordertime, String orderallmoney, String ordertype) {
        this.orderno = orderno;
        this.tableno = tableno;
        this.orderpeople = orderpeople;
        this.ordertime = ordertime;
        this.orderallmoney = orderallmoney;
        this.ordertype = ordertype;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getTableno() {
        return tableno;
    }

    public void setTableno(String tableno) {
        this.tableno = tableno;
    }

    public String getOrderpeople() {
        return orderpeople;
    }

    public void setOrderpeople(String orderpeople) {
        this.orderpeople = orderpeople;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getOrderallmoney() {
        return orderallmoney;
    }

    public void setOrderallmoney(String orderallmoney) {
        this.orderallmoney = orderallmoney;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    @Override
    public String toString() {
        return "Orde{" +
                "orderno='" + orderno + '\'' +
                ", tableno='" + tableno + '\'' +
                ", orderpeople='" + orderpeople + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", orderallmoney='" + orderallmoney + '\'' +
                ", ordertype='" + ordertype + '\'' +
                '}';
    }
}
