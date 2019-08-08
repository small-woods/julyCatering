package cat.dao;

import cat.bean.Orde;
import cat.bean.ShowOrder;
import cat.utils.JdbcTeamplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdeDao {
    public final int ONE_PAGE_INFO_COUNT = 8;
    /*
     *插入订单信息
     */

    public int addOrde(Orde orde){
        String sql = "INSERT INTO orde(orderno,tableno,orderpeople,ordertime,orderallmoney,ordertype) VALUES(?,?,?,?,?,?)";
        int rs;
        try {
            rs = JdbcTeamplate.insertOrUpdateOrDelete(
                    sql,
                    orde.getOrderno(),
                    orde.getTableno(),
                    orde.getOrderpeople(),
                    orde.getOrdertime(),
                    orde.getOrderallmoney(),
                    orde.getOrdertype()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return rs;
    }

    /*
     *根据餐桌查询订单号，台号，使用人数
     */

    public List<Orde> selectorde(String tableno){
        String sql="select * from orde where tableno =?";
        List<Orde> ordermores = null;
        try {
            ordermores = JdbcTeamplate.queryData(sql, Orde.class, tableno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordermores;
    }


    //查询员工记录数
    public int getorderCount(){
        return JdbcTeamplate.getTableRowCount("orde");
    }

    /*
     *根据订单号更新订单状态，总订单金额
     */
    public int updataorde(Orde orde){
        String sql="update orde set orderallmoney=?, ordertype=? where orderno=?";
        int count = 0;
        try {
            count = JdbcTeamplate.insertOrUpdateOrDelete(
                    sql,
                    orde.getOrderallmoney(),
                    orde.getOrdertype(),
                    orde.getOrderno()
            );
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*
     *分页查询美食记录，每条记录为8
     */
    public List<Orde> getordeForm(int currentPage){
        String sql = "select * from orde limit "+(currentPage-1)*ONE_PAGE_INFO_COUNT+","+ONE_PAGE_INFO_COUNT;
        List<Orde> ordeForm = new ArrayList<>();
        try {
            ordeForm = JdbcTeamplate.queryData(sql,Orde.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("数据库代码-->foodForm: "+ordeForm);
        return ordeForm;
    }

    //查询各个类型的总数
    public List<ShowOrder> selectorderTypeNum(){
        List<ShowOrder> showorders = new ArrayList<>();
        String sql ="SELECT ordertype,COUNT(*) AS Number FROM orde GROUP BY ordertype";
        try {
            showorders = JdbcTeamplate.queryData(sql,ShowOrder.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("OrdeDao-->selectorderTypeNum总数: "+showorders);
        return showorders;
    }

    /*
     *分组分页查询美食记录，每条记录为8
     */
    public List<Orde> getOrdeGroupbyForm(int currentPage,String ordertype){
        String sql = "select * from orde where ordertype = '"+ordertype+"' limit "+(currentPage-1)*ONE_PAGE_INFO_COUNT+","+ONE_PAGE_INFO_COUNT;
        List<Orde> ordeForm = new ArrayList<>();
        try {
            ordeForm = JdbcTeamplate.queryData(sql,Orde.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("数据库代码-->ordeForm: "+ordeForm);
        return ordeForm;
    }
}

