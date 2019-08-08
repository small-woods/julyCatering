package cat.service;

import cat.dao.StaffDao;

public class updelstaffinfo {

    public boolean delstaff(String staffNo){
        int rs=0;
        StaffDao staffDao = new StaffDao();
        if(staffDao.delete(staffNo)!=-1){
            return true;
        }else{
            return false;
        }
    }


}
