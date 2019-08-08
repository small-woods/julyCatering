package cat.bean;

public class Hr {
     String xadaccount;
     String xadpsd;

    public Hr(){}

    public Hr(String xadaccount, String xadpsd) {
        this.xadaccount = xadaccount;
        this.xadpsd = xadpsd;
    }

    public String getXadaccount() {
        return xadaccount;
    }

    public void setXadaccount(String xadaccount) {
        this.xadaccount = xadaccount;
    }

    public String getXadpsd() {
        return xadpsd;
    }

    public void setXadpsd(String xadpsd) {
        this.xadpsd = xadpsd;
    }

    @Override
    public String toString() {
        return "AdminLoginInfo{" +
                "xadaccount='" + xadaccount + '\'' +
                ", xadpsd='" + xadpsd + '\'' +
                '}';
    }
}
