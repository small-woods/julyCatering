package cat.utils;

import cat.bean.Staff;
import cat.dao.StaffDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 将得到的string类型数据变为对象
 * 保存到数据库
 */
public class ListToObject {

    /**
     * 将string类型转换为student类型
     * @param dataList
     * @return
     */
    public List<Staff> tostaff(List<List<String>> dataList) {
        List<Staff> staff = new ArrayList<>();
        for (int i = 1; i < dataList.size(); i++) {
            Staff sta = new Staff();
            StaffDao staffDao = new StaffDao();
            if(staffDao.select(dataList.get(i).get(1), dataList.get(i).get(2)) == null){
                sta.setStaffname(dataList.get(i).get(0));
                sta.setStaffno(dataList.get(i).get(1));
                sta.setPassword(dataList.get(i).get(2));
                sta.setIdcard(dataList.get(i).get(3));
                sta.setDegree(dataList.get(i).get(4));
                sta.setBirthday(dataList.get(i).get(5));
                sta.setDeptname(dataList.get(i).get(6));
                staff.add(sta);
            }
        }
        return staff;
    }

}
