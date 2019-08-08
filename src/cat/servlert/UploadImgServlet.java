package cat.servlert;

import cat.bean.Food;
import cat.dao.FoodDao;
import cat.dao.StaffDao;
import cat.service.FoodSevice;
import cat.utils.OrderCodeFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/UploadImg")
public class UploadImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //对解析出来的数据进行存储
        FoodSevice foodSevice = new FoodSevice();
        //建立一个存储数据的数据
        String strs= "";
        FoodDao foodDao = new FoodDao();
        Food food = new Food();

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            out.println("uploadFileServlet========Error: 表单必须包含 enctype=multipart/form-data");
            out.flush();
            return;
        }
        try {

            //创建FileItemFactory对象
            FileItemFactory factory=new DiskFileItemFactory();
            //创建文件上传的处理器
            ServletFileUpload upload=new ServletFileUpload(factory);
            //解析请求
            List<FileItem> items=upload.parseRequest(request);
            //迭代出每一个FileItem
            for (FileItem item : items) {
                String fileName = item.getFieldName();
                if (item.isFormField()) {
                    //普通的表单控件
                    String value = item.getString("utf-8");
                    strs+="&"+value;
                    System.out.println(fileName + "->" + value);
                } else {
//                    String RandomName = UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(item.getName());
                    String orderCode = new OrderCodeFactory().getOrderIdByTime();
                    String valueName = item.getName();//标签的name
                    String imgName = orderCode+FilenameUtils.getName(valueName); //文件的name
                    System.out.println("标签的name-->valueName: "+fileName);
                    System.out.println(fileName + "->" + imgName);
                    String path=super.getServletContext().getRealPath("/uploadimg");
                    System.out.println(path);
                    item.write(new File(path,imgName)); //把上传的文件保存到某个文件中
                    imgName = "uploadimg/"+imgName;
                    request.setAttribute(fileName, imgName);
                    strs+=imgName;

//                    out.println("<img src='uploadimg/20190628135838473img_flwr.gif\' alt='#'/>");

                }
            }
//            request.setAttribute();
            System.out.println("strs: "+strs);
            food = foodSevice.toFood(strs);
            System.out.println("food: "+food);
            if(foodDao.addFood(food) !=-1){
                response.sendRedirect("outFoodDetail.html");
            }
            else {
                out.println("出错");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
