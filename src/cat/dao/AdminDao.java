package cat.dao;

import cat.bean.Hr;
import cat.utils.JdbcTeamplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    /*
     *修改管理员密码
     */
    public int changehrPassword(String xadaccount, String xadpsd){
        String sql = "update hr set xadpsd=? where xadaccount=?";
        int count = 0;
        try {
            count = JdbcTeamplate.insertOrUpdateOrDelete(sql, xadpsd, xadaccount);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int insert(Hr hr){
        String sql="insert into alogin (xadaccount, xadpsd) values(?,?)";
        int rs = 0;
        try {
            rs = JdbcTeamplate.insertOrUpdateOrDelete(
                    sql,
                    hr.getXadaccount(),
                    hr.getXadpsd()
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public List<Hr> selectAll(){
        List<Hr> admins = new ArrayList<>();
        String sql = "select * from hr";
        try {
            admins = JdbcTeamplate.queryData(sql, Hr.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("查询："+admins);
        return admins;
    }
    /**
     * 查询 用于登录
     */

    public boolean select(Hr hr) {
        String id = hr.getXadaccount();
        String password = hr.getXadpsd();
        String sql="select * from hr where xadaccount =? and xadpsd =?";
        List<Hr> hrs = null;
        try {
            hrs = JdbcTeamplate.queryData(sql, Hr.class, id, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(hrs.size()>0)
            return true;
        else
            return false;
    }


    public static void main(String[] a){
        AdminDao adminDao = new AdminDao();
        Hr hr = new Hr("10003","123456");
        System.out.println(adminDao.insert(hr));
    }

}

