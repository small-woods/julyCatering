package cat.servlert;

import cat.service.TableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateTab")
public class updateTabServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("updateTab////");

        String btid = request.getParameter("btid");
        String type = request.getParameter("type");

        System.out.println("servlet.updateTab:type:"+type);
        TableService del = new TableService();
        if(type.equals("delete")){
            System.out.println("delete:btid:"+btid);
            if(del.delTab(btid)){
                out.println("1");
            }else{
                out.println("0");
            }
        }
    }
}
