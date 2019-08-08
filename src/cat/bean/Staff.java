package cat.bean;

/**
 * Created by wcfb on 2019/4/1
 * 员工类
 */
public class Staff {
    String staffname;
    String staffno;
    String password;
    String idcard;
    String degree;
    String birthday;
    String deptname;

    public Staff() {
    }

    public Staff(String staffname, String staffno, String password, String idcard, String degree, String birthday, String deptname) {
        this.staffname = staffname;
        this.staffno = staffno;
        this.password = password;
        this.idcard = idcard;
        this.degree = degree;
        this.birthday = birthday;
        this.deptname = deptname;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }

    public String getStaffno() {
        return staffno;
    }

    public void setStaffno(String staffno) {
        this.staffno = staffno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffname='" + staffname + '\'' +
                ", staffno='" + staffno + '\'' +
                ", password='" + password + '\'' +
                ", idcard='" + idcard + '\'' +
                ", degree='" + degree + '\'' +
                ", birthday='" + birthday + '\'' +
                ", deptname='" + deptname + '\'' +
                '}';
    }
}