package cat.servlert;

import cat.service.UploadService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/uploadFile")
public class uploadFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("uploadFileServlet...");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            out.println("uploadFileServlet========Error: 表单必须包含 enctype=multipart/form-data");
            out.flush();
            return;
        }

         /*
        将excel的内容传入数据库
         */
        UploadService uploadUtils = new UploadService();
        try {
            uploadUtils.upload(request);
            response.sendRedirect("successHtml/importStaffSuccess.html");
        }catch (Exception e){
            response.sendRedirect("failedHtml/importStaffFailed.html");
        }finally {
        }


    }

}
