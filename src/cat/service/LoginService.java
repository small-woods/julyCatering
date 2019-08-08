package cat.service;

import cat.bean.Hr;
import cat.dao.AdminDao;
import cat.dao.StaffDao;

public class LoginService {
    public boolean login(String type, String id, String password){
        //管理员登录
        if (type.equals("1")){
            AdminDao adminDao = new AdminDao();
            if (adminDao.select(new Hr(id, password))) {
                return true;
            }
            else{
                return false;
            }
        }
        //员工登录
        else{
            StaffDao staffDao = new StaffDao();
//            登录成功
            if (null != staffDao.select(id, password)){
                return true;
            }
            else{
                return false;
            }
        }
    }
}
