package cat.bean;

public class Table {
    String tableno;
    String tablestatus;

    public Table(){}

    public Table(String tableno, String tablestatus) {
        this.tableno = tableno;
        this.tablestatus = tablestatus;
    }


    public String getTableno() {
        return tableno;
    }

    public void setTableno(String tableno) {
        this.tableno = tableno;
    }

    public String getTablestatus() {
        return tablestatus;
    }

    public void setTablestatus(String tablestatus) {
        this.tablestatus = tablestatus;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableno='" + tableno + '\'' +
                ", tablestatus='" + tablestatus + '\'' +
                '}';
    }
}
