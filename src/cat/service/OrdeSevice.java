package cat.service;

import cat.bean.Orde;
import cat.bean.ShowOrder;
import cat.dao.OrdeDao;
import net.sf.json.JSONArray;

import java.util.List;

public class OrdeSevice {
    /*
     *根据前台传递的餐桌号，订单号，人数，时间，0,0，将数据插入订单表，插入成功返回true
     */

    public boolean insertorde(String orderno, String tableno, String orderpeople,String ordertime,String orderallmoney,String ordertype){
        OrdeDao ordeDao = new OrdeDao();
        if(ordeDao.addOrde(new Orde(orderno,tableno,orderpeople,ordertime,orderallmoney,ordertype)) != -1){
            return true;
        }else {
            return false;
        }
    }
    /*
     *根据前台数据更新订单状态为1，下单金额,更新成功返回true
     */
    public boolean updataorde(String orderno, String tableno, String orderpeople,String ordertime,String orderallmoney,String ordertype){
        OrdeDao ordeDao = new OrdeDao();
        if(ordeDao.updataorde(new Orde(orderno,tableno,orderpeople,ordertime,orderallmoney,ordertype)) != -1){
            return true;
        }else {
            return false;
        }
    }

    /*
     *根据前台餐桌查询订单号，台号，使用人数，返回String
     */
    public String orderpeopels(String tableno){
        String strs;
        OrdeDao ordeDao = new OrdeDao();
        List<Orde> ordermores =  ordeDao.selectorde(tableno);
        System.out.println("根据前台餐桌查询订单号，台号，使用人数-->ordermore: "+ordermores);
        strs =ordermores.get(0).getOrderno()+"&"+ordermores.get(0).getTableno()+"&"+ordermores.get(0).getOrderpeople();
        System.out.println("strs: "+strs);
        return strs;
    }

    //每页8条记录
    public JSONArray jsonselectorder(int currentpage){
        OrdeDao ordeDao = new OrdeDao();
        List<Orde> ordes = ordeDao.getordeForm(currentpage);
        for (int i = 0; i < ordes.size(); i++) {
            System.out.println(ordes.get(i));
        }
        JSONArray res = JSONArray.fromObject(ordes);
        return res;
    }

    public String ordeTypeNum(String ordeType){
        OrdeDao ordeDao = new OrdeDao();
        List<ShowOrder> showorders = ordeDao.selectorderTypeNum();
        for (int i = 0; i < showorders.size(); i++) {
            if(showorders.get(i).getOrdertype().equals(ordeType)){
                return showorders.get(i).getNumber();
            }
        }
        return null;
    }

    //分组每页8条记录
    public JSONArray jsonselectOrderGroup(int currentpage,String ordertype){
        OrdeDao ordeDao = new OrdeDao();
        List<Orde> ordes = ordeDao.getOrdeGroupbyForm(currentpage,ordertype);
        for (int i = 0; i < ordes.size(); i++) {
            System.out.println(ordes.get(i));
        }
        JSONArray res = JSONArray.fromObject(ordes);
        return res;
    }

    public static void main(String[] args){
        System.out.println(new OrdeSevice().jsonselectOrderGroup(1,"0"));

    }
}
