package cat.servlert;

import cat.service.updelstaffinfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateStaff")
public class updateStaffServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("updateStaff////");

        String btid = request.getParameter("btid");
        String type = request.getParameter("type");

        System.out.println("servlet.updateStaff:type:"+type);
        updelstaffinfo del = new updelstaffinfo();
        if(type.equals("delete")){
            System.out.println("delete:btid:"+btid);
            if(del.delstaff(btid)){
                out.println("1");
            }else{
                out.println("error");
            }
        }

    }
}

