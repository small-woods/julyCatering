package cat.dao;

import cat.bean.Table;
import cat.utils.JdbcTeamplate;

import java.sql.SQLException;
import java.util.List;

public class TableDao {

    /*
     *发布餐桌
     */
    public int addTab(Table table){
        String sql = "insert into tab (tableno,tablestatus) values(?,?)";
        int rs;
        try {
            rs = JdbcTeamplate.insertOrUpdateOrDelete(
                    sql,
                    table.getTableno(),
                    table.getTablestatus()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return rs;
    }

    /**
     * 查询 用于判断餐桌状态
     */
    public List<Table> selectTable(String tableno) {
        Table table = new Table();
        System.out.println(tableno);
        String sql = "select * from tab where tableno = ?";
        List<Table> tables = null;
        try {
            tables = JdbcTeamplate.queryData(sql, Table.class, tableno);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(tables);
        return tables;
    }

    /**
     * 查询 用于判断餐桌状态
     */
    public List<Table> selectAllTable() {
        Table table = new Table();
        String sql = "select * from tab";
        List<Table> tables = null;
        try {
            tables = JdbcTeamplate.queryData(sql, Table.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(tables);
        return tables;
    }

    /*
     *更新：用于修改餐桌的状态
     */

    public int update(String tableno){
        String sql = "update tab set tablestatus ='1' where tableno = ?";
        int rs = 0;
        try {
            rs = JdbcTeamplate.insertOrUpdateOrDelete(sql,tableno);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("rs: "+rs);
        return rs;
    }
    /*
     *更新：用于修改餐桌的状态 1---0
     */
    public int updateso(String tableno){
        String sql = "update tab set tablestatus ='0' where tableno = ?";
        int rs = 0;
        try {
            rs = JdbcTeamplate.insertOrUpdateOrDelete(sql,tableno);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("rs: "+rs);
        return rs;
    }


    /**
     * 根据餐桌号删除单条餐桌信息
     * @param tableno
     * @return -1 代表失败
     */
    public int delete(String tableno){
        String sql="delete from tab where tableno=?";
        try {
            int count=JdbcTeamplate.insertOrUpdateOrDelete(sql,tableno);
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
