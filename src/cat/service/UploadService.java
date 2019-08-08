package cat.service;

        import cat.bean.Staff;
        import cat.dao.StaffDao;
        import cat.utils.ListToObject;
        import cat.utils.UploadUtil;

        import javax.servlet.http.HttpServletRequest;
        import java.util.List;

/**
 * Created by ytt on 2019/7/1
 * 上传文件及保存
 */
public class UploadService {
    /**
     * 将读取到的string保存到数据库
     * @param request
     */
    public void upload(HttpServletRequest request){
        List<List<List<String>>> dataList = new UploadUtil().upload(request) ;
        List<Staff> staff = new ListToObject().tostaff(dataList.get(0));
        StaffDao staffDao=new StaffDao();

        for (int i = 0; i < staff.size(); i++) {
            if(staffDao.addStaff(staff.get(i))==-1){
                return;
            }
        }
    }

}
