package cat.service;

import cat.bean.Hr;
import cat.dao.AdminDao;
import net.sf.json.JSONArray;

import java.util.List;

public class AdminLogin {

    public JSONArray jsonAdmins(){
        AdminDao adminDao = new AdminDao();
        List<Hr> admins = adminDao.selectAll();
        for (int i = 0; i < admins.size(); i++) {
            System.out.println(admins.get(i));
        }

        JSONArray res = JSONArray.fromObject(admins);
        return res;
    }

    public static void main(String[] args){
        new AdminLogin().jsonAdmins();
    }
}
