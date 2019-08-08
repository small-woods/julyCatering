package cat.service;

import cat.bean.Table;
import cat.dao.TableDao;
import net.sf.json.JSONArray;

import java.util.List;

public class TableService {
    int  rs;

    /*
     *将餐桌信息插入数据库
     */
    public boolean addTab(String tableno,String tablestatus){
        TableDao tableDao = new TableDao();
        if(tableDao.addTab(new Table(tableno,tablestatus)) != -1){
            return true;
        }
        else {
            return false;
        }
    }

    /*
     *用于判断餐桌的状态
     * true:代表餐桌不在使用
     * false:代表餐桌正在使用
     */
    public boolean tableable(String tableno){
        TableDao tableDao = new TableDao();

        List<Table> tables = tableDao.selectTable(tableno);
        System.out.println(tables.get(0).getTablestatus());
        if((tables.get(0).getTablestatus()).equals("0")){
            return false;
        }else{
            return true;
        }
    }

    /*
     *用于修改餐桌的状态
     * 当餐桌状态从0-->1的时候，生成订单号
     */
    public int tableChange(String tableno){
        TableDao tableDao = new TableDao();
        rs = tableDao.update(tableno);
        if(rs !=0){
            return rs;
//
        }
       return rs;
    }

    /*
     *修改餐桌状态 1--0
     */
    public boolean tableChangeso(String tableno){
        TableDao tableDao = new TableDao();
        rs = tableDao.updateso(tableno);
        if(rs !=0){
            return true;
//
        }else {
            return false;
        }
    }

    /*
     *删除餐桌状态
     */
    public boolean delTab(String tableno){
        int rs=0;
        TableDao tableDao = new TableDao();
        if(tableDao.delete(tableno)!=-1){
            return true;
        }else{
            return false;
        }
    }

    /*
     *查询所有的餐桌
     */
    public JSONArray jsonselectAllTable(){
        TableDao tableDao = new TableDao();
        List<Table> tables = tableDao.selectAllTable();
        for (int i = 0; i < tables.size(); i++) {
            System.out.println(tables.get(i));
        }
        JSONArray res = JSONArray.fromObject(tables);
        return res;
    }

    public static void main(String[] args){
        System.out.println(new TableService().jsonselectAllTable());
    }
}
