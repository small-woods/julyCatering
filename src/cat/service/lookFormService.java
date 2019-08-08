package cat.service;

import cat.bean.Staff;
import cat.dao.StaffDao;
import net.sf.json.JSONArray;

import java.util.List;

public class lookFormService {
    public JSONArray jsonStaff(int currentpage){
        StaffDao staffDao = new StaffDao();
        List<Staff> staff =  staffDao.getStaffForm(currentpage);
        for (int i = 0; i < staff.size(); i++) {
//            System.out.println(staff.get(i));
        }
        JSONArray res = JSONArray.fromObject(staff);
        return res;
    }

    public static void main(String[] args){
        new lookFormService().jsonStaff(1);
    }
}