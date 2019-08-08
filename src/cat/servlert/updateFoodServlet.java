package cat.servlert;

import cat.service.FoodSevice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateFood")
public class updateFoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("updateFood////");
        String btid = request.getParameter("btid");
        String type = request.getParameter("type");

        System.out.println("servlet.updateFood:type:"+type);
        FoodSevice del = new FoodSevice();
        if(type.equals("delete")){
            System.out.println("delete:btid:"+btid);
            if(del.delfood(btid)){
                out.println("1");
            }else{
                out.println("error");
            }
        }
    }
}
