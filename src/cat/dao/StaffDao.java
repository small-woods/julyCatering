package cat.dao;

import cat.bean.Staff;
import cat.utils.JdbcTeamplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {
    /**
     * 一页最多多少条数据
     */
    public final int ONE_PAGE_INFO_COUNT = 7;

    /**
     * 查询员工（用于登录）
     * @param staffNo
     * @param password
     * @return 返回null为没有找到
     */
    public List<Staff> select(String staffNo, String password){
        String sql="select * from staff where staffNo =? and password =?";
        List<Staff> staffs = null;
        try {
            staffs = JdbcTeamplate.queryData(sql, Staff.class, staffNo, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(staffs.size()>0){
            System.out.println(staffs);
            return staffs;
        }
        System.out.println("null");
        return null;
    }

    /**
     * 根据员工号删除单条员工信息
     * @param staffNo
     * @return -1 代表失败
     */
    public int delete(String staffNo){
        String sql="delete from staff where staffNo=?";
        try {
            int count=JdbcTeamplate.insertOrUpdateOrDelete(sql,staffNo);
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 根据员工号更改员工信息
     * @param staff 要修改的员工
     * @return -1 代表失败
     */
    public int updata(Staff staff){
        String sql="update staff set staffName=?, password=?, idCard=?, degree=?,"+
                "birthday=?,attrName=?, type=?, isParty=?,deptName=? where staffNo=?";
        int count = 0;
        try {
            count = JdbcTeamplate.insertOrUpdateOrDelete(
                    sql,
                    staff.getStaffname(),
                    staff.getStaffno(),
                    staff.getPassword(),
                    staff.getIdcard(),
                    staff.getDegree(),
                    staff.getBirthday(),
                    staff.getDeptname()

            );
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 修改员工密码
     * @param staffNo
     * @param password
     * @return
     */
    public int changePassword(String staffNo, String password){
        String sql = "update staff set password=? where staffNo=?";
        int count = 0;
        try {
            count = JdbcTeamplate.insertOrUpdateOrDelete(sql, password, staffNo);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /*
     *插入员工数据
     */
    public int addStaff(Staff staff){
        String sql = "insert into staff (staffName,staffNo,password,idCard,degree,birthday,deptName) values(?,?,?,?,?,?,?)";
        int rs;
            try {
                rs = JdbcTeamplate.insertOrUpdateOrDelete(
                        sql,
                        staff.getStaffname(),
                        staff.getStaffno(),
                        staff.getPassword(),
                        staff.getIdcard(),
                        staff.getDegree(),
                        staff.getBirthday(),
                        staff.getDeptname()
                );
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        return rs;
    }


    /**
     * 根据员工号查询
     * @param staffNo
     * @return
     */
    public List<Staff> select(String staffNo){
        String sql="select * from staff where staffNo =?";
        List<Staff> staffs = null;
        try {
            staffs = JdbcTeamplate.queryData(sql, Staff.class, staffNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffs;
    }

    //查询员工记录数
    public int getStaffCount(){
        return JdbcTeamplate.getTableRowCount("staff");
    }

    /*
     *分页查询员工，每条记录为7
     */
    public List<Staff> getStaffForm(int currentPage){
        String sql = "select * from staff limit "+(currentPage-1)*ONE_PAGE_INFO_COUNT+","+ONE_PAGE_INFO_COUNT;
        List<Staff> staffForm = new ArrayList<>();
        try {
            staffForm = JdbcTeamplate.queryData(sql,Staff.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("数据库代码-->staffForm: "+staffForm);
        return staffForm;
    }

    public static void main(String arg[]){
//        System.out.println(new StaffDao().getStaffForm(1));
        StaffDao staffDao = new StaffDao();
        List<Staff> staffForm = staffDao.getStaffForm(1);
        for (Staff staff : staffForm) {
            System.out.println(staff);
        }
    }

}
